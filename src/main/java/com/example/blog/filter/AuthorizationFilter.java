package com.example.blog.filter;

import com.example.blog.dto.ApiKeyDto;
import com.example.blog.dto.RestAPITestDto;
import com.example.blog.util.JsonUtil;
import com.example.blog.util.ReadableRequestBodyWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

//        String apiKey = req.getHeader("apikey");
//
//        try{
//            if(!"testkey123".equals(apiKey)) {
//                throw new RuntimeException("filter invalid api key");
//            }
//            //핵심 코드 doFilter
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
//        catch (Exception e){
//            throw e;
//        }

        try {
            //Filter에서 request body를 읽으면 controller로 못넘기기 때문에, wrapper로 감싸서 읽을수 있도록 구현
            ReadableRequestBodyWrapper wrapper = new ReadableRequestBodyWrapper((HttpServletRequest) servletRequest);
            wrapper.setAttribute("requestBody", wrapper.getRequestBody());
            String requestBody = wrapper.getRequestBody();

            log.info("requestBody : {}", requestBody);

            //String json을 객체로 변환
            ApiKeyDto apiKeyDto = JsonUtil.fromJson(requestBody, ApiKeyDto.class);
            log.info("객체로 변환 후 값 : {}", apiKeyDto);

            String apiKey = req.getHeader("apikey");
            
            //실제로는 객체에서 ID값을 불러와, db에 저장된 것을 조회하여, request로부터 오는 apiKey와 일치하는지를 검사.
            String testkey = apiKeyDto.getApiKey();

            if(!testkey.equals(apiKey)) {
                throw new RuntimeException("invalid api key");
            }
            filterChain.doFilter(wrapper, servletResponse);
        }
        catch (Exception e){
            //try 문에 api 인증에 걸렸을때도, 다음 필터로 진행되길 원하면 catch안에 doFilter 구현
            // filterChain.doFilter(servletRequest,servletResponse);

            //try 문에 걸리면 멈추려면 아래와 같이 예외 throw
            throw e;
        }
        finally {
            log.info("AuthorizationFilter is applied");
        }
    }

}
