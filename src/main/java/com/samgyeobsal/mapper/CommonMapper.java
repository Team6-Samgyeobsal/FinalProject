package com.samgyeobsal.mapper;

import com.samgyeobsal.domain.common.CompetitionHyundaiVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommonMapper {

    List<CompetitionHyundaiVO> getActiveCompetitionList();

    CompetitionHyundaiVO getCompetitionByCidAndTid(@Param("cid") String cid, @Param("tid") String tid);
}
