package jp.ac.hcs.s3a127.weather;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	/**
	 * 待ち番号から天気を検索し、結果画面を表示する
	 * @param cityCode 街番号
	 * @param principal ログイン情報
	 * @param model
	 * @return 結果画面 - 天気
	 */
	@GetMapping("/weather")
	public String getWeather(@RequestParam("citycode") String cityCode, 
			Principal principal, Model model) {	
		if ((cityCode == null) || ("".equals(cityCode))) {
			return "index";
		}
		WeatherEntity weatherEntity = weatherService.getWeather(cityCode);
		model.addAttribute("weatherEntity", weatherEntity);
		log.info("[" + principal.getName() +  "天気検索:" + cityCode );
				
		return "weather/weather";
		}
}

