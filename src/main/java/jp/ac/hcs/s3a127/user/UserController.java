package jp.ac.hcs.s3a127.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * ログイン画面を表示する
	 * 
	 * @param model
	 * @return ログイン画面
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
}
