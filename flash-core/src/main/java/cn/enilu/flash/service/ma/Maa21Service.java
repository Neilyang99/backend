package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa21;
import cn.enilu.flash.dao.ma.Maa21Repository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.utils.DateUtil;

@Service
public class Maa21Service extends BaseService<Maa21,Long,Maa21Repository>{
	
	@Autowired
	private Maa21Repository repository;
	
	/**
	 * 取出最大的合約編號
	 * @param prefix
	 * @param year
	 * @return
	 */
	public String getMaxPONo(String prefix, String year) {
		String likeParam = prefix+"-"+year;// A01-2025-000001
		return repository.getMaxPONo(likeParam);
	}
	
	/**
	 * 取得最新的合約編號
	 * @param prefix
	 * @param secParam
	 * @return
	 */
	public String gengeratePONO(String prefix, String year) {
		String poNo = "";
		String formattedString = "";
		
		String maxPONO = this.getMaxPONo(prefix, year);
		if(maxPONO.equals(null)) {
			formattedString = String.format("%05d", 1);//固定5為流水碼
			poNo = prefix + "-" + year + "-" + formattedString;
		}else {
			int number = 0;
			String[] aryNumber = maxPONO.split("-");
			if(aryNumber.length == 3) {
				number = Integer.parseInt(aryNumber[2]);
			}
			number++;
			formattedString = String.format("%05d", number);//固定5為流水碼
			poNo = prefix + "-" + year + "-" + formattedString;
		}
		
		
		return poNo;
	}
	
	/**
	 * 作廢資料
	 * @param id
	 */
	public void CancelById(Long id) {
		repository.UpdateStatusById(id, StatusEnum.VOID.getValue());
	}
	
	/**
	 * 確認資料
	 * @param id
	 */
	public void ConfirmById(Long id) {
		repository.UpdateStatusById(id, StatusEnum.CONFIRM.getValue());
	}
	
	/**
	 * 取消確認，退回新增狀態
	 * @param id
	 */
	public void CancelConfirmById(Long id) {
		repository.UpdateStatusById(id, StatusEnum.CREATE.getValue());
	}
	
	/**
	 * PO發出
	 * @param id
	 */
	public void IssueById(Long id) {
		repository.UpdateStatusById(id, StatusEnum.POISSUE.getValue());
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
