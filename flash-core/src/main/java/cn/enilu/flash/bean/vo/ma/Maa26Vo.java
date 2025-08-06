package cn.enilu.flash.bean.vo.ma;

import lombok.Data;

@Data
/**
 * Maa26 value object
 *
 * @author YWG
 */
public class Maa26Vo {
	
   
	private Long id;
	private Long maa26002; //叫貨主檔唯一識別碼
	private Long maa26003; //合約明細唯一識別碼
	private double maa26004; //叫貨數量
	private double maa26004H; //叫貨數量
	private String maa26005; //備註
	
	private int maa22003; //項次
	private String maa22006; //合約項目名稱
	private String maa22011; //合約單位
	private double maa22012; //合約數量
	private double missingQty; //未叫貨數量

}
