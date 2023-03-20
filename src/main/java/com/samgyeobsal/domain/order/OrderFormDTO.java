package com.samgyeobsal.domain.order;

import com.samgyeobsal.domain.member.MemberVO;
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
    private int totalprice;

    private OrderFtitleVO orderFtitle;

    // TODO : order table column 채우기
}
