package com.openyich.framework.boot.jpa.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openyich.framework.boot.jpa.domain.AbstractAuditingEntity;

/**
 * Base abstract class for entities which will hold definitions for status, deleted, remark.
 * 
 * @see AbstractAuditingEntity
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractStateManagingEntity extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Min(0)
  @Column(name = "status")
  @JsonIgnore
  private Integer status = 0;

  @Column(name = "deleted")
  @JsonIgnore
  private Boolean deleted = Boolean.FALSE;

  @Length(max = 500)
  @Column(name = "remark", length = 500)
  @JsonIgnore
  private String remark;

  public Integer getStatus() {
    return status;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public String getRemark() {
    return remark;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
