package com.campus.common;

/**
 * 静态常量
 *
 */
public class ConstantMsg {
	
	/**
	 * 调试开关
	 */
	public static final boolean DEBUG = false;
	public static final String DEBUG_GROUP = "teacher";
	
	
	/**
	 * 参数状态
	 */
	public static final String STARTTIME_IS_NULL = "starttime can not be null";
	public static final String ENDTIME_IS_NULL 	 = "endtime can not be null";
	public static final String EMAIL_IS_NULL = "email can not be null";
	public static final String PASSWORD_IS_NULL = "email can not be null";
	public static final String CONFIRMPASSWORD_IS_NULL = "email can not be null";
	public static final String USERNAME_IS_NULL = "username can not be null";
	public static final String USERGROUP_IS_NULL = "usergroup can not be null";
	public static final String DEPTCODE_IS_NULL = "dept can not be null";
	public static final String FREE_DATE_IS_NULL = "freeDate can not be null";
	public static final String SESSION_CODE_IS_NULL = "sessionCode can not be null";
	public static final String OWNER_ID_IS_NULL = "ownerId can not be null";
	public static final String USER_LIST_IS_NULL = "userList is null";
	public static final String FREETIME_LIST_IS_NULL = "freetimeList is null";
	public static final String FREETIME_ID_IS_NULL = "freetimeId is null";
	public static final String TWO_PASSWORDS_ARE_NOT_SAME = "two passwords are not same";
	public static final String PASSWORD_CAN_NOT_BE_ENCRYPTED = "password can not be encrypted";	// 密码无法被加密
	
	
	/**
	 * 用户登录注册操作状态
	 */
	public static final String LOGIN_SUCCESS = "login successful";	// 登录成功
	public static final String REGISTER_SUCCESS = "register successful";	// 注册成功
	public static final String PASSWORD_IS_ERROR = "password is error";	// 密码错误
	public static final String USER_IS_NOT_LOGIN = "user is not login";	// 用户未登录
	public static final String USER_IS_NOT_REGISTERED = "user is not registered";	// 用户未注册
	public static final String USER_IS_ALREADY_LOGIN = "user is already login, do not login again";
	public static final String USER_IS_ALREADY_REGISTERED = "email is already registered, please turn to login";
	public static final String USER_RESERVE_SUCCESS = "user reserve successful";
	public static final String USER_ALREADY_RESERVED = "user already reserved";
	
	
	/**
	 * 用户组
	 */
	public static final String USERGROUP_STUDENT = "student";
	public static final String USERGROUP_TEACHER = "teacher";
	public static final String USERGROUP_ALU	 = "alu";	// 校友
	public static final String USERGROUP_ADMIN 	 = "admin";
	public static final String USER_IS_NOT_PERMITTED_TO_OOPERATE = "user is not permitted to operate";
	
	
	/**
	 * 用户状态
	 */
	public static final String USER_STATE_UNCHECK = "uncheck";
	public static final String USER_STATE_CHECKED = "checked";
	
	
	/**
	 * 空闲时间状态
	 */
	public static final String FREETIME_STATE_FREE = "free";	// 空闲
	public static final String FREETIME_STATE_BUSY = "busy";	// 已满
	public static final String FREETIME_STATE_RESERVED ="reserved";	// 已被预约
	
	
	/**
	 * 添加空闲时间
	 */
	public static final String FREETIME_ADD_SUCCESS = "add freetime success";
	public static final String FREETIME_IS_NOT_ADDED = "freetime is not added";
	public static final String FREETIME_IS_ALREADY_ADDED = "time is already added, please add another one";
	
	
	/**
	 * 院系
	 */
	public static final String DEPT_ALL = "00";	// 所有院系
	public static final String DEPT_CAR = "01";	// 汽车工程学院
	public static final String DEPT_INFO = "02";	// 信息科学与工程学院
	public static final String DEPT_MANAGE = "03";	// 管理学院
	public static final String DEPT_COMPUTER = "04";	// 计算机科学与技术学院
	public static final String DEPT_FOREGIN = "05";	// 外国语学院
	public static final String DEPT_MATH = "06";	// 数学系
	public static final String DEPT_SEA = "07";	// 海洋学院
	public static final String DEPT_METEA = "08";	// 材料科学与工程学院
	public static final String DEPT_BOAT = "09";	// 船舶学院
	public static final String DEPT_LIGHT = "10";	// 光电科学系
	public static final String DEPT_SOFT = "11";	// 软件学院
	public static final String DEPT_BUILDING = "12";	// 土木工程系
	public static final String DEPT_HUMAN = "13";	// 人文学院
	public static final String DEPT_NEW_ENERGY = "14"; // 新能源？
	public static final String DEPT_OTHER = "99";	//其他院系部门
	
	public static final String USER_RESERVE_NUM_IS_FULL = "user reserved num is full";
}
