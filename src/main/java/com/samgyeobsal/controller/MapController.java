package com.samgyeobsal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/map/")
@RequiredArgsConstructor
@Slf4j
public class MapController {

    @GetMapping
    public String loadMap(){
        return "map/map";
    }


}