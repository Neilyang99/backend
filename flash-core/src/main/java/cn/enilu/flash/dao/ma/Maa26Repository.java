package cn.enilu.flash.dao.ma;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.ma.Maa26;
import cn.enilu.flash.dao.BaseRepository;

public interface Maa26Repository  extends BaseRepository<Maa26,Long>{
	
	
	@Query(value="select IFNULL(c.id,0),IFNULL(c.maa26002,0),IFNULL(c.maa26003,0),IFNULL(c.maa26004,0),c.maa26005,b.maa22003,b.maa22006,b.maa22011,b.maa22012, (b.maa22012-ifnull(d.usedQty,0)) as missingQty " +
			"from maa21 a " + 
			"inner join maa22 b on a.id=b.maa22002 " + 
			"inner join maa26 c on b.id=c.maa26003 " + 
			"left join (select maa26003,sum(maa26004) usedQty from maa25 a1 inner join maa26 b1 on a1.id=b1.maa26002 where a1.maa25008<>9 group by maa26003) d on b.id=d.maa26003 " + 
			"where a.id=?1 ", nativeQuery=true)
	List<Object[]> findByPOId(Long poId);
	
	@Query(value="select b.maa22003,b.maa22006,b.maa22011,b.maa22012,(d.maa26004-ifnull(c.usedQty,0)) as missingQty,d.id,d.maa26004 " +
			"from maa21 a " + 
			"inner join maa22 b on a.id=b.maa22002 " + 
			"inner join maa26 d on b.id=d.maa26003 " + 
			"left join (select maa28003,sum(maa28004) usedQty from maa27 a1 inner join maa28 b1 on a1.id=b1.maa28002 where a1.maa27008<>9 group by maa28003) c on d.id=c.maa28003 " + 
			"where d.maa26002=?1 ", nativeQuery=true)
	List<Object[]> findByDeliveryId(Long maa25Id);
	
}
