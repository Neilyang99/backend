package cn.enilu.flash.api.controller.ma;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.ma.Maa92;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.ma.Maa92Service;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;


@RestController
@RequestMapping("/maa92")
public class Maa92Controller extends BaseController{

	@Autowired
    private Maa92Service maa92Service;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String name,
					   @RequestParam(required = false) String firstId,
					   @RequestParam(required = false) String secId) {
		Page<Maa92> page = new PageFactory<Maa92>().defaultPage();
		if(!StringUtil.isNullOrEmpty(name)) {
			page.addFilter( "maa92007", SearchFilter.Operator.LIKE, name);
		}
		if(!StringUtil.isNullOrEmpty(firstId)) {
			page.addFilter( "maa92002", SearchFilter.Operator.EQ, firstId);
		}
		if(!StringUtil.isNullOrEmpty(secId)) {
			page.addFilter( "maa92003", SearchFilter.Operator.EQ, secId);
		}
		page.setSort(Sort.by(Sort.Direction.ASC,"maa92012"));
		page = maa92Service.queryPage(page);
		List list = BeanUtil.objectsToMaps(page.getRecords());
        page.setRecords(list);
        
		return Rets.success(page);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Maa92 maObj) {
		if(maObj.getId() == null) {
			maa92Service.insert(maObj);
		}else {
			maa92Service.update(maObj);
		}
		
		return Rets.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
    public Object remove(Long id) {
		maa92Service.delete(id);
        return Rets.success();
    }
	
	/**
	 * 取出大分類與小分類資料
	 * @return
	 */
	@RequestMapping(value = "/selectBudget",method = RequestMethod.GET)
	public Object selectBudget() {
		return Rets.success(maa92Service.queryBudgetItem());
	}
	
	/**
	 * 根據小分類ID取出項目資料
	 * @return
	 */
	@RequestMapping(value = "/selectItemByLv2",method = RequestMethod.GET)
	public Object selectItemByLv2(Long lv2Id) {
		return Rets.success(maa92Service.queryByLv2Id(lv2Id));
	}
}
