package jp.ac.hcs.s3a127.task;

import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			model.addAttribute("taskEntity", taskEntity);
			String errorMessage = "";
			model.addAttribute("errorMessage",errorMessage);
			return "task/task";
		}
		
		@PostMapping("/task/insert")
		public String addTask(@RequestParam(name = "comment",required = false) String comment, 
								@RequestParam(name = "limitday", required = false) String limitday,
								Principal principal, Model model) {
			if (comment == null || comment == "" || comment.length() > 50) {
				String errorMessage = "入力が正しくありません";
				model.addAttribute("errorMessage",errorMessage);
				TaskEntity taskEntity = taskService.getTask(principal.getName());
				model.addAttribute("taskEntity" , taskEntity);
				return "task/task";
			}
			try {
				Date date = Date.valueOf(limitday);
				TaskEntity taskEntity = (TaskEntity) model.getAttribute("taskEntity");
				taskEntity = taskService.setTask(principal.getName(), comment,date,taskEntity);
				model.addAttribute("taskEntity",taskEntity);
				String errorMessage = "追加しました";
				model.addAttribute("errorMessage",errorMessage);
				return "task/task";
				
			}catch (IllegalArgumentException e){
				String errorMessage = "入力が正しくありません";
				model.addAttribute("errorMessage",errorMessage);
				TaskEntity taskEntity = taskService.getTask(principal.getName());
				model.addAttribute("taskEntity" , taskEntity);
				return "task/task";
			}
			
			
			
			
			
		}
		@GetMapping("/task/delete/{id}")
		public String deleteTask(@PathVariable("id") int id , Principal principal, Model model){
			TaskEntity taskEntity = taskService.deleteTask(id,principal.getName());
			model.addAttribute("taskEntity", taskEntity);
			String errorMessage = "削除しました";
			model.addAttribute("errorMessage",errorMessage);
			return "task/task";
		}
}
