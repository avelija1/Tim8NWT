package com.example.demo.Interfaces;
import java.util.List;

import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;

public interface IActivityService {
	
	Activity getActivity(long id);
	void CreateActivity(Activity newActivity);
	void EditActivity(long id, Activity ModifiedActivity);
	void DeleteActivity(long id);
	List<Activity> GetActivities();
	
	void CreateActivityType(ActivityType newActivityType);
	void EditActivityType(long id,ActivityType modifiedActivityType);
	void DeleteActivityType(long id);
	ActivityType GetActivityType(long id);
	List<ActivityType> GetActivityTypes();
	
	void CreateActivityPlace(ActivityPlace newActivityPlace);
	void EditActivityPlace(long id,ActivityPlace modifiedActivityPlace);
	void DeleteActivityPlace(long id);
	ActivityPlace GetActivityPlace(long id);
	List<ActivityPlace> GetActivityPlaces();

}
