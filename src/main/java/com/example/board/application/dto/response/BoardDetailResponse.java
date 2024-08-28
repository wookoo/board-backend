package com.example.board.application.dto.response;

import com.example.board.domain.board.entity.Board;
import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardDetailResponse {

    private String title;
    private String content;
    private int view;
    private LocalDateTime createdAt;
    private MemberInBoardDetailResponse member;
    private List<CommentInBoardDetailResponse> commentList;


    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class MemberInBoardDetailResponse {
        private final long id;
        private final String nickName;

        public static MemberInBoardDetailResponse from(Member member) {
            return new MemberInBoardDetailResponse(member.getId(), member.getNickName());
        }
    }


    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private static class CommentInBoardDetailResponse{
        private final long id;
        private final String content;

        public static CommentInBoardDetailResponse from(Comment comment) {
            return new CommentInBoardDetailResponse(comment.getId(), comment.getContent());
        }
    }





    private BoardDetailResponse(String title, String content, int view, LocalDateTime createdAt, MemberInBoardDetailResponse member, List<CommentInBoardDetailResponse> commentList) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
        this.member = member;
        this.commentList = commentList;
    }

    public static BoardDetailResponse from(Board board) {
        String title = board.getTitle();
        String content = board.getContent();
        int view = board.getView();
        LocalDateTime createdAt = board.getCreatedAt();
        Member member = board.getMember();

        List<Comment> commentList= board.getCommentList();

        List<CommentInBoardDetailResponse> commentInBoardDetailResponseList = new ArrayList<>();
        for(Comment comment :commentList){
            System.out.println(comment);
            commentInBoardDetailResponseList.add(CommentInBoardDetailResponse.from(comment));
        }

        return new BoardDetailResponse(title, content, view, createdAt, MemberInBoardDetailResponse.from(member),commentInBoardDetailResponseList);
    }
}
