package com.campus.controller;

import com.campus.common.ConstantMsg;
import com.campus.common.utils.EncryptUtil;
import com.campus.common.utils.ErrorResponseUtil;
import com.campus.common.utils.SessionUtil;
import com.campus.common.utils.StringUtil;
import com.campus.model.Dept;
import com.campus.model.User;
import com.campus.service.DeptService;
import com.campus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
	private DeptService deptService;


    /**
     * 跳转到用户登录页面
     */
    @RequestMapping(value="/login" ,method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    
    /**
     * 跳转到用户注册页面
     */
    @RequestMapping(value="/signup" ,method = RequestMethod.GET)
    public String register(){
        return "user/signup";
    }
    

    /**
     * 用户登录
     */
    @RequestMapping(value="/logining", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String logining(HttpServletRequest request){
    	
    	// 验证用户是否已登录
        if(SessionUtil.isLogin(request)) {
        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_ALREADY_LOGIN);
        }
        
        // 随机登陆用户
        if(ConstantMsg.DEBUG) {
        	Random random = new Random();
        	int id = random.nextInt(58);
        	List<User> userList = this.userService.findByUserGroup(ConstantMsg.DEBUG_GROUP);
        	User user = userList.get(id % userList.size());
        	SessionUtil.login(user, request);
        	return ErrorResponseUtil.setResponse("200", ConstantMsg.LOGIN_SUCCESS);
        }
        
        // 获取传递过来的参数
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 验证参数是否合法
        if(StringUtil.isEmpty(email)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.EMAIL_IS_NULL);
        }
        if(StringUtil.isEmpty(password)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.PASSWORD_IS_NULL);
        }
        
        User user;
        // 验证用户是否已注册
        try {
        	// 根据邮箱在数据库中查找用户
        	user = userService.findByEmail(email);
        }catch (Exception e){
        	// 用户未注册
            user = null;
        }
        if(null==user) {
        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_REGISTERED);
        }
        
        // 验证密码是否正确
        String shaPwd ;
        try {
        	// 用sha-1为传递过来的密码加密
            shaPwd = EncryptUtil.encryptSHA(password) ;
        } catch (NoSuchAlgorithmException e) {
            shaPwd = "" ;
        }
        if( shaPwd.equals(user.getPassword()) ) {      
        	// 将登录后的用户信息写入session
        	SessionUtil.login(user, request);
        	Date lastLoginTime = new Date();
            user.setLastLoginTime(lastLoginTime);
            try {
                userService.update(user);
            }catch(Exception e){
                return ErrorResponseUtil.setResponse("400", e.getMessage());
            }
            if(user.getUserGroup().equals("admin")) {
            	 return ErrorResponseUtil.setResponse("300",  user.getUserGroup()+" / "+ConstantMsg.LOGIN_SUCCESS);
            }
            
            return ErrorResponseUtil.setResponse("200",  user.getUserGroup()+" / "+ConstantMsg.LOGIN_SUCCESS);
        }else{
            return ErrorResponseUtil.setResponse("400", ConstantMsg.PASSWORD_IS_ERROR);
        }
    }
    

    /**
     * 用户注册
     *
     * @param request
     */
    @RequestMapping(value="/signuping", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String registering(HttpServletRequest request){
    	
    	// 验证用户是否已登录
        if(SessionUtil.isLogin(request)) {
        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_ALREADY_LOGIN);
        }
        
        // 获取传递过来的参数
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userName	= request.getParameter("userName");
        String userGroup = request.getParameter("userGroup");
        String deptCode = request.getParameter("deptCode");
        String userIntro = request.getParameter("userIntro");
        String userSex = null;
        String userPhone = null;
        
        // 调试时随机构造传递过来的参数
        if(ConstantMsg.DEBUG) {
        	System.out.println("\n\n前端提交：");
        	System.out.println("email="+email+"\n"
        			+"password="+password+"\n"
        			+"confirmPassword="+confirmPassword+"\n"
        			+"username="+userName+"\n"
        			+"usergroup="+userGroup+"\n"
        			+"dept="+deptCode+"\n"
        			+"introduction="+userIntro);
        	
        	// 构造随机数
        	Random random = new Random();
        	int i = random.nextInt(200);
        	int j = random.nextInt(25);
        	
        	// 随机取年级
        	String[] gradeList = {"14", "15", "16", "17", "18"};
        	String grade = gradeList[i % gradeList.length];
        	
        	// 随机取院系代码
        	String[] deptList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "99"};
        	deptCode = deptList[j % deptList.length];
        	
        	// 随机取专业代码
        	String[] majorList = {"1", "2", "3", "4", "5"};
        	String major = majorList[i % majorList.length];
        	
        	// 随机取班级代码
        	String[] classList = {"01", "02", "03", "04", "05"};
        	String cla = classList[i % classList.length];
        	
        	// 随机取2位学号
        	int num = i % 30;
        	String nums;
        	if(num == 0) {
        		nums = "30";
        	}else if(num < 10) {
        		nums = "0"+i;
        	} else {
        		nums = ""+i;
        	}
        	
        	// 构造学号
        	String stunum = grade+deptCode+major+cla+nums;
        	
        	// 邮箱
        	email = stunum+"@stu.hit.edu.cn";
        	
        	// 密码同学号
        	password = stunum;
        	confirmPassword = stunum;
        	
        	// 从数组中随机取一个名字
        	String[] userNameList = {"魏无羡", "蓝忘机", "温宁", "薛洋", "晓星尘", "蓝愿", "金凌", "蓝曦臣", 
        			"金光瑶", "聂明玦", "江厌离", "温如冰", "宋岚"};
        	userName = userNameList[i % userNameList.length];
        	
        	// 从数组中随机取一个用户组
        	String[] userGroupList = {"student", "teacher", "alu"};
        	userGroup = userGroupList[i % userGroupList.length];
        	
        	
        	// 性别
        	String[] userSexList = {"男", "女"};
        	userSex = userSexList[i % userSexList.length];
        	
        	// 电话
        	int phoneLast = (int)((Math.random()*9+1)*10000);
        	
        	String[] phoneFirstList = {"1786270", "1786313", "1786431", "1786525"};
        	String phoneFirst = phoneFirstList[i % phoneFirstList.length];
        	
        	userPhone = phoneFirst + phoneLast;
        	
        	System.out.println("\ndebug自动赋值：");
        	System.out.println("i="+i+"\n"
        			+"email="+email+"\n"
        			+"password="+password+"\n"
        			+"confirmPassword="+confirmPassword+"\n"
        			+"username="+userName+"\n"
        			+"usergroup="+userGroup+"\n"
        			+"dept="+deptCode+"\n"
        			+"introduction="+userIntro);
        	System.out.println("-------------------------");
        	
        }

        // 验证参数是否合法
        if(StringUtil.isEmpty(email)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.EMAIL_IS_NULL);
        }
        if(StringUtil.isEmpty(password)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.PASSWORD_IS_NULL);
        }
        if(StringUtil.isEmpty(confirmPassword)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.CONFIRMPASSWORD_IS_NULL);
        }
        if(!password.equals(confirmPassword)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.TWO_PASSWORDS_ARE_NOT_SAME);
        }
        if(StringUtil.isEmpty(userName)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.USERNAME_IS_NULL);
        }
        if(StringUtil.isEmpty(userGroup)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.USERGROUP_IS_NULL);
        }
        if(StringUtil.isEmpty(deptCode)) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.DEPTCODE_IS_NULL);
        }
        
        // 验证用户是否已注册
        User user;
        try {
            user = userService.findByEmail(email);
        }catch(Exception e){
            user = null;
        }
        if(user != null){
            return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_ALREADY_REGISTERED);
        }
        
//        // md5 加密密码
//        String md5Pwd = EncryptUtil.getMD5(password);
        // sha-1 加密密码
        String shaPwd;
        try {
            shaPwd = EncryptUtil.encryptSHA(password) ;
        } catch (NoSuchAlgorithmException e) {
            return ErrorResponseUtil.setResponse("400", ConstantMsg.PASSWORD_CAN_NOT_BE_ENCRYPTED);
        }
        
        // 节次名字
        Dept dept = deptService.findByDeptCode(deptCode);
        String deptName = dept.getDeptName();
        if(ConstantMsg.DEBUG) {
        	System.out.println(deptCode+deptName);
        }
        // 构造用户实例
        Date date = new Date();
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(shaPwd);
        newUser.setUserGroup(userGroup);
        newUser.setUserState(ConstantMsg.USER_STATE_UNCHECK);	// 用户状态为未验证
        newUser.setUserName(userName);
        newUser.setDeptCode(deptCode);
        newUser.setDeptName(deptName);
        newUser.setUserIntro(userIntro);
        newUser.setUserSex(userSex);
        newUser.setUserPhone(userPhone);
        newUser.setCreateTime(date);
        newUser.setUpdateTime(date);
        newUser.setLastLoginTime(date);
        
        // 将新用户写入数据库
        try {
            userService.insert(newUser);
        }catch(Exception e){
            return ErrorResponseUtil.setResponse("400", e.getMessage());
        }
        
        // 向用户邮箱发送验证链接
        // 此功能需结合user表中state字段来标志是否已验证
        // 后续待开发
        
        if(ConstantMsg.DEBUG) {
        	return ErrorResponseUtil.setResponse("400", ConstantMsg.REGISTER_SUCCESS);
        }else {
        	SessionUtil.login(newUser, request);
        	return ErrorResponseUtil.setResponse("200", ConstantMsg.REGISTER_SUCCESS);
        }
    }
    

//    /**
//     * 跳转到用户列表
//     */
//    @RequestMapping(value="/user-list" ,method = RequestMethod.GET)
//    public String userList(HttpServletRequest request, Model model){
////        if(!SessionUtil.isLogin(request))
////            return "redirect:login";
////        // 获取当前用户信息
////        User user = SessionUtil.getUserFromSession(request);
////        model.addAttribute("user",user);
////        List<User> userList = userService.findAll();
////        model.addAttribute("users",userList);
//
//        return "login/user-list";
//    }
  
    
//    /**
//     * 根据id删除用户
//     */
//    @RequestMapping(value = "/delete", method = RequestMethod.GET)
//    public String goDelete(HttpServletRequest request) {
//        int userid = Integer.valueOf(request.getParameter("userid"));
//        userService.delete(userid);
//        return "redirect:/user-list";
//    }
    
    
    
    /**
     * 根据院系搜索老师
     */
	@RequestMapping(value = "/select/{deptCode}", method = RequestMethod.GET)
	private String list(@PathVariable("deptCode") String deptCode, Model model){
		List<User> userList;
		if(deptCode.equals(ConstantMsg.DEPT_ALL)) {
			userList = userService.findByUserGroup(ConstantMsg.USERGROUP_TEACHER);
		}else {
			userList = userService.findByUserGroupAndDeptCode(ConstantMsg.USERGROUP_TEACHER, deptCode);
		}
		model.addAttribute("users",userList);
		return "index";
	}
	
	
    
    /**
     * 登出
     */
    @RequestMapping(value="/logout" ,method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
    	
    	// 验证用户是否已登录？
        SessionUtil.logout(request);
        return "redirect:/";
    }
}
