import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class Entrypoint {
    private final static String url = "https://opendata.leisurecloud.live/api/feeds/Oxford-Univesity-live-facility-uses";

    public static void main(String[] args) throws IOException  {
        System.out.println("Hello World!");

        Scraper scraperThread = new Scraper();

        scraperThread.start();
//
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//            .url(url)
//            .build();
//
//        Response response;
//        try {
//            response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("LOL");
//        }

    }
}
