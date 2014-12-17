package com.wisobi.leanbean.builder;

import com.wisobi.leanbean.jpa.entity.Device;
import com.wisobi.leanbean.jpa.entity.Meeting;
import com.wisobi.leanbean.jpa.entity.Topic;

/**
 * Created by bjork on 15/12/14.
 */
public class TopicBuilder {

  public Topic topic;

  public TopicBuilder() {
    this.topic = new Topic();
  }

  public TopicBuilder id(long id) {
    this.topic.setId(id);
    return this;
  }

  public TopicBuilder title(String title) {
    this.topic.setTitle(title);
    return this;
  }

  public TopicBuilder pitch(String pitch) {
    this.topic.setPitch(pitch);
    return this;
  }

  public TopicBuilder meeting(Meeting meeting) {
    this.topic.setMeeting(meeting);
    return this;
  }

  public TopicBuilder device(Device device) {
    this.topic.setDevice(device);
    return this;
  }

  public Topic build() {
    Topic returnTopic = this.topic;
    this.topic = new Topic();
    return returnTopic;
  }

}
