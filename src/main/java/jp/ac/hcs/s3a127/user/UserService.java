package jp.ac.hcs.s3a127.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	/**
	 * 指定した郵便番号に紐づく郵便番号情報を取得する。
	 * 
	 * @return userEntity
	 */
	public UserEntity selectAll() {
		UserEntity userEntity = userRepository.selectAll();	
		return userEntity;
	}
}
