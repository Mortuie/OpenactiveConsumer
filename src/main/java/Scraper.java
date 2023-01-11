class memes {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


public class Scraper extends Thread {

    public void run() {
        System.out.println("HELLO WORLD FROM SCRAPER");

        memes.sleep(3000);

        System.out.println("Post sleep wakeup");
    }
}
