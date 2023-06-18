package com.example.demo.src.post;

import com.example.demo.src.post.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PostDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PostDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int isExistBoardIdx(int boardIdx){
        String qry = "SELECT EXISTS(SELECT 1 FROM Board WHERE boardIdx ="+boardIdx+")";
        int param = boardIdx;
        return this.jdbcTemplate.queryForObject(qry,int.class);
    }

    public int createPost(CreatePostReq createPostReq){
        String qry = "INSERT INTO Post(boardIdx, userIdx, postTitle, postContents, postDate) VALUES (?,?,?,?,?)";
        Object[] createBoardParams = new Object[]{
                createPostReq.getBoardIdx(),
                createPostReq.getUserIdx(),
                createPostReq.getPostTitle(),
                createPostReq.getPostContents(),
                createPostReq.getPostDate()
        };
        this.jdbcTemplate.update(qry,createBoardParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }

    public int deletePost(DeletePostReq deletePostReq){
        String qry = "DELETE FROM Post WHERE boardIdx = "+deletePostReq.getBoardIdx()
                +" AND userIdx = "+deletePostReq.getUserIdx()+" AND postIdx = "+deletePostReq.getPostIdx();
        return this.jdbcTemplate.update(qry);
    }

    public int updatePost(UpdatePostReq updatePostReq){
        String qry = "UPDATE Post SET postTitle = '" + updatePostReq.getPostTitle()
                +"', postContents = '" + updatePostReq.getPostContents()
                +"', postDate = '"+updatePostReq.getPostDate()
                +"' WHERE boardIdx = "+updatePostReq.getBoardIdx()
                +" AND userIdx = "+ updatePostReq.getUserIdx()
                +" AND postIdx = "+ updatePostReq.getPostIdx();

        return this.jdbcTemplate.update(qry);
    }

    public List<GetPostRes> getPostByTitle(GetPostReq getPostReq){
        String qry = "SELECT boardIdx, userIdx, postIdx, postTitle FROM Post WHERE postTitle LIKE '%"+getPostReq.getTitle()+"%'";
        return this.jdbcTemplate.query(qry, (rs,rowNum)-> new GetPostRes(
                rs.getInt("boardIdx"),
                rs.getInt("userIdx"),
                rs.getInt("postIdx"),
                rs.getString("postTitle")));
    }

    public String getPostContents(GetPostContentsReq getPostContentsReq){
        String qry = "SELECT postContents FROM Post WHERE boardIdx = ? AND userIdx = ? AND postIdx = ?";
        Object[] param = new Object[]{
                getPostContentsReq.getBoardIdx(),
                getPostContentsReq.getUserIdx(),
                getPostContentsReq.getPostIdx()
        };
        try {
            return this.jdbcTemplate.queryForObject(qry, (rs, rowNum) -> new String(
                    rs.getString("postContents")), param);
        } catch (Exception e) {
            return null;
        }
    }


}
