
package scout24.techChallenge.rest.api;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import scout24.techChallenge.constants.IConstants;
import scout24.techChallenge.model.TestUrl;

/**
 * @author Luca
 *
 */

//@Path("/urlContent")
public interface IUrlContentResource extends IConstants
{

    /**
     * @param url
     * @return
     * 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Response getUrlContent(TestUrl url);

}