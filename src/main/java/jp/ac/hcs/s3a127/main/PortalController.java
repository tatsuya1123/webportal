package jp.ac.hcs.s3a127.main;

import org.springframework.web.bind.annotation.RequestMapping;

public class PortalController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
