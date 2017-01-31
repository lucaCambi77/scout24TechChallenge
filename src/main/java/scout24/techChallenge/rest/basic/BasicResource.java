package scout24.techChallenge.rest.basic;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import scout24.techChallenge.rest.response.WrappedResponse;

/**
 * @author luca Abstract Class for Basic Resource attributes
 * 
 * 
 *         All class extending BasicResource implements a @Path Interface for rest easy services that is used as an @EJB
 * 
 * 
 */

public abstract class BasicResource
{

    /**
     * @param WrappedResponse
     * @return Response
     * 
     *         Method for json serialization of WrappedResonse from Services. WrappedResponse can contains errors if the exception is catched inside
     *         the Ejb
     */
    protected <T> Response getObjectMapperResponse(WrappedResponse<T> wrappedResponse)
    {
        if (wrappedResponse.isSuccess())
            return Response.ok(wrappedResponse.getResponse()).type(MediaType.APPLICATION_JSON).build();

        /*
         * Response with errors
         */
        return Response.serverError().entity(wrappedResponse.getResponse()).type(MediaType.APPLICATION_JSON).build();

    }

}
