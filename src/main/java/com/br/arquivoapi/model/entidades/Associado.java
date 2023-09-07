package com.br.arquivoapi.model.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.br.arquivoapi.AppConstants.ENTITY_SCHEMA;


@Entity
@Table(
        name = "associado",
        schema = ENTITY_SCHEMA)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Associado extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo_pessoa", nullable = false)
    private String tipoPessoa;

    @Column(name = "documento", nullable = false)
    private String documento;


}
