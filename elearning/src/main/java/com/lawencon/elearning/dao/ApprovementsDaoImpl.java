package com.lawencon.elearning.dao;

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
		return approvement.size() > 0 ? approvement.get(0) : null;
	}

}