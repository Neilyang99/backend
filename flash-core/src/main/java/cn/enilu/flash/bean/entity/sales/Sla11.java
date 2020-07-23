package cn.enilu.flash.bean.entity.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.enilu.flash.bean.entity.BaseEntity;
import lombok.Data;

@Entity(name = "sla11")
@Table(appliesTo = "sla11",comment = "客戶洽詢記錄資料")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Sla11 extends BaseEntity {
	
	@Column
	private String sla11002;
	@Column
	private String sla11003;
	@Column
	private String sla11004;
	@Column
	private String sla11005;
	@Column
	private String sla11006;
	@Column
	private String sla11007;
	@Column
	private String sla11008;
	@Column
	private String sla11009;
}