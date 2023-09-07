package com.br.arquivoapi.service.impl;

import com.br.arquivoapi.exceptions.ArquivoException;
import com.br.arquivoapi.model.PagamentoDTO;
import com.br.arquivoapi.repositorios.AssociadoRepository;
import com.br.arquivoapi.repositorios.BoletoRepository;
import com.br.arquivoapi.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final BoletoRepository boletoRepository;

    @Override
    public List<PagamentoDTO> iniciaPagamento(String pagamentos) {
        byte[] array = Base64.getDecoder().decode(pagamentos);
        String string = new String(array);


        String[] arrayPagamentos = string.split(",");
        List<String> listaPagamentos = Arrays.asList(arrayPagamentos);

        var listaBoletos = listaPagamentos.parallelStream().map(this::converteStringEmDTO).collect(Collectors.toList());

        listaBoletos.forEach(this::pagaBoleto);
         return listaBoletos;
    }
    protected void pagaBoleto(PagamentoDTO pagamentoDTO){
        try{
            if (!validaDto(pagamentoDTO)){

                var boleto = boletoRepository.findByIndentificador(Long.parseLong(pagamentoDTO.getIdentificadorBoleto()));

                if(ObjectUtils.isNotEmpty(boleto)){

                    if(boleto.getVencimento().isAfter(LocalDate.now())){

                        if(boleto.getValor().equals(pagamentoDTO.getValor())) {

                            if(!boleto.getSituacao().equals("PAGO")){

                                boleto.setSituacao("PAGO");
                                boletoRepository.save(boleto);
                                pagamentoDTO.setPago(true);
                            }
                        }
                    }
                }
            }
        }catch (Exception ex){
            throw new ArquivoException("Erro ao pagar boleto. " + ex.getMessage());
        }
    }
    protected PagamentoDTO converteStringEmDTO(String pagamento) {
        if (pagamento.length() == 48) {
            return PagamentoDTO
                    .builder()
                    .documentoAssociado(pagamento.substring(0, 14))
                    .identificadorBoleto(pagamento.substring(15, 28).trim())
                    .valor(capturaValorDoBoleto(pagamento.substring(29, 48)))
                    .build();
        }
        return null;
    }
    protected boolean validaDto(PagamentoDTO pagamentoDTO){
        return ObjectUtils.isEmpty(pagamentoDTO.getDocumentoAssociado()) ||
                ObjectUtils.isEmpty(pagamentoDTO.getValor()) ||
                ObjectUtils.isEmpty(pagamentoDTO.getDocumentoAssociado());
    }
    protected BigDecimal capturaValorDoBoleto(String valor){
        if(StringUtils.isNotBlank(valor)) {
            int quantidadeCasaDecimal = 2;
            BigDecimal numero = new BigDecimal(valor);

            String s1 = numero.toString();
            String valorCasaDecimal = s1.substring(s1.length() - quantidadeCasaDecimal);
            String valorInteiro = s1.substring(0, s1.length() - quantidadeCasaDecimal);

            return new BigDecimal(valorInteiro + "." + valorCasaDecimal);
        }
        return null;
    }
}
