package jp.ac.hcs.s3a127.shop;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class ShopEntity {
	private String count;
	
	private int thisId;
	
	private List<Integer> cnts = new ArrayList<Integer>();
	/*Dataのリスト*/
	private List<ShopData> results = new ArrayList<ShopData>();
}
