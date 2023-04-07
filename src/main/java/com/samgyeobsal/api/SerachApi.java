package com.samgyeobsal.api;

import com.samgyeobsal.domain.search.SearchCriteria;
import com.samgyeobsal.domain.search.SearchVO;
import com.samgyeobsal.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SerachApi {

    private final SearchService searchService;
    @GetMapping("")
    ResponseEntity<Map<String,Object>> getSearchList(SearchCriteria criteria){

        List<SearchVO> search = searchService.getSearchList(criteria);
        int count=searchService.countSearch(criteria);
        Map<String,Object> map= new HashMap<>();
        map.put("search",search);
        map.put("count",count);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
