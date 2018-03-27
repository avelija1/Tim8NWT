package Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;

import Interfaces.IActivityService;

@Service("activityService")
@Transactional
public class ActivityService implements IActivityService {

	@Override
	public void CreateActivity(Activity newActivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditActivity(int id, Activity ModifiedActivity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteActivity(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Activity> GetActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void CreateActivityType(ActivityType newActivityType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditActivityType(int id, ActivityType modifiedActivityType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Void DeleteActivityType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityType GetActivityType(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityType> GetActivityTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void CreateActivityPlace(ActivityPlace newActivityPlace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EditActivityPlace(int id, ActivityPlace modifiedActivityPlace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteActivityPlace(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActivityPlace GetActivityPlace(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityPlace> GetActivityPlaces() {
		// TODO Auto-generated method stub
		return null;
	}

}
