package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.sample.SampleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {

    SampleVO getSample();
}
