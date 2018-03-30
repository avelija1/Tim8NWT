package com.example.demo.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.example.demo.Models.Activity;
import Services.ActivityService;


@RestController
//@ComponentScan({"com.example.demo"})
public class ActivityController {
	
	// @Autowired
	   ActivityService activityService;  
	 @RequestMapping(value = "/activity/", method = RequestMethod.GET)
	    public ResponseEntity<List<Activity>> listAllActivities() {
	        List<Activity> activities = activityService.GetActivities();
	        if(activities.isEmpty()){
	            return new ResponseEntity<List<Activity>>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/activity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Activity> getActivity(@PathVariable("id") long id) {
	        System.out.println("Fetching Activity with id " + id);
	        Activity activity = activityService.getActivity(id);
	        if (activity == null) {
	            System.out.println("Activity with id " + id + " not found");
	            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Activity>(activity, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/activity/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createActivity(@RequestBody Activity activity,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating Activity " + activity.getName());
	 
	     
	        activityService.CreateActivity(activity);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/activity/{id}").buildAndExpand(activity.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }

	 @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Activity> updateActivity(@PathVariable("id") long id, @RequestBody Activity activity) {
	        System.out.println("Updating Activity " + id);
	         
	        Activity currentActivity = activityService.getActivity(id);
	         
	        if (currentActivity==null) {
	            System.out.println("Activity with id " + id + " not found");
	            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentActivity.setName(activity.getName());

	         
	        activityService.EditActivity(id, currentActivity);
	        return new ResponseEntity<Activity>(currentActivity, HttpStatus.OK);
	    }
	 @RequestMapping(value = "/activity/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Activity> DeleteActivity(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting Activity with id " + id);
	 
	        Activity activity = activityService.getActivity(id);
	        if (activity == null) {
	            System.out.println("Unable to delete. Activity with id " + id + " not found");
	            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
	        }
	 
	        activityService.DeleteActivity(id);
	        return new ResponseEntity<Activity>(HttpStatus.NO_CONTENT);
	    }

		 
}