package task;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Download {

    private final String FORECAST_LOCATION =
            "https://www.yr.no/place/Estonia/Tartumaa/Tartu/forecast.xml";

    public String downloadXml() throws IOException {
        // download the latest forecast from yr.no weather service
        String forecastXml;
        try (InputStream stream = new URL(FORECAST_LOCATION).openStream()) {
            forecastXml = IOUtils.toString(stream, UTF_8);
        }
        return forecastXml;
    }
}
