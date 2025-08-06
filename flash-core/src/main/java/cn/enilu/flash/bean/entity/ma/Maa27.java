package cn.enilu.flash.bean.entity.ma;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name = "maa27")
@Table(appliesTo = "maa27",comment = "進貨主檔")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Maa27 extends BaseEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8051848021766468983L;

	@Id
	@Column(name = "id")
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
	
	
	@OneToMany(targetEntity = Maa28.class, fetch = FetchType.LAZY)
	@JoinColumn(name= "maa28002", referencedColumnName = "id", updatable = false)
	private List<Maa28> maa28;
	
}
