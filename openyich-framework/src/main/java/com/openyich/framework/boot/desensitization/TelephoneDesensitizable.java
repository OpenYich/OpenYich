package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class TelephoneDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String telephone) {
    if (StringUtils.isBlank(telephone)) {
      return "";
    }
    return StringUtils.leftPad(StringUtils.right(telephone, 4), StringUtils.length(telephone), "*");
  }

}
