package jp.ac.hcs.s3a127.task;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
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

	public TaskEntity setTask(String userId, String comment, Date limitday, TaskEntity taskEntity) {
		
		TaskData taskData = new TaskData();
		taskData.setUser_id(userId);
		taskData.setComment(comment);
		taskData.setLimitday(limitday);
		taskData.setId(taskRepository.insertOne(taskData));
		
		taskEntity = getTask(userId);
		
		return taskEntity;
	}
}
