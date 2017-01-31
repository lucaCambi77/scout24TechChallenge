package scout24.techChallenge.rest.response;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WrappedResponse<T>
{

    private boolean success = true;
    private T entity;
    private Integer count;
    private Long sequence;
    private Status errorType = Status.INTERNAL_SERVER_ERROR;
    private List<String> errorMessage = new ArrayList<String>();
    private List<String> succededMessage;
    private String developerMessage;
    private String locale;
    private String response;

    @JsonIgnore
    private Throwable exception;

    /**
     * 
     */
    public WrappedResponse()
    {
    }

    public WrappedResponse(Throwable exception)
    {
        this.exception = exception;
    }

    public Object getEntity()
    {
        return entity;
    }

    public WrappedResponse<T> setEntity(T entity)
    {

        this.entity = entity;
        return this;
    }

    public Status getErrorType()
    {
        return errorType;
    }

    public WrappedResponse<T> setErrorType(Status errorType)
    {
        this.errorType = errorType;
        return this;
    }

    public Integer getCount()
    {
        return count;
    }

    public WrappedResponse<T> setCount(Integer count)
    {
        this.count = count;
        return this;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public WrappedResponse<T> setSuccess(boolean success)
    {
        this.success = success;
        return this;
    }

    public Long getSequence()
    {
        return sequence;
    }

    public void setSequence(Long sequence)
    {
        this.sequence = sequence;
    }

    public String getDeveloperMessage()
    {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage)
    {
        this.developerMessage = developerMessage;
    }

    public List<String> getErrorMessage()
    {

        return errorMessage;
    }

    public WrappedResponse<T> setErrorMessages(List<String> errorMessage)
    {
        this.errorMessage = errorMessage;
        return this;
    }

    public List<String> getSuccededMessage()
    {
        return succededMessage;
    }

    public void setSuccededMessage(List<String> succededMessage)
    {
        this.succededMessage = succededMessage;
    }

    @JsonIgnore
    public Throwable getException()
    {
        return exception;
    }

    public WrappedResponse<T> setException(Throwable exception)
    {
        this.exception = exception;
        return this;
    }

    public String getLocale()
    {
        return locale;
    }

    public WrappedResponse<T> setLocale(String locale)
    {
        this.locale = locale;
        return this;
    }

    public WrappedResponse<T> processException()
    {

        setSuccess(false).setCount(0);

        getErrorMessage().add(getException().getMessage());

        return this;
    }

    public String getResponse()
    {
        return response;
    }

    public WrappedResponse<T> setResponse()
    {

        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            response = objectMapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            // log.error(e.getMessage(), e);
            //
            // List<String> errorMessageList = new ArrayList<String>();
            // errorMessageList.add(ERRORPARSE);
            // setSuccess(false).setErrorMessages(errorMessageList);
        }

        return this;
    }
}
