package com.fc.mq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: fc-mic
 * @description: This is a class
 * @author: fucheng.zou
 * @create: 2018-11-02 15:51
 **/
@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(value = "/employee/add")
    public Map add(@RequestBody Map employee) {
        LOGGER.info("Employee Tenant add: {}", employee);
        return null;
    }
}
