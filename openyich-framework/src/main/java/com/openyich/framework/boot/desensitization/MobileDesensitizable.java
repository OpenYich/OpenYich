package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class MobileDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String mobile) {
    if (StringUtils.isBlank(mobile)) {
      return "";
    }
    return StringUtils.left(mobile, 3).concat(StringUtils.removeStart(
        StringUtils.leftPad(StringUtils.right(mobile, 4), StringUtils.length(mobile), "*"), "***"));
  }
  
}
