package com.atl.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atl.dao.UserRepository;
import com.atl.dto.UserDTO;
import com.atl.exception.ErrorMessage;
import com.atl.model.User;
import com.atl.model.UserId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping("/hi/{name}")
	public String  helloMsg(@PathVariable String name) {
		return "Hello <b>"+name.toUpperCase()+"</b>";
	}
	
	/*
	 * @GetMapping(value= {"/","/home"}) public String home() { return
	 * "<b>Welcome Home!</b>"; }
	 */
	
	@GetMapping("/items")
	public ResponseEntity<?> getItems(){
		List<String> items=new ArrayList<String>(){{ 
			add("Computer items");
			add("Dressing items");
			add("Housing items");
		}};
		
		if(items==null || items.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data found");
		}	
		return ResponseEntity.status(HttpStatus.OK).body(items);
	}

	// create new User
	@GetMapping(value= {"/","/addUsers"})
	public List<User> createuser(){
		User emaUser=new User(new UserId(101,"ema"),"Amanuel","Gebru");
		User hagosUser=new User(new UserId(102,"hag"),"Hagos","Berhe");
		User selamUse=new User(new UserId(103,"sel"),"Selam","Kidane");
		
		List<User> allUsers=new ArrayList<User>() {{ add(emaUser);add(hagosUser);add(selamUse); }};
		List<User> savedUsers = userRepo.saveAll(allUsers);			
		
		if(savedUsers==null || savedUsers.isEmpty()) {
			//return RequestEntity.status(HttpStatus.BAD_REQUEST).body("Users not Saved");
			throw new RuntimeException("Record not Found");
		}	
		return savedUsers;
	}
	
	
	// find userId,firstName
	@GetMapping("/users-id-name")
	public List<UserDTO> findAllUserUserIdAndFirstName() throws JsonMappingException, JsonProcessingException{
		List<Object[]> usersWithIdAndFirstNames = userRepo.getUsersWithIdAndFirstNames();
		List<UserDTO> usersDtos=new ArrayList<>();
		for(Object[] ob:usersWithIdAndFirstNames) {			
			int id=(int)ob[0];
			String code=(String)ob[1];
			String fname=(String)ob[2];
			usersDtos.add(new UserDTO(id,code,fname)); 
			
			//Object val=(Object[])ob;
			//JSONArray ja=(JSONArray)val;
			//UserDTO user= mapper.readValue(ja.toString(), UserDTO.class);
			
			
		}
		return usersDtos;
	}
	
	@GetMapping("/users-dto")
	public List<UserDTO> getUsersUsingDTO() {
		List<UserDTO> usersDto = userRepo.getUserColumnsIDAndFirstName();
		if(usersDto!=null) {
			return usersDto;
		}
		else throw new RuntimeException("No users Found");
	}
	
	@GetMapping("/users")
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	@GetMapping("/users/{id}/{code}")
	public User findUser(@PathVariable("id") int id,@PathVariable("code") String code) throws NotFoundException {
		return userRepo.findById(new UserId(id,code))				
				//.orElseThrow(()->new ErrorMessage("User not found",id,code))
				.orElseThrow(()->new NotFoundException("User not found"));
				//.orElseGet(()->new User());
				//.orElseThrow(()->new RuntimeException(" User not Found with Id "+id));
		
	}
}
