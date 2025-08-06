package cn.enilu.flash.service.ma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa25;
import cn.enilu.flash.bean.vo.ma.Maa25Vo;
import cn.enilu.flash.bean.vo.ma.Maa26Vo;
import cn.enilu.flash.dao.ma.Maa21Repository;
import cn.enilu.flash.dao.ma.Maa22Repository;
import cn.enilu.flash.dao.ma.Maa25Repository;
import cn.enilu.flash.dao.ma.Maa26Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa25Service extends BaseService<Maa25,Long,Maa25Repository>{
	
	@Autowired
	private Maa25Repository repository;
	
	@Autowired
	private Maa26Repository maa26Dao;
	
	@Autowired
	private Maa21Repository maa21Dao;
	
	@Autowired
	private Maa22Repository maa22Dao;
	
	public List<Maa25Vo> getPOStructure(Long poId){
		List<Maa25Vo> list = new ArrayList<>();
		
		List<Object[]> maa21Data = maa21Dao.findByPOId(poId);
		List<Object[]> maa22Data = maa22Dao.findByPOId(poId);
		for(Object[] obj : maa21Data) {
			Maa25Vo vo = new Maa25Vo();
			vo.setId(0L);
			vo.setMaa25003(Long.parseLong(""+obj[0]));
			vo.setProjectName(""+obj[1]);
			vo.setVendorName(""+obj[2]);
			vo.setPoNo(""+obj[3]);
			vo.setVendorItems(""+obj[4]);
			vo.setDeliveryAddress(""+obj[5]);
			vo.setMaa25008(0);
			
			List<Maa26Vo> list26 = new ArrayList<>();
			for(Object[] obj2 : maa22Data) {
				Maa26Vo v26 = new Maa26Vo();
				
				v26.setMaa22003(Integer.parseInt(""+obj2[0]));
				v26.setMaa22006(""+obj2[1]);
				v26.setMaa22011(""+obj2[2]);
				v26.setMaa22012(Double.parseDouble(""+obj2[3]));
				v26.setMissingQty(Double.parseDouble(""+obj2[4]));
				v26.setMaa26003(Long.parseLong(""+obj2[5]));
				
				list26.add(v26);
			}
			vo.setMaa26(list26);
			
			list.add(vo);
			
		}
		
		return list;
	}
	
	public List<Maa25Vo> findByPOId(Long poId, String docNo, String formStatus) {
		List<Maa25Vo> list = new ArrayList<>();
		
		List<Object[]> maa26Data = maa26Dao.findByPOId(poId);//取出合約ID的所有叫貨單明細資料
		
		List<Object[]> listData = null;
		if(formStatus.equals("")) {
			listData = repository.findByPOId(poId,docNo);//取出合約ID的所有叫貨單資料
		}else {
			List<Integer> statusList = new ArrayList<Integer>();
			String[] aryStatus = formStatus.split(",");
			for(String str : aryStatus) {
				statusList.add(Integer.parseInt(str));
			}
			
			listData = repository.findByPOId(poId,docNo,statusList);//取出合約ID的叫貨單資料且狀態需要篩選
		}
		for(Object[] obj : listData) {
			Maa25Vo vo = new Maa25Vo();
			vo.setId(Long.parseLong(""+obj[0]));
			vo.setMaa25002(""+obj[1]);
			vo.setMaa25003(Long.parseLong(""+obj[2]));
			vo.setMaa25004(""+obj[3]);
			vo.setMaa25005(""+obj[4]);
			vo.setMaa25006(""+obj[5]);
			vo.setMaa25007(""+obj[6]);
			vo.setMaa25008(Integer.parseInt(""+obj[7]));
			vo.setMaa25009(""+obj[8]);
			vo.setMaa25010(""+obj[9]);
			vo.setMaa25011(""+obj[10]);
			vo.setMaa25012(""+obj[11]);
			vo.setProjectName(""+obj[12]);
			vo.setVendorName(""+obj[13]);
			vo.setPoNo(""+obj[14]);
			vo.setVendorItems(""+obj[15]);
			
			vo.setMaa25013(""+obj[16]);
			vo.setMaa25014(""+obj[17]);
			vo.setMaa25015(""+obj[18]);
			vo.setMaa25016(""+obj[19]);
			
			List<Maa26Vo> list26 = new ArrayList<>();
			for(Object[] obj2 : maa26Data) {
				if(vo.getId().equals(Long.parseLong(""+obj2[1]))) {//叫貨單Id相同的明細
					Maa26Vo v26 = new Maa26Vo();
					v26.setId(Long.parseLong(""+obj2[0]));
					v26.setMaa26002(Long.parseLong(""+obj2[1]));
					v26.setMaa26003(Long.parseLong(""+obj2[2]));
					v26.setMaa26004(Double.parseDouble(""+obj2[3]));
					v26.setMaa26004H(Double.parseDouble(""+obj2[3]));
					v26.setMaa26005(""+obj2[4]);
					v26.setMaa22003(Integer.parseInt(""+obj2[5]));
					v26.setMaa22006(""+obj2[6]);
					v26.setMaa22011(""+obj2[7]);
					v26.setMaa22012(Double.parseDouble(""+obj2[8]));
					v26.setMissingQty(Double.parseDouble(""+obj2[9]));
					
					list26.add(v26);
				}
			}
			vo.setMaa26(list26);
			
			list.add(vo);
		}
		
		return list;
	}
	
	/**
	 * 取出最大的單據編號
	 * @param prefix
	 * @param year
	 * @return
	 */
	public String getMaxSerialNumber(String prefix, String year) {
		String likeParam = prefix+"-"+year;// A02-2025-000001
		return repository.getMaxSerialNumber(likeParam);
	}
	
	/**
	 * 取得最新的單據編號
	 * @param prefix
	 * @param secParam
	 * @return
	 */
	public String gengerateDocNo(String prefix, String year) {
		String poNo = "";
		String formattedString = "";
		
		String maxNO = this.getMaxSerialNumber(prefix, year);
		if(maxNO.equals(null)) {
			formattedString = String.format("%05d", 1);//固定5為流水碼
			poNo = prefix + "-" + year + "-" + formattedString;
		}else {
			int number = 0;
			String[] aryNumber = maxNO.split("-");
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
	 * 發出
	 * @param id
	 */
	public void IssueById(Long id) {
		repository.UpdateStatusById(id, StatusEnum.ISSUE.getValue());
	}
	
	public enum StatusEnum {
		
		CREATE(0),
		CONFIRM(1),
		ISSUE(2),
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
