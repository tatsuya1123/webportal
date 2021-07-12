package jp.ac.hcs.s3a127.shop;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class GourmetService {

	@Autowired
	RestTemplate restTemplate;

	/** グルメAPI リクエストURL */
	private static final String URL =
			"http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key={API_KEY}&name={shopname}&large_service_area={large_service_area}&format=json&count=50";

	/**
	 * 検索ワードに紐づくグルメ情報を取得する。
	 * 
	 * @param  gourme 検索ワード
	 * @return ShopEntity
	 */
	public ShopEntity getShop(String gourme) {
		// APIへアクセスして、結果を取得
		String apikey = "3043a5dd2f7b7915";
		String large_service_area = "SS40";
		String json = restTemplate.getForObject(URL, String.class, apikey,gourme,large_service_area);
		// エンティティクラスを生成
		ShopEntity shopEntity = new ShopEntity();

		// jsonクラスへの変換失敗のため、例外処理
		try {
			// 変換クラスを生成し、文字列からjsonクラスへ変換する(例外の可能性あり)
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			node = node.get("results");
			System.out.println(node);
			for (JsonNode result : node.get("shop")) {
				//データのクラスの作成(result1件分)
				ShopData shopData = new ShopData();
				shopData.setAccess(result.get("access").asText());
				shopData.setAddress(result.get("address").asText());
				shopData.setUrl(result.get("urls").get("pc").asText());
				shopData.setLogo_image(result.get("photo").get("mobile").get("l").asText());
				shopData.setName(result.get("name").asText());
				//可変長配列の末尾に追加
				shopEntity.getResults().add(shopData);
			}	
			for (int i = 0; i < shopEntity.getResults().size() / 10; i++) {
				shopEntity.getCnts().add(i + 1);
			}

		} catch (IOException e) {
			// 例外発生時は、エラーメッセージの詳細を標準エラー出力
			e.printStackTrace();
		}

		return shopEntity;
	}
}