package cn.enilu.flash.bean.entity.ma;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name = "maa33")
@Table(appliesTo = "maa33",comment = "廠商扣款資料檔")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Maa33 extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9207167348782922674L;
	
	
	private String maa33002; //扣款編號
	private String maa33003; //扣款日期
	private Long maa33004; //合約主檔唯一識別碼
	private Long maa33005; //工程案唯一識別碼
	private String maa33006; //合約編號
	private Long maa33007; //承包商唯一識別碼
	private Long maa33008; //合約明細唯一識別碼
	private String maa33009; //合約項目名稱
	private Long maa33010; //出工廠商唯一識別碼
	private Long maa33011; //出工合約明細唯一識別碼
	private String maa33012; //出工合約項目名稱
	private int maa33013; //扣款金額
	private Long maa33014; //請款單唯一識別碼
	private String maa33015; //扣款原因
	private int maa33016; //資料狀態
	private String maa33017; //備註


	
}
