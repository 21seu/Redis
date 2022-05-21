package com.hmdp.config;

import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.utils.UserHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by fengtj on 2022/5/21 13:15
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        //获取session中的用户
        User user = (User) session.getAttribute("user");
        //判断是否存在
        if (user == null) {
            //不存在，拦截 返回401状态码
            response.setStatus(401);
        }
        //存在，保存到ThreadLocal
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        UserHolder.saveUser(userDTO);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
