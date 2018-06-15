package jp.co.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.UserDao;
import jp.co.example.entity.User;
import jp.co.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	public boolean isNumber(String id) {
		try {
			Integer.parseInt(id);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}

	@Override
	public List<User> find(String id, String name, String tel) {
		if(id !="" && !isNumber(id)) {
			return null;
		}
		return userDao.find(id,name,tel);
	}




	}
