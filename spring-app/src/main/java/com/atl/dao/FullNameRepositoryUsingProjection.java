package com.atl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atl.model.User;
import com.atl.model.UserId;


@Repository
public interface FullNameRepositoryUsingProjection extends JpaRepository<User, UserId>{
	
	List<UserFirstNameLastNameProjection> findByFirstName(String fname);
}
