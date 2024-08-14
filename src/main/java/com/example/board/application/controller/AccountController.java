package com.example.board.application.controller;


import com.example.board.application.dto.request.CreateAccountRequest;
import com.example.board.application.dto.request.LoginAccountRequest;
import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.application.facade.AccountFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountController {
    private final AccountFacade accountFacade;

    @GetMapping("")
    public ResponseEntity<CanUseMemberIdResponse> canUseMemberId(
            @RequestParam(name = "memberId") String memberId
    ){
        CanUseMemberIdResponse response = accountFacade.canUseMemberId(memberId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("")
    public ResponseEntity<Void> createAccount(
            @RequestBody CreateAccountRequest createAccountRequest
            ){

        accountFacade.registerMember(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody LoginAccountRequest loginAccountRequest){
        //accessToken 을 발급 해야함. jwt 에서.

        String accessToken = accountFacade.loginAccount(
                loginAccountRequest.getMemberId(),
                loginAccountRequest.getPassword()
        );

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+accessToken);

        return ResponseEntity.ok().headers(httpHeaders).build();
    }




}
