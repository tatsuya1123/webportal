package jp.ac.hcs.s3a127.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserEntity {
	
	/**ユーザ情報のリスト*/
	private List<UserData> userlist = new ArrayList<UserData>();
}
