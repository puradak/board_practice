package com.example.demo.src.board;

import com.example.demo.src.board.model.GetBoardRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*게시판 생성*/
    public int createBoard(String boardName){
        String qry = "INSERT INTO Board(boardName) VALUES (?)";
        Object[] createBoardParams = new Object[]{boardName};
        this.jdbcTemplate.update(qry,createBoardParams);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
    }
    /*모든 게시판 조회*/
    public List<GetBoardRes> getAllBoardList(){
        String qry = "SELECT * FROM Board";

        return this.jdbcTemplate.query(qry, (rs,rowNum)->new GetBoardRes(
                rs.getInt("boardIdx"),
                rs.getString("boardName")));
    }

    public int deleteBoard(String boardName){
        String qry = "DELETE FROM Board WHERE boardName = ?";
        String param = boardName;
        return this.jdbcTemplate.update(qry,param);
    }

    public int updateBoard(String from, String to){
        String qry = "UPDATE Board SET boardName = ? WHERE boardName = '"+from+"'";
        String param = to;

        return this.jdbcTemplate.update(qry,param);
    }

    public int checkDuplicateBoardName(String boardName){
        String qry = "SELECT exists(SELECT boardName FROM Board WHERE boardName = ?)";
        String param = boardName;
        return this.jdbcTemplate.queryForObject(qry,int.class,param);
    }
}
