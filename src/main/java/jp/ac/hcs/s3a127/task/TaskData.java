package jp.ac.hcs.s3a127.task;



import java.sql.Date;

import lombok.Data;

/**
 * 1件分のタスク情報
 *
 */
@Data
public class TaskData {
	
	/**
	 * タスクOD
	 * 主キー、SQLにて自動裁判
	 */
	private int id;
	
	/**
	 * ゆーざID （メールアドレス）
	 * Userテーブルの主キーと紐づく、ログイン情報から取得
	 */
	private String user_id;
	
	/**
	 * コメント
	 * 必須入力
	 */
	private String comment;
	
	/**
	 * 期限日
	 * 必須入力
	 */
	private Date limitday;
}
