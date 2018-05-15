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
import com.example.demo.Models.ActivityType;
import com.example.demo.Services.ActivityService;
import com.example.demo.Models.ActivityPlace;

@RestController
public class ActivityController {

	@Autowired
	ActivityService activityService;

	@RequestMapping(value = "/activity/", method = RequestMethod.GET)
	public ResponseEntity<List<Activity>> GetActivities() {

		List<Activity> activities = activityService.GetActivities();
		activities.add(new Activity("testActivity",null,null,null));

		if (activities.isEmpty()) {
			return new ResponseEntity<List<Activity>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Activity> getActivity(@PathVariable("id") long id) {

		Activity activity = activityService.getActivity(id);

		if (activity == null) {
			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Activity>(activity, HttpStatus.OK);
	}

	@RequestMapping(value = "/activity/", method = RequestMethod.POST)
	public ResponseEntity<Void> createActivity(@RequestBody Activity activity, UriComponentsBuilder ucBuilder) {

		activityService.CreateActivity(activity);

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(ucBuilder.path("/activity/{id}").buildAndExpand(activity.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Activity> updateActivity(@PathVariable("id") long id, @RequestBody Activity activity) {

		Activity currentActivity = activityService.getActivity(id);

		if (currentActivity == null) {
			System.out.println("Activity with id " + id + " not found");

			return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
		}

		activityService.EditActivity(id, activity);

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

	@RequestMapping(value = "/activityType/", method = RequestMethod.GET)
	public ResponseEntity<List<ActivityType>> getActivityTypes() {
		List<ActivityType> activityTypes = activityService.GetActivityTypes();

		if (activityTypes.isEmpty()) {

			return new ResponseEntity<List<ActivityType>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ActivityType>>(activityTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityType/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActivityType> GetActivityType(@PathVariable("id") long id) {
		System.out.println("Fetching Activity Type with id " + id);

		ActivityType activityType = activityService.GetActivityType(id);

		if (activityType == null) {
			System.out.println("Activity Type with id " + id + " not found");

			return new ResponseEntity<ActivityType>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActivityType>(activityType, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityType/", method = RequestMethod.POST)
	public ResponseEntity<Void> CreateActivityType(@RequestBody ActivityType activityType,
			UriComponentsBuilder ucBuilder) {

		activityService.CreateActivityType(activityType);

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(ucBuilder.path("/activityType/{id}").buildAndExpand(activityType.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activityType/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ActivityType> updateActivityType(@PathVariable("id") long id,
			@RequestBody ActivityType activityType) {
		System.out.println("Updating Activity Type " + id);

		ActivityType currentActivityType = activityService.GetActivityType(id);

		if (currentActivityType == null) {

			return new ResponseEntity<ActivityType>(HttpStatus.NOT_FOUND);
		}

		activityService.EditActivityType(id, activityType);

		return new ResponseEntity<ActivityType>(activityType, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActivityType> deleteActivityType(@PathVariable("id") long id) {

		ActivityType activityType = activityService.GetActivityType(id);

		if (activityType == null) {
			System.out.println("Unable to delete. Activity Type with id " + id + " not found");

			return new ResponseEntity<ActivityType>(HttpStatus.NOT_FOUND);
		}

		activityService.DeleteActivityType(id);

		return new ResponseEntity<ActivityType>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/activityPlace/", method = RequestMethod.GET)
	public ResponseEntity<List<ActivityPlace>> getActivityPlaces() {
		List<ActivityPlace> activityPlaces = activityService.GetActivityPlaces();

		if (activityPlaces.isEmpty()) {
			return new ResponseEntity<List<ActivityPlace>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<ActivityPlace>>(activityPlaces, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityPlace/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActivityPlace> GetActivityPlace(@PathVariable("id") long id) {
		System.out.println("Fetching Activity Place with id " + id);

		ActivityPlace activityType = activityService.GetActivityPlace(id);

		if (activityType == null) {
			System.out.println("Activity Place with id " + id + " not found");
			return new ResponseEntity<ActivityPlace>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ActivityPlace>(activityType, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityPlace/", method = RequestMethod.POST)
	public ResponseEntity<Void> CreateActivityPlace(@RequestBody ActivityPlace activityPlace,
			UriComponentsBuilder ucBuilder) {

		activityService.CreateActivityPlace(activityPlace);

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(ucBuilder.path("/activityPlace/{id}").buildAndExpand(activityPlace.getId()).toUri());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/activityPlace/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ActivityPlace> updateActivityPlace(@PathVariable("id") long id,
			@RequestBody ActivityPlace activityPlace) {
		System.out.println("Updating Activity Type " + id);

		ActivityPlace currentActivityPlace = activityService.GetActivityPlace(id);

		if (currentActivityPlace == null) {
			return new ResponseEntity<ActivityPlace>(HttpStatus.NOT_FOUND);
		}

		activityService.EditActivityPlace(id, activityPlace);

		return new ResponseEntity<ActivityPlace>(activityPlace, HttpStatus.OK);
	}

	@RequestMapping(value = "/activityPlace/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ActivityPlace> deleteActivityPlace(@PathVariable("id") long id) {

		ActivityPlace activityPlace = activityService.GetActivityPlace(id);

		if (activityPlace == null) {
			System.out.println("Unable to delete. Activity Place with id " + id + " not found");

			return new ResponseEntity<ActivityPlace>(HttpStatus.NOT_FOUND);
		}

		activityService.DeleteActivityPlace(id);

		return new ResponseEntity<ActivityPlace>(HttpStatus.NO_CONTENT);
	}

}