import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;


public class DataServiceTest {

    @Test
    public void testTemperature(){
    	// This is the line that contains the data you want to test
        String lastLine = readLastLine("weather_data.txt");
        assertNotNull(lastLine, "The last line of weather data should not be null");

        // Parse the last line as a JSON object
        JSONObject weatherData = new JSONObject(lastLine);
        JSONObject currentConditions = weatherData.getJSONObject("current");
        
        // Extract values from JSON
        double temperature = currentConditions.getDouble("temp_f");
        System.out.println(temperature);
        
        assertTrue("Temperature should be within range!", temperature >= -100 && temperature <= 150);
    }
    
    @Test
    public void testHumidity(){
    	// This is the line that contains the data you want to test
        String lastLine = readLastLine("weather_data.txt");
        assertNotNull(lastLine, "The last line of weather data should not be null");

        // Parse the last line as a JSON object
        JSONObject weatherData = new JSONObject(lastLine);
        JSONObject currentConditions = weatherData.getJSONObject("current");
        
        int humidity = currentConditions.getInt("humidity");
        System.out.println(humidity);
        
        assertTrue("Humidity should be within range!", humidity >= 0 && humidity <= 100);
    }
    
    @Test
    public void testPrecipitation(){
    	// This is the line that contains the data you want to test
        String lastLine = readLastLine("weather_data.txt");
        assertNotNull(lastLine, "The last line of weather data should not be null");

        // Parse the last line as a JSON object
        JSONObject weatherData = new JSONObject(lastLine);
        JSONObject currentConditions = weatherData.getJSONObject("current");
        
        double precipitation = currentConditions.getDouble("precip_mm");
        System.out.println(precipitation);
        
        assertTrue("Precipitation should be within range!", precipitation >= 0 && precipitation <= 100);

    }

    // Helper method to read the last line of a file
    private String readLastLine(String filePath) {
        String lastLine = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            fail("IOException occurred when reading the file: " + e.getMessage());
        }
        return lastLine;
    }
}
