package com.example.demo.src.Reply;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.Reply.model.GetReplyOfReq;
import com.example.demo.src.Reply.model.GetReplyOnPostReq;
import com.example.demo.src.Reply.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResponse<List<Reply>> getAllReplyOnPost(@RequestBody GetReplyOnPostReq getReplyOnPostReq){
        try{
            List<Reply> list = this.replyProvider.getAllReplyOnPost(getReplyOnPostReq);
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
}
