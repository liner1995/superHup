package cn.hups.orgs.service.impl;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.po.ClientRefPo;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.StringUtils;
import cn.hups.orgs.dao.*;
import cn.hups.orgs.po.BdCorpPo;
import cn.hups.orgs.po.BdDeptdocPo;
import cn.hups.orgs.po.BdPsndocPo;
import cn.hups.orgs.service.IOrgsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * 组织架构服务实现类
 */
@Service("iOrgsService")
public class IOrgsServiceImpl implements IOrgsService {

    @Resource
    private BdCorpMapper bdCorpMapper;

    @Resource
    private BdCorpMapperExpand bdCorpMapperExpand;

    @Resource
    private BdDeptdocMapper bdDeptdocMapper;

    @Resource
    private BdDeptdocMapperExpand bdDeptdocMapperExpand;

    @Resource
    private BdPsndocMapper bdPsndocMapper;

    @Resource
    private BdPsndocMapperExpand bdPsndocMapperExpand;

    /**
     * 分页查询所有公司
     * @param bdCorpPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdCorpAsPage(BdCorpPo bdCorpPo) throws GlobalException{
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = bdCorpMapperExpand.countData(bdCorpPo);
            List<BdCorpPo> resultList = bdCorpMapperExpand.selectAllBdCorpAsPage(bdCorpPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 查询所有公司作为参照
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdCorpAsRef() throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            List<ClientRefPo> resultRefs = bdCorpMapperExpand.selectAllBdCorpAsRef();
            ajaxJson.setObj(resultRefs);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 插入公司基本档案
     * @param bdCorpPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertBdCorp(BdCorpPo bdCorpPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCorpPo != null) {
                bdCorpMapper.insertSelective(bdCorpPo);
                ajaxJson.setPrimary(bdCorpPo.getPkCorp());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除公司档案
     * @param bdCorpPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteBdCorpByPks(BdCorpPo bdCorpPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCorpPo != null && StringUtils.isNotEmpty(bdCorpPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(bdCorpPo.getPkArr());
                bdCorpMapperExpand.deleteBdCorpAsPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键更新公司档案
     * @param bdCorpPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateBdCorp(BdCorpPo bdCorpPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdCorpPo != null && bdCorpPo.getPkCorp() != null) {
                bdCorpMapper.updateByPrimaryKeySelective(bdCorpPo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 分页查询部门基本档案
     * @param bdDeptdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdDeptdocAsPage(BdDeptdocPo bdDeptdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = bdDeptdocMapperExpand.countData(bdDeptdocPo);
            List<BdDeptdocPo> resultList = bdDeptdocMapperExpand.selectAllBdDeptdocAsPage(bdDeptdocPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据公司查询所有部门作为参照
     * @param bdDeptdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdDeptdocAsRef(BdDeptdocPo bdDeptdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdDeptdocPo != null && bdDeptdocPo.getPkCorp() != null) {
                List<ClientRefPo> resultRefs = bdDeptdocMapperExpand.selectAllBdDeptdocAsRef(bdDeptdocPo.getPkCorp());
                ajaxJson.setObj(resultRefs);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 插入部门基本档案
     * @param bdDeptdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertBdDeptdoc(BdDeptdocPo bdDeptdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdDeptdocPo != null) {
                bdDeptdocMapper.insertSelective(bdDeptdocPo);
                ajaxJson.setPrimary(bdDeptdocPo.getPkDeptdoc());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除部门档案
     * @param bdDeptdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteBdDeptdocByPks(BdDeptdocPo bdDeptdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdDeptdocPo != null && StringUtils.isNotEmpty(bdDeptdocPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(bdDeptdocPo.getPkArr());
                bdDeptdocMapperExpand.deleteBdDeptdocByPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键更新部门档案
     * @param bdDeptdocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateBdDeptdoc(BdDeptdocPo bdDeptdocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdDeptdocPo != null && bdDeptdocPo.getPkDeptdoc() != null) {
                bdDeptdocMapper.updateByPrimaryKeySelective(bdDeptdocPo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 分页查询人员信息基本档案
     * @param bdPsndocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdPsndocAsPage(BdPsndocPo bdPsndocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            Long countNum = bdPsndocMapperExpand.countData(bdPsndocPo);
            List<BdPsndocPo> resultList = bdPsndocMapperExpand.selectAllBdPsndocAsPage(bdPsndocPo);
            ajaxJson.setTotal(countNum);
            ajaxJson.setObj(resultList);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 查询所有人员信息档案作为参照
     * @return
     * @throws GlobalException
     */
    public AjaxJson selectAllBdPsndocAsRef() throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            List<ClientRefPo> resultRefs = bdPsndocMapperExpand.selectAllPsndocAsRef();
            ajaxJson.setObj(resultRefs);
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 插入人员基本档案
     * @param bdPsndocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson insertBdPsndoc(BdPsndocPo bdPsndocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdPsndocPo != null) {
                bdPsndocMapper.insertSelective(bdPsndocPo);
                ajaxJson.setPrimary(bdPsndocPo.getPkPsndoc());
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键集合删除人员信息
     * @param bdPsndocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson deleteBdPsndocByPks(BdPsndocPo bdPsndocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdPsndocPo != null && StringUtils.isNotEmpty(bdPsndocPo.getPkArr())) {
                List<Integer> pkArr = StringUtils.idsParamToListInt(bdPsndocPo.getPkArr());
                bdPsndocMapperExpand.deleteBdPsndocByPks(pkArr);
            }
        } catch (SQLException e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }

    /**
     * 根据主键更新人员信息
     * @param bdPsndocPo
     * @return
     * @throws GlobalException
     */
    public AjaxJson updateBdPsndoc(BdPsndocPo bdPsndocPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        try {
            if (bdPsndocPo != null && bdPsndocPo.getPkPsndoc() != null) {
                bdPsndocMapper.updateByPrimaryKeySelective(bdPsndocPo);
            }
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }

        return ajaxJson;
    }
}
