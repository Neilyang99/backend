package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa91;
import cn.enilu.flash.dao.ma.Maa91Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa91Service extends BaseService<Maa91,Long,Maa91Repository>{
	
	@Autowired
	private Maa91Repository repository;
	
	
	
}
