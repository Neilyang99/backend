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
import cn.enilu.flash.bean.entity.ma.Maa21;
import cn.enilu.flash.bean.entity.ma.Maa22;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.ma.Maa21Vo;
import cn.enilu.flash.bean.vo.ma.Maa22Vo;
import cn.enilu.flash.bean.vo.ma.MaaVo;
import cn.enilu.flash.bean.vo.query.SearchFilter;
import cn.enilu.flash.service.ma.Maa21Service;
import cn.enilu.flash.service.ma.Maa21Service.StatusEnum;
import cn.enilu.flash.service.ma.Maa22Service;
import cn.enilu.flash.service.ma.Maa93Service;
import cn.enilu.flash.service.system.DeptService;
import cn.enilu.flash.utils.BeanUtil;
import cn.enilu.flash.utils.DateUtil;
import cn.enilu.flash.utils.JsonUtil;
import cn.enilu.flash.utils.StringUtil;
import cn.enilu.flash.utils.factory.Page;
import cn.enilu.flash.warpper.Maa21Wrapper;


@RestController
@RequestMapping("/maa21")
public class Maa21Controller extends BaseController{

	@Autowired
    private Maa21Service maa21Service;
	
	@Autowired
    private Maa22Service maa22Service;
	
	@Autowired
    private Maa93Service maa93Service;
	
	@Autowired
	private DeptService dptService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String selMaa21002,String selMaa21003) {
        
        Page<Maa21> page = this.getPOList(selMaa21002, selMaa21003, "");//get all data
        
		return Rets.success(page);
	}
	
	@RequestMapping(value = "/listByPOIssue",method = RequestMethod.GET)
	public Object listByPOIssue(@RequestParam(required = false) String selMaa21002,String selMaa21003) {
		
		Page<Maa21> page = this.getPOList(selMaa21002, selMaa21003, ""+StatusEnum.POISSUE.getValue());
		
		
		return Rets.success(page);
	}
	
	/**
	 * get vendor PO data list
	 * @param poNo 
	 * @param projectId
	 * @param status data status
	 * @return
	 */
	public Page<Maa21> getPOList(String poNo,String projectId,String status) {
		
		Page<Maa21> page = new PageFactory<Maa21>().defaultPage();
		
		if(StringUtil.isNullOrEmpty(poNo)) {
			
		}else {
			page.addFilter( "maa21002", SearchFilter.Operator.LIKE, poNo);
		}
		if(!StringUtil.isNullOrEmpty(projectId)) {
			
			page.addFilter( "maa21003", SearchFilter.Operator.EQ, projectId);
		}
		if(!StringUtil.isNullOrEmpty(status)) {
			
			page.addFilter( "maa21021", SearchFilter.Operator.EQ, status);
		}
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(new Order(Sort.Direction.DESC,"maa21002"));
		orderList.add(new Order(Sort.Direction.DESC,"maa21006"));
		
		page.setSort(Sort.by(orderList));
		//page.setSort(Sort.by(Sort.Direction.DESC,"maa21006"));
		page = maa21Service.queryPage(page);
		
		
		List<MaaVo> vendorList = maa93Service.getVendor();
		List<MaaVo> companyList = dptService.getCompanyByMaa();
		List<MaaVo> itemList = new ArrayList<>();
		itemList.addAll(vendorList);
		itemList.addAll(companyList);
		
		
		List list = (List) new Maa21Wrapper(itemList, BeanUtil.objectsToMaps(page.getRecords())).warp();
		
        page.setRecords(list);
		
		return page;
	}
	
	@Transactional
	@RequestMapping(value = "/save",method = RequestMethod.GET)
	public Object save(@ModelAttribute @Valid Maa21Vo maObj) {
		
		try {
			
			Maa21 maa21After = null;
			
			Maa21 maa21 = new Maa21();
			maa21.setId(maObj.getId());
			maa21.setMaa21002(maObj.getMaa21002());
			maa21.setMaa21003(maObj.getMaa21003());
			maa21.setMaa21004(maObj.getMaa21004());
			maa21.setMaa21005(maObj.getMaa21005());
			maa21.setMaa21006(maObj.getMaa21006());
			maa21.setMaa21007(maObj.getMaa21007());
			maa21.setMaa21008(maObj.getMaa21008());
			maa21.setMaa21009(maObj.getMaa21009());
			maa21.setMaa21010(maObj.getMaa21010());
			maa21.setMaa21011(maObj.getMaa21011());
			maa21.setMaa21012(maObj.getMaa21012());
			maa21.setMaa21013(maObj.getMaa21013());
			maa21.setMaa21014(maObj.getMaa21014());
			maa21.setMaa21015(maObj.getMaa21015());
			maa21.setMaa21016(maObj.getMaa21016());
			maa21.setMaa21017(maObj.getMaa21017());
			maa21.setMaa21018(maObj.getMaa21018());
			maa21.setMaa21019(maObj.getMaa21019());
			maa21.setMaa21020(maObj.getMaa21020());
			maa21.setMaa21021(maObj.getMaa21021());
			maa21.setMaa21022(maObj.getMaa21022());
			maa21.setMaa21023(maObj.getMaa21023());
			maa21.setMaa21024(maObj.getMaa21024());
			maa21.setMaa21025(maObj.getMaa21025());
			maa21.setMaa21026(maObj.getMaa21026());
			maa21.setMaa21027(maObj.getMaa21027());
			
			if(maObj.getId() == null || maObj.getId() == 0) {
				String newpoNo = maa21Service.gengeratePONO("A01", DateUtil.getYear());
				maa21.setMaa21002(newpoNo);
				
				maa21After = maa21Service.insert(maa21);
			}else {
				maa21After= maa21Service.update(maa21);
			}
			
			
			//前端有encode: 中文字處理 --> 特殊符號處理
			String arr = new String(Base64.getDecoder().decode(maObj.getMaa22String()));
			String decodeStr = URLDecoder.decode(arr,"UTF-8");
			
			decodeStr = "["+decodeStr+"]";
			//JSON string 轉成 object
			List<Maa22Vo> maa22List = JsonUtil.fromJsonAsList(Maa22Vo.class, decodeStr);
			
			for (Maa22Vo maa22 : maa22List) {
				
				Maa22 maa22Value = new Maa22();
				maa22Value.setId(maa22.getId());
				maa22Value.setMaa22002(maa22.getMaa22002());
				maa22Value.setMaa22003(maa22.getMaa22003());
				maa22Value.setMaa22004(maa22.getMaa22004());
				maa22Value.setMaa22005(maa22.getMaa22005());
				maa22Value.setMaa22006(maa22.getMaa22005());
				maa22Value.setMaa22007(maa22.getMaa22007());
				maa22Value.setMaa22008(maa22.getMaa22008());
				maa22Value.setMaa22009(maa22.getMaa22009());
				maa22Value.setMaa22010(maa22.getMaa22010());
				maa22Value.setMaa22011(maa22.getMaa22011());
				maa22Value.setMaa22012(maa22.getMaa22012());
				maa22Value.setMaa22013(maa22.getMaa22013());
				maa22Value.setMaa22014(maa22.getMaa22014());
				maa22Value.setMaa22015(maa22.getMaa22015());
				maa22Value.setMaa22016(maa22.getMaa22016());
				maa22Value.setMaa22017(maa22.getMaa22017());
				maa22Value.setMaa22018(maa22.getMaa22018());
				maa22Value.setMaa22019(maa22.getMaa22019());
				maa22Value.setMaa22020(maa22.getMaa22020());
				maa22Value.setMaa22021(maa22.getMaa22021());
				
				
				if(maa22.getId() == null) {
					maa22Value.setMaa22002(maa21After.getId());
					maa22Service.insert(maa22Value);
				}else {
					maa22Service.update(maa22Value);
				}
				
			}
			
			return Rets.success();
		} catch (Exception e) {
			e.printStackTrace();
			
			return Rets.failure("新增失敗:"+e.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object add(@ModelAttribute @Valid Maa21 maObj) {
		
		if(maObj.getId() == null) {
			maa21Service.insert(maObj);
		}else {
			maa21Service.update(maObj);
		}
		
		return Rets.success();
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE)
    public Object remove(Long id) {
		
		maa21Service.CancelById(id);
		maa22Service.CancelByMaa22002(id);
		
        return Rets.success();
    }
	
	@Transactional
	@RequestMapping(value = "/poConfirm",method = RequestMethod.GET)
    public Object poCconfirm(Long id, int type) {
		
		if(type == 1) {//確認合約
			maa21Service.ConfirmById(id);
			maa22Service.ConfirmByMaa22002(id);
		}else if(type == 0) {//取消確認
			maa21Service.CancelConfirmById(id);
			maa22Service.CancelConfirmByMaa22002(id);
		}else if(type == 2) {//合約發出
			maa21Service.IssueById(id);
			maa22Service.IssueByMaa22002(id);
		}
		
        return Rets.success();
    }
	
}
