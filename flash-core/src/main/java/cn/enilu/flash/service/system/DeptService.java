package cn.enilu.flash.service.system;

import cn.enilu.flash.bean.entity.system.Dept;
import cn.enilu.flash.bean.vo.ma.MaaVo;
import cn.enilu.flash.bean.vo.node.DeptNode;
import cn.enilu.flash.bean.vo.node.ZTreeNode;
import cn.enilu.flash.dao.system.DeptRepository;
import cn.enilu.flash.service.BaseService;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
@Service
public class DeptService extends BaseService<Dept,Long,DeptRepository> {
    @Autowired
    private DeptRepository deptRepository;

    public List<ZTreeNode> tree() {
        String sql = "SELECT id, pid AS pId, simplename AS NAME, ( CASE WHEN (pId = 0 OR pId IS NULL) THEN 'true' ELSE 'false' END ) AS open FROM t_sys_dept";
        List nodes = deptRepository.queryObjBySql(sql,ZTreeNode.class);
        return nodes;
    }


    public List<Dept> query(String condition) {
        List<Dept> list = null;
        if(Strings.isNullOrEmpty(condition)){
            list =   deptRepository.findAll();
        }else{
            condition = "%"+condition+"%";
            list = deptRepository.findBySimplenameLikeOrFullnameLike(condition,condition);
        }
        return list;
    }

    public void deleteDept(Long deptId) {
        Dept dept = get(deptId);
        List<Dept> subDepts = deptRepository.findByPidsLike("%[" + dept.getId() + "]%");
        deptRepository.deleteAll(subDepts);
        deptRepository.delete(dept);
    }

    public List<DeptNode> queryAllNode() {
        List<Dept> list = queryAll();
        return generateTree(list);
    }

    public void deptSetPids(Dept dept) {
        if ( dept.getPid() ==null || dept.getPid().intValue() == 0) {
            dept.setPid(0L);
            dept.setPids("[0],");
        } else {
            Long pid = dept.getPid();
            Dept temp = get(pid);
            String pids = "";
            if(temp!=null){
                pids = temp.getPids();
            }
            dept.setPid(pid);
            dept.setPids(pids + "[" + pid + "],");
        }
    }

    private List<DeptNode> generateTree(List<Dept> list){

        List<DeptNode> nodes = new ArrayList<>(20);
        for(Dept dept:list){
            DeptNode deptNode = new DeptNode();
            BeanUtils.copyProperties(dept,deptNode);
            nodes.add(deptNode);
        }
        for(DeptNode deptNode:nodes){
            for(DeptNode child:nodes){
                if(child.getPid().intValue() == deptNode.getId().intValue()){
                    deptNode.getChildren().add(child);
                }
            }
        }
        List<DeptNode> result = new ArrayList<>(20);
        for(DeptNode node:nodes){
            if(node.getPid().intValue() == 0){
                result.add(node);
            }
        }
        return result;


    }

    /**
     * 取出第一階資料
     * @return
     */
    public List<Dept> getCompany() {
    	List<Dept> list = deptRepository.findByPid((long) 0);
    	return list;
    }
    
    /**
     * 取出付款公司清單
     * @return
     */
    public List<MaaVo> getCompanyByMaa(){
    	List<MaaVo> list = new ArrayList<MaaVo>();
    	List<Dept> depList = this.getCompany();
    	
    	for(Dept obj : depList) {
    		MaaVo v = new MaaVo();
			v.setKey(obj.getTips());//id
			v.setName(obj.getSimplename());//名稱
			v.setValue(""+obj.getId());//統編
			
			list.add(v);
    	}
    	return list;
    }


}
