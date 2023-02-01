import databaseConnections.ElasticsearchClientSingleton;

import java.io.IOException;

public class Entrypoint {

    public static void main(String[] args) throws IOException  {
        // setup ES indexes and connection
        ElasticsearchClientSingleton.getClient();


        Scraper scraperThread = new Scraper();
        scraperThread.start();


        ElasticsearchClientSingleton.closeClient();
    }
}
