package com.MSBootCoinBootCamp.domain.model;

import com.MSBootCoinBootCamp.domain.enums.CurrencyExchange;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private BigDecimal montocambio;
    private CurrencyExchange monedacambio;
    private BigDecimal tasacambio;
    private BigDecimal montobase;
    private CurrencyExchange monedabase;
}
