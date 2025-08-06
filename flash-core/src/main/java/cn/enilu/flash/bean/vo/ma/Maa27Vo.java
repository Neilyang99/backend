package cn.enilu.flash.bean.vo.ma;

import java.util.List;

import lombok.Data;

@Data
/**
 * Maa27 value object
 *
 * @author YWG
 */
public class Maa27Vo {
	
   
	private Long id;
	private String maa27002; //進貨單編號
	private Long maa27003; //叫貨主檔唯一識別碼
	private String maa27004; //單據日期
	private String maa27005; //備註
	private String maa27006; //進貨人1
	private String maa27007; //進貨人2
	private int maa27008; //資料狀態
	private String maa27009; //發出者工號
	private String maa27010; //發出日期時間
	private String maa27011; //確認者工號
	private String maa27012; //確認日期時間
	
	private String projectName;//工程案
	private String vendorName;//廠商
	private String vendorItems;//主要工程項目
	private String poNo;//合約編號
	private String maa25002;//叫貨單編號
	
	private String maa28String;
	
	private List<Maa28Vo> maa28;

}
