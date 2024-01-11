package com.goodee.guestbook.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.goodee.guestbook.model.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate; //jdbc-context에 빈으로 등록했기 때문

	public List<GuestBookVo> selectBookAll(){
		//1. list만들고
		//2. 쿼리 날리고
		//3. 연결
		//4. vo 리턴 => 서비스가 받아서 컨트롤러에 전달해줌
		
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		try {
			String sql = "SELECT * FROM content_list";
			list = jdbcTemplate.query(sql, new  RowMapper<GuestBookVo>() {
				@Override
				public GuestBookVo mapRow(ResultSet rs, int rowNum) throws SQLException{
					//rowNum 행의 번호
					GuestBookVo vo = new GuestBookVo();
					vo.setAuthor(rs.getString("g_author"));
					vo.setContent(rs.getString("g_content"));
					vo.setReg_date(rs.getDate("g_reg_date"));
					return vo;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int insertGuestBook() {
		String sql = "INSERT INTO content_list(g_author, g_content, g_reg_date) VALUES('남가람','테스트 내용', NOW())";
		
		int result = -1; //초기값 설정
		try {
			result = jdbcTemplate.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		//결과가 잘 됐으면 update된 값이(result>1), 안되면 -1 리턴됨
	}
	
	
}
