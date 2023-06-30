
package di.uniba.map.ilsestosenso;

/**
 *
 * @author giuli
 */

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Weather {
    
        public static final String API_KEY = "6bedf33afcad0fc380e5a6f199309146";
        private String weather = getWeatherByCity( "Bari");
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(weather, JsonObject.class);

                
    /**
     *
     * @param city
     * @return
     */
        
    public static String getWeatherByCity(String city) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.openweathermap.org/data/2.5");
        Response resp = target.path("weather")
                .queryParam("appid", API_KEY)
                .queryParam("q", city)
                .request(MediaType.APPLICATION_JSON).get();
        return resp.readEntity(String.class);
    }
    
    
    
    public String getWeather() {
        JsonArray weatherArray = json.getAsJsonArray("weather");
        String weatherMain = weatherArray.get(0).getAsJsonObject().get("main").getAsString();
        return (weatherMain);
    }

    public int getTemperature() {
        JsonArray weatherArray = json.getAsJsonArray("weather");
        String weatherMain = weatherArray.get(0).getAsJsonObject().get("main").getAsString();
        return (5);
    }
   
    public static void main(String[] args) {
        Weather weather = new Weather();
        System.out.println(weather.getWeather());
    }
}

