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
import cn.enilu.flash.bean.entity.ma.Maa27;
import cn.enilu.flash.bean.entity.ma.Maa28;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.ma.Maa27Vo;
import cn.enilu.flash.bean.vo.ma.Maa28Vo;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.ma.Maa27Service;
import cn.enilu.flash.service.ma.Maa28Service;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;


@RestController
@RequestMapping("/maa27")
public class Maa27Controller extends BaseController{

	@Autowired
    private Maa27Service maa27Service;
	
	@Autowired
    private Maa28Service maa28Service;
	
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String selMaa27002,String selMaa27003) {
        
		
		Page<Maa27> page = new PageFactory<Maa27>().defaultPage();
		
		if(StringUtil.isNullOrEmpty(selMaa27002)) {
			
		}else {
			page.addFilter( "maa27002", SearchFilter.Operator.LIKE, selMaa27002);
		}
		if(!StringUtil.isNullOrEmpty(selMaa27003)) {
			
			page.addFilter( "maa27003", SearchFilter.Operator.EQ, selMaa27003);
		}
		
		page.setSort(Sort.by(Sort.Direction.DESC,"maa27002"));//叫貨單號
		page = maa27Service.queryPage(page);
		
		List list = BeanUtil.objectsToMaps(page.getRecords());
		
        page.setRecords(list);
		
        return Rets.success(page);
	}
	
	@RequestMapping(value = "/list2",method = RequestMethod.GET)
	public Object list2(@RequestParam(required = false) String selMaa27002,Long selMaa27003, String selMaa27008) {
		
		List<Maa27Vo> list = maa27Service.findByDeliveryId(selMaa27003,selMaa27002,selMaa27008);
		
		return Rets.success(list);
	}
	
	@RequestMapping(value = "/getStructure",method = RequestMethod.GET)
	public Object getStructure(@RequestParam(required = false) Long maa25Id) {
		
		List<Maa27Vo> list = maa27Service.getStructure(maa25Id);
		
		return Rets.success(list);
	}
	
	@Transactional
	@RequestMapping(value = "/save",method = RequestMethod.GET)
	public Object save(@ModelAttribute @Valid Maa27Vo maObj) {
		
		try {
			
			Maa27 maa27After = null;
			
			Maa27 maa27 = new Maa27();
			maa27.setId(maObj.getId());
			maa27.setMaa27002(maObj.getMaa27002());
			maa27.setMaa27003(maObj.getMaa27003());
			maa27.setMaa27004(maObj.getMaa27004());
			maa27.setMaa27005(maObj.getMaa27005());
			
			
			if(maObj.getId() == null || maObj.getId() == 0) {
				String newpoNo = maa27Service.gengerateDocNo("A03", DateUtil.getYear());
				maa27.setMaa27002(newpoNo);
				
				maa27After = maa27Service.insert(maa27);
			}else {
				maa27After= maa27Service.update(maa27);
			}
			
			
			//前端有encode: 中文字處理 --> 特殊符號處理
			String arr = new String(Base64.getDecoder().decode(maObj.getMaa28String()));
			String decodeStr = URLDecoder.decode(arr,"UTF-8");
			
			decodeStr = "["+decodeStr+"]";
			//JSON string 轉成 object
			List<Maa28Vo> maa28List = JsonUtil.fromJsonAsList(Maa28Vo.class, decodeStr);
			
			for (Maa28Vo maa28 : maa28List) {
				
				Maa28 maa28Value = new Maa28();
				maa28Value.setId(maa28.getId());
				maa28Value.setMaa28002(maa28.getMaa28002());
				maa28Value.setMaa28003(maa28.getMaa28003());
				maa28Value.setMaa28004(maa28.getMaa28004());
				maa28Value.setMaa28005(maa28.getMaa28005());
				
				if(maa28.getId() == null) {
					maa28Value.setMaa28002(maa27After.getId());
					maa28Service.insert(maa28Value);
				}else {
					maa28Service.update(maa28Value);
				}
				
			}
			
			return Rets.success();
		} catch (Exception e) {
			e.printStackTrace();
			
			return Rets.failure("新增失敗:"+e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Maa27 maObj) {
		
		if(maObj.getId() == null) {
			maa27Service.insert(maObj);
		}else {
			maa27Service.update(maObj);
		}
		
		return Rets.success();
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE)
    public Object remove(Long id) {
		
		maa27Service.CancelById(id);
		
        return Rets.success();
    }
	
	@Transactional
	@RequestMapping(value = "/poConfirm",method = RequestMethod.GET)
    public Object poCconfirm(Long id, int type) {
		
		if(type == 1) {//確認
			maa27Service.ConfirmById(id);
		}else if(type == 0) {//取消確認
			maa27Service.CancelConfirmById(id);
		}else if(type == 2) {//發出
			maa27Service.IssueById(id);
		}
		
        return Rets.success();
    }
	
}
