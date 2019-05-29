package com.ecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.UserPassword;


@Repository
@Transactional
public interface PasswordRepository extends JpaRepository<UserPassword, Long>{

}
