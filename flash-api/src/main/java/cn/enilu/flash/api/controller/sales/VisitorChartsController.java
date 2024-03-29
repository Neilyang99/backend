package cn.enilu.flash.api.controller.sales;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.enilu.flash.api.controller.BaseController;
import cn.enilu.flash.bean.vo.front.Rets;
import cn.enilu.flash.bean.vo.sales.SalesVo;
import cn.enilu.flash.service.sales.VisitorService;


@RestController
@RequestMapping("/visitorCharts")
public class VisitorChartsController  extends BaseController {
	@Autowired
    private VisitorService visitorService;
	//private Sla00Service sla00Service;
	
	
	/**
	 * 動機總數圓餅圖(購買用途)
	 * @return
	 */
	@RequestMapping(value = "/getCountBySla10020",method = RequestMethod.GET)
	public Object getCountBySla10020(String projectCode) {
		
		List<SalesVo> list = visitorService.countMotivationBySla10002(projectCode);
		
		return Rets.success(list);
	}
	
	/**
	 * 年齡總數圓餅圖(購買用途)
	 * @return
	 */
	@RequestMapping(value = "/getCountBySla10017",method = RequestMethod.GET)
	public Object getCountBySla10017(String projectCode) {
		
		List<SalesVo> list = visitorService.countAgeBySla10002(projectCode);
		
		return Rets.success(list);
	}
	
	/**
	 * 來客量曲線圖(填單日期)
	 * @return
	 */
	@RequestMapping(value = "/getCountBySla10013",method = RequestMethod.GET)
	public Object getCountBySla10013(String projectCode, String type) {
		
		List<SalesVo> list = visitorService.countVisitorBySla10002(projectCode, type);
		
		return Rets.success(list);
	}
	
	/**
	 * 位置長條圖(區域)
	 * @return
	 */
	@RequestMapping(value = "/getCountBySla10016",method = RequestMethod.GET)
	public Object getCountBySla10016(String projectCode) {
		
		List<SalesVo> list = visitorService.countAreaBySla10002(projectCode);
		
		return Rets.success(list);
	}
	
	/**
	 * 來源長條圖(來人/來電)
	 * @return
	 */
	@RequestMapping(value = "/getCountBySla10004",method = RequestMethod.GET)
	public Object getCountBySla10004(String projectCode) {
		
		List<SalesVo> list = visitorService.countVisitTypeBySla10002(projectCode);
		
		return Rets.success(list);
	}
	
}
