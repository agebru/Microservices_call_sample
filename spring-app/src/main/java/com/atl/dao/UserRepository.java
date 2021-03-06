package com.atl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atl.dto.UserDTO;
import com.atl.model.User;
import com.atl.model.UserId;

public interface UserRepository extends JpaRepository<User, UserId> {
	
	
	
	@Query(value="SELECT INT_CODE,STR_CODE,FIRST_NAME FROM USER",nativeQuery = true)
	public List<Object[]> getUsersWithIdAndFirstNames();
	
	// Using DTO
	@Query("select new com.atl.dto.UserDTO(u.id.intCode,u.id.strCode,u.firstName) from User u")
	public List<UserDTO> getUserColumnsIDAndFirstName();
	
}
