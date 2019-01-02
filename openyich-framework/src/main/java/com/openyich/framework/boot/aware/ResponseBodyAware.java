package com.openyich.framework.boot.aware;

public interface ResponseBodyAware {

  Object convert(Object responseBody);

}
