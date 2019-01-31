package com.campus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.campus.model.User;

/**
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	/**
	 *  默认示例
	 */
	// 查询表中所有数据
    @Query("SELECT u FROM User u")
    List<User> findAll();
    
    
    /**
     * 自定义数据操作
     */
	// 根据 id 查询
    @Query("select u from User u where u.id = ?1") 
    User findById(String id);
    
    // 根据 id 更新 userState
    @Modifying
    @Query("update User u set u.userState = ?1 where u.id = ?2")
    int setUserStateById(String userState, String id); 	//方法的返回值是int，表示更新语句所影响的行数
    
    // 根据 userGroup 查询
//    @Query("select u from User u where u.userGroup = 'student'  order by ?1")	//测试语句，证明@query可以覆盖默认的ipa查询
    @Query("select u from User u where u.userGroup = ?1  order by u.id")
    List<User> findByUserGroup(String userGroup);
    
    User findByUserName(String userName);
    
    User findByEmail(String email);
    
    @Query("select u from User u where u.userGroup = ?1 and u.deptCode = ?2")
    List<User> findByUserGroupAndDeptCode(String userGroup, String deptCode);
}
