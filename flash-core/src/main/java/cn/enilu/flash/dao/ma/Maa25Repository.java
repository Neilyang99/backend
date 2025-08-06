package cn.enilu.flash.dao.ma;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.ma.Maa25;
import cn.enilu.flash.dao.BaseRepository;

public interface Maa25Repository  extends BaseRepository<Maa25,Long>{
	
	@Query(value="select max(maa25002) from maa25 where maa25002 like CONCAT(?1,'%') ", nativeQuery=true)
	String getMaxSerialNumber(String param);
	
	@Modifying
	@Query(value="UPDATE maa25 SET maa25008=?2 WHERE id=?1 ", nativeQuery=true)
	int UpdateStatusById(Long id, int status);
	
	@Query(value="select IFNULL(b.id,0),IFNULL(b.maa25002,''),a.id AS poId,b.maa25004,IFNULL(b.maa25005,''),b.maa25006,b.maa25007,IFNULL(b.maa25008,0),b.maa25009,b.maa25010,b.maa25011,b.maa25012,c.maa00004,d.maa93004,a.maa21002,a.maa21023,"
			+ "b.maa25013,b.maa25014,b.maa25015,IFNULL(b.maa25016,'') " +
			"from maa21 a " + 
			"inner join maa25 b on a.id=b.maa25003 " + 
			"inner join maa00 c on a.maa21003=c.id " + 
			"inner join maa93 d on a.maa21004=d.id " + 
			"where a.id=?1 and b.maa25002 LIKE concat('%', ?2,'%') " +
			"order by 1 desc ", nativeQuery=true)
	List<Object[]> findByPOId(Long poId, String docNo);
	
	@Query(value="select IFNULL(b.id,0),IFNULL(b.maa25002,''),a.id AS poId,b.maa25004,IFNULL(b.maa25005,''),b.maa25006,b.maa25007,IFNULL(b.maa25008,0),b.maa25009,b.maa25010,b.maa25011,b.maa25012,c.maa00004,d.maa93004,a.maa21002,a.maa21023,"
			+ "b.maa25013,b.maa25014,b.maa25015,IFNULL(b.maa25016,'') " +
			"from maa21 a " + 
			"inner join maa25 b on a.id=b.maa25003 " + 
			"inner join maa00 c on a.maa21003=c.id " + 
			"inner join maa93 d on a.maa21004=d.id " + 
			"where a.id=?1 and b.maa25002 LIKE concat('%', ?2,'%') and b.maa25008 in (?3) " +
			"order by 1 desc", nativeQuery=true)
	List<Object[]> findByPOId(Long poId, String docNo, List<Integer> status);
	
	@Query(value="select IFNULL(b.id,0),c.maa00004,d.maa93004,a.maa21002,a.maa21023,b.maa25002 " +
			"from maa21 a " + 
			"inner join maa25 b on a.id=b.maa25003 " + 
			"inner join maa00 c on a.maa21003=c.id " + 
			"inner join maa93 d on a.maa21004=d.id " + 
			"where b.id=?1  ", nativeQuery=true)
	List<Object[]> findByDeliveryId(Long maa25Id);
}
