package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.event.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    Event findByName(String eCouponName);
}
