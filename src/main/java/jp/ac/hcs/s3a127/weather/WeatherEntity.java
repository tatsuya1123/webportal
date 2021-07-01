package jp.ac.hcs.s3a127.weather;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 1件分の天気予報
 * 各項目のデータ使用について、APIの使用を参照する
 * -https://weather/tsdukumijima.net/
 *
 */
@Data
public class WeatherEntity {

		/**タイトル*/
		private String title;
		
		/**説明文*/
		private String description;
		
		/**天気情報のリスト */
		private List<WeatherData> forecasts = new ArrayList<WeatherData>();
}
