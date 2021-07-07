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
	private Role role;
}
/**
 * 優先度のEnumクラス
 */
enum Role {
	ADMIN("ROLE_ADMIN","管理者"),
	GENERAL("ROLE_GENERAL","一般");
	
	/**権限名*/
	private String role_name;
	
	/**値*/
	private String value;
	
	/**コンストラクタ*/
	Role(String role_name,String value){
		this.role_name = role_name;
		this.value = value;
	}
	
	public String getRole_name() {
		return this.role_name;
	}
	public String getValue() {
		return this.value;
	}
	
	/**
	 * IDから合致したRole型を返却する
	 * @param role_name
	 * @return role
	 * 
	 */
	public static Role nameOf(String role_name) {
		for (Role role : values()) {
			System.out.println(role.getRole_name());
			if (role.getRole_name().equals(role_name)) {
				System.out.println("あ");
				return role;
			}
		}
		throw new IllegalArgumentException("指定されたIDのPriorityが存在しません");
	}
}
