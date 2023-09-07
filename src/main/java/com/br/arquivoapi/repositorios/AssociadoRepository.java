package com.br.arquivoapi.repositorios;


import com.br.arquivoapi.model.entidades.Associado;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;


@Repository
public interface AssociadoRepository extends BaseRepository<Associado>{

    Associado findByUuid(UUID uuid);

    Associado findByDocumento(String documento);

    List<Associado> findAll();
}
