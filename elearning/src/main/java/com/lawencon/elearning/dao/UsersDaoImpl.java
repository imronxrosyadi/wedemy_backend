package com.lawencon.elearning.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.elearning.model.Profiles;
import com.lawencon.elearning.model.Roles;
import com.lawencon.elearning.model.Users;
import com.lawencon.elearning.util.HibernateUtils;
import com.lawencon.util.Callback;

@Repository
public class UsersDaoImpl extends ElearningBaseDaoImpl<Users> implements UsersDao {	

	@Override
	public void insertUser(Users user, Callback before) throws Exception {
		save(user, before, null);
	}

	@Override
	public List<Users> getAllUsers() throws Exception {
		return getAll();
	}

	@Override
	public Users getUserById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public Users getUserByUsername(String username) throws Exception {
		List<Users> user = createQuery("FROM Users WHERE username = ?1 ", Users.class).setParameter(1, username).getResultList();
		return user.size() > 0 ? user.get(0) : null;
	}

	@Override
	public void updateUser(Users user, Callback before) throws Exception {
		save(user, before, null, true, true);
	}

	@Override
	public void deleteUserById(String id) throws Exception {
		String sql = sqlBuilder("DELETE FROM t_m_users WHERE id = ?1").toString();
		createNativeQuery(sql).setParameter(1, id).executeUpdate();
	}
	
	@Override
	public Users getUserByIdProfile(Profiles profile) throws Exception {
		Users user = createQuery("FROM Users WHERE idProfile.id = ?1 ", Users.class).setParameter(1, profile.getId()).getSingleResult();
		return user;
	}

	@Override
	public List<Users> getUsersByRoleCode(String code) throws Exception {
		String sql = sqlBuilder("SELECT u.username, r.code, p.fullname, p.id_number, p.birth_place, p.birth_date,",
				" p.email, p.phone, p.address FROM t_m_users u",
				" INNER JOIN t_m_profiles p ON p.id = u.id_profile",
				" INNER JOIN t_m_roles r ON r.id = u.id_role",
				" WHERE r.code = ?1 ").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, code).getResultList();
		List<Users> listResult = new ArrayList<>();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			Users user = new Users();
			user.setUsername((String) objArr[0]);
			
			Roles role = new Roles();
			role.setCode((String) objArr[1]);
			
			Profiles profile = new Profiles();
			profile.setFullName((String) objArr[2]);
//			profile.setIdNumber((String) objArr[3]);
//			profile.setBirthPlace((String) objArr[4]);
//			LocalDate birthDate = ((java.sql.Date) objArr[5]).toLocalDate();
//			profile.setBirthDate(birthDate);
//			profile.setEmail((String) objArr[6]);
//			profile.setPhone((String) objArr[7]);
//			profile.setAddress((String) objArr[8]);
			user.setIdRole(role);
			user.setIdProfile(profile);
			
			listResult.add(user);
		});
//		return listResult;
		return HibernateUtils.bMapperList(listObj, Users.class, "username", "idRole.code", "idProfile.fullName",
				 "idProfile.idNumber", "idProfile.birthPlace", "idProfile.birthDate", "idProfile.email", "idProfile.phone",
				 "idProfile.address");
	}
	
	@Override
	public void softDeleteUserById(String id) throws Exception {
		updateNativeSQL("UPDATE t_m_users SET is_active = false", id, "mas imam");
	}
	
	@Override
	public List<?> validateDeleteUser(String id) throws Exception {
		String sql = sqlBuilder("select ",
				" tmc.id as id_class, ",
				" trp.id as id_presence, ",
				" tras.id as id_assignment_submission, ",
				" trf.id as id_forum, ",
				" trdf.id as id_detail_forum, ",
				" trce.id as id_class_enrollment ",
				" from t_m_users tmu ",
				" full join t_m_classes tmc on tmu.id = tmc.id_tutor ",
				" full join t_r_presences trp on tmu.id = trp.id_user ",
				" full join t_r_assignment_submissions tras on tmu.id = tras.id_participant ",
				" full join t_r_forums trf on tmu.id = trf.id_user ",
				" full join t_r_dtl_forums trdf on tmu.id = trdf.id_user ",
				" full join t_r_class_enrollments trce on tmu.id = trce.id_user ",
				" where tmu.id = ?1 ").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, id)
				.setMaxResults(1).getResultList();
		List<String> result = new ArrayList<String>();
		listObj.forEach(val -> {
			Object[] objArr = (Object[]) val;
			result.add(objArr[0] != null ? objArr[0].toString() : null);
			result.add(objArr[1] != null ? objArr[1].toString() : null);
			result.add(objArr[2] != null ? objArr[2].toString() : null);
			result.add(objArr[3] != null ? objArr[3].toString() : null);
			result.add(objArr[4] != null ? objArr[4].toString() : null);
			result.add(objArr[5] != null ? objArr[5].toString() : null);
		});
		return result;
	}
	
	@Override
	public Users getUserByIdNumber(String idNumber) throws Exception {
		String sql = sqlBuilder("SELECT u.id, u.username, r.code, p.fullname, p.id_number, p.birth_place, p.birth_date,",
				" p.email, p.phone, p.address FROM t_m_users u",
				" INNER JOIN t_m_profiles p ON p.id = u.id_profile",
				" INNER JOIN t_m_roles r ON r.id = u.id_role",
				" WHERE p.id_number = ?1 ").toString();
		List<?> listObj = createNativeQuery(sql).setParameter(1, idNumber).getResultList();
		List<Users> listUsers = HibernateUtils.bMapperList(listObj, Users.class, "id", "username", "idRole.code", "idProfile.fullName",
				 "idProfile.idNumber", "idProfile.birthPlace", "idProfile.birthDate", "idProfile.email", "idProfile.phone",
				 "idProfile.address");
		return listUsers.size() > 0 ? listUsers.get(0) : null;
	}

}
