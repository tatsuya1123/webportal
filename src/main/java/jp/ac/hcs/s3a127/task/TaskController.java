package jp.ac.hcs.s3a127.task;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class TaskController {
		@Autowired
		private TaskService taskService;
		/**
		 * 郵便番号から住所を検索し、結果画面を表示する
		 * @param zipcode 検索する郵便番号(ハイフン無し)
		 * @param principal ログイン情報
		 * @param model
		 * @return 結果画面 - 郵便番号
		 */
		@PostMapping("/task")
		public String getTask(Principal principal, Model model) {
			TaskEntity taskEntity = taskService.getTask(principal.getName());
			System.out.println(taskEntity.getTasklist().get(0).getUser_id());
			model.addAttribute("taskEntity", taskEntity);
			
			return "task/task";
		}
		
		@PostMapping("/task/insert")
		public String addTask(@RequestParam("comment") String comment,
								@RequestParam("limitday") String limitday,
								Principal principal, Model model) {
			Date date = Date.valueOf(limitday);
			TaskEntity taskEntity = (TaskEntity) model.getAttribute("taskEntity");
			taskEntity = taskService.setTask(principal.getName(), comment,date,taskEntity);
			
			model.addAttribute("taskEntity",taskEntity);
			
			
			return "task/task";
			
		}
		@PostMapping("/task/delete")
		public void deleteTask(@RequestParam ("") String id) {
			
		}
}
