package com.samgyeobsal.domain.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderFormDTO {
    private List<OrderListVO> orderList;

    private OrderFtitleVO orderFtitle;

    private Integer totalOriginPrice;

    // 추가한 컬럼
    private Integer totalPrice;

    private String oid;

    private String ophone;

    private Integer oused_mileage;

    private String pmcode;

    private String cpid;


    // TODO : order table column 채우기

    public OrderFormDTO(List<OrderListVO> orderList, OrderFtitleVO orderFtitle){
        this.orderList = orderList;
        this.orderFtitle = orderFtitle;
        int totalOriginPrice = 0;
        for (OrderListVO orderListVO : orderList) {
            totalOriginPrice += orderListVO.getSumprice();
        }
        this.totalOriginPrice = totalOriginPrice;
    }

}
