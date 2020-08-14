package top.bujiaban.netflixcloud.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class ClientErrorDecoder extends ErrorDecoder.Default {

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public Exception decode(String methodKey, Response response) {
        return super.decode(methodKey, response);

//        try {
//            String body = CharStreams.toString(response.body().asReader());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ErrorResult result = objectMapper.readValue(body, ErrorResult.class);
//
//        return new ServiceRequestException(HttpStatus.valueOf(response.status()),
//                ErrorCode.fromValue(result.getErrorCode()),
//                result.getMessage());
    }
}
