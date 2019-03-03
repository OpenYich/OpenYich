package com.openyich.framework.boot.desensitization;

import org.apache.commons.lang3.StringUtils;

public class BankCardDesensitizable implements Desensitizable<String> {

  @Override
  public String desensitize(String bankCard) {
    if (StringUtils.isBlank(bankCard)) {
      return "";
    }
    return StringUtils.left(bankCard, 6)
        .concat(StringUtils.removeStart(
            StringUtils.leftPad(StringUtils.right(bankCard, 4), StringUtils.length(bankCard), "*"),
            "******"));
  }

}
