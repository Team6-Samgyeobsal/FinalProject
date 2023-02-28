package com.samgyeobsal.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String mId;
    private String psId;
    private Integer pQuantity;
}
