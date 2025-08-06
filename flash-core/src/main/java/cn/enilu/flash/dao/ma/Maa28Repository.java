package cn.enilu.flash.dao.ma;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.ma.Maa28;
import cn.enilu.flash.dao.BaseRepository;

public interface Maa28Repository  extends BaseRepository<Maa28,Long>{
	
	
	@Query(value="select d.id,d.maa28002,d.maa28003,d.maa28004,d.maa28005,b.maa22003,b.maa22006,b.maa22011,b.maa22012, (c.maa26004-ifnull(e.usedQty,0)) as missingQty,c.maa26004 " +
			"from maa21 a " + 
			"inner join maa22 b on a.id=b.maa22002 " + 
			"inner join maa26 c on b.id=c.maa26003 " + 
			"inner join maa28 d on c.id=d.maa28003 " +
			"left join (select maa28003,sum(maa28004) usedQty from maa27 a1 inner join maa28 b1 on a1.id=b1.maa28002 where a1.maa27008<>9 group by maa28003) e on c.id=e.maa28003 " + 
			"where c.maa26002=?1 ", nativeQuery=true)
	List<Object[]> findByDeliveryId(Long maa25Id);
	
}
