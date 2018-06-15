package jp.co.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.dao.UserDao;
import jp.co.example.entity.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate jt;

	@Autowired
	private NamedParameterJdbcTemplate npjt;

	@Transactional
	public User findById(Integer id) {

		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

		try {
			return npjt.queryForObject("SELECT * FROM user_info WHERE user_id = :id ",param,new BeanPropertyRowMapper<User>(User.class));
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Transactional
	public List<User> find(String id, String name, String tel) {
		String SELECT = "SELECT user_id,user_name,telephone FROM user_info";

		boolean judgeId =!(id.equals(""));
		boolean judgeName = !(name.equals(""));
		boolean judgeTel = !(tel.equals(""));
		boolean flg = (judgeId || judgeName || judgeTel);


		SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);

		if(flg) {
			SELECT += "WHERE";

			if(judgeId) {
				SELECT += "user_id = ?";
			}else {
				SELECT += "'1' = ?";
			}

			if(judgeName){
				SELECT += "AND user_name = ?";
			}else {
				SELECT += "AND user_name  LIKE";
			}
			if(judgeTel) {
				SELECT += "AND telephone = ?";
			}else {
				SELECT += "AND telephone  LIKE";
			}
		}
			SELECT += "ORDER BY user_id";


		if(flg) {
			if(judgeId&&judgeName&&judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),Integer.parseInt(id),name,tel);
			}
			else if(!judgeId && judgeName && judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),1,name,tel);
			}
			else if(judgeId && !judgeName && judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),Integer.parseInt(id),"%%",tel);
			}
			else if(judgeId && judgeName && !judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),Integer.parseInt(id),name,"%%");
			}
			else if(!judgeId && !judgeName && judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),1,"%%",tel );
			}
			else if(!judgeId && judgeName && !judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),1, name,"%%");
			}
			else if(judgeId && !judgeName && !judgeTel) {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class),Integer.parseInt(id),"%%","%%");
			}else {
				return jt.query(SELECT,new BeanPropertyRowMapper<User>(User.class));
			}
		}
		return null;
	}


}
