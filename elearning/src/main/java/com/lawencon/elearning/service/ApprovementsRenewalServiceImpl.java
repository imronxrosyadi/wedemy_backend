package com.lawencon.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.dao.ApprovementsRenewalDao;
import com.lawencon.elearning.model.ApprovementsRenewal;

@Service
public class ApprovementsRenewalServiceImpl extends ElearningBaseServiceImpl implements ApprovementsRenewalService {
	@Autowired
	private ApprovementsRenewalDao approvementsRenewalDao;
	
	@Override
	public void insertApprovementsRenewal(ApprovementsRenewal approvementsRenewal) throws Exception {
		approvementsRenewalDao.insertApprovementsRenewal(approvementsRenewal, ()-> validateInsert(approvementsRenewal));
	}
	
	@Override
	public List<ApprovementsRenewal> getAllApprovementsRenewal() throws Exception {
		return approvementsRenewalDao.getAllApprovementsRenewal();
	}
	
	@Override
	public ApprovementsRenewal getApprovementsRenewalById(String id) throws Exception {
		return approvementsRenewalDao.getApprovementsRenewalById(id);
	}
	
	private void validateInsert(ApprovementsRenewal approvementsRenewal) throws Exception {

	}
}