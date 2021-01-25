package com.lawencon.elearning.dao;

import java.util.List;

import com.lawencon.elearning.model.Assignments;
import com.lawencon.util.Callback;

public interface AssignmentsDao {
	
	void insertAssignment(Assignments assignments, Callback before) throws Exception;

	List<Assignments> getAllAssignments() throws Exception;

	Assignments getAssignmentsById(String id) throws Exception;
	
	Assignments getAssignmentsByCode(String code) throws Exception;

}
