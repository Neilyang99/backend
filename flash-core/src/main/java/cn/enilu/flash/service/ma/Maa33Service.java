package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa33;
import cn.enilu.flash.dao.ma.Maa33Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa33Service extends BaseService<Maa33,Long,Maa33Repository>{
	
	@Autowired
	private Maa33Repository repository;
	
	
	
}
