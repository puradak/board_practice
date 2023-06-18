package com.example.demo.src.Reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.Reply.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.NOT_EXIST_REPLY_IDX;

@Controller
@RequestMapping("/app/reply")
public class ReplyController {
    private final ReplyProvider replyProvider;
    private final ReplyService replyService;

    @Autowired
    private ReplyController(ReplyProvider replyProvider, ReplyService replyService){
        this.replyProvider = replyProvider;
        this.replyService = replyService;
    }

    @ResponseBody
    @GetMapping(value = "getAllReplyOnPost")
    public BaseResponse<List<Reply>> getAllReplyOnPost(@RequestParam int postIdx){
        try{
            List<Reply> list = this.replyProvider.getAllReplyOnPost(postIdx);
            for(Reply r : list){
                System.out.println(r.getReplyContents());
            }
            return new BaseResponse<>(list);
        }catch(BaseException e){
            return new BaseResponse(e.getStatus());
        }
    }
    @ResponseBody
    @GetMapping(value = "getReplyOfUser")
    public BaseResponse<List<Reply>> getReplyOfUser(@RequestParam int userIdx){
        try{
            List<Reply> list = this.replyProvider.getReplyOfUser(userIdx);
            return new BaseResponse<>(list);
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping(value = "getReplyOf")
    public BaseResponse<Reply> getReplyOf(@RequestParam int replyIdx){
        try{
            Reply reply = this.replyProvider.getReplyOf(replyIdx);
            return new BaseResponse<>(reply);
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
    @ResponseBody
    @PostMapping(value = "update")
    public BaseResponse<String> update(@RequestBody Reply reply){
        try{
            String result = "";
            if(this.replyService.update(reply) == 1) result = "정상적으로 변경되었습니다.";
            else throw new BaseException(NOT_EXIST_REPLY_IDX);

            return new BaseResponse<>(result);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResponse<String> delete(int replyIdx){
        try{
            String result = "";
            if(this.replyService.delete(replyIdx) == 1) result = "정상적으로 삭제되었습니다.";
            else throw new BaseException(NOT_EXIST_REPLY_IDX);

            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
