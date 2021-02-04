package com.lawencon.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.elearning.helper.ClassesHelper;
import com.lawencon.elearning.helper.Response;
import com.lawencon.elearning.model.Classes;
import com.lawencon.elearning.service.ClassesService;
import com.lawencon.elearning.util.MessageStat;

@RestController
@RequestMapping("class")
public class ClassesController extends ElearningBaseController{

	@Autowired
	private ClassesService classesService;

	@GetMapping("all")
	public ResponseEntity<?> getAllClasses() {
		try {
			List<Classes> clazz = classesService.getAllClasses();
			Response<List<Classes> > res = new Response<List<Classes> >(true, HttpStatus.OK.toString(), MessageStat.SUCCESS_RETRIEVE.msg, clazz);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Response<List<Classes> > res = new Response<List<Classes> >(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), getMessage(e), null);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getClassById(@PathVariable("id") String id) {
		try {
			Classes clazz = classesService.getClassById(id);
			Response<Classes> res = new Response<Classes>(true, HttpStatus.OK.toString(), MessageStat.SUCCESS_RETRIEVE.msg, clazz);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Response<Classes> res = new Response<Classes>(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), getMessage(e), null);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> insertClass(@RequestPart("body") String body, @RequestPart("file") MultipartFile file) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			ClassesHelper clazzHelper = obj.readValue(body, ClassesHelper.class);
			classesService.insertClass(clazzHelper, file);
			Response<ClassesHelper> res = new Response<ClassesHelper>(true, HttpStatus.CREATED.toString(), MessageStat.SUCCESS_CREATED.msg, clazzHelper);
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			Response<ClassesHelper> res = new Response<ClassesHelper>(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), getMessage(e), null);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateClass(@RequestPart String body, @RequestPart("file") MultipartFile file) {
		try {
			ObjectMapper obj = new ObjectMapper();
			obj.registerModule(new JavaTimeModule());
			Classes clazz = obj.readValue(body, Classes.class);
			classesService.updateClass(clazz, file);
			Response<Classes> res = new Response<Classes>(true, HttpStatus.CREATED.toString(), MessageStat.SUCCESS_UPDATE.msg, clazz);
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			Response<Classes> res = new Response<Classes>(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), getMessage(e), null);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteClassById(@RequestParam("id") String id,
			@RequestParam("idUser") String idUser) {
		try {
			classesService.deleteClassById(id, idUser);
			Response<Classes> res = new Response<Classes>(true, HttpStatus.OK.toString(), MessageStat.SUCCESS_DELETE.msg, null);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Response<Classes> res = new Response<Classes>(false, HttpStatus.INTERNAL_SERVER_ERROR.toString(), getMessage(e), null);
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
