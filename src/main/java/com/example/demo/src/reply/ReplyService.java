package com.example.demo.src.reply;

import com.example.demo.config.BaseException;
import com.example.demo.src.reply.model.CreateReplyReq;
import com.example.demo.src.reply.model.CreateReplyRes;
import com.example.demo.src.reply.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyProvider replyProvider;
    private final ReplyDao replyDao;

    @Autowired
    public ReplyService (ReplyProvider replyProvider, ReplyDao replyDao){
        this.replyProvider = replyProvider;
        this.replyDao = replyDao;
    }

    public int update(Reply reply) throws BaseException {
        return this.replyDao.update(reply);
    }

    public int delete(int replyIdx) throws BaseException{
        return this.replyDao.delete(replyIdx);
    }

    public CreateReplyRes create(CreateReplyReq createReplyReq) throws BaseException{
        return this.replyDao.create(createReplyReq);

    }
}
