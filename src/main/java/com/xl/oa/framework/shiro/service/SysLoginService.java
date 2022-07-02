package com.xl.oa.framework.shiro.service;

import com.xl.oa.project.service.user.IUserService;
import com.xl.oa.common.constant.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 * 
 * @author 毕业设计
 */
@Component
public class SysLoginService
{

    @Autowired
    private IUserService userService;


    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }

}
