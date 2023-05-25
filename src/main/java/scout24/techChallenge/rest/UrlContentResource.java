
/**
 * @author Luca
 */

package scout24.techChallenge.rest;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import scout24.techChallenge.model.Site;
import scout24.techChallenge.model.TestUrl;
import scout24.techChallenge.rest.basic.BasicResource;
import scout24.techChallenge.rest.response.WrappedResponse;
import scout24.techChallenge.tasks.HyperLinksTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@CrossOrigin("*")
@RequestMapping("/urlContent")
@RestController
public class UrlContentResource extends BasicResource {

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> checkUrlContent(@RequestBody TestUrl url) {

        UrlValidator urlValidator = new UrlValidator();
        boolean isValidUrl = urlValidator.isValid(url.getUrl());
        if (!isValidUrl) {
            List<String> errors = new ArrayList<>();
            errors.add("Not a valid Url");

            return getObjectMapperResponse(new WrappedResponse<Boolean>().setCount(0).setSuccess(false).setErrorMessages(errors).setResponse());

        }

        try {
            URI uri = new URI(url.getUrl());
            String myDomain = uri.getHost();
            String domain = myDomain.startsWith("www.") ? myDomain.substring(4) : myDomain;

            // setProxy(url);

            Document doc = Jsoup.connect(url.getUrl()).get();

            /*
             * Site info to return
             */
            Site site = new Site();

            site.setTitle(doc.title());

            setDocType(doc, site);

            setGroupedHeadings(doc, site);

            hasLoginForm(doc, site);

            setHyperLinks(domain, doc, site);

            return getObjectMapperResponse(new WrappedResponse<Site>().setEntity(site).setResponse());
        } catch (Exception exception) {

            return getObjectMapperResponse(new WrappedResponse<String>(exception).processException().setResponse());
        }
    }

    /**
     * @param doc
     * @param site If page has a login form , should at least contain a type = password in input field TODO find more ways to detect login form
     */
    private void hasLoginForm(Document doc, Site site) {
        Elements inputs = doc.getElementsByTag("input");

        for (Element element : inputs) {
            String password = element.attr("type");
            if (password.equalsIgnoreCase("password")) {
                site.setHasLoginForm(true);
                break;
            }

        }
    }

    /**
     * @param domain
     * @param doc
     * @param site
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void setHyperLinks(String domain, Document doc, Site site) throws InterruptedException, ExecutionException {
        Elements links = doc.select("a[href]");

        int internalLinks = 0, externalLinks = 0;

        List<String> linksList = new ArrayList<>();

        /*
         * Retrieve href
         */
        for (Element link : links) {
            String href = link.attr("abs:href");

            if (!href.isEmpty()) {
                if (href.contains(domain))
                    internalLinks++;
                if (!href.contains(domain))
                    externalLinks++;

                linksList.add(href);
            }
        }

        /*
         * Create a new ExecutorService with n thread (n is total hyper links sublist size) to execute and store the Futures
         *
         */
        int linkListSize = linksList.size();
        int numberOfThreads = (int) Math.ceil(linkListSize / 20);

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads == 0 ? 1 : numberOfThreads);

        List<FutureTask<Map<String, Integer>>> taskList = new ArrayList<FutureTask<Map<String, Integer>>>();

        int start = 1, end = 0;

        while ((end < linkListSize && start < linkListSize)) {
            end = (start - 1) + 20;
            List<String> map = Collections.list(Collections.enumeration(linksList)).subList(start == 1 ? 0 : start,
                    end > linkListSize ? linkListSize - 1 : end);

            start = (end + 1) + 20;

            /*
             * Create new task from sublist
             */
            FutureTask<Map<String, Integer>> futureTask = new FutureTask<>(new HyperLinksTask(map));

            taskList.add(futureTask);

            executor.execute(futureTask);

        }

        // Wait until all results are available and combine them at the same time
        for (FutureTask<Map<String, Integer>> futureTask : taskList) {

            site.getHyperLinksMap().add(futureTask.get());

        }

        site.setInternalLinks(internalLinks);
        site.setExternalLinks(externalLinks);

    }

    /**
     * @param url
     */
    @SuppressWarnings("unused")
    private void setProxy(TestUrl url) {
        System.setProperty("http.proxyHost", url.getProxyHost());
        System.setProperty("http.proxyPort", url.getProxyPort());

        System.setProperty("https.proxyHost", url.getProxyHost());
        System.setProperty("https.proxyPort", url.getProxyPort());
    }

    /**
     * @param doc
     * @param site
     */
    private void setGroupedHeadings(Document doc, Site site) {
        Elements hTags = doc.select("h1, h2, h3, h4, h5, h6");

        site.addToHeadingTags("h1", hTags.select("h1").size())
                .addToHeadingTags("h2", hTags.select("h2").size())
                .addToHeadingTags("h3", hTags.select("h3").size())
                .addToHeadingTags("h4", hTags.select("h4").size())
                .addToHeadingTags("h5", hTags.select("h5").size())
                .addToHeadingTags("h6", hTags.select("h6").size());
    }

    /**
     * @param doc
     * @param site
     */
    private void setDocType(Document doc, Site site) {
        List<Node> nods = doc.childNodes();
        for (Node node : nods) {
            if (node instanceof DocumentType) {
                DocumentType documentType = (DocumentType) node;
                site.setVersion(documentType.toString());
                break;
            }
        }
    }

}
