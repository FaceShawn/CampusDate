package com.campus.controller;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.utils.EncryptUtil;
import com.campus.common.utils.ErrorResponseUtil;
import com.campus.common.utils.StringUtil;
import com.campus.model.User;
import com.campus.service.UserService;

/**
 * 
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	
    @Autowired
    private UserService userService;
    
    /**
     * 跳转到管理员页面
     */
    @RequestMapping(value="/MainAdmin" ,method = RequestMethod.GET)
    public String manage(){
        return "admin/MainAdmin";
    }
    
    
    
    	
    	
    /**
     * 跳转到管理员维护页面
     */
    @RequestMapping(value="/Maintence" ,method = RequestMethod.GET)
       public String Maintence(HttpServletRequest request, Model model){
    	List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
           return "admin/Maintence";
       }
    
    

    @RequestMapping(value="/ShowState" ,method = RequestMethod.GET)  
    //@ResponseBody  
    public String number(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ModelMap map){  
        try{  //把sessionId记录在浏览器  
            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));  
            c.setPath("/");  
            //先设置cookie有效期为2天，不用担心，session不会保存2天  
            c.setMaxAge( 48*60 * 60);  
            httpServletResponse.addCookie(c);  
        }catch (Exception e){  
            e.printStackTrace();  
        }  
  
        HttpSession session = httpServletRequest.getSession();  
        Object count=session.getServletContext().getAttribute("count");  
        String StringCount=String.valueOf(count);
        map.addAttribute("count", StringCount);
        System.out.println("hello");
        return "admin/ShowState";  
    }  
    
    
    /**
     * 跳转到修改学生与老师密码页面
     */
    @RequestMapping(value="/Change" ,method = RequestMethod.GET)
    public String SATChange(HttpServletRequest request, Model model){
    List<User> userList = userService.findAll();
    model.addAttribute("users",userList);

       return "admin/Change";
    }
    
    

//    /**
//     * 跳转到院系管理页面
//     */
//    @RequestMapping(value="/dept" ,method = RequestMethod.GET)
//    public String dept(){
//        return "admin/dept";
//    }
    
}
