package cn.enilu.flash.service.ma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa27;
import cn.enilu.flash.bean.vo.ma.Maa27Vo;
import cn.enilu.flash.bean.vo.ma.Maa28Vo;
import cn.enilu.flash.dao.ma.Maa25Repository;
import cn.enilu.flash.dao.ma.Maa26Repository;
import cn.enilu.flash.dao.ma.Maa27Repository;
import cn.enilu.flash.dao.ma.Maa28Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa27Service extends BaseService<Maa27,Long,Maa27Repository>{
	
	@Autowired
	private Maa27Repository repository;
	
	@Autowired
	private Maa28Repository maa28Dao;
	
	@Autowired
	private Maa25Repository maa25Dao;
	
	@Autowired
	private Maa26Repository maa26Dao;
	
	public List<Maa27Vo> getStructure(Long maa25Id){
		List<Maa27Vo> list = new ArrayList<>();
		
		List<Object[]> maa25Data = maa25Dao.findByDeliveryId(maa25Id);
		List<Object[]> maa26Data = maa26Dao.findByDeliveryId(maa25Id);
		for(Object[] obj : maa25Data) {
			Maa27Vo vo = new Maa27Vo();
			vo.setId(0L);
			vo.setMaa27003(Long.parseLong(""+obj[0]));
			vo.setProjectName(""+obj[1]);
			vo.setVendorName(""+obj[2]);
			vo.setPoNo(""+obj[3]);
			vo.setVendorItems(""+obj[4]);
			vo.setMaa25002(""+obj[5]);
			vo.setMaa27008(0);
			
			List<Maa28Vo> list28 = new ArrayList<>();
			for(Object[] obj2 : maa26Data) {
				Maa28Vo v28 = new Maa28Vo();
				
				v28.setMaa22003(Integer.parseInt(""+obj2[0]));
				v28.setMaa22006(""+obj2[1]);
				v28.setMaa22011(""+obj2[2]);
				v28.setMaa22012(Double.parseDouble(""+obj2[3]));
				v28.setMissingQty(Double.parseDouble(""+obj2[4]));
				v28.setMaa28003(Long.parseLong(""+obj2[5]));
				v28.setMaa26004(Double.parseDouble(""+obj2[6]));
				
				list28.add(v28);
			}
			vo.setMaa28(list28);
			
			list.add(vo);
			
		}
		
		return list;
	}
	
	public List<Maa27Vo> findByDeliveryId(Long maa25Id, String docNo, String formStatus) {
		List<Maa27Vo> list = new ArrayList<>();
		
		List<Object[]> maa28Data = maa28Dao.findByDeliveryId(maa25Id);//取出進貨單明細資料
		
		List<Object[]> listData = null;
		if(formStatus.equals("")) {
			listData = repository.findByDeliveryId(maa25Id,docNo);
		}else {
			List<Integer> statusList = new ArrayList<Integer>();
			String[] aryStatus = formStatus.split(",");
			for(String str : aryStatus) {
				statusList.add(Integer.parseInt(str));
			}
			listData = repository.findByDeliveryId(maa25Id,docNo,statusList);
		}
		for(Object[] obj : listData) {
			Maa27Vo vo = new Maa27Vo();
			vo.setId(Long.parseLong(""+obj[0]));//maa27.id
			vo.setMaa27002(""+obj[1]);
			vo.setMaa27003(Long.parseLong(""+obj[2]));
			vo.setMaa27004(""+obj[3]);
			vo.setMaa27005(""+obj[4]);
			vo.setMaa27006(""+obj[5]);
			vo.setMaa27007(""+obj[6]);
			vo.setMaa27008(Integer.parseInt(""+obj[7]));
			vo.setMaa27009(""+obj[8]);
			vo.setMaa27010(""+obj[9]);
			vo.setMaa27011(""+obj[10]);
			vo.setMaa27012(""+obj[11]);
			vo.setProjectName(""+obj[12]);
			vo.setVendorName(""+obj[13]);
			vo.setPoNo(""+obj[14]);
			vo.setVendorItems(""+obj[15]);
			vo.setMaa25002(""+obj[16]);
			
			List<Maa28Vo> list28 = new ArrayList<>();
			for(Object[] obj2 : maa28Data) {
				if(vo.getId().equals(Long.parseLong(""+obj2[1]))) {//叫貨單Id相同的明細
					Maa28Vo v28 = new Maa28Vo();
					v28.setId(Long.parseLong(""+obj2[0]));
					v28.setMaa28002(Long.parseLong(""+obj2[1]));
					v28.setMaa28003(Long.parseLong(""+obj2[2]));
					v28.setMaa28004(Double.parseDouble(""+obj2[3]));
					v28.setMaa28004H(Double.parseDouble(""+obj2[3]));
					v28.setMaa28005(""+obj2[4]);
					v28.setMaa22003(Integer.parseInt(""+obj2[5]));
					v28.setMaa22006(""+obj2[6]);
					v28.setMaa22011(""+obj2[7]);
					v28.setMaa22012(Double.parseDouble(""+obj2[8]));
					v28.setMissingQty(Double.parseDouble(""+obj2[9]));
					v28.setMaa26004(Double.parseDouble(""+obj2[10]));
					
					list28.add(v28);
				}
			}
			vo.setMaa28(list28);
			
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
		String likeParam = prefix+"-"+year;// A03-2025-000001
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
