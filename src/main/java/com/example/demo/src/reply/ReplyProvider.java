package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.src.reply.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyProvider {

    private final ReplyDao replyDao;

    @Autowired
    public ReplyProvider(ReplyDao replyDao){
        this.replyDao = replyDao;
    }

    public List<Reply> getAllReplyOnPost(int postIdx) throws BaseException {
        return this.replyDao.getAllReplyOnPost(postIdx);
    }

    public List<Reply> getReplyOfUser(int userIdx) throws BaseException{
        return this.replyDao.getReplyOfUser(userIdx);
    }

    public Reply getReplyOf(int replyIdx) throws BaseException{
        return this.replyDao.getReplyOf(replyIdx);
    }
}
