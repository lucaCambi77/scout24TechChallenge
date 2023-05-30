package scout24.techChallenge.rest.basic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import scout24.techChallenge.rest.response.WrappedResponse;

public abstract class BasicResource {

    /**
     *
     * @param wrappedResponse
     * @return
     * @param <T>
     */
    protected <T> ResponseEntity<WrappedResponse<?>> getObjectMapperResponse(WrappedResponse<T> wrappedResponse) {

        if (wrappedResponse.isSuccess())
            return ResponseEntity.ok(wrappedResponse);

        /*
         * Response with errors
         */
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(wrappedResponse);

    }

}
