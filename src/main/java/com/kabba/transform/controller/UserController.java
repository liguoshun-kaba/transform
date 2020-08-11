package com.kabba.transform.controller;

import com.kabba.transform.response.ResultDO;
import com.kabba.transform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transform")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResultDO bookDetail(){
        userService.transformUser();
        return new ResultDO().success();
    }

}
