/**
 * 
 */
package scout24.techChallenge.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luca
 *
 */
public class Site
{

    private String version;
    private String head;
    private String body;
    private String title;
    private Map<String, Integer> headingTags = new HashMap<>();
    private boolean hasLoginForm = false;
    private int internalLinks = 0;
    private int externalLinks = 0;
    private List<Map<String, Integer>> hyperLinksMap = new ArrayList<>();

    public String getVersion()
    {
        return version;
    }

    public String getHead()
    {
        return head;
    }

    public String getBody()
    {
        return body;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public void setHead(String head)
    {
        this.head = head;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Map<String, Integer> getHeadingTags()
    {
        return headingTags;
    }

    public void setHeadingTags(Map<String, Integer> headingTags)
    {
        this.headingTags = headingTags;
    }

    public Site addToHeadingTags(String key, int value)
    {
        headingTags.put(key, value);
        return this;
    }

    public boolean isHasLoginForm()
    {
        return hasLoginForm;
    }

    public void setHasLoginForm(boolean hasLoginForm)
    {
        this.hasLoginForm = hasLoginForm;
    }

    public int getInternalLinks()
    {
        return internalLinks;
    }

    public int getExternalLinks()
    {
        return externalLinks;
    }

    public void setInternalLinks(int internalLinks)
    {
        this.internalLinks = internalLinks;
    }

    public void setExternalLinks(int externalLinks)
    {
        this.externalLinks = externalLinks;
    }

    public List<Map<String, Integer>> getHyperLinksMap()
    {
        return hyperLinksMap;
    }

}
