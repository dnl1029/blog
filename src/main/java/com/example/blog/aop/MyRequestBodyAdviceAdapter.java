package com.example.blog.aop;

import com.example.blog.dto.ApiKeyDto;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class MyRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    //springMVC가 RequestBody를 읽은 후 실행되는 Method. 여기서 Object가 Controller에서 @RequestBody로 받는 객체를 의미
    @Override
    public Object afterBodyRead(final Object body,
                                final HttpInputMessage inputMessage,
                                final MethodParameter parameter,
                                final Type targetType,
                                final Class<? extends HttpMessageConverter<?>> converterType) {
        //AdviceAdapter에서 Request 정보 불러오기 위해 추가
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String apiKey = req.getHeader("apikey");

        ApiKeyDto apiKeyDto = (ApiKeyDto) body;
        log.info("afterBodyRead. apiKeyDto : {}",apiKeyDto);

        String testkey = apiKeyDto.getApiKey();

        if(!testkey.equals(apiKey)) {
            throw new RuntimeException("MyRequestBodyAdviceAdapter. invalid api key");
        }
        return body;
    }

    //spring MVC가 RequestBody 읽기전에 실행되는 Method

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
                                           final MethodParameter parameter,
                                           final Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        log.info("beforeBodyRead");
        return inputMessage;
    }

    //requestBody가 비워져있을때 디폴트값 넣을수 있음.
   @Override
   public Object handleEmptyBody(final Object body,
                                 final HttpInputMessage inputMessage,
                                 final MethodParameter parameter,
                                 final Type targetType,
                                 final Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
   }

    //이게 true여야 컨트롤러로 넘어가고, false면 요청이 Controller로 넘어가지 않음.
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
}
