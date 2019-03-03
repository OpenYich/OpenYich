package com.openyich.framework.boot.vo;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.openyich.framework.boot.objects.JSONSerializable;

/**
 * Base class for Spring MVC Query Request types.
 */
public class QueryVO implements JSONSerializable {

  private static final long serialVersionUID = 1L;

  private String q;

  private String startDate;

  private String endDate;

  public String getQ() {
    return q;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  
  public Date parseStartDate() throws ParseException {
    return parseStartDate("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
  }

  public Date parseStartDate(String... parsePatterns) throws ParseException {
    return DateUtils.parseDate(startDate, parsePatterns);
  }

  public Date parseEndDate() throws ParseException {
    return parseEndDate("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
  }

  public Date parseEndDate(String... parsePatterns) throws ParseException {
    Date eDate = DateUtils.parseDate(endDate, parsePatterns);
    if (StringUtils.equals(startDate, endDate)) {
      return DateUtils.addDays(eDate, 1);
    }
    return eDate;
  }

}
