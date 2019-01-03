package com.openyich.framework.boot.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import com.google.common.collect.Maps;
import com.openyich.framework.boot.vo.ResponseVO;

/**
 * Base Controller for Spring MVC RestController.
 */
public abstract class BaseController {

  protected static <T> ResponseVO<T> customize(String code, String message) {
    return customize(code, message, null, null, null);
  }

  protected static <T> ResponseVO<T> customize(String code, String message,
      @Nullable MultiValueMap<String, String> headers) {
    return customize(code, message, null, null, headers);
  }

  protected static <T> ResponseVO<T> customize(String code, String message, @Nullable T data) {
    return customize(code, message, data, null, null);
  }

  protected static <T> ResponseVO<T> customize(String code, String message, @Nullable T data,
      @Nullable Map<String, Object> extData) {
    return customize(code, message, data, extData, null);
  }

  protected static <T> ResponseVO<T> customize(String code, String message, @Nullable T data,
      @Nullable Map<String, Object> extData, @Nullable MultiValueMap<String, String> headers) {
    ResponseVO<T> vo = new ResponseVO<>();
    vo.setCode(code);
    vo.setMessage(message);
    vo.setData(data);

    if (!CollectionUtils.isEmpty(extData)) {
      vo.setExtData(extData);
    }

    HttpHeaders tempHeaders = new HttpHeaders();
    if (headers != null) {
      tempHeaders.putAll(headers);
    }
    vo.setHeaders(HttpHeaders.readOnlyHttpHeaders(tempHeaders));
    return vo;
  }

  protected static <T> ResponseVO<T> customize(String code, String message, @Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return customize(code, message, data, null, headers);
  }

  protected static <T> ResponseVO<T> ok() {
    return ok(null, null, null);
  }

  protected static <T> ResponseVO<T> ok(@Nullable MultiValueMap<String, String> headers) {
    return ok(null, null, headers);
  }

  protected static <T> ResponseVO<T> ok(@Nullable T data) {
    return ok(data, null, null);
  }

  protected static <T> ResponseVO<T> ok(@Nullable T data, @Nullable Map<String, Object> extData) {
    return ok(data, extData, null);
  }

  protected static <T> ResponseVO<T> ok(@Nullable T data, @Nullable Map<String, Object> extData,
      @Nullable MultiValueMap<String, String> headers) {
    return status(HttpStatus.OK, data, extData, headers);
  }

  protected static <T> ResponseVO<T> ok(@Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return ok(data, null, headers);
  }

  protected static <T> ResponseVO<List<T>> pageable(Page<T> page) {
    return pageable(page, null);
  }

  protected static <T> ResponseVO<List<T>> pageable(Page<T> page,
      @Nullable MultiValueMap<String, String> headers) {
    Map<String, Object> extData = Maps.newConcurrentMap();
    extData.put("isFirst", page.isFirst());
    extData.put("isLast", page.isLast());
    extData.put("hasNext", page.hasNext());
    extData.put("hasPrevious", page.hasPrevious());
    extData.put("totalPages", page.getTotalPages());
    extData.put("totalElements", page.getTotalElements());
    return status(HttpStatus.OK, page.getContent(), extData, headers);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status) {
    return status(status, null, null, null);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status,
      @Nullable MultiValueMap<String, String> headers) {
    return status(status, null, null, headers);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status, @Nullable T data) {
    return status(status, data, null, null);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status, @Nullable T data,
      @Nullable Map<String, Object> extData) {
    return status(status, data, extData, null);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status, @Nullable T data,
      @Nullable Map<String, Object> extData, @Nullable MultiValueMap<String, String> headers) {
    Assert.notNull(status, "HttpStatus must not be null");
    return customize(String.valueOf(status.value()), status.getReasonPhrase(), data, extData,
        headers);
  }

  protected static <T> ResponseVO<T> status(HttpStatus status, @Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return status(status, data, null, headers);
  }

}
