package com.example.board.application.facade;

import com.example.board.application.dto.request.CreateAccountRequest;
import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Gender;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountFacadeTest {

    @Autowired
    AccountFacade accountFacade;

    @Autowired
    MemberService memberService;

    @Nested
    @DisplayName("canUseMemberId 테스트")
    class canUseMemberId {

        @Test
        @DisplayName("성공, MemberId 가 없을때 true 를 반환한다.")
        void whenSuccessMemberIdNotExists() {

            //given
            String memberId = "rokmc";

            //when
            CanUseMemberIdResponse canUseMemberIdResponse = accountFacade.canUseMemberId(memberId);

            //then
            assertTrue(canUseMemberIdResponse.isStatus());
        }

        @Test
        @DisplayName("성공, MemberId 가 있을때 false 를 반환한다.")
        void whenSuccessMemberIdExists() {

            //given
            String memberId = "rokmc";
            Member member = new Member(
                    memberId,
                    "password",
                    "name",
                    "call",
                    Gender.MALE,
                    "nickname",
                    "email"
            );
            memberService.save(member);

            //when
            CanUseMemberIdResponse canUseMemberIdResponse = accountFacade.canUseMemberId(memberId);

            //then
            assertFalse(canUseMemberIdResponse.isStatus());


        }
    }


    @Nested
    @DisplayName("registerMember 테스트")
    class registerMember {


        @Test
        @DisplayName("성공")
        void whenSuccess() {
            //given
            String memberId = "rokmc";
            String password = "1234";
            CreateAccountRequest createAccountRequest = new CreateAccountRequest(
                    memberId,
                    password,
                    "name",
                    "call",
                    Gender.MALE,
                    "nickname",
                    "email"
            );
            //when
            accountFacade.registerMember(createAccountRequest);
            //insert 가 됬으니, DB 에서 조회해야 함
            //then
            Member member = memberService.findByMemberId(memberId);
            assertEquals(member.getMemberId(), createAccountRequest.getMemberId());
            assertNotEquals(member.getPassword(), createAccountRequest.getPassword());
            assertEquals(member.getName(), createAccountRequest.getName());
            assertEquals(member.getCall(), createAccountRequest.getCall());
            assertEquals(member.getGender(), createAccountRequest.getGender());
            assertEquals(member.getNickName(), createAccountRequest.getNickName());
            assertEquals(member.getEmail(), createAccountRequest.getEmail());
        }

        @Test
        @DisplayName("실패, 회원이 이미 존재하는 경우")
        void whenFailMemberExists() {

            //given
            String memberId = "rokmc";
            String password = "1234";
            Member member = new Member(
                    memberId,
                    password,
                    "name",
                    "call",
                    Gender.MALE,
                    "nickname",
                    "email"
            );
            CreateAccountRequest createAccountRequest = new CreateAccountRequest(
                    memberId,
                    password,
                    "name",
                    "call",
                    Gender.MALE,
                    "nickname",
                    "email"
            );
            memberService.save(member);
            //when, then
            Assertions.assertThrows(RuntimeException.class, () -> {

                accountFacade.registerMember(createAccountRequest);
            });


        }
    }

}