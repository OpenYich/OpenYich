package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class EmailDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String email) {
    if (StringUtils.isBlank(email)) {
      return "";
    }
    int index = StringUtils.indexOf(email, "@");
    if (index <= 1) {
      return email;
    }
    return StringUtils.rightPad(StringUtils.left(email, 2), index, "*")
        .concat(StringUtils.mid(email, index, StringUtils.length(email)));
  }

}
