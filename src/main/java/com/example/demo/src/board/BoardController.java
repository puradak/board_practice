package com.example.demo.src.board;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.CreateBoardReq;
import com.example.demo.src.board.model.CreateBoardRes;
import com.example.demo.src.board.model.GetBoardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
@RestController
@RequestMapping("/app/board")
public class BoardController {

    @Autowired
    private final BoardProvider boardProvider;
    @Autowired
    private final BoardService boardService;
    @Autowired
    BoardController(BoardProvider boardProvider, BoardService boardService){
        this.boardProvider = boardProvider;
        this.boardService = boardService;
    }
    @ResponseBody
    @PostMapping(value = "create")
    public BaseResponse<CreateBoardRes> createBoard(@RequestBody CreateBoardReq boardName) {
        try{
            if(boardName == null) return new BaseResponse<>(REQUEST_ERROR);
            CreateBoardRes board = boardService.createBoard(boardName);
            return new BaseResponse<>(board);
        } catch (BaseException e){
            return new BaseResponse<>((e.getStatus()));
        }
    }
    @GetMapping("/getAllList")
    public BaseResponse<List<GetBoardRes>> getAllList(){
        try{
            List<GetBoardRes> list = boardProvider.getAllBoardList();
            return new BaseResponse<>(list);
        }catch(BaseException e){
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @PostMapping("/delete/{boardName}")
    public BaseResponse<String> deleteBoard(@PathVariable(value = "boardName") String boardName){
        try{
            this.boardService.deleteBoard(boardName);
            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }

    @PostMapping("/updateBoardName")
    public BaseResponse<String> updateBoard(@RequestParam(value="from") String from, @RequestParam(value="to")String to){
        try{
            this.boardService.updateBoard(from,to);
            return new BaseResponse<>("");
        } catch (BaseException e) {
            return new BaseResponse<>((e.getStatus()));
        }
    }
}

