package com.MSBootCoinBootCamp.domain.model;

import com.MSBootCoinBootCamp.domain.enums.TypesCurrencyExchange;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@ToString
public class Operacion {

    private String estado;
    @Id
    private String codigooperacion;
    private String bootcoinaccount;
    private BigDecimal montocambio;
    private TypesCurrencyExchange monedacambio;
    private BigDecimal tasacambio;
    private BigDecimal montobase;
    private TypesCurrencyExchange monedabase;
}
