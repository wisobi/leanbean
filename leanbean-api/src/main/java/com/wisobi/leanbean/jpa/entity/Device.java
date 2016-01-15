package com.wisobi.leanbean.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Created by bjork on 27/08/14.
 */
@Entity
@Table(name = "device")
public class Device implements Serializable {

  private long id;
  private String alias;
  private String uuid;
  private Date created;
  private Date updated;

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Column(unique = true)
  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created", nullable = false, updatable=false)
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated", nullable = false)
  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  @PrePersist
  protected void onCreate() {
    Date date = new Date();
    setUpdated(date);
    setCreated(date);
  }

  @PreUpdate
  protected void onUpdate() {
    setUpdated(new Date());
  }

}
