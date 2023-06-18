package com.example.demo.src.Reply;

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

    public List<Reply> getAllReplyOnPost(int postIdx){
        String qry = "SELECT * FROM Reply WHERE postIdx = "+postIdx;
        try{
            return this.jdbcTemplate.query(qry, (rs,rowNum) -> new Reply(
                    rs.getInt("replyIdx"),
                    rs.getInt("postIdx"),
                    rs.getInt("userIdx"),
                    rs.getInt("liked"),
                    rs.getString("replyContents"),
                    rs.getString("replyDate")
                    ));
        }catch(Exception e){
            System.out.println("*****Exception 발생*****\n내용 : "+e.getMessage());
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

    public int update(Reply reply){
        String qry = "UPDATE Reply SET liked = ?, replyContents = ?, replyDate = now() WHERE replyIdx = ?";
        Object[] param = new Object[]{
                reply.getLiked(), reply.getReplyContents(), reply.getReplyIdx()
        };
        return this.jdbcTemplate.update(qry,param);
    }

    public int delete(int replyIdx){
        String qry = "DELETE FROM Reply Where replyIdx = ?";
        Object[] param = new Object[] {
                replyIdx
        };
        try{
            return this.jdbcTemplate.update(qry,param);
        } catch (Exception e) {
            System.out.println("Exception 발견\n"+e.getStackTrace());
            return 0;
        }
    }
}
