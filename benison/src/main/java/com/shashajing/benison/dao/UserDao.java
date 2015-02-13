package com.shashajing.benison.dao;

import java.util.List;
import java.util.Map;

import com.shashajing.benison.entity.User;

/**
 * 用户dao
 * @author yanghanjing
 *
 */
@MyBatisDao
public interface UserDao {
	
	List<User> searchUser(Map<String, Object> parameters);
	
	int countUser(Map<String, Object> parameters);
	
	List<User> findUserAndRoleModule(Map<String, Object> parameters);
	
	int addUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(List<Integer> ids);
}
