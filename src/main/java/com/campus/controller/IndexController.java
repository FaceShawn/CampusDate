package com.campus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.campus.common.ConstantMsg;
import com.campus.common.utils.SessionUtil;
import com.campus.model.User;
import com.campus.service.UserService;


/**
 * 主界面控制器
 * @author Cooper
 *
 */

@Controller
public class IndexController {
	
	@Autowired
    private UserService userService;
	
    
	/**
     * 主页面
     */
    @RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model){
    	
    	// 获取当前用户信息
		User user = SessionUtil.getUserFromSession(request);
		model.addAttribute("nowUser",user);
    	
		List<User> userList = userService.findByUserGroup(ConstantMsg.USERGROUP_TEACHER);
		model.addAttribute("users",userList);
		return "index";
    }
    
    /**
     * 跳转到个人中心页面
     */
    @RequestMapping(value="/myspace" ,method = RequestMethod.GET)
    public String me(HttpServletRequest request, Model model){
    	
    	// 获取当前用户信息
		User user = SessionUtil.getUserFromSession(request);
		model.addAttribute("nowUser",user);
        return "myspace/index";
    }
}
