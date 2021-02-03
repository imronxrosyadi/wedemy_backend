package com.lawencon.elearning.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Approvements;
import com.lawencon.util.Callback;

@Repository
public class ApprovementsDaoImpl extends ElearningBaseDaoImpl<Approvements> implements ApprovementsDao {

	@Override
	public void insertApprovement(Approvements approvement, Callback before) throws Exception {
		save(approvement, before, null, true, true);
	}

	@Override
	public List<Approvements> getAllApprovements() throws Exception {
		return getAll();
	}

	@Override
	public Approvements getApprovementsById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public void updateApprovement(Approvements approvement, Callback before) throws Exception {
		save(approvement, before, null, true, true);
	}

	@Override
	public void deleteApprovementById(String id) throws Exception {
		deleteById(id);
	}	

	@Override
	public Approvements getApprovementByCode(String code) throws Exception {
		List<Approvements> approvement = createQuery("FROM Approvements WHERE code = ?1", Approvements.class)
				.setParameter(1, code).getResultList();
		return resultCheck(approvement);
	}

	@Override
	public void softDeleteApprovementById(String id, String idUser) throws Exception {
		updateNativeSQL("UPDATE t_m_approvements SET is_active = FALSE", id, idUser);
	}

	@Override
	public List<?> validateDeleteApprovement(String id) throws Exception {
		String sql = sqlBuilder("SELECT ar.id FROM t_m_approvements a ",
				" FULL JOIN t_r_approvement_renewal ar ON ar.id_approvement = a.id ",
				" WHERE a.id = ?1").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		return result;
	}

}