package ca.six.daily.data;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DailyListResponse {
    public String date;
	public ArrayList<Story> stories;
	public ArrayList<TopStory> top_stories;

	public DailyListResponse(String jsonStr){
		if(!TextUtils.isEmpty(jsonStr)){
			try{
				JSONObject json = new JSONObject(jsonStr);
				date = json.optString("date");

				JSONArray array = json.optJSONArray("stories");
				stories = Story.createWithJsonArray(array);

				JSONArray array2 = json.optJSONArray("top_stories");
				top_stories = TopStory.createWithJsonArray(array);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}