package task;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherParserTest {

  // you can hardcode dates to test against:
  // LocalDateTime ldt = LocalDateTime.parse("2018-02-12T00:00:00");

  @Test
  public void testDataMustBeAvailable() throws Exception {
    assertNotNull(readTestForecast());
  }
  
  @Test
  public void findsCorrectTemperatureFromForecast() throws Exception {
    var weatherParser = new WeatherParser(new MockDownload());
    assertEquals(-6, weatherParser.temperatureAt(LocalDateTime.parse("2018-02-12T06:00:00")));
  }

  @Test
  void throwsExceptionIfTemperatureNotFoundForGivenDate() throws Exception {
    var weatherParser = new WeatherParser(new MockDownload());

    assertThrows(IllegalStateException.class, () -> weatherParser.temperatureAt(LocalDateTime.parse("2018-02-10T00:00:00")));
  }

  // TODO add your tests here

  private static String readTestForecast() throws IOException {
    // reads src/test/resources/forecast.xml to string
    try (InputStream is = WeatherParserTest.class.getResourceAsStream("/forecast.xml")) {
      return IOUtils.toString(is, UTF_8);
    }
  }
}

class MockDownload extends Download{

  private final String FORECAST_LOCATION = "forecast.xml";

  public String downloadXml() throws IOException {
    // download the latest forecast from yr.no weather service
    String forecastXml;
    try (InputStream stream = MockDownload.class.getClassLoader().getResourceAsStream(FORECAST_LOCATION)) {
      forecastXml = IOUtils.toString(stream, UTF_8);
    }
    return forecastXml;
  }
}
