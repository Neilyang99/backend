package cn.enilu.flash.bean.entity.ma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name = "maa28")
@Table(appliesTo = "maa28",comment = "進貨明細檔")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Maa28 extends BaseEntity {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6183136555439188230L;

	@Id
	@Column(name = "id")
	private Long id; //資料表唯一識別碼
	
	private Long maa28002; //進貨主檔唯一識別碼
	private Long maa28003; //工地叫貨明細唯一識別碼
	private double maa28004; //進貨數量
	private String maa28005; //備註
}
