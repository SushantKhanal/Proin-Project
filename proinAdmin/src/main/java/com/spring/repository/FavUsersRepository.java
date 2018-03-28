package com.spring.repository;

import com.spring.model.FavUsers;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavUsersRepository extends JpaRepository<FavUsers, Long> {

}
