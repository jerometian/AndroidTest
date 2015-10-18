package test.example.com.activitytest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerometian on 2015/10/18.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    /*add Activity*/
    public static void addAcitivity(Activity activity){

        activities.add(activity);
    }
    /*remove activity*/
    public static void removeActivity(Activity activity){

        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity :activities)
        {
            if ( !activity.isFinishing())
            {
                activity.finish();
            }
        }

    }
}
