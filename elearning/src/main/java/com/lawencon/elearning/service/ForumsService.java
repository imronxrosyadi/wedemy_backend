package com.lawencon.elearning.service;

import java.util.List;

import com.lawencon.elearning.helper.ForumAndDetailForums;
import com.lawencon.elearning.model.Forums;

public interface ForumsService {

	void insertForum(Forums forum) throws Exception;

	void updateContentForum(Forums forum) throws Exception;

	void deleteForumById(String id, String idUser) throws Exception;

	List<Forums> getAllForums() throws Exception;

	Forums getForumById(String id) throws Exception;

	List<ForumAndDetailForums> getForumByIdDetailModuleRegistration(String id) throws Exception;

}
