package cn.hups.technics.controller;

import cn.hups.technics.service.ITechnicsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 工艺管理Controller
 */
@RestController
@RequestMapping("/technics")
public class TechnicsController {

    @Resource
    private ITechnicsService iTechnicsService;

}
