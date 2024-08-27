package com.example.board.domain.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.global.exception.BaseException;
import com.example.board.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardService {
    private final BoardRepository boardRepository;

    public Board findById(long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> BaseException.from(ErrorCode.BOARD_NOT_FOUND)
        );
    }

    public void save(Board board) {
        boardRepository.save(board);
    }
}
