package cn.enilu.flash.service.ma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.enilu.flash.bean.entity.ma.Maa28;
import cn.enilu.flash.dao.ma.Maa28Repository;
import cn.enilu.flash.service.BaseService;

@Service
public class Maa28Service extends BaseService<Maa28,Long,Maa28Repository>{
	
	@Autowired
	private Maa28Repository dao;
	
	
	
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
