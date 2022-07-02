package com.xl.oa.framework.shiro.service;
import com.xl.oa.project.po.User;
import com.xl.oa.project.service.user.IUserService;
import com.xl.oa.common.constant.ShiroConstants;
import com.xl.oa.common.constant.UserConstants;
import com.xl.oa.common.utils.ServletUtils;
import com.xl.oa.common.utils.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注册校验方法
 *
 * @author 毕业设计
 */
@Component
public class SysRegisterService {
    @Autowired
    private IUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    /**
     * 注册
     */
    public String register(User user) {
        String msg = "", username = user.getLoginName(), password = user.getPwd();

        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            msg = "验证码错误";
        } else if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPwd(passwordService.encryptPassword(user.getLoginName(), user.getPwd(), user.getSalt()));
            boolean regFlag = userService.registerUser(user);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {

            }
        }
        return msg;
    }
}
