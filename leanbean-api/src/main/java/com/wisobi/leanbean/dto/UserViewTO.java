package com.wisobi.leanbean.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by bjork on 05/11/14.
 */
public class UserViewTO implements Serializable {

  private long id;
  private String name;
  private String email;

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
