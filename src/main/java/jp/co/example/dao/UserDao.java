package jp.co.example.dao;

import java.util.List;

import jp.co.example.entity.User;

public interface UserDao {
	public User findById(Integer id);

	public List<User> find(String id,String name,String tel);

}
