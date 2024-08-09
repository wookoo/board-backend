package com.example.board.application.facade;


import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountFacade {

    private final MemberService memberService;

    public CanUseMemberIdResponse canUseMemberId(String memberId) {
        boolean status = memberService.notExistsByMemberId(memberId);
        return new CanUseMemberIdResponse(status);
    }


}
