package jp.co.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.dao.LoginDao;
import jp.co.example.entity.Login;
import jp.co.example.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public Login findByIdAndPass(String id, String pass) {
		return loginDao.findByIdAndPass(id,pass);
	}
}
