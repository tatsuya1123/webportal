package jp.ac.hcs.s3a127.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String getLogin(Model model) {
		UserEntity userEntity = userService.selectAll();
		model.addAttribute("userEntity", userEntity);
		return "user/userList";
	}
}
