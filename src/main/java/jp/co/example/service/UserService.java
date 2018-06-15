package jp.co.example.service;

import java.util.List;

import jp.co.example.entity.User;

public interface UserService {

	public User findById(Integer id) ;

	public List<User> find(String id,String name,String tel);

}