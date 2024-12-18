package cn.enilu.flash.service.ma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa01a;
import cn.enilu.flash.bean.vo.ma.BudgetExcelVo;
import cn.enilu.flash.dao.ma.Maa01aRepository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa01aService extends BaseService<Maa01a,Long,Maa01aRepository>{
	
	@Autowired
	private Maa01aRepository repository;
	
	public List<Maa01a> findByMaa01a002(Long projectId){
		return repository.findByMaa01a002(projectId);
	}
	
	/**
	 * 根據工程案與小類別ID取出所有資料
	 * @param projectId
	 * @param lv2
	 * @return
	 */
	public List<Maa01a> findByMaa01a002And004(Long projectId, Long lv2){
		return repository.findByMaa01a002AndMaa01a004(projectId,lv2);
	}
	
	public List<BudgetExcelVo> exportToXlsx(Long projectId){
		//TODO: 要加入資料狀態
		ArrayList<BudgetExcelVo> list = new ArrayList<BudgetExcelVo>();
		
		List objList = repository.exportToExcel(projectId);
		for (int i = 0; i < objList.size(); i++) {
            Object[] arr = (Object[]) objList.get(i);
            BudgetExcelVo vo = new BudgetExcelVo();
            vo.setMaa01a009(arr[7].toString());
        	vo.setMaa01a010(arr[0].toString());
        	vo.setMaa01a011(arr[1].toString());
        	if(arr[2] == null) {
        		vo.setMaa01a013("");
        	}else {
        		vo.setMaa01a013(arr[2].toString());
        	}
        	if(arr[3] == null) {
        		vo.setMaa01a014("");
        	}else {
        		vo.setMaa01a014(arr[3].toString());
        	}
        	if(arr[4] == null) {
        		vo.setMaa01a015(0);
        	}else {
        		vo.setMaa01a015(Double.parseDouble(arr[4].toString()));
        	}
			if(arr[5] == null) {
				vo.setMaa01a016(0);
			}else {
				vo.setMaa01a016(Double.parseDouble(arr[5].toString()));
			}
			if(arr[6] == null) {
				vo.setMaa01a017(0);
			}else {
				vo.setMaa01a017(Double.parseDouble(arr[6].toString()));
			}
			if(arr[8] == null) {
        		vo.setMaa01a018("");
        	}else {
        		vo.setMaa01a018(arr[8].toString());
        	}
        	
        	list.add(vo);
		}
		return list;
	}
	
	//工程案是否有預算項目資料
	public int checkByMaa01a002(Long projectId){
		return repository.checkfindByMaa01a002(projectId);
	}
	
	//新工程案從預算項目(maa92)新增該工程的預算項目
	public int insertByNewProject(Long projectId, String buildTypeList) {
		int cnt = 0;
		if(buildTypeList.trim().equals("")) {
			//全部的營建分類都要產生
			cnt = repository.insertByNewProject(projectId);
		}else {
			//部分的營建分類要產生
			String[] aryStr =buildTypeList.trim().split(",");
			List<String> list = new ArrayList<String>();
			for(String v : aryStr){
				list.add(v);
			}
			cnt = repository.insertByNewProject(projectId, list);
		}
		return cnt;
	}
	
	/**
	 * 根據工程案更新預算金額(整批產生預算時使用)
	 * @param Project Id
	 * @return
	 */
	public int updateBudgeAmountByProject(Long projectId) {
		return repository.updateBudgeAmountByProject(projectId);
	}
	
	/**
	 * 根據工程案與預算項目更新預算金額
	 * @param Project Id
	 * @param item Id
	 * @return
	 */
	public int updateAmountByProjectAndItem(Long projectId, Long itemId) {
		return repository.updateAmountByProjectAndItem(projectId, itemId);
	}
	
	/**
	 * 更新資料狀態
	 * @param 工程案ID
	 * @param 資料狀態
	 * @return
	 */
	public int updateBudgeConfirmByProject(Long projectId, String status) {
		return repository.updateBudgeConfirmByProject(projectId, status);
	}
	
	/**
	 * Maa01刪除時，同步刪除maa01a資料
	 * @param projectId
	 * @param lv1
	 * @param lv2
	 * @return
	 */
	public int delByMaa01(Long projectId, Long lv1, Long lv2) {
		return repository.deleteByMaa01(projectId,lv1,lv2);
	}
	
	/**
	 * 刪除所有工程案資料
	 * @param projectId
	 * @return
	 */
	public int deleteByMaa01a002(Long projectId) {
		return repository.deleteByMaa01a002(projectId);
	}
	
	/**
	 * 更新是否有施工項目資料
	 * @param itemId
	 * @return
	 */
	public int updateWorkItemFlag(Long projectId, Long itemId) {
		return repository.updateWorkItemFlag(projectId, itemId);
	}
}

