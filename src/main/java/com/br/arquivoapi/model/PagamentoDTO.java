package com.br.arquivoapi.model;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PagamentoDTO {

   private String documentoAssociado;
   private String identificadorBoleto;
   private BigDecimal valor;
   private boolean pago;

}
