package com.lawencon.elearning.dao;

import java.util.List;

import com.lawencon.elearning.model.Roles;
import com.lawencon.util.Callback;

public interface RolesDao {
	void insertRole(Roles role, Callback before) throws Exception;
	
	List<Roles> getAllRoles() throws Exception;
	
	Roles getRoleById(String id) throws Exception;
	
	void updateRole(Roles role, Callback before) throws Exception;
	
	void deleteRoleById(String id) throws Exception;
	
	Roles getRoleByCode(String code) throws Exception;
}
