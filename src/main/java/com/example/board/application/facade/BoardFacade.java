package com.example.board.application.facade;

import com.example.board.application.dto.response.BoardDetailResponse;
import com.example.board.domain.board.entity.Board;
import com.example.board.domain.board.service.BoardService;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFacade {

    private final BoardService boardService;
    private final MemberService memberService;

    public void writeBoard(String title, String content) {
        Member member = memberService.findById(1L);
        Board board = new Board(member, title, content);
        boardService.save(board);
    }

    public BoardDetailResponse getBoard(long id) {
        Board board = boardService.findById(id);
        boardService.increaseViewOnce(board);
        /*
        제목
        내용
        작성자정보
        조회수 -> 게시물을 조회했을때 1 증가 시키고 보여줘야 함.
        작성일자
         */

        String title = board.getTitle();
        String content = board.getContent();
        int view = board.getView();
        LocalDateTime createdAt = board.getCreatedAt();
        Member member = board.getMember();

        return new BoardDetailResponse(
                title,
                content,
                view,
                createdAt
        );


    }

}
