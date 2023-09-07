package com.br.arquivoapi.repositorios;



import com.br.arquivoapi.model.entidades.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity> extends CrudRepository<T, String> {
}
