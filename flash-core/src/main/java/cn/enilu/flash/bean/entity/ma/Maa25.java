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

@Entity(name = "maa25")
@Table(appliesTo = "maa25",comment = "叫貨主檔")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Maa25 extends BaseEntity {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2682008004129454725L;

	@Id
	@Column(name = "id")
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
	
	
	@OneToMany(targetEntity = Maa26.class, fetch = FetchType.LAZY)
	@JoinColumn(name= "maa26002", referencedColumnName = "id", updatable = false)
	private List<Maa26> maa26;
	
}
