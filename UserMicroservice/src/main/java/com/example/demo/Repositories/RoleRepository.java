package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Models.Role;
@CrossOrigin(origins = "http://localhost:4200")
public interface RoleRepository extends CrudRepository<Role, Long> {


}
