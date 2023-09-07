package com.br.arquivoapi.service.impl;

import com.br.arquivoapi.model.entidades.Associado;
import com.br.arquivoapi.model.entidades.Boleto;
import com.br.arquivoapi.repositorios.AssociadoRepository;
import com.br.arquivoapi.repositorios.BoletoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagamentoServiceImplTest {
    private PagamentoServiceImpl target;
    private final BoletoRepository boletoRepository = mock(BoletoRepository.class);

    @BeforeEach
    public void setup(){
        target = new PagamentoServiceImpl(boletoRepository);
    }

    @Test
    public void iniciaPagamentoTest(){
     when(boletoRepository.findByIndentificador(any())).thenReturn(createBoleto());
     var output = target.iniciaPagamento(criaBase64());
     assertNotNull(output);
     assertEquals("00058799307006", output.get(0).getDocumentoAssociado());
     assertEquals("55555555555", output.get(0).getIdentificadorBoleto());
     assertEquals(BigDecimal.valueOf(250.25), output.get(0).getValor());
     assertTrue(output.get(0).isPago());
    }


    private String criaBase64(){
        return "MDAwNTg3OTkzMDcwMDY1NTU1NTU1NTU1NTUgIDAwMDAwMDAwMDAwMDAwMDI1MDI1";
    }
    private Boleto createBoleto(){
        return Boleto.builder()
                .valor(BigDecimal.valueOf(250.25))
                .documentoPagador("58799307006")
                .indentificador(555555555)
                .situacao("EM_ABERTO")
                .nomePagador("TESTE")
                .nomeFantasiaPagador("TESTE")
                .vencimento(LocalDate.now().plusDays(1))
                .associado(Associado
                        .builder()
                        .documento("58799307006")
                        .tipoPessoa("PF")
                        .nome("TESTE")
                        .build())
                .build();
    }
}
