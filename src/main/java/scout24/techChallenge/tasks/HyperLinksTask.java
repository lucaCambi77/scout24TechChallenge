/**
 *
 */
package scout24.techChallenge.tasks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author luca
 */
public class HyperLinksTask implements Callable<Map<String, Integer>> {

    private final List<String> linksList;

    public HyperLinksTask(List<String> linksList) {
        this.linksList = linksList;
    }

    @Override
    public Map<String, Integer> call() {

        Map<String, Integer> map = new HashMap<>();
        for (String linkMap : this.linksList) {

            try {

                map.put(linkMap, getResponseCode(linkMap));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;

    }

    public static int getResponseCode(String urlString) throws IOException {
        URL u = new URL(urlString);
        if (u.openConnection() instanceof HttpURLConnection) {
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();

            /*
             * set request method head and follow redirects to false TODO check if this is the best practice
             */
            huc.setRequestMethod("HEAD");
            HttpURLConnection.setFollowRedirects(false);

            huc.connect();

            return huc.getResponseCode();
        }

        return 0;
    }
}
