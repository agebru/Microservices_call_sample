package com.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rest.model.User;
import com.rest.model.UserId;

@RestController
public class CallRemoteController {
	
	private static final Logger logger=LoggerFactory.getLogger(CallRemoteController.class);

	@Autowired
	private RestTemplate restTemplate;
	private static final String remoteServiceUrl = "http://localhost:8096/my-app/users";
	
	@GetMapping("/")
	public String home() {
		return "home page";
	}

	

	@SuppressWarnings("unchecked")
	@GetMapping("/remote-users")
	public ResponseEntity<List<User>> getRemoteUserAndUpdateList() {
		 List<User> Allusers = restTemplate.getForObject(remoteServiceUrl, List.class);
		 Allusers.add(new User(new UserId(2001,"new"),"Neftegna","Amara"));		
		return ResponseEntity.ok().body(Allusers);

	}

	@GetMapping("/all-remote-users")
	public ResponseEntity<?> getRemoteCallResponse() {

		ResponseEntity<String> response1 =
				restTemplate.getForEntity(remoteServiceUrl,String.class);
		
		logger.info("Response1: {}", response1.getBody());

		ResponseEntity<Object[]> response2 = restTemplate.getForEntity(remoteServiceUrl,Object[].class);
		Object[] usersList = response2.getBody();
		
		logger.info("response2 response 2: size = {}, objects = {}", usersList.length, usersList);
		return ResponseEntity.ok().body(usersList);

	}
	
	// find  specific User
	@GetMapping("/remote-users/{id}/{code}")
	public ResponseEntity<?> getRemoteUser(@PathVariable int id,@PathVariable String code){
		Map<String,String> vars=new HashMap<String,String>();
		vars.put("id", String.valueOf(id));
		vars.put("code", code);
		
		ResponseEntity<String> remoteUser=restTemplate
				.getForEntity(remoteServiceUrl+"/"+id+"/"+code, String.class);
				//.getForEntity(remoteServiceUrl, String.class,vars);
		logger.info("Response: {}", remoteUser.getBody());
		return ResponseEntity.ok().body(remoteUser.getBody());
		
	}
	
	//Using exchange
	@GetMapping("/remote-users-exchange/{id}/{code}")
	public ResponseEntity<?> getRemoteUserExchange(@PathVariable int id,@PathVariable String code){
		Map<String,String> vars=new HashMap<String,String>();
		vars.put("id", String.valueOf(id));
		vars.put("code", code);
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity httpEntity=new HttpEntity("Hello there",headers);
		ResponseEntity<?> remoteUser=restTemplate
				.exchange(remoteServiceUrl+"/{id}/{code}", HttpMethod.GET, httpEntity, Object.class,id,code);
				//.getForEntity(remoteServiceUrl+"/"+id+"/"+code, String.class);
				//.getForEntity(remoteServiceUrl, String.class,vars);
		logger.info("Response: {}", remoteUser.getBody());
		return ResponseEntity.ok().body(remoteUser.getBody());
		
	}


}
