package com.example.demo.src.Reply;

import com.example.demo.src.Reply.model.GetReplyOfReq;
import com.example.demo.src.Reply.model.GetReplyOnPostReq;
import com.example.demo.src.Reply.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReplyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReplyDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Reply> getAllReplyOnPost(GetReplyOnPostReq getReplyOnPostReq){
        String qry = "SELECT * FROM Reply WHERE replyIdx = ?";
        Object[] param = new Object[]{
                getReplyOnPostReq.getPostIdx()
        };
        try{
            return this.jdbcTemplate.query(qry, (rs,rowNum) -> new Reply(
                    rs.getInt("replyIdx"),
                    rs.getInt("postIdx"),
                    rs.getInt("userIdx"),
                    rs.getInt("liked"),
                    rs.getString("replyContents"),
                    rs.getString("replyData")
                    ),param);
        }catch(Exception e){
            return null;
        }
    }

    public List<Reply> getReplyOfUser(int userIdx){
        String qry = "SELECT * FROM Reply WHERE userIdx = "+userIdx;
        try {
            return this.jdbcTemplate.query(qry, (rs, rowNum) -> new Reply(
                    rs.getInt("replyIdx"),
                    rs.getInt("postIdx"),
                    rs.getInt("userIdx"),
                    rs.getInt("liked"),
                    rs.getString("replyContents"),
                    rs.getString("replyDate")
            ));
        }catch(Exception e){
            return null;
        }
    }

    public Reply getReplyOf(int replyIdx){
        String qry = "SELECT * FROM Reply WHERE replyIdx = "+replyIdx;
        try{
            return this.jdbcTemplate.queryForObject(qry, (rs,rowNum) -> new Reply(
                    rs.getInt("replyIdx"),
                    rs.getInt("postIdx"),
                    rs.getInt("userIdx"),
                    rs.getInt("liked"),
                    rs.getString("replyContents"),
                    rs.getString("replyDate")
                    )
            );
        }catch(Exception e){
            return null;
        }
    }

}
