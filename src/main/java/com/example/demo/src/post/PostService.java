package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.board.model.CreateBoardReq;
import com.example.demo.src.board.model.CreateBoardRes;
import com.example.demo.src.post.model.CreatePostReq;
import com.example.demo.src.post.model.DeletePostReq;
import com.example.demo.src.post.model.Post;
import com.example.demo.src.post.model.UpdatePostReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class PostService {
    private PostDao postDao;
    private PostProvider postProvider;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PostService(PostDao postDao, PostProvider postProvider) {
        this.postDao = postDao;
        this.postProvider = postProvider;
    }

    public Post createPost(CreatePostReq createPostReq) throws BaseException {
        int postIdx = postDao.createPost(createPostReq);
        logger.debug("postService -> createPost = {}",createPostReq.getPostTitle());
        return new Post(
                postIdx,
                createPostReq.getBoardIdx(),
                createPostReq.getUserIdx(),
                createPostReq.getPostTitle(),
                createPostReq.getPostContents(),
                createPostReq.getPostDate()
        );
    }

    public void deletePost(DeletePostReq deletePostReq) throws BaseException {
        try{
            int result = this.postDao.deletePost(deletePostReq);
            if(result != 1) throw new BaseException(REQUEST_ERROR);
        } catch (Exception e) {
            logger.debug("PostService -> deletePost : ",e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void updatePost(UpdatePostReq updatePostReq) throws BaseException {
        try{
            int result = this.postDao.updatePost(updatePostReq);
            if(result == 0) throw new BaseException(REQUEST_ERROR);
        } catch (BaseException e) {
            logger.debug("PostService -> updatePost : ", e);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
