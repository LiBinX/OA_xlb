package com.xl.oa.framework.shiro;

import com.xl.oa.common.utils.AjaxUtis;
import com.xl.oa.common.utils.shiro.ShiroUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 毕业设计
 * 处理ajax Session过期请求
 */
public class MySessionExpiredFilter extends PathMatchingFilter{

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        Subject subjct = ShiroUtils.getSubjct();
        HttpServletRequest reqs = WebUtils.toHttp(request);
        String url = reqs.getRequestURI();
        //是否ajax请求
        if (AjaxUtis.isAjax(reqs))
        {
            //是否认证登录了
            if (!subjct.isAuthenticated())
            {
                //是否是登录请求
                Map<String, String> map = new HashMap<>();
                map.put("code", "1");
                map.put("msg", "会话超时/未登录！");
                AjaxUtis.out(response, map);
                return false;
            }
        }
        return true;
    }
}
