package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class IdCardDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String idCard) {
    if (StringUtils.isBlank(idCard)) {
      return "";
    }
    String num = StringUtils.right(idCard, 4);
    return StringUtils.leftPad(num, StringUtils.length(idCard), "*");
  }

}
