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
	 * タスクID
	 * 主キー、SQLにて自動裁判
	 */
	private int id;
	
	/**
	 * ゆーざID （メールアドレス）
	 * Userテーブルの主キーと紐づく、ログイン情報から取得
	 */
	private String user_id;
	
	/**
	 * 優先度
	 * 大、中、小の3種類
	 */
	private Priority priority;
	
	/**
	 * 件名
	 * 必須入力
	 */
	private String title;
	
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

/**
 * 優先度のEnumクラス
 */
enum Priority {
	HIGH(1,"大"),
	MIDDLE(2,"中"),
	LOW(3,"小");
	
	/** ID*/
	private int id;
	
	/**値*/
	private String value;
	
	/**コンストラクタ*/
	Priority(int id, String value){
		this.id = id;
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	public int getId() {
		return this.id;
	}
	
	/**
	 * IDから合致したPriority型を返却する
	 * @param id
	 * @return Priority
	 * 
	 */
	public static Priority idOf(int id) {
		for (Priority priority : values()) {
			if (priority.getId() == id) {
				return priority;
			}
		}
		throw new IllegalArgumentException("指定されたIDのPriorityが存在しません");
	}
}