package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa26;
import cn.enilu.flash.dao.ma.Maa26Repository;
import cn.enilu.flash.service.BaseService;
import cn.enilu.flash.service.ma.Maa22Service.StatusEnum;

@Service
public class Maa26Service extends BaseService<Maa26,Long,Maa26Repository>{
	
	@Autowired
	private Maa26Repository dao;
	
	
	
	public enum StatusEnum {
		
		CREATE(0),
		CONFIRM(1),
		VOID(9);

		private int value;
		
		StatusEnum(int value) {
			this.value = value;
		}
		
		public int getValue() {
	        return value;
	    }
		
	}
}
