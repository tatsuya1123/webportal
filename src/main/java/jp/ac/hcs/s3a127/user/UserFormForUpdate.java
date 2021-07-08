package jp.ac.hcs.s3a127.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserFormForUpdate {
	/** ユーザID（メールアドレス）*/
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check")
	private String user_id;
	
	/**パスワード*/
	private String password;
	
	/**ユーザ名*/
	@NotBlank(message = "{require_check}")
	private String user_name;
	
	/*ダークモードフラグ*/
	private boolean darkmode;
	
	/** 権限*/
	private String role;
}
