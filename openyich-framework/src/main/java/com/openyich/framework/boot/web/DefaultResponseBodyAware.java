package com.openyich.framework.boot.web;

import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.zalando.problem.Problem;

import com.google.common.base.Strings;
import com.openyich.framework.boot.aware.ResponseBodyAware;
import com.openyich.framework.boot.aware.ResponseWordsAware;
import com.openyich.framework.boot.vo.ResponseVO;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnMissingBean(ResponseBodyAware.class)
public class DefaultResponseBodyAware extends BaseController implements ResponseBodyAware {

  private ResponseWordsAware responseWordsAware;

  public DefaultResponseBodyAware(ResponseWordsAware responseWordsAware) {
    this.responseWordsAware = responseWordsAware;
  }

  @Override
  public Object handle(Object body, ServerHttpRequest request, ServerHttpResponse response) {
    if (body instanceof Problem) {
      Problem problem = (Problem) body;
      String code = String.valueOf(problem.getStatus().getStatusCode());
      String message = Strings.isNullOrEmpty(problem.getDetail())
          ? Strings.nullToEmpty(problem.getTitle())
          : Strings.nullToEmpty(problem.getDetail());
      return getWords(customize(code, message));
    }

    if (body instanceof ResponseVO) {
      ResponseVO<?> vo = (ResponseVO<?>) body;
      response.getHeaders().addAll(vo.getHeaders());
      return getWords(vo);
    }

    return body;
  }

  private ResponseVO<?> getWords(ResponseVO<?> vo) {
    if (Objects.nonNull(responseWordsAware)) {
      vo.setMessage(responseWordsAware.getWords(vo.getCode(), vo.getMessage()));
    }
    return vo;
  }

}
