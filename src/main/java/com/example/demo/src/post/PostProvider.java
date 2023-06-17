package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.src.post.model.GetPostContentsReq;
import com.example.demo.src.post.model.GetPostReq;
import com.example.demo.src.post.model.GetPostRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostProvider {
    private final PostDao postDao;

    @Autowired
    public PostProvider(PostDao postDao){
        this.postDao = postDao;
    }

    public int isExistBoardIdx(int boardIdx){
        return this.postDao.isExistBoardIdx(boardIdx);
    }

    public List<GetPostRes> getPostByTitle (GetPostReq getPostReq) throws BaseException {
        return this.postDao.getPostByTitle(getPostReq);
    }

    public String getPostContents(GetPostContentsReq getPostContentsReq) throws BaseException{
        return this.postDao.getPostContents(getPostContentsReq);
    }
}
