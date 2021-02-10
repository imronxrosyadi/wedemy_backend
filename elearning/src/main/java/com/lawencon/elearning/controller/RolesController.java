package com.lawencon.elearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.elearning.model.Roles;
import com.lawencon.elearning.service.RolesService;
import com.lawencon.elearning.util.MessageStat;

@RestController
@RequestMapping("role")
public class RolesController extends ElearningBaseController {
	@Autowired
	private RolesService rolesService;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody String body) {
		try {
			Roles role = new ObjectMapper().readValue(body, Roles.class);
			rolesService.insert(role);
			return responseSuccess(role, HttpStatus.CREATED, MessageStat.SUCCESS_CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(e);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id) {
		Roles role = new Roles();
		try {
			role = rolesService.getById(id);
			return responseSuccess(role, HttpStatus.OK, MessageStat.SUCCESS_RETRIEVE);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(e);
		}
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Roles> rolesList = new ArrayList<Roles>();
		try {
			rolesList = rolesService.getAll();
			return responseSuccess(rolesList, HttpStatus.OK, MessageStat.SUCCESS_RETRIEVE);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(e);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteById(@RequestParam("id") String id) {
		try {
			rolesService.deleteById(id);
			return responseSuccess(null, HttpStatus.OK, MessageStat.SUCCESS_DELETE);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(e);
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody String body) {
		try {
			Roles role = new ObjectMapper().readValue(body, Roles.class);
			rolesService.update(role);
			return responseSuccess(role, HttpStatus.OK, MessageStat.SUCCESS_UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
			return responseError(e);
		}
	}
}
