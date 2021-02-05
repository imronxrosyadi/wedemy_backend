package com.lawencon.elearning.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Approvements;
import com.lawencon.elearning.model.ApprovementsRenewal;
import com.lawencon.util.Callback;

@Repository
public class ApprovementsRenewalDaoImpl extends ElearningBaseDaoImpl<ApprovementsRenewal>
		implements ApprovementsRenewalDao {
	@Override
	public void insertApprovementsRenewal(ApprovementsRenewal approvementsRenewal, Callback before) throws Exception {
		save(approvementsRenewal, before, null);
	}

	@Override
	public List<ApprovementsRenewal> getAllApprovementsRenewal() throws Exception {
		return getAll();
	}

	@Override
	public List<ApprovementsRenewal> getListParticipantsPresence(String idDtlClass, String idDtlModuleRgs)
			throws Exception {
		List<ApprovementsRenewal> listResult = new ArrayList<>();
		String sql = sqlBuilder("SELECT pf.fullname, pc.presence_time, a.approvement_name ",
				"FROM t_m_class_enrollments ce INNER JOIN t_m_users u ON ce.id_user = u.id ",
				"INNER JOIN t_m_profiles pf ON u.id_profile = pf.id ",
				"LEFT JOIN t_r_presences pr ON ce.id_user = pr.id_user ",
				"LEFT JOIN t_r_approvement_renewal ar ON pr.id = ar.id_presence ",
				"LEFT JOIN t_m_approvements a ON ar.id_approvement = a.id ",
				"WHERE ce.id_detail_class =?1 AND pr.id_detail_module_rgs = ?2").toString();
		return null;
	}

	@Override
	public ApprovementsRenewal getApprovementsRenewalById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public ApprovementsRenewal checkParticipantPresence(String idDtlModuleRgs, String idUser) throws Exception {
		List<ApprovementsRenewal> listResult = new ArrayList<>();
		String sql = sqlBuilder("SELECT a.code FROM t_r_presences p INNER JOIN t_r_approvement_renewal ar ",
				"ON ar.id_presence = p.id INNER JOIN t_m_approvements a ON ar.id_approvement = a.id ",
				"WHERE p.id_detail_module_rgs =?1 AND p.id_user =?2 ORDER BY ar.created_at DESC LIMIT 1").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, idDtlModuleRgs).setParameter(2, idUser)
				.getResultList();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Approvements approvement = new Approvements();
			approvement.setApprovementName((String) objArr[0]);
			ApprovementsRenewal approvementRenewal = new ApprovementsRenewal();
			approvementRenewal.setIdApprovement(approvement);
			listResult.add(approvementRenewal);
		});
		return listResult.size() > 0 ? listResult.get(0) : null;
	}

}
