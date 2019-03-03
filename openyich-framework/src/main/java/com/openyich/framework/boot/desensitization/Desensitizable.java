package com.openyich.framework.boot.desensitization;

public interface Desensitizable<T> {

  T desensitize(T data);

}
