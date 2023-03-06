package com.samgyeobsal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 * @filename UtilRestController
 * @author 최태승
 * @since 2023.03.04
 * create uuid
 *
 * <pre>
 * 수정일        	수정자       			수정내용
 * ----------  --------    ---------------------------
 * 2023.03.04	최태승		최초 생성
 * </pre>
 */
@RestController
@RequestMapping("/web/util")
public class UtilRestController {

    @GetMapping("/uuid")
    public Map<?, ?> getUuid() {
        HashMap<Object, Object> result = new HashMap<>();

        String uuid = UUID.randomUUID().toString();
        result.put("uuid", uuid);

        return result;
    }
}
