package com.example.board.domain.comment.service;


import com.example.board.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommentRepository extends JpaRepository<Comment,Long> {
}
