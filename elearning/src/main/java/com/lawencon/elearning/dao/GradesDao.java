package com.lawencon.elearning.dao;

import java.util.List;

import com.lawencon.elearning.model.Grades;
import com.lawencon.util.Callback;

public interface GradesDao {
	void insertGrade(Grades assignmentType, Callback before) throws Exception;
	
	List<Grades> getAllGrades() throws Exception;
	
	Grades getGradeById(String id) throws Exception;
}
