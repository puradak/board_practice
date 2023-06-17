package com.example.demo.src.post;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Controller
@RequestMapping("/app/post")
public class PostController {

    private final PostProvider postProvider;

    private final PostService postService;

    @Autowired
    public PostController(PostProvider postProvider, PostService postService){
        this.postProvider = postProvider;
        this.postService = postService;
    }

    @ResponseBody
    @PostMapping(value = "create")
    public BaseResponse<Post> createPost(@RequestBody CreatePostReq createPostReq){
        try{
            // 유효한 게시판인지 확인
            if(postProvider.isExistBoardIdx(createPostReq.getBoardIdx()) != 1){
                return new BaseResponse<>(NON_EXIST_BOARDIDX);
            }
            Post post = postService.createPost(createPostReq);
            return new BaseResponse<>(post);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResponse<String> deletePost(@RequestBody DeletePostReq deletePostReq){
        try{
            this.postService.deletePost(deletePostReq);
            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @PostMapping(value = "update")
    public BaseResponse<String> updatePost(@RequestBody UpdatePostReq updatePostReq){
        try{
            if(updatePostReq.getPostTitle().equals(""))
                throw new BaseException(EMPTY_POST_TITLE);
            if(updatePostReq.getPostContents().equals(""))
                throw new BaseException(EMPTY_POST_CONTENTS);
            this.postService.updatePost(updatePostReq);
            return new BaseResponse<>("");
        }catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping(value = "findByTitle")
    public BaseResponse<List<GetPostRes>> getPostByTitle(@RequestParam GetPostReq getPostReq){
        try{
            if(getPostReq.getTitle().isEmpty() && getPostReq.getTitle().equals(""))
                throw new BaseException(EMPTY_POST_TITLE);
            List<GetPostRes> list = this.postProvider.getPostByTitle(getPostReq);
            return new BaseResponse<>(list);

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
    @ResponseBody
    @GetMapping(value = "getPostContents")
    public BaseResponse<String> getPostContents (@RequestBody GetPostContentsReq getPostContentsReq){
        try{
            //idx가 존재하지 않을 시 예외처리 해야 함
            String contents = this.postProvider.getPostContents(getPostContentsReq);
            if(contents == null) throw new BaseException(NOT_EXIST_POST_CONTENTS);
            return new BaseResponse<>(contents);
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

}
