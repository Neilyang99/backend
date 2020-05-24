package cn.enilu.flash.api.controller.sales;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.entity.sales.Visitor;
import cn.enilu.flash.bean.enumeration.Permission;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.service.sales.VisitorService;


@RestController
@RequestMapping("/visitor")
public class VisitorController  extends BaseController {
	@Autowired
    private VisitorService visitorService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
    //@RequiresPermissions(value = {Permission.VISITOR})
	public Object list(String name) {
		if(StringUtil.isNullOrEmpty(name)) {
			return Rets.success(visitorService.queryAll());
		}else {
			return Rets.success(visitorService.findBySla10006(name));
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Visitor visitor) {
		if(visitor.getId() == null) {
			visitorService.insert(visitor);
		}else {
			
			visitorService.update(visitor);
		}
		
		return Rets.success();
	}
}