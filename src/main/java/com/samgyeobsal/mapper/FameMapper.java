package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.fame.FameCriteria;
import com.samgyeobsal.domain.fame.FameVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FameMapper {

    List<FameVO> getFameList(@Param("criteria") FameCriteria criteria);
}
