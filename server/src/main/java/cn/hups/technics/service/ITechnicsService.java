package cn.hups.technics.service;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.salemage.po.BdProductPo;

/**
 * 工艺管理服务类
 */
public interface ITechnicsService {

    /**
     * 分页查询工艺审核
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson selectAllTechniceAsPage(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 工艺审核通过
     * @param bdProductPo
     * @return
     * @throws GlobalException
     */
    AjaxJson auditPassTecaudit(BdProductPo bdProductPo) throws GlobalException;

    /**
     * 认领工艺审核任务
     * @param userid
     * @param pkProduct
     * @param taskId
     * @return
     * @throws GlobalException
     */
    AjaxJson claimTecaudit(Integer userid, Integer pkProduct, String taskId) throws GlobalException;
}
