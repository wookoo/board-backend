package com.example.board.domain.board.service;

import com.example.board.domain.board.entity.Board;
import com.example.board.global.exception.BaseException;
import com.example.board.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void increaseViewOnce(Board board) {
        int view = board.getView() + 1;
        board.setView(view);
        boardRepository.save(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt"));
    }
}
