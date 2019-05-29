package com.ecommerce.repository;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

}
