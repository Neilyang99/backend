package cn.enilu.flash.service.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.sales.Sla11;
import cn.enilu.flash.dao.sales.Sla11Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Sla11Service  extends BaseService<Sla11,Long,Sla11Repository>{

	@Autowired
	private Sla11Repository sla11Repository;
	
	public List<Sla11> findByVisitId(String visitorId) {
		return sla11Repository.findBySla11002(visitorId);
	}
	public Sla11 findByVisitDate(String dt) {
		return sla11Repository.findBySla11004(dt);
	}
	
	
}
