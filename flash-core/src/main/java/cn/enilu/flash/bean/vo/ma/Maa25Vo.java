package cn.enilu.flash.bean.vo.ma;

import java.util.List;

import lombok.Data;

@Data
/**
 * Maa25 value object
 *
 * @author YWG
 */
public class Maa25Vo {
	
   
	private Long id;
	private String maa25002; //叫貨單編號
	private Long maa25003; //合約主檔唯一識別碼
	private String maa25004; //單據日期
	private String maa25005; //備註
	private String maa25006; //叫貨人1
	private String maa25007; //叫貨人2
	private int maa25008; //資料狀態
	private String maa25009; //發出者工號
	private String maa25010; //發出日期時間
	private String maa25011; //確認者工號
	private String maa25012; //確認日期時間
	private String maa25013; //送貨地址
	private String maa25014; //聯絡人
	private String maa25015; //連絡電話
	private String maa25016; //預計進貨日期
	
	private String projectName;//工程案
	private String vendorName;//廠商
	private String vendorItems;//主要工程項目
	private String poNo;//合約編號
	private String deliveryAddress;//工程案預設送貨地址
	
	private String maa26String;
	
	private List<Maa26Vo> maa26;

}
