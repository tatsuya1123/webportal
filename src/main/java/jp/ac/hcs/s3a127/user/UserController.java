package jp.ac.hcs.s3a127.user;

import java.security.Principal;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	private final String emailRegex = "^(.+)@(.+)$";

	/**
	 * ユーザーリストを表示する
	 * 
	 * @param model
	 * @return ユーザーリスト画面
	 */
	@GetMapping("/user/list")
	public String getUserList(Model model) {
		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);
		return "user/userList";
	}
	/**
	 * ユーザ登録画面（管理者用）を表示する
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用)
	 */
	@GetMapping("/user/insert")
	public String getUserInsert(UserForm form, Model model) {
		return "user/insert";
	}
	/**
	 * 1件分のユーザ情報をデータベースに登録する
	 * @param form 登録するユーザ情報（パスワードは平分)
	 * @param bindingResult データバインド実施結果
	 * @param model
	 * @return ユーザー一覧画面
	 */
	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		//入力チェックに引っかかった場合、前の画面に戻る
		if (bindingResult.hasErrors()) {
			return getUserInsert(form,model);
		}
		
		UserData userData = new UserData();
		userData.setUser_id(form.getUser_id());
		userData.setPassword(form.getPassword());
		userData.setDarkmode(false);
		userData.setEnabled(true);
		userData.setUser_name(form.getUser_name());
		userData.setRole(Role.nameOf(form.getRole()));
	
		
		int rownumber = userService.insertOne(userData);
		return getUserList(model);
	}
	@GetMapping("/user/detail/{id}")
	public String getUserDetail(@PathVariable("id") String user_id, Principal principal, Model model) {
		if("".equals(user_id)) {
			return "index";
		}
		
		if(!Pattern.matches(emailRegex, user_id)) {
			String errorMessage = "不正な入力です";
			model.addAttribute("errorMessage",errorMessage);
			System.out.println("bbbbb");
			return getUserList(model);
		}
		
		UserData data = userService.selectOne(user_id);
		if(!(data instanceof Object)) {
			System.out.println("aaaaaa");
			String errorMessage = "不正な入力です。";
			model.addAttribute("errorMessage",errorMessage);
			return getUserList(model);
		}
		log.info("[" + data.getUser_id() + "] 詳細画面へ");
		model.addAttribute("errorMessage"," ");
		model.addAttribute("userData",data);
		return "user/detail";
	}
	@PostMapping("/user/update")
	public String updateUserDetail(@ModelAttribute @Validated UserData data,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
				int rownumber = userService.updateOne(data);
				return getUserList(model);
	}
	@PostMapping("/user/delete")
	public String deleteUserDetail(@RequestParam("user_id") String user_id,
			Principal principal,
			Model model) {
				int rownumber = userService.deleteOne(user_id);
				if (rownumber == 0) {
					String errorMessage = "不正な入力です。";
					model.addAttribute("errorMessage",errorMessage);
				}
				return getUserList(model);
	}
}
