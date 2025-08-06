package cn.enilu.flash.dao.ma;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.ma.Maa22;
import cn.enilu.flash.dao.BaseRepository;

public interface Maa22Repository  extends BaseRepository<Maa22,Long>{
	
	@Modifying
	@Query(value="UPDATE maa22 SET maa22018=?2 WHERE maa22002=?1 ", nativeQuery=true)
	int UpdateStatusByMaa22002(Long maa22002, int status);
	
	@Query(value="select b.maa22003,b.maa22006,b.maa22011,b.maa22012,(b.maa22012-ifnull(c.usedQty,0)) as missingQty,b.id " +
			"from maa21 a " + 
			"inner join maa22 b on a.id=b.maa22002 " + 
			"left join (select maa26003,sum(maa26004) usedQty from maa25 a1 inner join maa26 b1 on a1.id=b1.maa26002 where a1.maa25008<>9 group by maa26003) c on b.id=c.maa26003 " + 
			"where a.id=?1 ", nativeQuery=true)
	List<Object[]> findByPOId(Long poId);
}
