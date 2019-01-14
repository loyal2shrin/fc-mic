package com.fc.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fc.api.bean.domain.Tenant;
import com.fc.api.service.TenantService;
import com.fc.base.entity.base.Response;
import com.fc.base.utils.Jsons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-12-29 17:31
 **/
@RestController
@RequestMapping("/tenant")
public class TenantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @Reference
    private TenantService tenantService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Response save(@RequestBody Tenant tenant) {
        LOGGER.info(new Jsons().toJson(tenant));
        Date date = new Date();
        tenant.setCreateUserCode("fucheng.zou");
        tenant.setCreateTime(date);
        tenant.setUpdateUserCode("fucheng.zou");
        tenant.setUpdateTime(date);
        tenant.setRecVer(1);
        tenantService.save(tenant);
        return new Response();
    }

}
