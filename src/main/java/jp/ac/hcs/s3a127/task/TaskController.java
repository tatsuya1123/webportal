package jp.ac.hcs.s3a127.task;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.ac.hcs.s3a127.WebConfig;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class TaskController {
		@Autowired
		private TaskService taskService;
		/**
		 * DBでタスクを管理する
		 * @param principal ログイン情報
		 * @param model
		 * @return 結果画面 - タスク情報
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
		public String addTask(@RequestParam(name = "title",required=false) String title,
								@RequestParam(name = "comment",required = false) String comment, 
								@RequestParam(name = "limitday", required = false) String limitday,
								@RequestParam(name = "priority", required = false) String priority,
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
				taskEntity = taskService.setTask(principal.getName(),title,Integer.parseInt(priority), comment,date,taskEntity);
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
			int rownumber = taskService.deleteTask(id,principal.getName());
			String errorMessage = "";
			if (rownumber == 0) {
				errorMessage = "削除できませんでした。";
			}
			else {
				errorMessage = "削除しました";
			}
			TaskEntity taskEntity = taskService.getTask(principal.getName());
			model.addAttribute("taskEntity", taskEntity);
			model.addAttribute("errorMessage",errorMessage);
			return "task/task";
		}

		/**
		 * 自分の全てのタスク情報をCSVファイルとしてダウンロードさせる.
		 * @param principal ログイン情報
		 * @param model
		 * @return タスク情報のCSVファイル
		 */
		@PostMapping("/task/csv")
		public ResponseEntity<byte[]> getTaskCsv(Principal principal, Model model) {

			final String OUTPUT_FULLPATH = WebConfig.OUTPUT_PATH + WebConfig.FILENAME_TASK_CSV;

			log.info("[" + principal.getName() + "]CSVファイル作成:" + OUTPUT_FULLPATH);

			// CSVファイルをサーバ上に作成
			taskService.taskListCsvOut(principal.getName());

			// CSVファイルをサーバから読み込み
			byte[] bytes = null;
			try {
				bytes = taskService.getFile(OUTPUT_FULLPATH);
				log.info("[" + principal.getName() + "]CSVファイル読み込み成功:" + OUTPUT_FULLPATH);
			} catch (IOException e) {
				log.warn("[" + principal.getName() + "]CSVファイル読み込み失敗:" + OUTPUT_FULLPATH);
				e.printStackTrace();
			}

			// CSVファイルのダウンロード用ヘッダー情報設定
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "text/csv; charset=UTF-8");
			header.setContentDispositionFormData("filename", WebConfig.FILENAME_TASK_CSV);

			// CSVファイルを端末へ送信
			return new ResponseEntity<byte[]>(bytes, header, HttpStatus.OK);
		}
}
