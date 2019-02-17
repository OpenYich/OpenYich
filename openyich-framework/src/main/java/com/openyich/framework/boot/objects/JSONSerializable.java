package com.openyich.framework.boot.objects;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public interface JSONSerializable extends Serializable {

  default String toJSONString() {
    return JSON.toJSONString(this);
  }

}
