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

	@Autowired
	private ApprovementsService approvementService;

	@Override
	public void insertApprovementsRenewal(ApprovementsRenewal approvementsRenewal) throws Exception {
		approvementsRenewal.setIdApprovement(approvementService.getApprovementByCode("PND"));
		approvementsRenewalDao.insertApprovementsRenewal(approvementsRenewal,
				() -> validateInsert(approvementsRenewal));
	}

	@Override
	public void participantApprovementsRenewal(ApprovementsRenewal approvementsRenewal) throws Exception {
		approvementsRenewal.setIdApprovement(
				approvementService.getApprovementByCode(approvementsRenewal.getIdApprovement().getCode()));
		approvementsRenewalDao.participantApprovementsRenewal(approvementsRenewal,
				() -> validateInsert(approvementsRenewal));
	}

	@Override
	public List<ApprovementsRenewal> getAllApprovementsRenewal() throws Exception {
		return approvementsRenewalDao.getAllApprovementsRenewal();
	}

	@Override
	public List<ApprovementsRenewal> getListParticipantsPresence(String idDtlClass, String idDtlModuleRgs)
			throws Exception {
		return approvementsRenewalDao.getListParticipantsPresence(idDtlClass, idDtlModuleRgs);
	}

	@Override
	public ApprovementsRenewal getApprovementsRenewalById(String id) throws Exception {
		return approvementsRenewalDao.getApprovementsRenewalById(id);
	}

	@Override
	public ApprovementsRenewal checkParticipantPresence(String idDtlModuleRgs, String idUser) throws Exception {
		return approvementsRenewalDao.checkParticipantPresence(idDtlModuleRgs, idUser);
	}

	private void validateInsert(ApprovementsRenewal approvementsRenewal) throws Exception {

	}

}
