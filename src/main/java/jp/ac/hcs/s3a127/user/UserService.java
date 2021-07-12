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
	 * ユーザーリストを取得する。
	 * 
	 * @return userEntity
	 */
	public UserEntity selectAll() {
		UserEntity userEntity = userRepository.selectAll();	
		return userEntity;
	}
	/**
	 * ユーザー情報を一件取得する。
	 * 
	 * @return userEntity
	 */
	public UserData selectOne(String user_id) {
		UserData userData = userRepository.selectOne(user_id);
		return userData;
	}
	/**
	 * ユーザーを一人追加する。
	 * @param data
	 * @return
	 */
	public int insertOne(UserData data) {
		int rownumber = userRepository.insertOne(data);
		return rownumber;
	}
	/**
	 * ユーザー情報を一人更新する。
	 * @param data
	 * @return
	 */
	public int updateOne(UserData data) {
		int rownumber = 0;
		if ((data.getRole().name().equals("ROLE_ADMIN"))) {
			if ((data.getPassword() == null) || ("".equals(data.getPassword()))) {
				rownumber = userRepository.updateOne(data);
			}
			else {
				rownumber = userRepository.updateOneWithPassword(data);
			}
		}
		else if(data.getRole().name().equals("ROLE_GENERAL")) {
			if ((data.getPassword() == null) || ("".equals(data.getPassword()))) {
				rownumber = userRepository.updateGeneral(data);
			}
			else {
				rownumber = userRepository.updateOneWithPassword(data);
			}
		}
		return rownumber;
	}
	/**
	 * ユーザー情報を一件削除する。
	 * @param user_id
	 * @return
	 */
	public int deleteOne(String user_id) {
		int rownumber = userRepository.deleteOne(user_id);
		return rownumber;
	}
}
