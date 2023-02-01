import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.List;


public class Scraper extends Thread {
//    private final static String url = "https://opendata.leisurecloud.live/api/feeds/Oxford-Univesity-live-facility-uses";
    private final static String url = "https://gorest.co.in/public/v2/users";
    private static ObjectMapper objectMapper = new ObjectMapper().configure(
        JsonGenerator.Feature.IGNORE_UNKNOWN, true
    );

    public void run() {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        try {
            CloseableHttpResponse response = client.execute(new HttpGet(url));

            String bodyAsString = EntityUtils.toString(response.getEntity());
            List<User> users = objectMapper.readValue(bodyAsString, new TypeReference<>() {});
            for (User user: users) {
                System.out.println(user.getId());
            }
        } catch (Exception e) {
            System.out.println("\n\n\nMEMES");
            System.out.println(e);
        }
    }
}
