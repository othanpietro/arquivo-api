package com.br.arquivoapi.model;

import lombok.*;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssociadoDTO {
    private String uuid;
    private String nome;
    private String TipoPessoa;
    private String documento;
}
