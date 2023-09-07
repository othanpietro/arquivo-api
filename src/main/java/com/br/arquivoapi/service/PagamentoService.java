package com.br.arquivoapi.service;

import com.br.arquivoapi.model.PagamentoDTO;

import java.util.List;

public interface PagamentoService {

    public List<PagamentoDTO> iniciaPagamento(String cargaDTO);
}
