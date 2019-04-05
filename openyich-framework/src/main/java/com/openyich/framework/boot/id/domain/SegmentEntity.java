package com.openyich.framework.boot.id.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * A Leaf-segment Entity
 */
@Entity
@Table(name = "leaf_segment")
@EntityListeners(AuditingEntityListener.class)
public class SegmentEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "biz_tag")
  private String bizTag;

  @Column(name = "max_id")
  private Long maxId;

  @Column(name = "step")
  private Long step;

  @Column(name = "description")
  private String description;

  @CreatedDate
  @Column(name = "created")
  private Date created;

  @LastModifiedDate
  @Column(name = "last_modified")
  private Date lastModified;

  public String getBizTag() {
    return bizTag;
  }

  public Long getMaxId() {
    return maxId;
  }

  public Long getStep() {
    return step;
  }

  public String getDescription() {
    return description;
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setBizTag(String bizTag) {
    this.bizTag = bizTag;
  }

  public void setMaxId(Long maxId) {
    this.maxId = maxId;
  }

  public void setStep(Long step) {
    this.step = step;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public String toString() {
    return "SegmentEntity [bizTag=" + bizTag + ", maxId=" + maxId + ", step=" + step
        + ", description=" + description + ", created=" + created + ", lastModified=" + lastModified
        + "]";
  }

}
