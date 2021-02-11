package com.lawencon.elearning.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.LearningMaterialTypes;
import com.lawencon.util.Callback;

/**
 * @author Nur Alfilail
 */

@Repository
public class LearningMaterialTypesDaoImpl extends ElearningBaseDaoImpl<LearningMaterialTypes>
		implements LearningMaterialTypesDao {

	@Override
	public void insert(LearningMaterialTypes lmType, Callback before) throws Exception {
		save(lmType, before, null, true, true);
	}

	@Override
	public List<LearningMaterialTypes> getAllLearningMaterialType() throws Exception {
		return getAll();
	}

	@Override
	public LearningMaterialTypes getLearningMaterialTypeById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public void update(LearningMaterialTypes lmType, Callback before) throws Exception {
		save(lmType, before, null, true, true);
	}

	@Override
	public void deleteTypeById(String id) throws Exception {
		deleteById(id);
	}

	@Override
	public LearningMaterialTypes getByCode(String code) throws Exception {
		List<LearningMaterialTypes> lmType = createQuery("FROM LearningMaterialTypes WHERE code = ?1",
				LearningMaterialTypes.class).setParameter(1, code).getResultList();
		return resultCheck(lmType);
	}

	@Override
	public void softDeleteById(String id, String idUser) throws Exception {
		updateNativeSQL("UPDATE t_m_learning_material_types SET is_active = FALSE", id, idUser);
	}

	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = sqlBuilder("SELECT lm.id FROM t_m_learning_material_types mt",
				" FULL JOIN t_m_learning_materials lm ON mt.id = lm.id_type",
				" WHERE mt.id = ?1 ").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<String>();
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		return result;
	}

}
