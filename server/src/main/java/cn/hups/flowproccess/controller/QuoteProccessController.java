package cn.hups.flowproccess.controller;

import cn.hups.common.exception.GlobalException;
import cn.hups.common.utils.AjaxJson;
import cn.hups.common.utils.StringUtils;
import cn.hups.flowproccess.po.FlowParamsPo;
import cn.hups.flowproccess.po.FlowTaskPo;
import cn.hups.flowproccess.po.QuoteFlowParam;
import cn.hups.produce.po.BillProduceQuoteHPo;
import cn.hups.produce.service.IProduceService;
import cn.hups.purchase.po.BillPurchaseQuotePo;
import cn.hups.purchase.service.IPurchaseService;
import cn.hups.rbac.service.IRbacService;
import cn.hups.salemage.po.BdProductPo;
import cn.hups.salemage.po.BillSaleQuotePo;
import cn.hups.salemage.service.ISaleService;
import cn.hups.technics.service.ITechnicsService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报价流程处理引擎
 */
@RestController
@RequestMapping("/quoteprocess")
public class QuoteProccessController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;

    @Resource
    private ResourceLoader resourceLoader;
    // 权限管理Service
    @Resource
    private IRbacService iRbacService;
    // 销售管理Service
    @Resource
    private ISaleService iSaleService;
    // 采购管理Service
    @Resource
    protected IPurchaseService iPurchaseService;
    // 工艺管理Service
    @Resource
    private ITechnicsService iTechnicsService;
    // 生产管理Service
    @Resource
    private IProduceService iProduceService;

    /**
     * 部署报价流程
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/deployQuoteFlow")
    public AjaxJson deployQuoteFlow() throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        // 报价流程文件地址
        String filePath = "bpmn/报价流程2.bpmn20.xml";
        XMLStreamReader reader = null;
        InputStream inputStream = null;
        try {
            // 通过文件方式部署流程
            XMLInputFactory factory = XMLInputFactory.newInstance();
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + filePath);
            inputStream = resource.getInputStream();
            reader = factory.createXMLStreamReader(inputStream);
            DeploymentBuilder deploymentBuilder = repositoryService.createDeployment().name("QUOTE_FLOW").category("QUOTE")
                    .addInputStream(filePath,inputStream);
            Deployment deployment = deploymentBuilder.deploy();
            ajaxJson.setObj(deployment);
        } catch (Exception e) {
            throw new GlobalException("部署流程失败!");
        } finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

        return ajaxJson;
    }

    /**
     * 启动报价流程
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/startQuoteProccess")
    public AjaxJson startQuoteProccess(@RequestBody QuoteFlowParam quoteFlowParam) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        if (quoteFlowParam != null && quoteFlowParam.getPkProduct() != null && quoteFlowParam.getUserid() != null) {
            // 流程实例设置变量
            Map<String, Object> variables = new HashMap<>();
            // 产品主键
            variables.put("pkProduct", quoteFlowParam.getPkProduct());
            // 销售报价任务接收人
            String userId = quoteFlowParam.getUserid() == null ? "" : quoteFlowParam.getUserid() + "";
            if (StringUtils.isNotEmpty(userId)) {
                variables.put("saleQuoteUser", userId);
            }
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("sm_quote_model", variables);
            // 送审工艺部
            iSaleService.commitProduct(quoteFlowParam);
        } else {
            throw new GlobalException("启动流程失败,请检查参数!");
        }
        return ajaxJson;
    }

    /**
     * 根据用户ID查询人员任务信息
     * @return
     */
    @RequestMapping("/selectTaskByUserId")
    public AjaxJson selectTaskByUserId(@RequestBody FlowParamsPo flowParamsPo) {
        AjaxJson ajaxJson = new AjaxJson();

        // 任务集合 - 最终返回集合
        List<FlowTaskPo> flowTaskPoList = new ArrayList<>();
        if (flowParamsPo != null) {
            // 用户主键
            String userId = flowParamsPo.getUserid() == null ? "" : flowParamsPo.getUserid() + "";
            if (StringUtils.isNotEmpty(userId)) {
                // 个人任务查询
                List<Task> taskUserList = taskService.createTaskQuery().taskAssignee(userId).list();
                if (taskUserList != null && taskUserList.size() > 0) {
                    for (Task task : taskUserList) {
                        System.out.println(task.getName());
                        FlowTaskPo flowTaskPo = new FlowTaskPo();
                        // 代办任务ID
                        String taskId = task.getId();
                        flowTaskPo.setTaskId(taskId);
                        // 任务名称
                        String taskName = task.getName();
                        flowTaskPo.setTaskName(taskName);
                        // 流程实例主键
                        String processInstanceId = task.getProcessInstanceId();
                        flowTaskPo.setProcessInstanceId(processInstanceId);
                        // 任务类型
                        flowTaskPo.setTaskType(FlowTaskPo.TASK_TYPE_TODO);

                        flowTaskPoList.add(flowTaskPo);
                    }
                }

                // 组任务查询
                // 根据用户主键查询用户人员类别
                String psnTypeName = iRbacService.selectPsnTypeByUserId(flowParamsPo.getUserid());
                if (StringUtils.isNotEmpty(psnTypeName)) {
                    List<Task> taskGroupList = taskService.createTaskQuery().taskCandidateGroup(psnTypeName).list();
                    if (taskGroupList != null && taskGroupList.size() > 0) {
                        for (Task task : taskGroupList) {
                            System.out.println(task.getName());
                            FlowTaskPo flowTaskPo = new FlowTaskPo();
                            // 代办任务ID
                            String taskId = task.getId();
                            flowTaskPo.setTaskId(taskId);
                            // 任务名称
                            String taskName = task.getName();
                            flowTaskPo.setTaskName(taskName);
                            // 流程实例主键
                            String processInstanceId = task.getProcessInstanceId();
                            flowTaskPo.setProcessInstanceId(processInstanceId);
                            // 任务类型
                            flowTaskPo.setTaskType(FlowTaskPo.TASK_TYPE_CLAIM);

                            flowTaskPoList.add(flowTaskPo);
                        }
                    }
                }
            } else {
                throw new GlobalException("用户不能为空!");
            }
        }

        ajaxJson.setObj(flowTaskPoList);
        return ajaxJson;
    }

    /**
     * 用户认领任务
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/claim")
    public AjaxJson claim(@RequestBody FlowParamsPo flowParamsPo) throws GlobalException {
        AjaxJson ajaxJson = new AjaxJson();

        if (flowParamsPo != null) {
            // 任务主键
            String taskId = flowParamsPo.getTaskId();
            // 任务名称
            String taskName = flowParamsPo.getTaskName();
            // 用户主键
            String userId = flowParamsPo.getUserid() == null ? "" : flowParamsPo.getUserid() + "";
            // 流程实例主键
            String processInstanceId = flowParamsPo.getProcessInstanceId();

            if (StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(processInstanceId) && StringUtils.isNotEmpty(taskName)) {
                // 根据流程实例主键获取本流程中的产品主键
                Integer pkProduct = runtimeService.getVariable(processInstanceId, "pkProduct") == null ? 0 : (Integer)runtimeService.getVariable(processInstanceId, "pkProduct");
                // 认领采购报价任务
                if ("采购报价".equals(taskName)) {
                    iPurchaseService.claimPurchaseQuote(flowParamsPo.getUserid(), pkProduct, taskId);
                }
                // 认领工艺审核任务
                if ("工艺审核".equals(taskName)) {
                    iTechnicsService.claimTecaudit(flowParamsPo.getUserid(), pkProduct, taskId);
                }
                // 认领生产报价任务
                if ("生产报价".equals(taskName)) {
                    iProduceService.claimProduceQuote(flowParamsPo.getUserid(), pkProduct, taskId);
                }
                taskService.claim(taskId, userId);
            } else {
                throw new GlobalException("认领任务失败，请检查!");
            }
        }

        return ajaxJson;
    }

    /**
     * 完成采购报价
     * @param billPurchaseQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/overPassPurchaseQuote")
    public AjaxJson overPassPurchaseQuote(@RequestBody BillPurchaseQuotePo billPurchaseQuotePo) throws GlobalException {
        // 任务主键
        String taskId = billPurchaseQuotePo.getTaskId();
        if (StringUtils.isNotEmpty(taskId)) {
            taskService.complete(taskId);
            return iPurchaseService.overPassPurchaseQuote(billPurchaseQuotePo);
        } else {
            throw new GlobalException("审核失败,任务信息为空!");
        }
    }

    /**
     * 工艺审核通过
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/auditPassTecaudit")
    public AjaxJson auditPassTecaudit(@RequestBody BdProductPo bdProductPo) throws GlobalException {
        // 任务主键
        String taskId = bdProductPo.getTaskId();
        if (StringUtils.isNotEmpty(taskId)) {
            taskService.complete(taskId);
            return iTechnicsService.auditPassTecaudit(bdProductPo);
        } else {
            throw new GlobalException("审核失败,任务信息为空!");
        }
    }

    /**
     * 完成生成报价
     * @param billProduceQuoteHPo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/overPassProduceQuote")
    public AjaxJson overPassProduceQuote(@RequestBody BillProduceQuoteHPo billProduceQuoteHPo) throws GlobalException {
        // 任务主键
        String taskId = billProduceQuoteHPo.getTaskId();
        if (StringUtils.isNotEmpty(taskId)) {
            taskService.complete(taskId);
            return iProduceService.overPassProduceQuote(billProduceQuoteHPo);
        } else {
            throw new GlobalException("审核失败,任务信息为空!");
        }
    }

    /**
     * 完成销售报价
     * @param billSaleQuotePo
     * @return
     * @throws GlobalException
     */
    @RequestMapping("/overProccessSaleQupte")
    public AjaxJson overProccessSaleQupte(@RequestBody BillSaleQuotePo billSaleQuotePo) throws GlobalException {
        // 任务主键
        String taskId = billSaleQuotePo.getTaskId();
        if (StringUtils.isNotEmpty(taskId)) {
            taskService.complete(taskId);
            return iSaleService.updateSaleQuote(billSaleQuotePo);
        } else {
            throw new GlobalException("审核失败,任务信息为空!");
        }
    }
}
