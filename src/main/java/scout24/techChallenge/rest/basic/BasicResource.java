package scout24.techChallenge.rest.basic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import scout24.techChallenge.rest.response.WrappedResponse;

public abstract class BasicResource {

    /**
     * @param WrappedResponse
     * @return Response
     * <p>
     * Method for json serialization of WrappedResonse from Services. WrappedResponse can contain errors if any exception occurs
     */
    protected <T> ResponseEntity<String> getObjectMapperResponse(WrappedResponse<T> wrappedResponse) {

        if (wrappedResponse.isSuccess())
            return ResponseEntity.ok(wrappedResponse.getResponse());

        /*
         * Response with errors
         */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(wrappedResponse.getResponse());

    }

}
