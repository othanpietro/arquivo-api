package com.br.arquivoapi.repositorios;

import com.br.arquivoapi.model.entidades.Associado;
import com.br.arquivoapi.model.entidades.Boleto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface BoletoRepository extends BaseRepository<Boleto>{

    List<Boleto> findByAssociado(Associado associado);

    Boleto findByUuid(UUID uuid);

    Boleto findByIndentificador(Long identificador);

}
