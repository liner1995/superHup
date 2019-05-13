package cn.hups.orgs.dao;

import cn.hups.common.po.ClientRefPo;
import cn.hups.orgs.po.BdDeptdocPo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 部门档案拓展Mapper
 */
public interface BdDeptdocMapperExpand {

    /**
     * 查询表中数据数量
     * @param bdDeptdocPo
     * @return
     * @throws SQLException
     */
    Long countData(@Param("bdDeptdocPo") BdDeptdocPo bdDeptdocPo) throws SQLException;

    /**
     * 分页查询表中数据
     * @param bdDeptdocPo
     * @return
     * @throws SQLException
     */
    List<BdDeptdocPo> selectAllBdDeptdocAsPage(@Param("bdDeptdocPo") BdDeptdocPo bdDeptdocPo) throws SQLException;

    /**
     * 根据主键集合删除部门档案
     * @param pkArr
     * @return
     * @throws SQLException
     */
    int deleteBdDeptdocByPks(@Param("pkArr") List<Integer> pkArr) throws SQLException;

    /**
     * 根据公司查询部门信息作为参照
     * @param pkCorp
     * @return
     * @throws SQLException
     */
    List<ClientRefPo> selectAllBdDeptdocAsRef(@Param("pkCorp") Integer pkCorp) throws SQLException;
}
