package cn.enilu.flash.dao.ma;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.ma.Maa27;
import cn.enilu.flash.dao.BaseRepository;

public interface Maa27Repository  extends BaseRepository<Maa27,Long>{
	
	@Query(value="select max(maa27002) from maa27 where maa27002 like CONCAT(?1,'%') ", nativeQuery=true)
	String getMaxSerialNumber(String param);
	
	@Modifying
	@Query(value="UPDATE maa27 SET maa27008=?2 WHERE id=?1 ", nativeQuery=true)
	int UpdateStatusById(Long id, int status);

	
	@Query(value="select IFNULL(e.id,0),IFNULL(e.maa27002,''),a.id AS poId,e.maa27004,IFNULL(e.maa27005,''),e.maa27006,e.maa27007,IFNULL(e.maa27008,0),e.maa27009,e.maa27010,e.maa27011,e.maa27012,c.maa00004,d.maa93004,a.maa21002,a.maa21023,b.maa25002 " +
			"from maa21 a " + 
			"inner join maa25 b on a.id=b.maa25003 " + 
			"inner join maa00 c on a.maa21003=c.id " + 
			"inner join maa93 d on a.maa21004=d.id " +
			"inner join maa27 e on b.id=e.maa27003 " +
			"where b.id=?1 and e.maa27002 LIKE concat('%', ?2,'%') order by 1 desc", nativeQuery=true)
	List<Object[]> findByDeliveryId(Long maa25Id, String docNo);
	
	@Query(value="select IFNULL(e.id,0),IFNULL(e.maa27002,''),a.id AS poId,e.maa27004,IFNULL(e.maa27005,''),e.maa27006,e.maa27007,IFNULL(e.maa27008,0),e.maa27009,e.maa27010,e.maa27011,e.maa27012,c.maa00004,d.maa93004,a.maa21002,a.maa21023,b.maa25002 " +
			"from maa21 a " + 
			"inner join maa25 b on a.id=b.maa25003 " + 
			"inner join maa00 c on a.maa21003=c.id " + 
			"inner join maa93 d on a.maa21004=d.id " +
			"inner join maa27 e on b.id=e.maa27003 " +
			"where b.id=?1 and e.maa27002 LIKE concat('%', ?2,'%') and e.maa27008 in (?3) " +
			"order by 1 desc", nativeQuery=true)
	List<Object[]> findByDeliveryId(Long maa25Id, String docNo, List<Integer> status);
}
