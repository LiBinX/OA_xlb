package com.xl.oa.project.controller;

import com.xl.oa.project.po.Dept;
import com.xl.oa.project.po.Position;
import com.xl.oa.project.po.Role;
import com.xl.oa.project.po.User;
import com.xl.oa.project.service.dept.IDeptService;
import com.xl.oa.project.service.position.IPositionService;
import com.xl.oa.project.service.role.IRoleService;
import com.xl.oa.project.service.user.IUserService;
import com.xl.oa.common.constant.CsEnum;
import com.xl.oa.common.exception.file.FileNameLengthException;
import com.xl.oa.common.exception.file.FileSizeException;
import com.xl.oa.common.utils.HttpHeaderUtil;
import com.xl.oa.common.utils.StringUtils;
import com.xl.oa.common.utils.file.UploadFile;
import com.xl.oa.common.utils.shiro.ShiroUtils;
import com.xl.oa.framework.annotation.Operlog;
import com.xl.oa.framework.shiro.service.SysPasswordService;
import com.xl.oa.framework.web.controller.BaseController;
import com.xl.oa.framework.web.page.TableDataInfo;
import com.xl.oa.framework.web.po.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author 毕业设计
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private String prefix = "system/user/";

    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IDeptService iDeptService;
    @Autowired
    IPositionService iPositionService;

    @Autowired
    private SysPasswordService passwordService;



    /**
     *
     * @描述 跳转到用户页面
     *
     * @date 2022/4/27 10:54
     */
    @RequestMapping("/tolist")
    @RequiresPermissions("user:list")
    public String toUserList()
    {
        return prefix + "user";
    }


    /**
     * @描述 用户数据
     * @date 2022/4/27 12:30
     */
    @RequestMapping("/tableList")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> users = iUserService.selectByUser(user);

        return getDataTable(users);
    }


    /**
     * 编辑用户 system/user/edit/20180914-1
     */
    @RequiresPermissions("user:update")
    @RequestMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") String userId, Model model)
    {
        // 个人信息
        User user = iUserService.selectByPrimaryKey(userId);
        Map<String, Object> role_post_dept = getRole_Post_Dept();
        model.addAttribute("depts", role_post_dept.get("dept"));
        model.addAttribute("roles", role_post_dept.get("role"));
        model.addAttribute("positions", role_post_dept.get("position"));
        user.setPwd(null);
        model.addAttribute("user", user);
        return prefix + "edit";
    }

    /**
     *
     * @描述 保存用户
     *
     * @date 2022/4/27 18:53
     */
    @PostMapping("/editSave")
    @RequiresPermissions("user:update")
    @Operlog(modal = "用户管理", descr = "修改用户信息")
    @ResponseBody
    public AjaxResult save(User user)
    {
        if (StringUtils.isNotNull(user.getUid()) && User.isBoss(user.getUid()))
        {
            return error("不允许修改管理员用户");
        }

        if(!StringUtils.isEmpty(user.getPwd())){
            user.setSalt(ShiroUtils.randomSalt());
            SimpleHash md5 = new SimpleHash("MD5", user.getPwd(), user.getSalt(), 1024);
            user.setPwd(md5.toHex());
        }else
            user.setPwd(null);

        return result(iUserService.updateByPrimaryKeySelective(user));
    }


    /**
     * @描述 添加用户页面
     * @date 2022/4/27 18:46
     */
    @RequestMapping("/toAdd")
    @RequiresPermissions("user:add")
    public String toaddUser(Model model)
    {
        Map<String, Object> role_post_dept = getRole_Post_Dept();
        model.addAttribute("depts", role_post_dept.get("dept"));
        model.addAttribute("roles", role_post_dept.get("role"));
        model.addAttribute("positions", role_post_dept.get("position"));
        return prefix + "add";
    }

    /**
     *
     * @描述 添加用户
     *
     * @date 2022/4/27 20:40
     */

    @RequestMapping("/addSave")
    @RequiresPermissions("user:add")
    @Operlog(modal = "用户管理", descr = "添加用户")
    @ResponseBody
    public AjaxResult addUser(User user)
    {
        user.setSalt(ShiroUtils.randomSalt());
        SimpleHash md5 = new SimpleHash("MD5", user.getPwd(), user.getSalt(), 1024);
        user.setPwd(md5.toHex());
        user.setAvatar(CsEnum.avatar.USER_AVATAR.getValue());
        user.setCreateTime(new Date());
        return result(iUserService.insertSelective(user));
    }

    /**
     *
     * @描述 批量删除
     *
     * @date 2022/4/27 9:31
     */
    @RequestMapping("/del")
    @RequiresPermissions("user:del")
    @Operlog(modal = "用户模块", descr = "删除用户")
    @ResponseBody
    public AjaxResult delByUserIds(String[] ids)
    {
        try
        {
            int i = iUserService.deleteByPrimaryKeys(ids);
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
        return success();
    }

    /**
     *
     * @描述 编辑密码修改页面
     *
     * @date 2022/4/27 10:25
     */
    @RequestMapping("/resetPwd/{userId}")
    @RequiresPermissions("user:update")
    public String editPwd(@PathVariable("userId") String id, Model model)
    {
        model.addAttribute("uid", id);
        return prefix + "resetPwd";
    }


    /**
     *
     * @描述 密码修改
     *
     * @date 2022/4/27 10:42
     */

    @RequestMapping("/resetPwd")
    @RequiresPermissions("user:update")
    @Operlog(modal = "用户模块", descr = "修改密码")
    @ResponseBody
    public AjaxResult resetPwd(User user)
    {
        return result(iUserService.resrtPwd(user));
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = iUserService.checkPhoneUnique(user);
        }
        return uniqueFlag;
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = iUserService.checkEmailUnique(user);
        }
        return uniqueFlag;
    }


    /**
     *
     * @描述: 校验登录名唯一性
     *
     * @params:
     * @return:
     * @date: 2022/4/27 17:06
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user)
    {
        String uniqueFlag = "0";
        if (user != null)
        {
            uniqueFlag = iUserService.checkLoginNameUnique(user);
        }
        return uniqueFlag;
    }


    public Map<String, Object> getRole_Post_Dept()
    {
        Map<String, Object> map = new HashMap<>();
//        角色
        List<Role> roles = iRoleService.selectRoleList(new Role());
//        部门信息
        List<Dept> depts = iDeptService.selectDeptList(new Dept());
//        岗位
        List<Position> positions = iPositionService.selectPositionList(new Position());
        map.put("role", roles);
        map.put("dept", depts);
        map.put("position", positions);

        return map;
    }


    /**
     * 用户个人信息查看页面
     */
    @RequestMapping("/myMsg")
    public String ToMyMsg(Model model, HttpServletRequest request)
    {
        User user = iUserService.selectByPrimaryKey(getUserId());
        model.addAttribute("user", user);
        model.addAttribute("loginIp", HttpHeaderUtil.getIpAddr(request));
        return prefix + "profile/msg";
    }


    /**
     * 密码修改页面
     */
    @RequestMapping("/resetMyPwd")
    public String toResetPwd(Model model)
    {
        User user = iUserService.selectByPrimaryKey(getUserId());
        model.addAttribute("user", user);
        return prefix + "profile/resetPwd";
    }

    /**
     * 密码修改保存
     */
    @RequestMapping("/updateMyPwdSave")
    @ResponseBody
    @RequiresPermissions("user:update")
    @Operlog(modal = "个人信息", descr = "修改密码")
    public AjaxResult updateMyPwdSave(String password)
    {
        User user = new User();
        user.setSalt(ShiroUtils.randomSalt());
        SimpleHash md5 = new SimpleHash("MD5", password, user.getSalt(), 1024);
        user.setPwd(md5.toHex());
        user.setUid(getUserId());
        int i = iUserService.updateByPrimaryKeySelective(user);
        if (i > 0)
        {
            //更新shiro中的信息
            ShiroUtils.reloadUser(iUserService.selectByPrimaryKey(getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 编辑用户头像修改
     */
    @RequestMapping("/updateAvatar")
    public String toupdateAvatar(Model model)
    {
        model.addAttribute("user", getUser());
        return prefix + "profile/avatar";
    }

    /**
     * 修改保存用户头像
     */
    @RequestMapping("/updateAvatarSave")
    @RequiresPermissions("user:update")
    @Operlog(modal = "个人信息", descr = "修改头像")
    @ResponseBody
    public AjaxResult toupdateAvatar(MultipartFile file)
    {
        try
        {
            String imgPath = UploadFile.uploadUserImg(file);
            if (StringUtils.isEmpty(imgPath))
            {
                return error("图片上传失败，稍后再试！");
            }

            User user = new User();
            user.setUid(getUserId());

            user.setAvatar(imgPath);
            int i = iUserService.updateByPrimaryKeySelective(user);
            if (i > 0)
            {
                ShiroUtils.reloadUser(iUserService.selectByPrimaryKey(getUserId()));
            }
            return result(i);
        }
        catch (IOException e)
        {
            return error();
        }
        catch (FileSizeException e)
        {
            //文件过大
            return error(e.getMsg());
        }
        catch (FileNameLengthException e)
        {
            //文件名字超长
            return error(e.getMsg());
        }
    }


    /**
     * 校验密码和原来密码是否相同
     */
    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        //加密后与数据库密码比较
        User user = getUser();
        SimpleHash md5 = new SimpleHash("MD5", password, user.getSalt(), 1024);
        String oldPassword = md5.toHex();
        String pwd = getPwd();
        if (pwd.equals(oldPassword))
        {
            return true;
        }
        return false;
    }


}
