package scout24.techChallenge.rest.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WrappedResponse<T> {

    @Builder.Default
    private boolean success = true;
    private T entity;
    private List<String> errorMessage;
}
