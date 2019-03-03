package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class ChineseNameDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String chineseName) {
    if (StringUtils.isBlank(chineseName)) {
      return "";
    }
    String name = StringUtils.left(chineseName, 1);
    return StringUtils.rightPad(name, StringUtils.length(chineseName), "*");
  }

}
