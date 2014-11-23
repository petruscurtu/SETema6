package mta.se.tema6.mvc.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * helper class for parsing JSON data
 * 
 * @author petru scurtu
 * @since 22.11.2014
 */
public class JSONHelper {
	
		/**
		 * 
		 * @param tagName
		 * 			the tag name for the data we want to return
		 * @param jObj
		 * 			the JSON object containing the data
		 * @return 
		 * 			JSON object
		 * @throws JSONException
		 */
		private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
		    JSONObject subObj = jObj.getJSONObject(tagName);
		    return subObj;
		}
		
		/**
		 * 
		 * @param tagName
		 * 			the tag name for the data we want to return
		 * @param jObj
		 * 			the JSON object containing the data
		 * @return 
		 * 			Data as a String
		 * @throws JSONException
		 */
		@SuppressWarnings("unused")
		private static String getString(String tagName, JSONObject jObj) throws JSONException {
		    return jObj.getString(tagName);
		}
		
		/**
		 * 
		 * @param tagName
		 * 			the tag name for the data we want to return
		 * @param jObj
		 * 			the JSON object containing the data
		 * @return 
		 * 			Data as a Float
		 * @throws JSONException
		 */
		private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
		    return (float) jObj.getDouble(tagName);
		}
		
		/**
		 * 
		 * @param tagName
		 * 			the tag name for the data we want to return
		 * @param jObj
		 * 			the JSON object containing the data
		 * @return 
		 * 			Data as a String
		 * @throws JSONException
		 */
		@SuppressWarnings("unused")
		private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
		    return jObj.getInt(tagName);
		}
		
		/**
		 * 
		 * @param data
		 * 			the JSON data we want to parse
		 * @return
		 * 			wind speed information as a Float
		 */
		public static float getWindInfo(String data)
		{
			try {
				JSONObject jObj = new JSONObject(data);
				JSONObject windObj = getObject("wind", jObj);
				float speed=getFloat("speed",windObj);
				return speed;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Float.NEGATIVE_INFINITY;
			}
			
		}
		
		/**
		 * 
		 * @param data
		 * 			the JSON data we want to parse
		 * @return
		 * 			temperature information as a Float
		 */
		public static float getTempInfo(String data)
		{
			try {
				JSONObject jObj = new JSONObject(data);
				JSONObject tempObj = getObject("main", jObj);
				float min_temp=getFloat("temp_min",tempObj);
				float max_temp=getFloat("temp_max",tempObj);
				return (float) ((min_temp+max_temp)/2-273.15);//convert to Celsius
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Float.NEGATIVE_INFINITY;
			}
		}
}
