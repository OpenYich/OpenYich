package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class PasswordDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String password) {
    if (StringUtils.isBlank(password)) {
      return "";
    }
    String pwd = StringUtils.left(password, 0);
    return StringUtils.rightPad(pwd, StringUtils.length(password), "*");
  }

}
