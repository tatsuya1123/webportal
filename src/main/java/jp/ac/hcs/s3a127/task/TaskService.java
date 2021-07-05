package jp.ac.hcs.s3a127.task;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	/**
	 * 指定したユーザIDに紐づくタスクを取得する
	 * 
	 * @param userId ユーザID
	 * @return TaskEntity
	 */
	public TaskEntity getTask(String userId) {
		TaskEntity taskEntity = taskRepository.selectAll(userId);
		return taskEntity;
	}
	public TaskEntity setTask(String userId, String comment, int priority, String title,Date limitday, TaskEntity taskEntity) {
		
		TaskData taskData = new TaskData();
		taskData.setUser_id(userId);
		taskData.setPriority(Priority.idOf(priority));
		taskData.setTitle(title);
		taskData.setComment(comment);
		taskData.setLimitday(limitday);
		taskData.setId(taskRepository.insertOne(taskData));
		
		taskEntity = getTask(userId);
		
		return taskEntity;
	}
	public int deleteTask(int id,String userId) {
		int rownumber = taskRepository.deleteOne(id);
		return rownumber;
		
	}
	/**
	 * タスク情報をCSVファイルとしてサーバに保存する.
	 * @param user_id ユーザID
	 * @throws DataAccessException
	 */
	public void taskListCsvOut(String user_id) throws DataAccessException {
		taskRepository.tasklistCsvOut(user_id);
	}

	/**
	 * サーバーに保存されているファイルを取得して、byte配列に変換する.
	 * @param fileName ファイル名
	 * @return ファイルのbyte配列
	 * @throws IOException ファイル取得エラー
	 */
	public byte[] getFile(String fileName) throws IOException {
		FileSystem fs = FileSystems.getDefault();
		Path p = fs.getPath(fileName);
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
}
