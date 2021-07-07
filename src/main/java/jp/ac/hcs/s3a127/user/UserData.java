package jp.ac.hcs.s3a127.user;

import lombok.Data;

@Data
public class UserData {

	/**
	 * ユーザID（メールアドレス）
	 * 主キー、必須入力、メールアドレス形式
	 */
	private String user_id;
	
	/**
	 * パスワード
	 * 必須入力、長さ４から１００桁まで、半角英数字のみ
	 */
	private String password;
	
	/**
	 * アカウント有効性
	 * 有効 : true
	 * 無効 : false
	 * ユーザ作成時はtrue固定
	 */
	private boolean enabled;
	
	/**
	 * ユーザ名
	 * 必須入力
	 */
	private String user_name;
	
	/**
	 * ダークモードフラグ
	 * -ON : true
	 * -OFF : false
	 * ユーザ作成時はfalse固定
	 */
	private boolean darkmode;
	
	/**
	 * 権限
	 * -管理 : "ROLE_ADMIN"
	 * -一般 : "ROLE_GENERAL"
	 * 必須入力
	 */
	private String role;
}
