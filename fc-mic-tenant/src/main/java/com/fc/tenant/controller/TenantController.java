package com.fc.tenant.controller;

import com.fc.base.entity.base.Response;
import com.fc.base.utils.Jsons;
import com.fc.tenant.bean.domain.Tenant;
import com.fc.tenant.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
