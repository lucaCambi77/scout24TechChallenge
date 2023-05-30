package scout24.techChallenge.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class WrappedResponse<T> {

    private boolean success = true;
    private T entity;
    private Integer count;
    private List<String> errorMessage = new ArrayList<>();
    private String response;

    @JsonIgnore
    private Throwable exception;

    /**
     *
     */
    public WrappedResponse() {
    }

    public WrappedResponse(Throwable exception) {
        this.exception = exception;
    }

    public Object getEntity() {
        return entity;
    }

    public WrappedResponse<T> setEntity(T entity) {

        this.entity = entity;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public WrappedResponse<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public List<String> getErrorMessage() {

        return errorMessage;
    }

    public WrappedResponse<T> setErrorMessages(List<String> errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @JsonIgnore
    public Throwable getException() {
        return exception;
    }

    public WrappedResponse<T> setException(Throwable exception) {
        this.exception = exception;
        return this;
    }

    public WrappedResponse<T> processException() {

        getErrorMessage().add(getException().getMessage());

        return this;
    }

    public String getResponse() {
        return response;
    }

    public WrappedResponse<T> setResponse() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            response = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // log.error(e.getMessage(), e);
            //
            // List<String> errorMessageList = new ArrayList<String>();
            // errorMessageList.add(ERRORPARSE);
            // setSuccess(false).setErrorMessages(errorMessageList);
        }

        return this;
    }
}
