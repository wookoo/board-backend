package com.example.board.application.dto.response;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponse {

    private String title;
    private String content;
    private int view;
    private LocalDateTime createdAt;

    private String memberNickname;
    private long memberId;

    private BoardDetailResponse(String title, String content, int view, LocalDateTime createdAt, String memberNickname, long memberId) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
        this.memberNickname = memberNickname;
        this.memberId = memberId;
    }

    public static BoardDetailResponse from(Board board) {
        String title = board.getTitle();
        String content = board.getContent();
        int view = board.getView();
        LocalDateTime createdAt = board.getCreatedAt();
        Member member = board.getMember();
        String nickname = member.getNickName();
        long memberId = member.getId();
        return new BoardDetailResponse(title, content, view, createdAt, nickname, memberId);
    }
}
