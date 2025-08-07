package cn.enilu.flash.api.controller.ma;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.ma.Maa33;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.ma.Maa33Service;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;


@RestController
@RequestMapping("/maa33")
public class Maa33Controller extends BaseController{

	@Autowired
    private Maa33Service objService;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list( @RequestParam(required = false) Long maa21ID) {
		Page<Maa33> page = new PageFactory<Maa33>().defaultPage();
		
		page.addFilter( "maa33004", SearchFilter.Operator.EQ, maa21ID);//合約ID
		
		page = objService.queryPage(page);
		List list = BeanUtil.objectsToMaps(page.getRecords());
        page.setRecords(list);
        
		return Rets.success(page);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Maa33 obj) {
		if(obj.getId() == null) {
			objService.insert(obj);
		}else {
			objService.update(obj);
		}
		
		return Rets.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
    public Object remove(Long id) {
		objService.delete(id);
        return Rets.success();
    }
	
	
}
