package cn.hups.salemage.dao;

import cn.hups.salemage.po.BdProductPo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 产品信息拓展Mapper
 */
public interface BdProductMapperExpand {

    /**
     * 查询表中数据数量
     * @param bdProductPo
     * @return
     * @throws SQLException
     */
    Long countData(@Param("bdProductPo") BdProductPo bdProductPo) throws SQLException;

    /**
     * 分页查询所有产品信息
     * @param bdProductPo
     * @return
     * @throws SQLException
     */
    List<BdProductPo> selectAllProductAsPage(@Param("bdProductPo") BdProductPo bdProductPo) throws SQLException;

    /**
     * 根据主键集合删除产品信息
     * @param pkArr
     * @return
     * @throws SQLException
     */
    int deleteProductByPs(@Param("pkArr") List<Integer> pkArr) throws SQLException;

    /**
     * 认领工艺审核任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws SQLException
     */
    int claimTecaudit(@Param("userid") Integer userid, @Param("pkProduct") Integer pkProduct, @Param("taskId") String taskId) throws SQLException;
}
