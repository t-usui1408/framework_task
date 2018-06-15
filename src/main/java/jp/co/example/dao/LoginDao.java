package jp.co.example.dao;

import jp.co.example.entity.Login;

public interface LoginDao {
	public Login findByIdAndPass(String id,String pass);

}
