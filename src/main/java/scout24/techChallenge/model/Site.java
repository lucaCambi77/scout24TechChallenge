/**
 *
 */
package scout24.techChallenge.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luca
 */
@Data
public class Site {
    private String version;
    private String head;
    private String body;
    private String title;
    private Map<String, Integer> headingTags = new HashMap<>();
    private boolean hasLoginForm = false;
    private int internalLinks = 0;
    private int externalLinks = 0;
    private Map<String, Integer> hyperLinksMap = new HashMap<>();
}
