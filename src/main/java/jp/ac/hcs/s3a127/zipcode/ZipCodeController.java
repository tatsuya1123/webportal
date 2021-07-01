package jp.ac.hcs.s3a127.zipcode;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ZipCodeController {
	@Autowired
	private ZipCodeService zipCodeService;
	/**
	 * 郵便番号から住所を検索し、結果画面を表示する
	 * @param zipcode 検索する郵便番号(ハイフン無し)
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 郵便番号
	 */
	@PostMapping("/zip")
	public String getZipCode(@RequestParam("zipcode") String zipcode,
			Principal principal, Model model) {
		
				if ((zipcode == null) || ("".equals(zipcode))) {
					return "index";
				}
				ZipCodeEntity zipCodeEntity = zipCodeService.getZip(zipcode);
				model.addAttribute("zipCodeEntity", zipCodeEntity);
				log.info("[" + principal.getName() +  "]住所検索:" + zipcode );
				
				return "zipcode/zipcode";
			}
}
