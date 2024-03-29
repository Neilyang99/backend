package cn.enilu.flash.service.ma;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa92;
import cn.enilu.flash.bean.vo.ma.BudgetItemVo;
import cn.enilu.flash.bean.vo.sales.SalesVo;
import cn.enilu.flash.dao.ma.Maa92Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa92Service extends BaseService<Maa92,Long,Maa92Repository>{
	
	@Autowired
	private Maa92Repository maa92Dao;
	
	
	public List<BudgetItemVo> queryBudgetItem(){
		
		List<BudgetItemVo> list = new ArrayList<BudgetItemVo>();
		
		List<Object[]> objs = maa92Dao.queryBudgetItem();
		for(Object[] ary : objs) {
			BudgetItemVo vo = new BudgetItemVo();
			vo.setFirstId(""+ary[0]);//第一階ID
			vo.setFirstName(""+ary[1]);//第一階Name
			vo.setSecId(""+ary[2]);//第2階ID
			vo.setSecName(""+ary[3]);//第一階Name
			
			list.add(vo);
		}
		
		return list;
	}
	
	/**
	 * 利用小分類的ID取得所有的項目名稱
	 * @param lv2
	 * @return
	 */
	public List<BudgetItemVo> queryByLv2Id(Long lv2){
		List<BudgetItemVo> list = new ArrayList<BudgetItemVo>();
		List<Object[]> objs = maa92Dao.findByMaa92003(lv2);
		
		for(Object[] ary : objs) {
			BudgetItemVo vo = new BudgetItemVo();
			vo.setFirstId(""+ary[0]);//第一階ID
			vo.setFirstName(""+ary[1]);//第一階Name
			vo.setSecId(""+ary[2]);//第2階ID
			vo.setSecName(""+ary[3]);//第一階Name
			vo.setItemId(""+ary[4]);//第3階ID
			vo.setItemName(""+ary[5]);//第3階Name
			
			list.add(vo);
		}
		
		return list;
	}
}
