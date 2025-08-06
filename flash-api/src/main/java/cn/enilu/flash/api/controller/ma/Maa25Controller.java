package cn.enilu.flash.api.controller.ma;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.constant.factory.PageFactory;
import cn.enilu.flash.bean.entity.ma.Maa25;
import cn.enilu.flash.bean.entity.ma.Maa26;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.ma.Maa25Vo;
import cn.enilu.flash.bean.vo.ma.Maa26Vo;
import cn.enilu.flash.bean.vo.ma.MaaVo;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.ma.Maa25Service;
import cn.enilu.flash.service.ma.Maa25Service.StatusEnum;
import cn.enilu.flash.service.ma.Maa26Service;
import cn.enilu.flash.service.ma.Maa93Service;
import cn.enilu.flash.service.system.DeptService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;
import cn.enilu.flash.warpper.Maa21Wrapper;


@RestController
@RequestMapping("/maa25")
public class Maa25Controller extends BaseController{

	@Autowired
    private Maa25Service maa25Service;
	
	@Autowired
    private Maa26Service maa26Service;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String selMaa25002,String selMaa25003) {
        
		
		Page<Maa25> page = new PageFactory<Maa25>().defaultPage();
		
		if(StringUtil.isNullOrEmpty(selMaa25002)) {
			
		}else {
			page.addFilter( "maa25002", SearchFilter.Operator.LIKE, selMaa25002);
		}
		if(!StringUtil.isNullOrEmpty(selMaa25003)) {
			
			page.addFilter( "maa25003", SearchFilter.Operator.EQ, selMaa25003);
		}
		
		page.setSort(Sort.by(Sort.Direction.DESC,"maa25002"));//叫貨單號
		page = maa25Service.queryPage(page);
		
		List list = BeanUtil.objectsToMaps(page.getRecords());
		
        page.setRecords(list);
		
        return Rets.success(page);
	}
	
	@RequestMapping(value = "/list2",method = RequestMethod.GET)
	public Object list2(@RequestParam(required = false) String selMaa25002,Long selMaa25003, String selMaa25008) {
		
		List<Maa25Vo> list = maa25Service.findByPOId(selMaa25003,selMaa25002, selMaa25008);
		
		return Rets.success(list);
	}
	
	@RequestMapping(value = "/getPOStructure",method = RequestMethod.GET)
	public Object getPOStructure(@RequestParam(required = false) Long poId) {
		
		List<Maa25Vo> list = maa25Service.getPOStructure(poId);
		
		return Rets.success(list);
	}
	
	@Transactional
	@RequestMapping(value = "/save",method = RequestMethod.GET)
	public Object save(@ModelAttribute @Valid Maa25Vo maObj) {
		
		try {
			
			Maa25 maa25After = null;
			
			Maa25 maa25 = new Maa25();
			maa25.setId(maObj.getId());
			maa25.setMaa25002(maObj.getMaa25002());
			maa25.setMaa25003(maObj.getMaa25003());
			maa25.setMaa25004(maObj.getMaa25004());
			maa25.setMaa25005(maObj.getMaa25005());
			maa25.setMaa25013(maObj.getMaa25013());
			maa25.setMaa25014(maObj.getMaa25014());
			maa25.setMaa25015(maObj.getMaa25015());
			if(!maObj.getMaa25016().equals("") && !maObj.getMaa25016().equals("null")) {
				maa25.setMaa25016(maObj.getMaa25016());
			}
			
			
			if(maObj.getId() == null || maObj.getId() == 0) {
				String newpoNo = maa25Service.gengerateDocNo("A02", DateUtil.getYear());
				maa25.setMaa25002(newpoNo);
				
				maa25After = maa25Service.insert(maa25);
			}else {
				maa25After= maa25Service.update(maa25);
			}
			
			
			//前端有encode: 中文字處理 --> 特殊符號處理
			String arr = new String(Base64.getDecoder().decode(maObj.getMaa26String()));
			String decodeStr = URLDecoder.decode(arr,"UTF-8");
			
			decodeStr = "["+decodeStr+"]";
			//JSON string 轉成 object
			List<Maa26Vo> maa26List = JsonUtil.fromJsonAsList(Maa26Vo.class, decodeStr);
			
			for (Maa26Vo maa26 : maa26List) {
				
				Maa26 maa26Value = new Maa26();
				maa26Value.setId(maa26.getId());
				maa26Value.setMaa26002(maa26.getMaa26002());
				maa26Value.setMaa26003(maa26.getMaa26003());
				maa26Value.setMaa26004(maa26.getMaa26004());
				maa26Value.setMaa26005(maa26.getMaa26005());
				
				if(maa26.getId() == null) {
					maa26Value.setMaa26002(maa25After.getId());
					maa26Service.insert(maa26Value);
				}else {
					maa26Service.update(maa26Value);
				}
				
			}
			
			return Rets.success();
		} catch (Exception e) {
			e.printStackTrace();
			
			return Rets.failure("新增失敗:"+e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Maa25 maObj) {
		
		if(maObj.getId() == null) {
			maa25Service.insert(maObj);
		}else {
			maa25Service.update(maObj);
		}
		
		return Rets.success();
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE)
    public Object remove(Long id) {
		
		maa25Service.CancelById(id);
		
        return Rets.success();
    }
	
	@Transactional
	@RequestMapping(value = "/poConfirm",method = RequestMethod.GET)
    public Object poCconfirm(Long id, int type) {
		
		if(type == 1) {//確認
			maa25Service.ConfirmById(id);
		}else if(type == 0) {//取消確認
			maa25Service.CancelConfirmById(id);
		}else if(type == 2) {//發出
			maa25Service.IssueById(id);
		}
		
        return Rets.success();
    }
	
}
