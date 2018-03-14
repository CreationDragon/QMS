package com.jfw.qms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping(path = "/index")
    public String getIndex() {
        return "index";
    }
}
