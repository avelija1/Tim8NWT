package Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import Interfaces.IActivityService;
import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;



@Service("activityService")
@Transactional

public class ActivityService implements IActivityService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Activity> activities;
	private static List<ActivityType> activityTypes;
	private static List<ActivityPlace> activityPlaces;
	
	static {
		activities = populateDummyActivities();
	}
	private static List<Activity> populateDummyActivities(){
		List<Activity> activities = new ArrayList<Activity>();
		Activity a=new Activity("Test");
		activities.add(a);
		return activities;
	}
	
	@Override
	public Activity getActivity(long id) {
		 for(Activity activity : activities){
	            if(activity.getId() == id){
	                return activity;
	            }
	        }
	        return null;
	}
	
	@Override
	public void CreateActivity(Activity newActivity) {
		newActivity.setId(counter.incrementAndGet());
		activities.add(newActivity);
	}

	@Override
	public void EditActivity(long id, Activity ModifiedActivity) {
		int index = activities.indexOf(ModifiedActivity);
		activities.set(index, ModifiedActivity);
			}

	@Override
	public void DeleteActivity(long id) {
		for (Iterator<Activity> iterator = activities.iterator(); iterator.hasNext(); ) {
			Activity activity = iterator.next();
			if (activity.getId() == id) {
				iterator.remove();
			}
		}
		
	}

	@Override
	public List<Activity> GetActivities() {
		return activities;
	}

	@Override
	public void CreateActivityType(ActivityType newActivityType) {
		newActivityType.setId(counter.incrementAndGet());
		   activityTypes.add(newActivityType);
	}

	@Override
	public void EditActivityType(long id, ActivityType modifiedActivityType) {
		int index = activityTypes.indexOf(modifiedActivityType);
        activityTypes.set(index, modifiedActivityType);
		
	}

	@Override
	public void DeleteActivityType(long id) {
		for (Iterator<Activity> iterator = activities.iterator(); iterator.hasNext(); ) {
            Activity activity = iterator.next();
            if (activity.getId() == id) {
                iterator.remove();
            }
        }
	}

	@Override
	public ActivityType GetActivityType(long id) {
		for(ActivityType activityType : activityTypes){
            if(activityType.getId() == id){
                return activityType;
            }
        }
        return null;
	}

	@Override
	public List<ActivityType> GetActivityTypes() {
		return activityTypes;
	}

	@Override
	public void CreateActivityPlace(ActivityPlace newActivityPlace) {
		newActivityPlace.setId(counter.incrementAndGet());
		activityPlaces.add(newActivityPlace);
	}

	@Override
	public void EditActivityPlace(long id, ActivityPlace modifiedActivityPlace) {
		int index = activityPlaces.indexOf(modifiedActivityPlace);
		activityPlaces.set(index, modifiedActivityPlace);
	}

	@Override
	public void DeleteActivityPlace(long id) {
		for (Iterator<Activity> iterator = activities.iterator(); iterator.hasNext(); ) {
            Activity activity = iterator.next();
            if (activity.getId() == id) {
                iterator.remove();
            }
        }
		
	}

	@Override
	public ActivityPlace GetActivityPlace(long id) {
		for(ActivityPlace activityPlace : activityPlaces){
            if(activityPlace.getId() == id){
                return activityPlace;
            }
        }
        return null;
	}

	@Override
	public List<ActivityPlace> GetActivityPlaces() {
		return activityPlaces;
	}

}
