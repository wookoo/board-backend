package com.example.board.application.facade;


import com.example.board.application.dto.request.CreateAccountRequest;
import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import com.example.board.global.exception.BaseException;
import com.example.board.global.exception.ErrorCode;
import com.example.board.global.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountFacade {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    public CanUseMemberIdResponse canUseMemberId(String memberId) {
        boolean status = memberService.notExistsByMemberId(memberId);
        return new CanUseMemberIdResponse(status);
    }

    public void registerMember(CreateAccountRequest createAccountRequest){
        //1. meberId 가 존재하면? 예외를 발생 시켜야 함.

        String memberId = createAccountRequest.getMemberId();
        if(memberService.existsByMemberId(memberId)){
            throw BaseException.from(ErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = createAccountRequest.toMember();
        memberService.save(member);
    }

    public String loginAccount(String memberId, String password){
        Member member = memberService.findByMemberId(memberId); // memberId 로 조회를 한다.
        String databasePassword = member.getPassword();
        if(!BCrypt.checkpw(password,databasePassword)){ //비밀번호가 일치 하지 않으면
            throw BaseException.from(ErrorCode.INVALID_PASSWORD);
        }
        return  jwtProvider.createAccessToken(member.getId());
    }



}
