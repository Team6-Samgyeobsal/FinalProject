package com.samgyeobsal.api;

import com.samgyeobsal.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/account")
public class AccountApi {

    private final AccountService accountService;

    @GetMapping("/dupCheck")
    public ResponseEntity<Boolean> dupCheck(
            @RequestParam("email") String email){
        boolean isExist = accountService.isExist(email);
        return new ResponseEntity<>(isExist, HttpStatus.OK);
    }
}
