package com.lawencon.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.elearning.model.ApprovementsRenewal;
import com.lawencon.elearning.service.ApprovementsRenewalService;

@RestController
@RequestMapping("approvement-renewal")
public class ApprovementsRenewalController {
	@Autowired
	private ApprovementsRenewalService approvementsRenewalService;

	@PostMapping
	public ResponseEntity<?> insertApprovementsRenewal(@RequestBody String body) {
		try {
			ApprovementsRenewal approvementsRenewal = new ObjectMapper().readValue(body, ApprovementsRenewal.class);
			approvementsRenewalService.insertApprovementsRenewal(approvementsRenewal);
			return new ResponseEntity<>(approvementsRenewal, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("participant-approvement")
	public ResponseEntity<?> participantApprovementsRenewal(@RequestBody String body) {
		try {
			ApprovementsRenewal approvementsRenewal = new ObjectMapper().readValue(body, ApprovementsRenewal.class);
			approvementsRenewalService.participantApprovementsRenewal(approvementsRenewal);
			return new ResponseEntity<>(approvementsRenewal, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("participant-presence")
	public ResponseEntity<?> listParticipant(@RequestParam("idDtlClass") String idDtlClass,
			@RequestParam("idDtlModuleRgs") String idDtlModuleRgs) {
		try {
			List<ApprovementsRenewal> listResult = approvementsRenewalService.getListParticipantsPresence(idDtlClass,
					idDtlModuleRgs);
			return new ResponseEntity<>(listResult, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("participant")
//	public ResponseEntity<?> getassignmentSubmissionsById(@RequestParam("id") String id) {
//		try {
//			AssignmentSubmissions assignmentSubmissions = assignmentSubmissionsService.getAssignmentSubmissionsById(id);
//			return new ResponseEntity<>(assignmentSubmissions, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
