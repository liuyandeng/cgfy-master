package com.cgfy.redisson.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockModel implements Serializable {

    private Integer id;

    private Integer productId;

    private Integer stock;

}
