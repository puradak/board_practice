package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.src.board.model.GetBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class BoardProvider {

    private final BoardDao boardDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BoardProvider(BoardDao boardDao, JwtService jwtService) {
        this.boardDao = boardDao;
        this.jwtService = jwtService;
    }

    public List<GetBoardRes> getAllBoardList() throws BaseException {
        try {
            List<GetBoardRes> list = boardDao.getAllBoardList();
            return list;
        } catch (Exception e) {
            logger.error("Error!", e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkDuplicateBoardName(String boardName){
        return this.boardDao.checkDuplicateBoardName(boardName);
    }
}
