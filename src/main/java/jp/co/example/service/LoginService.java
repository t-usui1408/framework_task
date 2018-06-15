package jp.co.example.service;

import jp.co.example.entity.Login;

public interface LoginService {
	public Login findByIdAndPass(String id,String pass);

}
