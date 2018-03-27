package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

}
