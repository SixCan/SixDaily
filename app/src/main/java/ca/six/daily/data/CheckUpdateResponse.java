package ca.six.daily.data;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckUpdateResponse {
   public int status;
   public String message;
   public String url;
   public String latest;

   public CheckUpdateResponse (String jsonStr){
      if(!TextUtils.isEmpty(jsonStr)){
         try{
            JSONObject json = new JSONObject(jsonStr);
            status = json.optInt("status");
            message = json.optString("msg");
            url = json.optString("url");
            latest = json.optString("latest");
         } catch (JSONException e) {
            e.printStackTrace();
         }
      }
   }

}
