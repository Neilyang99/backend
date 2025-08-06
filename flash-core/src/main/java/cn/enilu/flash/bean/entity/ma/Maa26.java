package cn.enilu.flash.bean.entity.ma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name = "maa26")
@Table(appliesTo = "maa26",comment = "叫貨明細檔")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Maa26 extends BaseEntity {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2831375655371305818L;

	@Id
	@Column(name = "id")
	private Long id; //資料表唯一識別碼
	
	private Long maa26002; //叫貨主檔唯一識別碼
	private Long maa26003; //合約明細唯一識別碼
	private double maa26004; //叫貨數量
	private String maa26005; //備註
	
}
