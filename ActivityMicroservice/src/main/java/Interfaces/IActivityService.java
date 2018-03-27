package Interfaces;

import java.util.List;

import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;

public interface IActivityService {
	
	void CreateActivity(Activity newActivity);
	void EditActivity(int id, Activity ModifiedActivity);
	void DeleteActivity(int id);
	List<Activity> GetActivities();
	
	void CreateActivityType(ActivityType newActivityType);
	void EditActivityType(int id,ActivityType modifiedActivityType);
	Void DeleteActivityType(int id);
	ActivityType GetActivityType(int id);
	List<ActivityType> GetActivityTypes();
	
	void CreateActivityPlace(ActivityPlace newActivityPlace);
	void EditActivityPlace(int id,ActivityPlace modifiedActivityPlace);
	void DeleteActivityPlace(int id);
	ActivityPlace GetActivityPlace(int id);
	List<ActivityPlace> GetActivityPlaces();

}
