package com.campus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campus.common.ConstantMsg;
import com.campus.common.utils.ErrorResponseUtil;
import com.campus.common.utils.SessionUtil;
import com.campus.common.utils.StringUtil;
import com.campus.model.Freetime;
import com.campus.model.Section;
import com.campus.model.User;
import com.campus.service.DeptService;
import com.campus.service.FreetimeService;
import com.campus.service.SectionService;
import com.campus.service.UserService;
import com.google.gson.Gson;

import groovyjarjarcommonscli.ParseException;

@Controller
@RequestMapping(value="/freetime")
public class FreetimeController {

		@Autowired
	    private FreetimeService freetimeService;
		@Autowired
		private UserService userService;
		@Autowired
		private SectionService sectionService;
		
		
		User sessionUser = null;

		/**
	     * 跳转到添加空闲时间页面
	     */
	    @RequestMapping(value="/add" ,method = RequestMethod.GET)
	    public String addFreetime(){
	        return "freetime/add";
	    }
	    
	    /**
	     * 添加空闲时间
	     *
	     * @param request
	     */
	    @RequestMapping(value="/adding", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ResponseBody
	    public String registering(HttpServletRequest request) throws java.text.ParseException, ParseException{
	        
	    	// 获取传递过来的参数
	        String freeDate 	= request.getParameter("freeDate");
	        String sectionCode	= request.getParameter("sectionCode");
	        String freeNote		= request.getParameter("freeNote");
	        String freeState=null;
	        String reserverName=null;
	        String reserverMsg=null;
	        
	        String allowStr = request.getParameter("allowNum");
	        if(allowStr==null || !StringUtil.isNumeric(allowStr) ) {
	        	allowStr = "1";
	        }
	        int allowNum = Integer.parseInt(allowStr);
	        
//	        // 判断是否为数字
//	        int allowNum;
//	        if( StringUtil.isNumeric( request.getParameter("allowNum") ) ) {
//	        	allowNum = Integer.parseInt(request.getParameter("allowNum"));
//	        }else {
//	        	allowNum = 1;
//	        }
	        
	    	// 调试时随机构造传递过来的参数
	        if(ConstantMsg.DEBUG) {
	        	System.out.println("\n\n前端提交：");
	        	System.out.println("freeDate="+freeDate+"\n"
	        			+"sectionCode="+sectionCode);
	        	
	        	// 随机教师用户登陆
	        	Random random = new Random();
	        	int id = random.nextInt(200);
	        	List<User> userList = this.userService.findByUserGroup(ConstantMsg.USERGROUP_TEACHER);
	        	User user = userList.get(id % userList.size());
	        	SessionUtil.login(user, request);
	        	
	        	// 从数组中随机取一个节次
	        	String[] sectionCodeList = {"1", "2", "3", "4", "5", "6", "7",
	        								"8", "9", "10", "11"};
	        	sectionCode = sectionCodeList[id % sectionCodeList.length];
	        	
	        	// 取得当前时间戳（精确到秒）
	        	long time = 1526642665;
	        	// 随机生成一个月以内的秒数
	        	long j = random.nextInt(2592000);
	        	time = time + j;
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	            freeDate = sdf.format(new Date(Long.valueOf(time+"000")));  
	            
	            // 预约人数
	            allowNum = random.nextInt(10);
	            if(allowNum == 0) {
	            	allowNum = 1;
	            }
	            
	        	System.out.println("\ndebug自动赋值：");
	        	System.out.println("freeDate="+freeDate+"\n"
	        			+"sectionCode="+sectionCode);
	        	System.out.println("-------------------------");
	        }else {
	        }
	       
	        // 验证用户是否已登录
	        if(SessionUtil.isLogin(request)) {
	        	this.sessionUser = SessionUtil.getUserFromSession(request);
	        }else {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_LOGIN);
	        }
	        // 验证用户是否有权限操作
	        if(!this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_TEACHER)) {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_PERMITTED_TO_OOPERATE);
	        }
	        
	        // 验证参数是否合法
	        if(StringUtil.isEmpty(freeDate)) {
	            return ErrorResponseUtil.setResponse("400", ConstantMsg.FREE_DATE_IS_NULL);
	        }
	        if(StringUtil.isEmpty(sectionCode)) {
	            return ErrorResponseUtil.setResponse("400", ConstantMsg.SESSION_CODE_IS_NULL);
	        }
	        
//	        // 验证时间是否已添加过
//	        List<Freetime> freetimeList = null;
//	        try {
//	            freetimeList = freetimeService.findByFreeDateAndSectionCode(freeDate, sectionCode);
//	        }catch(Exception e){
//	            freetimeList = null;
//	        }
//	        if(freetimeList!=null){
//	            return ErrorResponseUtil.setResponse("400", ConstantMsg.FREETIME_IS_ALREADY_ADDED);
//	        }
	        
	        // 节次名字
	        Section section = sectionService.findBySectionCode(sectionCode);
	        String sectionName = section.getSectionName();
	        if(ConstantMsg.DEBUG) {
	        	System.out.println(sectionCode+sectionName);
	        }
	        
	        // 构造空闲时间实例
	        Date date = new Date();
	        Freetime newFreetime = new Freetime();

	        newFreetime.setFreeDate(freeDate);
	        newFreetime.setSectionCode(sectionCode);
	        newFreetime.setSectionName(sectionName);
    		newFreetime.setOwnerId(this.sessionUser.getId());
    		newFreetime.setOwnerName(this.sessionUser.getUserName());
    		newFreetime.setFreeState(ConstantMsg.FREETIME_STATE_FREE);
    		newFreetime.setReserverId(null);
    		newFreetime.setReserverName(reserverName);
    		newFreetime.setAllowNum(allowNum);
    		newFreetime.setReservedNum(0);
	        newFreetime.setFreeNote(freeNote);
	        newFreetime.setReserverMsg(reserverMsg);
	        newFreetime.setCreateTime(date);
	        newFreetime.setUpdateTime(null);
    	    
	        // 将新空闲时间写入数据库
	        try {
	            freetimeService.insert(newFreetime);
	        }catch(Exception e){
	            return ErrorResponseUtil.setResponse("400", e.getMessage());
	        }
	        return ErrorResponseUtil.setResponse("200", ConstantMsg.FREETIME_ADD_SUCCESS);
	    }
	    
	    
	    /**
	     * 根据 ownerId 查看空闲时间列表
	     */
		@RequestMapping(value = "/list/{ownerId}", method = RequestMethod.GET)
		private String list(@PathVariable("ownerId") String ownerId, HttpServletRequest request, Model model){
			
			// 验证参数是否合法
			if(null == ownerId) {
	        	model.addAttribute("errmsg", ConstantMsg.OWNER_ID_IS_NULL);
	        	return "freetime/list";
	        }
	        
	        List<Freetime> freetimeList = freetimeService.findAllByOwnerId(ownerId);
			
			// 验证 freetimeList 是否为空
			if(null == freetimeList) {
				model.addAttribute("errmsg", ConstantMsg.FREETIME_LIST_IS_NULL);
	        	return "freetime/list";
	        }
			
			// 获取当前用户信息
			User user = SessionUtil.getUserFromSession(request);
			model.addAttribute("nowUser",user);
			
			model.addAttribute("freetimes",freetimeList);
			return "freetime/list";
		}
		
		
		
		 /**
	     * 查看我发布的空闲时间
	     */
		@RequestMapping(value = "/my-add", method = RequestMethod.GET)
		private String allList(HttpServletRequest request, Model model){
			
			// 验证用户是否已登录
	        if(SessionUtil.isLogin(request)) {
	        	this.sessionUser = SessionUtil.getUserFromSession(request);
	        }else {
	        	model.addAttribute("errmsg", ConstantMsg.USER_IS_NOT_LOGIN);
	        	return "myspace/my-add";
	        }
	        
	        // 验证用户是否有权限操作
	        if(!this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_TEACHER)) {
	        	model.addAttribute("errmsg", ConstantMsg.USER_IS_NOT_PERMITTED_TO_OOPERATE);
	        	return "myspace/my-add";
	        }
	        
	        // 获取当前用户信息
			User user = SessionUtil.getUserFromSession(request);
			model.addAttribute("nowUser",user);
			
	        List<Freetime> freetimeList = freetimeService.findAllByOwnerId(this.sessionUser.getId());
			model.addAttribute("freetimes",freetimeList);
			return "myspace/my-add";
		}
		
		
		
		/**
	     * 查看我发布的空闲时间
	     */
		@RequestMapping(value = "/my-reserve", method = RequestMethod.GET)
		private String myReserve(HttpServletRequest request, Model model){
			
			// 验证用户是否已登录
	        if(SessionUtil.isLogin(request)) {
	        	this.sessionUser = SessionUtil.getUserFromSession(request);
	        }else {
	        	model.addAttribute("errmsg", ConstantMsg.USER_IS_NOT_LOGIN);
	        	return "myspace/my-reserve";
	        }
	        
//	        // 验证用户是否有权限操作
//	        if(!this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_STUDENT)
//	        		|| !this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_ALU)) {
//	        	model.addAttribute("errmsg", ConstantMsg.USER_IS_NOT_PERMITTED_TO_OOPERATE);
//	        	return "myspace/my-reserve";
//	        }
	        
	        // 获取当前用户信息
 			User user = SessionUtil.getUserFromSession(request);
 			model.addAttribute("nowUser",user);
	     			
	        List<Freetime> freetimeList = freetimeService.findAllByReserveId(this.sessionUser.getId());
			model.addAttribute("freetimes",freetimeList);
			return "myspace/my-add";
		}
		
		
		
	    /**
	     * 根据 fid 查看空闲时间详情
	     */
		@RequestMapping(value = "/detail/{fid}", method = RequestMethod.GET)
		private String detail(@PathVariable("fid") String fid, HttpServletRequest request,Model model){
				
			// 验证参数是否合法
			if(null == fid) {
	        	model.addAttribute("errmsg", ConstantMsg.FREETIME_ID_IS_NULL);
	        	return "freetime/detail";
	        }
			
			// 验证空闲时间是否已添加
	        Freetime freetime = null;
	        try {
	        	// 根据 id 在数据库中查找空闲时间
	        	freetime= freetimeService.findById(fid);
	        }catch (Exception e){
	        	// 空闲时间未添加
	            freetime = null;
	        }
	        if(null == freetime) {
	        	model.addAttribute("errmsg", ConstantMsg.FREETIME_IS_NOT_ADDED);
	        	return "freetime/detail";
	        }
	        
	        // 获取当前用户信息
 			User user = SessionUtil.getUserFromSession(request);
 			model.addAttribute("nowUser",user);
	        
			model.addAttribute("freetime",freetime);
			return "freetime/detail";
		}
		
		
		/**
	     * 根据 fid 预约空闲时间
	     */
	    @RequestMapping(value = "/reserve",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	    @ResponseBody
	    public String reserveByFreetimeId(HttpServletRequest request) throws java.text.ParseException, ParseException{
	        
//	    	Map<String,String> response = new HashMap<String,String>();
//	        String result = "this is result";
//			response.put("result", result);
//			response.put("errcode", "200");
//			response.put("errmsg", "hhaha");
//			Gson gson = new Gson();
//			return gson.toJson(response);
			
//	    	Map<String,List<User>>  response = new HashMap<String,List<User>>();
//			List<User> userList = userService.findByUserGroup(ConstantMsg.USERGROUP_STUDENT);
//			response.put("user1", userList);
//			Gson gson = new Gson();
//			return gson.toJson(response);
			
			
	    	// 验证用户是否已登录
	        if(SessionUtil.isLogin(request)) {
	        	this.sessionUser = SessionUtil.getUserFromSession(request);
	        }else {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_LOGIN);
	        }
	        
	        // 验证用户是否有权限操作
	        if(this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_TEACHER)) {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_PERMITTED_TO_OOPERATE);
	        }

	        // 获取传递过来的参数
	        String fid = request.getParameter("freetimId");
	        String reserverMsg = request.getParameter("reserverMsg");
	      
	        // 验证参数是否合法
	        if(StringUtil.isEmpty(fid)) {
	            return ErrorResponseUtil.setResponse("400", ConstantMsg.FREETIME_ID_IS_NULL);
	        }
	        
	        // 验证空闲时间是否已添加
	        Freetime freetime;
	        try {
	        	// 根据 id 在数据库中查找空闲时间
	        	freetime= freetimeService.findById(fid);
	        }catch (Exception e){
	        	// 空闲时间未添加
	            freetime = null;
	        }
	        if(null==freetime) {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.FREETIME_IS_NOT_ADDED);
	        }
	        
	        // 预约人数已满
	        if(freetime.getFreeState().equals(ConstantMsg.FREETIME_STATE_BUSY)) {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_RESERVE_NUM_IS_FULL);
	        }
	        
	        // 更新预约人数
	        int reservedNum = freetime.getReservedNum() + 1;
	        freetime.setReservedNum(reservedNum);
	        if(freetime.getReservedNum()>=freetime.getAllowNum()) {
	        	freetime.setFreeState(ConstantMsg.FREETIME_STATE_BUSY);
	        }
	        
	        // 将 session 里的 user id 添加到预约人 reserverId
	        String reserverId = this.sessionUser.getId();
	        freetime.setReserverId(reserverId);
	        // 将 session 里的 userName 添加到预约人 reserverName
	        String reserverName = this.sessionUser.getUserName();
	        freetime.setReserverName(reserverName);
	        // 更新预约时的时间
	        Date updateTime = new Date();
	        freetime.setUpdateTime(updateTime);
	        // 更新留言
	        freetime.setReserverMsg(reserverMsg);
	        // 更新状态
	        freetime.setFreeState(ConstantMsg.FREETIME_STATE_RESERVED);
	        
	        
	        List<Freetime> freetimeList = null;
            freetimeList = freetimeService.findByFreeDateAndSectionCode(freetime.getFreeDate(), freetime.getSectionCode());
            if(freetimeList.size()==1 && freetimeList.get(0).getFreeState()==ConstantMsg.FREETIME_STATE_FREE) {
            	// 没有预约者时更新空闲时间 
            	freetimeService.update(freetime);
                return ErrorResponseUtil.setResponse("200", ConstantMsg.USER_RESERVE_SUCCESS);
            }else {
            	// 有一位及以上预约者时
            	Freetime ft;
//            	for(int i=0; i < freetimeList.size(); i++) {
//            		
//            		ft=freetimeList.get(i);
//            		// 此用户已预约过
//        	        if(ft.getReserverId().equals(sessionUser.getId())) {
//        	        	 return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_ALREADY_RESERVED);
//        	        }
//            	}
            	// 更新所有旧空闲时间的已预约人数
            	for(int i=0; i < freetimeList.size(); i++) {
            		
            		ft=freetimeList.get(i);
            		// 预约人数已满
        	        if(ft.getFreeState().equals(ConstantMsg.FREETIME_STATE_BUSY)) {
        	        	continue;
        	        }
        	        // 更新预约人数
        	        ft.setReservedNum(reservedNum);
        	        if(ft.getReservedNum()>=ft.getAllowNum()) {
        	        	ft.setFreeState(ConstantMsg.FREETIME_STATE_BUSY);
        	        }
            		freetimeService.update(ft);
            	}
            	// 插入新空闲时间 
//            	try {
            		int ftid = freetimeService.findAll().size()+1;
            		freetime.setId(ftid+"");
                    freetimeService.insert(freetime);
//                 }catch(Exception e){
//                     return ErrorResponseUtil.setResponse("400", e.getMessage());
//                 }
            }
            
           
            return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_RESERVE_SUCCESS);
	    }

	    

		/**
	     * 根据id删除空闲时间
	     */
	    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	    public String deleteById(HttpServletRequest request, Model model) {
	    	
			// 验证用户是否已登录
	        if(SessionUtil.isLogin(request)) {
	        	this.sessionUser = SessionUtil.getUserFromSession(request);
	        }else {
	        	model.addAttribute("errmsg", ConstantMsg.USER_IS_NOT_LOGIN);
	        	return "myspace/my-add";
	        }
	        
	        // 验证参数是否合法
	        String fid = request.getParameter("fid");
			if(null == fid) {
	            return ErrorResponseUtil.setResponse("400", ConstantMsg.OWNER_ID_IS_NULL);
	        }
			
			// 验证空闲时间是否已添加
	        Freetime freetime = null;
	        try {
	        	// 根据 id 在数据库中查找空闲时间
	        	freetime= freetimeService.findById(fid);
	        }catch (Exception e){
	        	// 空闲时间未添加
	            freetime = null;
	        }
	        if(null == freetime) {
	        	model.addAttribute("errmsg", ConstantMsg.FREETIME_IS_NOT_ADDED);
	        	return "myspace/my-add";
	        }
	        
	        // 验证用户是否有权限操作
	        if(!this.sessionUser.getUserGroup().equals(ConstantMsg.USERGROUP_TEACHER)
	        		||!this.sessionUser.getId().equals(freetime.getOwnerId())) {
	        	return ErrorResponseUtil.setResponse("400", ConstantMsg.USER_IS_NOT_PERMITTED_TO_OOPERATE);
	        }
			
	        freetimeService.delete(fid);
	        List<Freetime> freetimeList = freetimeService.findAllByOwnerId(this.sessionUser.getId());
	        
	        // 获取当前用户信息
 			User user = SessionUtil.getUserFromSession(request);
 			model.addAttribute("nowUser",user);
 			
			model.addAttribute("freetimes",freetimeList);
			return "myspace/my-add";
	    }
}
