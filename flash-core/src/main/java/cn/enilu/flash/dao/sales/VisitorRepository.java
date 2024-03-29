package cn.enilu.flash.dao.sales;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import cn.enilu.flash.bean.entity.sales.Visitor;
import cn.enilu.flash.dao.BaseRepository;

public interface VisitorRepository extends BaseRepository<Visitor, Long>{

	 //List<Visitor> findByVisitorName(String name);
	
	//@Query(value = "select id,sla10002,sla10004,sla10005,sla10006,sla10009,sla10013,sla10014 from sla10 where sla10006 like ?1", nativeQuery = true)
	List<Visitor> findBySla10006(String name);
	
	Optional<Visitor> findById(Long id);
	
	@Query(value = "select sla10.id,sla10002,sla00003 as sla10002name,sla10004,sla10005,sla10006,sla10010,sla10013,sla10014,sla10015 from sla10 "
			+ "left join sla00 on sla10002=sla00002 WHERE sla10003 LIKE ?1 and sla10006 LIKE ?2 and sla10010 LIKE ?3 and sla10009 LIKE ?4", nativeQuery = true)
	List<Object[]> queryGridList(String projectName, String name, String cellPhone, String tel);
	
	//get media statistics
	//totalCount=累計, thisWeek=本周
	@Query(value = "select a.sla10002 projectCode,a.sla10003 projectName,a.sla10004 visitorType,a.sla10023 itemCode,a.acc totalCount,if(isnull(thisWeek),0,b.thisWeek) weeklyCount from "
			+ "(select sla10002,sla10003,sla10004,sla10023,count(1) acc from sla10  group by sla10002,sla10004,sla10023) a "
			+ "left join (select sla10002,sla10003,sla10004,sla10023,count(1) thisWeek from sla10 where sla10013 between ?2 and ?3 group by sla10002,sla10004,sla10023) b " 
			+ "on a.sla10002=b.sla10002 and a.sla10004=b.sla10004 and a.sla10023=b.sla10023 "
			+ "where a.sla10002=?1 "
			+ "order by 3,4", nativeQuery = true)
	List<Object[]> summarizeMediaBySla10002AndSla10013Between(String projectCode, String dateFrom, String dateTo);
	
	//區域分析
	@Query(value = "select a.sla10002 projectCode,a.sla10003 projectName,a.sla10004 visitorType,a.itemCode,a.acc totalCount,if(isnull(thisWeek),0,b.thisWeek) weeklyCount from "
			+ "(select sla10002,sla10003,sla10004,sla10016 itemCode,count(1) acc from sla10  group by sla10002,sla10004,sla10016) a "
			+ "left join (select sla10002,sla10003,sla10004,sla10016 itemCode,count(1) thisWeek from sla10 where sla10013 between ?2 and ?3 group by sla10002,sla10004,sla10016) b " 
			+ "on a.sla10002=b.sla10002 and a.sla10004=b.sla10004 and a.itemCode=b.itemCode "
			+ "where a.sla10002=?1 "
			+ "order by 3,4", nativeQuery = true)
	List<Object[]> sumAreaBySla10002AndSla10013Between(String projectCode, String dateFrom, String dateTo);
	
	//職業分析
	@Query(value = "select a.sla10002 projectCode,a.sla10003 projectName,a.sla10004 visitorType,a.itemCode,a.acc totalCount,if(isnull(thisWeek),0,b.thisWeek) weeklyCount from "
			+ "(select sla10002,sla10003,sla10004,sla10018 itemCode,count(1) acc from sla10  group by sla10002,sla10004,sla10018) a "
			+ "left join (select sla10002,sla10003,sla10004,sla10018 itemCode,count(1) thisWeek from sla10 where sla10013 between ?2 and ?3 group by sla10002,sla10004,sla10018) b " 
			+ "on a.sla10002=b.sla10002 and a.sla10004=b.sla10004 and a.itemCode=b.itemCode "
			+ "where a.sla10002=?1 "
			+ "order by 3,4", nativeQuery = true)
	List<Object[]> sumCareerBySla10002AndSla10013Between(String projectCode, String dateFrom, String dateTo);
	
	//購買動機分析
	@Query(value = "select a.sla10002 projectCode,a.sla10003 projectName,a.sla10004 visitorType,a.itemCode,a.acc totalCount,if(isnull(thisWeek),0,b.thisWeek) weeklyCount from "
			+ "(select sla10002,sla10003,sla10004,sla10020 itemCode,count(1) acc from sla10  group by sla10002,sla10004,sla10020) a "
			+ "left join (select sla10002,sla10003,sla10004,sla10020 itemCode,count(1) thisWeek from sla10 where sla10013 between ?2 and ?3 group by sla10002,sla10004,sla10020) b " 
			+ "on a.sla10002=b.sla10002 and a.sla10004=b.sla10004 and a.itemCode=b.itemCode "
			+ "where a.sla10002=?1 "
			+ "order by 3,4", nativeQuery = true)
	List<Object[]> sumMotivationBySla10002AndSla10013Between(String projectCode, String dateFrom, String dateTo);
	
	//年齡
	@Query(value = "select a.sla10002 projectCode,a.sla10003 projectName,a.sla10004 visitorType,a.itemCode,a.acc totalCount,if(isnull(thisWeek),0,b.thisWeek) weeklyCount from "
			+ "(select sla10002,sla10003,sla10004,sla10017 itemCode,count(1) acc from sla10  group by sla10002,sla10004,sla10017) a "
			+ "left join (select sla10002,sla10003,sla10004,sla10017 itemCode,count(1) thisWeek from sla10 where sla10013 between ?2 and ?3 group by sla10002,sla10004,sla10017) b " 
			+ "on a.sla10002=b.sla10002 and a.sla10004=b.sla10004 and a.itemCode=b.itemCode "
			+ "where a.sla10002=?1 "
			+ "order by 3,4", nativeQuery = true)
	List<Object[]> sumAgeBySla10002AndSla10013Between(String projectCode, String dateFrom, String dateTo);
	
	//尚未使用
	//get media statistics by SLA11
	//itemCode=媒體Code, totalCount=累計, thisWeek=本周
	@Query(value = "select t.sla10002 projectCode,t.sla10003 projectName,t.sla11003 visitorType,t.sla10023 itemCode,t.acc totalCount,if(isnull(thisWeek),0,w.thisWeek) weeklyCount " + 
			"from (select sla10002,sla10003,sla11003,sla10023,count(1) acc from sla10 a inner join sla11 b on a.id=b.sla11002 group by sla10002,sla11003,sla10023) t " + 
			"left join (select sla10002,sla10003,sla11003,sla10023,count(1) thisWeek from sla10 a inner join sla11 b on a.id=b.sla11002 where sla11004 between ?2 and ?3 group by sla10002,sla11003,sla10023) w " + 
			"on t.sla10002=w.sla10002 and t.sla11003=w.sla11003 " + 
			"where t.sla10002=?1", nativeQuery = true)
	List<Object[]> sumMediaBySla11(String projectCode, String dateFrom, String dateTo);
	
	
	//for 客戶資料圖表 :動機總數量---------------
	@Query(value = "SELECT sla10002,sla10020,count(1) FROM sla10 where sla10002=?1 group by sla10002,sla10020", nativeQuery = true)
	List<Object[]> countMotivationBySla10002(String projectCode);
	
	//for 客戶資料圖表 :年齡總數量---------------
	@Query(value = "SELECT sla10002,sla10017,count(1) FROM sla10 where sla10002=?1 group by sla10002,sla10017", nativeQuery = true)
	List<Object[]> countAgeBySla10002(String projectCode);
	
	//for 客戶資料圖表 :來客量總數量(月)---------------
	@Query(value = "SELECT sla10002,count(1),substr(sla10013,1,6) FROM sla10 where sla10002=?1 group by sla10002,substr(sla10013,1,6)", nativeQuery = true)
	List<Object[]> countVisitorBySla10002(String projectCode);
	
	//for 客戶資料圖表 :來客量總數量(季)---------------
	@Query(value = "SELECT sla10002,count(1),CONCAT(YEAR(sla10013),'-',QUARTER(sla10013),'Q') AS yq FROM sla10 where sla10002=?1 group by sla10002,yq", nativeQuery = true)
	List<Object[]> countVisitorBySla10002AndQuarter(String projectCode);
	
	//for 客戶資料圖表 :來客量總數量(年)---------------
	@Query(value = "SELECT sla10002,count(1),YEAR(sla10013)AS yr FROM sla10 where sla10002=?1 group by sla10002,yr", nativeQuery = true)
	List<Object[]> countVisitorBySla10002AndYear(String projectCode);

	//for 客戶資料圖表 :來客量總數量(季)---------------
	@Query(value = "SELECT sla10002,count(1),CONCAT(YEAR(sla10013),'-',WEEK(sla10013),'W ') AS yw FROM sla10 where sla10002=?1 group by sla10002,yw", nativeQuery = true)
	List<Object[]> countVisitorBySla10002AndWeek(String projectCode);
	
	//for 客戶資料圖表 :位置總數---------------
	@Query(value = "SELECT sla10002,sla10016,count(1) FROM sla10 where sla10002=?1 group by sla10002,sla10016", nativeQuery = true)
	List<Object[]> countAreaBySla10002(String projectCode);

	//for 客戶資料圖表 :來源總數---------------
	@Query(value = "SELECT sla10002,sla10004,count(1) FROM sla10 where sla10002=?1 group by sla10002,sla10004", nativeQuery = true)
	List<Object[]> countVisitTypeBySla10002(String projectCode);
	
	//for 已購原因分析_本週
	@Query(value = "SELECT sla10029,COUNT(1) weekly FROM sla20 " + 
			"INNER JOIN sla10 b ON sla20006=b.id WHERE sla20002=?1 AND sla20005 LIKE 'B%' and sla20004 BETWEEN ?2 and ?3 Group by sla10029", nativeQuery = true)
	List<Object[]> orderCauseBySla10002(Long projectId, String dateFrom, String dateTo);
	
	//for 已購原因分析_上周累計
	@Query(value = "SELECT sla10029,COUNT(1) acc FROM sla20 " + 
			"INNER JOIN sla10 b ON sla20006=b.id WHERE sla20002=?1 AND sla20005 LIKE 'B%' and sla20004 < ?2 Group by sla10029", nativeQuery = true)
	List<Object[]> orderCauseAccBySla10002(Long projectId, String dateFrom);	

	//for 回籠未成交---------------
	@Query(value = "SELECT sla11004,sla10006,sla11005,sla10030,sla10015 FROM sla11 INNER JOIN sla10 a ON sla11002=a.id AND a.sla10002=?1 WHERE sla11003='F' AND a.sla10030<>'' order by sla11004", nativeQuery = true)
	List<Object[]> notDealAgainBySla10002(String projectCode);
	
	//for 格局去化_房型規劃---------------
	@Query(value = "SELECT concat(sla01007,'_',sla01008),COUNT(1) FROM sla01 WHERE sla01002=?1 GROUP BY sla01007,sla01008", nativeQuery = true)
	List<Object[]> houseTypeBySla01002(Long projectId);
	
	//for 銷況表_總銷---------------
	@Query(value = "SELECT sla00028,sla00029,(sla00011+sla00012+sla00013+sla00014) totalQty,(sla00018+sla00019+sla00020+sla00021) soldQty,(sla00015+sla00017) totalCar FROM sla00 WHERE id=?1", nativeQuery = true)
	List<Object[]> projectStatusById(Long projectId);
	
	//for 週報表_實際銷售---------------
	@Query(value = "SELECT COUNT(1),SUM(sla20068) FROM sla20 WHERE sla20002=?1 AND ( sla20005 LIKE 'B%' OR sla20005 LIKE 'E%') GROUP BY sla20002", nativeQuery = true)
	List<Object[]> actualSalesByProjectId(Long projectId);
	
	//for 週報表_上周累計的來人/來電/回籠---------------
	@Query(value = "SELECT sla10004,COUNT(1) FROM sla10 WHERE sla10002=?1 AND sla10013<?2 GROUP BY sla10004 UNION ALL SELECT b.sla11003,COUNT(1) FROM sla10 a INNER JOIN sla11 b ON a.id=b.sla11002 AND b.sla11003='F' WHERE a.sla10002=?1 AND b.sla11004<?2 GROUP BY a.sla10002", nativeQuery = true)
	List<Object[]> customerCountByLastWeekAndProject(String projectCode,String date);
	
	//for 週報表_當日來人來電回籠數量---------------
	@Query(value = "SELECT sla10004,COUNT(1) FROM sla10 WHERE sla10002=?1 AND sla10013 = ?2 GROUP BY sla10004,sla10013 " + 
			"UNION ALL " + 
			"SELECT b.sla11003,COUNT(1) FROM sla10 a INNER JOIN sla11 b ON a.id=b.sla11002 AND b.sla11003='F' WHERE a.sla10002=?1 AND b.sla11004=?2 GROUP BY a.sla10002", nativeQuery = true)
	List<Object[]> customerCountByDateAndProject(String projectCode,String date);
	
	//for 週報表_當日銷售紀錄---------------
	@Query(value = "SELECT sla20005,COUNT(1),sla20068 FROM sla20 WHERE sla20002=?1 AND sla20004=?2 GROUP BY sla20005", nativeQuery = true)
	List<Object[]> salesDataByDateAndProject(Long projectId,String date);
	
	//for 週報表_上周累計銷售紀錄---------------
	@Query(value = "SELECT sla20005,COUNT(1),sla20068 FROM sla20 WHERE sla20002=?1 AND sla20004<?2 GROUP BY sla20005", nativeQuery = true)
	List<Object[]> salesDataByLastWeekAndProject(Long projectId,String date);
	

}