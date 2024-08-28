package com.example.board.domain.board.entity;


import com.example.board.domain.comment.entity.Comment;
import com.example.board.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title",nullable = false)
    private String title;


    @CreatedDate
    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt;


    @Setter
    @ColumnDefault("0")
    @Column(name = "view",nullable = false)
    private int view;

    @Column(name = "content",nullable = false)
    private String content;

    @OneToMany(mappedBy = "board") //1:N 관계에서 mappedBy 가 필요하다.
    //mappedBy에 들어가는 String 은 Comment Class 의 board 의 "필드명" 이다.
    private List<Comment> commentList;
    

    public Board(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.view = 0;
    }
}
