package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa22;
import cn.enilu.flash.dao.ma.Maa22Repository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.service.ma.Maa21Service.StatusEnum;

@Service
public class Maa22Service extends BaseService<Maa22,Long,Maa22Repository>{
	
	@Autowired
	private Maa22Repository repository;
	
	
	/**
	 * 作廢資料
	 * @param id
	 */
	public void CancelByMaa22002(Long maa22002) {
		repository.UpdateStatusByMaa22002(maa22002, StatusEnum.VOID.getValue());
	}
	
	/**
	 * 確認資料
	 * @param 合約編號PK
	 */
	public void ConfirmByMaa22002(Long maa22002) {
		repository.UpdateStatusByMaa22002(maa22002, StatusEnum.CONFIRM.getValue());
	}
	
	/**
	 * 取消確認資料，退回新增狀態
	 * @param 合約編號PK
	 */
	public void CancelConfirmByMaa22002(Long maa22002) {
		repository.UpdateStatusByMaa22002(maa22002, StatusEnum.CREATE.getValue());
	}
	
	/**
	 * 合約發出
	 * @param 合約編號PK
	 */
	public void IssueByMaa22002(Long maa22002) {
		repository.UpdateStatusByMaa22002(maa22002, StatusEnum.POISSUE.getValue());
	}
	
	public enum StatusEnum {
		
		CREATE(0),
		CONFIRM(1),
		POISSUE(2),
		StopTrade(3),
		VOID(9);

		private int value;
		
		StatusEnum(int value) {
			this.value = value;
		}
		
		public int getValue() {
	        return value;
	    }
		
	}
}
