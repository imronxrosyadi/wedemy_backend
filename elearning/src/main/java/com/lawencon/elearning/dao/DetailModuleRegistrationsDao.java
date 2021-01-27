package com.lawencon.elearning.dao;

import java.util.List;

import com.lawencon.elearning.model.DetailModuleRegistrations;
import com.lawencon.util.Callback;

public interface DetailModuleRegistrationsDao {

	void insertDetailModuleRegistration(DetailModuleRegistrations dtlModRegist, Callback before) throws Exception;

	DetailModuleRegistrations getDetailModuleRegistrationsById(String id) throws Exception;

	List<DetailModuleRegistrations> getDetailModuleRegistrationsByIdModuleRgs(String idModuleRgs) throws Exception;

}
