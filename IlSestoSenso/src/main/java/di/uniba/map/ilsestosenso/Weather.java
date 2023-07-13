
package di.uniba.map.ilsestosenso;

/**
 *
 * @author IlSestoSenso
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
        private final String weather = getWeatherByCity( "Bari");
        private final Gson gson = new Gson();
        private final JsonObject json;
        
        public Weather() {
        this.json = gson.fromJson(weather, JsonObject.class);
        };
                
    /**
     *
     * @param city
     * @return
     */
        
    private static String getWeatherByCity(String city) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.openweathermap.org/data/2.5");
        Response resp = target.path("weather")
                .queryParam("appid", API_KEY)
                .queryParam("q", city)
                .request(MediaType.APPLICATION_JSON).get();
        return resp.readEntity(String.class);
    }
    
    
    
    private String getWeather() {
        JsonArray weatherArray = json.getAsJsonArray("weather");
        String weatherMain = weatherArray.get(0).getAsJsonObject().get("main").getAsString();
        return (weatherMain);
    }

    /**
     *Return the temperature
     * @return
     */
    private int getTemperature() {
        JsonObject weatherObject = json.getAsJsonObject("main");
        Double weatherTemperature = weatherObject.get("temp").getAsDouble();
        int temperatureInCelsius = (int)(weatherTemperature - 273.15);
        return (temperatureInCelsius);
    }
    
    public String weatherOutput() {
        StringBuilder outputMessage = new StringBuilder();

        switch (getWeather()) {
            case "Clear":
                outputMessage.append("Il cielo e' sereno ");
                break;
            case "Thunderstorm":
                outputMessage.append("Non conviene uscire, fuori ci sono tuoni, fulmini e pioggia a dirotto ");
                break;
            case "Drizzle":
                outputMessage.append("C'e' una leggera pioggia ");
                break;
            case "Rain":
                outputMessage.append("Piove ");
                break;
            case "Snow":
                outputMessage.append("Sembra Natale, nevica ");
                break;
            case "Atmosphere":
                outputMessage.append("C'e' una fitta nebbia fuori ");
                break;
            case "Cloud":
                outputMessage.append("E' nuvoloso ");
                break;           
        }
        
        if(getTemperature() < 0)
        {
            outputMessage.append("e la temperatura è sotto zero, si gela.");
        } else if (getTemperature() < 10)
        {
            outputMessage.append("e fa freddo.");
        } else if (getTemperature() < 20)
        {
            outputMessage.append("e c'e' una temperatura media: non fa ne freddo ne caldo");
        } else if (getTemperature() < 30)
        {
            outputMessage.append("e fa abbastanza caldo.");
        } else if (getTemperature() > 30)
        {
            outputMessage.append("e fa un caldo torrido.");
        }
        
        return(outputMessage.toString());
    }
}

