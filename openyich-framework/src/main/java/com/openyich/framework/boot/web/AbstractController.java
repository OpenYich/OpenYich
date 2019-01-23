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
import com.openyich.framework.boot.json.JSONResponse;

/**
 * Base Controller for Spring MVC Controller.
 */
public abstract class AbstractController {

  protected static <T> JSONResponse<T> bodyBuilder() {
    return bodyBuilder(null, null, null);
  }

  protected static <T> JSONResponse<T> bodyBuilder(
      @Nullable MultiValueMap<String, String> headers) {
    return bodyBuilder(null, null, headers);
  }

  protected static <T> JSONResponse<List<T>> bodyBuilder(Page<T> page) {
    return bodyBuilder(page, new HttpHeaders());
  }

  protected static <T> JSONResponse<List<T>> bodyBuilder(Page<T> page,
      @Nullable MultiValueMap<String, String> headers) {
    Map<String, Object> extData = Maps.newHashMap();
    extData.put("isFirst", page.isFirst());
    extData.put("isLast", page.isLast());
    extData.put("hasNext", page.hasNext());
    extData.put("hasPrevious", page.hasPrevious());
    extData.put("totalPages", page.getTotalPages());
    extData.put("totalElements", page.getTotalElements());
    return httpBuilder(HttpStatus.OK, page.getContent(), extData, headers);
  }

  protected static <T> JSONResponse<T> bodyBuilder(@Nullable T data) {
    return bodyBuilder(data, null, null);
  }

  protected static <T> JSONResponse<T> bodyBuilder(@Nullable T data,
      @Nullable Map<String, Object> extData) {
    return bodyBuilder(data, extData, null);
  }

  protected static <T> JSONResponse<T> bodyBuilder(@Nullable T data,
      @Nullable Map<String, Object> extData, @Nullable MultiValueMap<String, String> headers) {
    return httpBuilder(HttpStatus.OK, data, extData, headers);
  }

  protected static <T> JSONResponse<T> bodyBuilder(@Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return bodyBuilder(data, null, headers);
  }

  protected static <T> JSONResponse<T> builder(String code, String message) {
    return builder(code, message, null, null, null);
  }

  protected static <T> JSONResponse<T> builder(String code, String message,
      @Nullable MultiValueMap<String, String> headers) {
    return builder(code, message, null, null, headers);
  }

  protected static <T> JSONResponse<T> builder(String code, String message, @Nullable T data) {
    return builder(code, message, data, null, null);
  }

  protected static <T> JSONResponse<T> builder(String code, String message, @Nullable T data,
      @Nullable Map<String, Object> extData) {
    return builder(code, message, data, extData, null);
  }

  protected static <T> JSONResponse<T> builder(String code, String message, @Nullable T data,
      @Nullable Map<String, Object> extData, @Nullable MultiValueMap<String, String> headers) {
    JSONResponse<T> res = new JSONResponse<>();
    res.setCode(code);
    res.setMessage(message);
    res.setData(data);

    if (!CollectionUtils.isEmpty(extData)) {
      res.setExtData(extData);
    }

    HttpHeaders tempHeaders = new HttpHeaders();
    if (headers != null) {
      tempHeaders.putAll(headers);
    }
    res.setHeaders(HttpHeaders.readOnlyHttpHeaders(tempHeaders));
    return res;
  }

  protected static <T> JSONResponse<T> builder(String code, String message, @Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return builder(code, message, data, null, headers);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status) {
    return httpBuilder(status, null, null, null);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status,
      @Nullable MultiValueMap<String, String> headers) {
    return httpBuilder(status, null, null, headers);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status, @Nullable T data) {
    return httpBuilder(status, data, null, null);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status, @Nullable T data,
      @Nullable Map<String, Object> extData) {
    return httpBuilder(status, data, extData, null);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status, @Nullable T data,
      @Nullable Map<String, Object> extData, @Nullable MultiValueMap<String, String> headers) {
    Assert.notNull(status, "HttpStatus must not be null");
    return builder(String.valueOf(status.value()), status.getReasonPhrase(), data, extData,
        headers);
  }

  protected static <T> JSONResponse<T> httpBuilder(HttpStatus status, @Nullable T data,
      @Nullable MultiValueMap<String, String> headers) {
    return httpBuilder(status, data, null, headers);
  }

}
