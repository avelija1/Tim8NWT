package com.example.demo.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.IActivityService;
import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;
import com.example.demo.Repositories.ActivityPlaceRepository;
import com.example.demo.Repositories.ActivityRepository;
import com.example.demo.Repositories.ActivityTypeRepository;

@Service("activityService")
@Transactional

public class ActivityService implements IActivityService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Activity> activities;
	private static List<ActivityType> activityTypes;
	private static List<ActivityPlace> activityPlaces;

	private ActivityRepository activityRepository;
	private ActivityTypeRepository activityTypeRepository;
	private ActivityPlaceRepository activityPlaceRepository;

	public ActivityService(ActivityRepository ar, ActivityTypeRepository at, ActivityPlaceRepository ap) {
		this.activityRepository = ar;
		this.activityTypeRepository = at;
		this.activityPlaceRepository = ap;
	}

	@Override
	public Activity getActivity(long id) {

		activities = (List<Activity>) activityRepository.findAll();
		for (Activity activity : activities) {

			if (activity.getId() == id) {
				return activity;
			}
		}

		return null;
	}

	@Override
	public void CreateActivity(Activity newActivity) {
		activityRepository.save(newActivity);
	}

	@Override
	public void EditActivity(long id, Activity ModifiedActivity) {
		Activity activity = activityRepository.findOne(id);
		activity.setName(ModifiedActivity.getName());
		activity.setActivityPlace(ModifiedActivity.getActivityPlace());
		activity.setActivityType(ModifiedActivity.getActivityType());
		activity.setCourse(ModifiedActivity.getCourse());
		activityRepository.save(activity);
	}

	@Override
	public void DeleteActivity(long id) {
		for (Iterator<Activity> iterator = activities.iterator(); iterator.hasNext();) {
			Activity activity = iterator.next();

			if (activity.getId() == id) {
				activityRepository.delete(id);
			}
		}

	}

	@Override
	public List<Activity> GetActivities() {
		return (List<Activity>) activityRepository.findAll();
	}

	@Override
	public void CreateActivityType(ActivityType newActivityType) {

		activityTypeRepository.save(newActivityType);
	}

	@Override
	public void EditActivityType(long id, ActivityType modifiedActivityType) {
		ActivityType activityType = activityTypeRepository.findOne(id);
		activityType.setName(modifiedActivityType.getName());
		activityTypeRepository.save(activityType);

	}

	@Override
	public void DeleteActivityType(long id) {
		for (Iterator<ActivityType> iterator = activityTypes.iterator(); iterator.hasNext();) {
			ActivityType activityType = iterator.next();

			if (activityType.getId() == id) {
				activityTypeRepository.delete(id);
			}
		}
	}

	@Override
	public ActivityType GetActivityType(long id) {
		activityTypes = (List<ActivityType>) activityTypeRepository.findAll();
		for (ActivityType activityType : activityTypes) {

			if (activityType.getId() == id) {
				return activityType;
			}
		}
		return null;
	}

	@Override
	public List<ActivityType> GetActivityTypes() {

		return (List<ActivityType>) activityTypeRepository.findAll();
	}

	@Override
	public void CreateActivityPlace(ActivityPlace newActivityPlace) {

		activityPlaceRepository.save(newActivityPlace);
	}

	@Override
	public void EditActivityPlace(long id, ActivityPlace modifiedActivityPlace) {
		ActivityPlace activityPlace = activityPlaceRepository.findOne(id);
		activityPlace.setBuildingName(modifiedActivityPlace.getBuildingName());
		activityPlace.setClassRoomName(modifiedActivityPlace.getClassRoomName());
		activityPlaceRepository.save(activityPlace);
	}

	@Override
	public void DeleteActivityPlace(long id) {
		activityPlaces = (List<ActivityPlace>) activityPlaceRepository.findAll();
		for (Iterator<ActivityPlace> iterator = activityPlaces.iterator(); iterator.hasNext();) {
			ActivityPlace activityPlace = iterator.next();

			if (activityPlace.getId() == id) {
				activityTypeRepository.delete(id);
			}
		}

	}

	@Override
	public ActivityPlace GetActivityPlace(long id) {
		activityPlaces = (List<ActivityPlace>) activityPlaceRepository.findAll();

		for (ActivityPlace activityPlace : activityPlaces) {

			if (activityPlace.getId() == id) {
				return activityPlace;
			}
		}

		return null;
	}

	@Override
	public List<ActivityPlace> GetActivityPlaces() {
		return (List<ActivityPlace>) activityPlaceRepository.findAll();
	}

}
