/**
 * 
 */
package scout24.techChallenge.tasks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author luca
 *
 */
public class HyperLinksTask implements Callable<Map<String, Integer>>
{

    private List<String> linksList;
    private Map<String, Integer> linksMap = new HashMap<>();

    public HyperLinksTask(List<String> linksList)
    {
        this.linksList = linksList;
    }

    @Override
    public Map<String, Integer> call()
    {
        for (String linkMap : getLinksList())
        {

            int responseCode;
            try
            {
                responseCode = getResponseCode(linkMap);

                getLinksMap().put(linkMap, responseCode);

                // System.out.println(linkMap + "------------------>" + responseCode);
            }
            catch (MalformedURLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return getLinksMap();

    }

    public static int getResponseCode(String urlString) throws MalformedURLException, IOException
    {
        URL u = new URL(urlString);
        if (u.openConnection() instanceof HttpURLConnection)
        {
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

    public List<String> getLinksList()
    {
        return linksList;
    }

    public Map<String, Integer> getLinksMap()
    {
        return linksMap;
    }

}
