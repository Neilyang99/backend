package cn.enilu.flash.bean.vo.ma;

import lombok.Data;

@Data
/**
 * Maa28 value object
 *
 * @author YWG
 */
public class Maa28Vo {
	
   
	private Long id;
	private Long maa28002; //進貨主檔唯一識別碼
	private Long maa28003; //工地叫貨明細唯一識別碼
	private double maa28004; //進貨數量
	private double maa28004H; //進貨數量
	private String maa28005; //備註
	
	private Long maa22Id; //合約明細id
	private int maa22003; //項次
	private String maa22006; //合約項目名稱
	private String maa22011; //合約單位
	private double maa22012; //合約數量
	private double maa26004; //叫貨數量
	private double missingQty; //未進貨數量

}
