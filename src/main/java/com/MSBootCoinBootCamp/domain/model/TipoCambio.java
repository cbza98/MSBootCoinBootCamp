package com.MSBootCoinBootCamp.domain.model;

import com.MSBootCoinBootCamp.domain.enums.CurrencyExchange;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@ToString
public class TipoCambio {

    @Id
    private String codigocambio;
    @Digits(integer =20, fraction=6)
    private BigDecimal preciocompra;
    @Digits(integer =20, fraction=6)
    private BigDecimal precioventa;
    @NotNull
    private LocalDate fechacambio;
    @NotNull
    private CurrencyExchange monedacambio;
}
