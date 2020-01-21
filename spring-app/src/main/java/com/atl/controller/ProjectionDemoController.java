package com.atl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atl.dao.FullNameRepositoryUsingProjection;
import com.atl.dao.UserFirstNameLastNameProjection;
import com.atl.model.User;


@RestController
public class ProjectionDemoController {
	@Autowired
	private FullNameRepositoryUsingProjection repo;
	
	@GetMapping("/fullNames")	
	public List<User> getAllUsersFullNames(){
		 List<User> users=new ArrayList<>();		
		 List<UserFirstNameLastNameProjection> fullNames = repo.findBy();
		 for(UserFirstNameLastNameProjection usr:fullNames) {
			User u=new User();
			u.setFirstName(usr.getFirstName());
			u.setLastName(usr.getLastName());
			
			users.add(u);			
		 }
		 
		return users;
	}

	@GetMapping("/fullNames/{fname}")	
	public List<User> getUserFullNames(@PathVariable String fname){
		 List<User> users=new ArrayList<>();
		// List<UserFirstNameLastNameProjection> fullNames = repo.findByFirstName(fname);
		 List<UserFirstNameLastNameProjection> fullNames = repo.findByFirstName(fname);
		 for(UserFirstNameLastNameProjection usr:fullNames) {
			User u=new User();
			u.setFirstName(usr.getFirstName());
			u.setLastName(usr.getLastName());
			
			users.add(u);			
		 }
		 
		return users;
	}
}
