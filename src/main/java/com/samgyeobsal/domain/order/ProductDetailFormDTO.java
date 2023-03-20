package com.samgyeobsal.domain.order;

import lombok.*;


import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailFormDTO {
    List<ProductDetailVO> productDetail;
    OrderFtitleVO orderFtitle;
}
