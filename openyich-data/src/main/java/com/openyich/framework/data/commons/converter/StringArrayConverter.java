package com.openyich.framework.data.commons.converter;

import java.util.Objects;

import javax.persistence.AttributeConverter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class StringArrayConverter implements AttributeConverter<String[], String> {

  @Override
  public String convertToDatabaseColumn(String[] attribute) {
    return JSON.toJSONString(attribute);
  }

  @Override
  public String[] convertToEntityAttribute(String dbData) {
    if (Objects.isNull(dbData)) {
      return new String[0];
    }
    return JSON.parseObject(dbData, new TypeReference<String[]>() {}.getType());
  }

}
