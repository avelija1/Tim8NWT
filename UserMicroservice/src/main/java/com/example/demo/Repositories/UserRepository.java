package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Models.User;

public interface UserRepository extends CrudRepository<User, Long> {


}
