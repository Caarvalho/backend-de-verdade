package com.vinicius.demo.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;


@Component
public class BigDecimalConverter {
   
    public BigDecimal converter(String valor){
        if (valor == null)
        {
            return null;
        }
        valor = valor.replace(".", "").replace(",", ".");
        return new BigDecimal(valor);

    }
    
}
