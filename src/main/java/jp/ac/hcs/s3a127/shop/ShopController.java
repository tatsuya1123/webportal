package jp.ac.hcs.s3a127.shop;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ShopController {
	@Autowired
	private GourmetService gourmetService;
	/**
	 * ホットペッパーグルメAPIを使って、店名部分一致で店を検索して結果を変えす
	 * @param gourmet 検索ワード
	 * @@param id ページ番号
	 * @param principal ログイン情報
	 * @param model	
	 * @return 結果画面 - 店情報
	 */
	@GetMapping("/gourmet/{id}")
	public String getZipCode(@RequestParam(name="gourmet",required=false) String gourmet,@PathVariable("id") int id,
			Principal principal, Model model) {
				if ((gourmet == null) || ("".equals(gourmet))) {
					gourmet = (String) model.getAttribute(gourmet);
				}
				ShopEntity shopEntity = gourmetService.getShop(gourmet);
				ShopEntity shop = new ShopEntity();
				for(int i = id * 10 - 10; i < id * 10;i++) {
					shop.getResults().add(shopEntity.getResults().get(i));
				}
				model.addAttribute("shopEntity2", shopEntity);
				model.addAttribute("shopEntity", shop);
				model.addAttribute("thisId",id * 10);
				model.addAttribute("gourmet",gourmet);
				log.info("[" + principal.getName() +  "]住所検索:" + gourmet );
				
				return "gourmet/gourmet";
			}
}