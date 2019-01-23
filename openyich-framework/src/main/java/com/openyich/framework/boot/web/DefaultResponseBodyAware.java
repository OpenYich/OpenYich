package com.openyich.framework.boot.web;

import java.util.Map;
import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.zalando.problem.Problem;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.openyich.framework.boot.aware.ResponseBodyAware;
import com.openyich.framework.boot.errors.ProblemUtils;
import com.openyich.framework.boot.json.JSONResponse;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(ResponseBodyAware.class)
public class DefaultResponseBodyAware extends AbstractController implements ResponseBodyAware {

  @Override
  public Object handle(Object body, ServerHttpRequest request, ServerHttpResponse response) {
    
    // Handle Problem Exception
    if (body instanceof Problem) {
      Problem problem = (Problem) body;
      
      // Code
      String errorCode = ProblemUtils.getCode();
      String code = !Strings.isNullOrEmpty(errorCode)
          ? errorCode
          : String.valueOf(problem.getStatus().getStatusCode());

      // Message
      String message = !Strings.isNullOrEmpty(problem.getDetail())
          ? Strings.nullToEmpty(problem.getDetail())
          : Strings.nullToEmpty(problem.getTitle());
      return handleMessageConverter(builder(code, message));
    }

    // Handle JSONResponse
    if (body instanceof JSONResponse) {
      JSONResponse<?> res = (JSONResponse<?>) body;
      response.getHeaders().addAll(res.getHeaders());
      return handleMessageConverter(res);
    }

    return body;
  }
  
  private JSONResponse<?> handleMessageConverter(JSONResponse<?> res) {
    String message = handleMessageConverter(res.getCode(), res.getMessage());
    res.setMessage(message);

    if (Objects.isNull(res.getData())) {
      JSONResponse<Map<String, Object>> newRes = new JSONResponse<>();
      newRes.setCode(res.getCode());
      newRes.setMessage(res.getMessage());
      newRes.setExtData(res.getExtData());
      newRes.setHeaders(res.getHeaders());
      newRes.setData(Maps.newConcurrentMap());
      return newRes;
    }

    return res;
  }

}
