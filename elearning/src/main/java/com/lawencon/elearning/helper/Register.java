package com.lawencon.elearning.helper;

import com.lawencon.elearning.model.Profiles;
import com.lawencon.elearning.model.Users;

import lombok.Data;

/**
 * @author Nur Alfilail
 */

@Data
public class Register {

	private Users user;
	private Profiles profile;
}
