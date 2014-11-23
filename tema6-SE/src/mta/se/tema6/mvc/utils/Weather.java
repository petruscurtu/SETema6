package mta.se.tema6.mvc.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
	
		//Constants
		/**
		 *  base url for our weather web service
		 */
		private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
	    
		/**
		 * 
		 * @param location
		 * 			latitude and longitude for the location
		 * @return
		 * 			weather data is JSON format
		 */
	    public static String getWeatherData(String location) {
	        HttpURLConnection con = null ;
	        InputStream is = null;
	 
	        try {
	            con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
	            con.setRequestMethod("GET");
	            con.setDoInput(true);
	            con.setDoOutput(true);
	            con.connect();
	            // Let's read the response
	            StringBuffer buffer = new StringBuffer();
	            is = con.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            String line = null;
	            while (  (line = br.readLine()) != null )
	                buffer.append(line + "\r\n");
	            is.close();
	            con.disconnect();
	            return buffer.toString();
	        }
	        catch(Throwable t) {
	            t.printStackTrace();
	        }
	        finally {
	            try { is.close(); } catch(Throwable t) {}
	            try { con.disconnect(); } catch(Throwable t) {}
        }
	        return null;
	    }


}
