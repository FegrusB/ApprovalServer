package com.teamone.approvalserver;

import com.teamone.approvalserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V1Approved")
public class ApprovalServerController {

    UserService userService;

    @Autowired
    public ApprovalServerController(UserService userService) {
        this.userService = userService;
    }
}
