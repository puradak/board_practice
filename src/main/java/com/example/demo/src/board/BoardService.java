package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.src.board.model.CreateBoardReq;
import com.example.demo.src.board.model.CreateBoardRes;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class BoardService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BoardDao boardDao;
    private final BoardProvider boardProvider;
    private final JwtService jwtService;


    @Autowired
    public BoardService(BoardDao boardDao, BoardProvider boardProvider, JwtService jwtService){
        this.boardDao = boardDao;
        this.boardProvider = boardProvider;
        this.jwtService = jwtService;
    }

    public CreateBoardRes createBoard (CreateBoardReq boardName) throws BaseException {
       //중복검사
        if(this.boardProvider.checkDuplicateBoardName(boardName.getBoardName()) == 1) {
            throw new BaseException(POST_BOARD_EXISTS_BOARD);
        }

        int boardIdx = boardDao.createBoard(boardName.getBoardName());
        logger.debug("service -> createBoard = {}",boardName);
        return new CreateBoardRes(boardIdx,boardName.getBoardName());
    }

    public void deleteBoard(String boardName) throws BaseException {
        try{
            int result = this.boardDao.deleteBoard(boardName);
            if(result == 0) throw new BaseException(REQUEST_ERROR);
        } catch (Exception e){
            logger.debug("BoardService -> deleteBoard : ",e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void updateBoard(String from, String to) throws BaseException{
        try{
            //중복검사
            if(this.boardProvider.checkDuplicateBoardName(to) == 1) {
                throw new BaseException(POST_BOARD_EXISTS_BOARD);
            }
            int result = this.boardDao.updateBoard(from,to);
            if(result == 0) throw new BaseException(REQUEST_ERROR);
        } catch (BaseException e) {
            System.out.println("BoardService -> updateBoard : "+e);
            logger.debug("BoardService -> updateBoard : ", e);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
