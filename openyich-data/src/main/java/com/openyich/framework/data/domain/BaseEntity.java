package com.openyich.framework.data.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.openyich.framework.data.domain.AbstractAuditingEntity;

/**
 * Base abstract class for entities.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity extends AbstractAuditingEntity {

  private static final long serialVersionUID = 1L;

  @Min(0)
  @Column(name = "status")
  private int status;

  @Column(name = "deleted")
  private boolean deleted;

  @Length(max = 500)
  @Column(name = "remark", length = 500)
  private String remark;

  public int getStatus() {
    return status;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public String getRemark() {
    return remark;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
