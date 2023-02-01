import databaseConnections.ElasticsearchClientSingleton;

import java.io.IOException;

public class Entrypoint {

    public static void main(String[] args) throws IOException, InterruptedException {
        // setup ES indexes and connection
        ElasticsearchClientSingleton.getClient();


        Scraper scraperThread = new Scraper();
        scraperThread.start();
        scraperThread.join();


        ElasticsearchClientSingleton.closeClient();
    }
}
