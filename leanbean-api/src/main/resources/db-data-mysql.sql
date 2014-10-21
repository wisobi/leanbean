insert into leanbean.user (first_name, last_name, email) values
  ("Jonas", "Björk", "bjork@wisobi.com"),
  ("Shadi", "Björk", "shadi@wisobi.com");

insert into leanbean.meeting (id, title, user_id) values
  (1, "Weekly Manager Sync", 1),
  (2, "New Meeting", 1);

insert into leanbean.topic (id, meeting_id, user_id, title, pitch) values
  (1, 1, 1, "Test topic", "This is a short pitch of Test topic."),
  (2, 1, 1, "Topic number 2", "This is a short pitch of Topic number 2."),
  (3, 1, 2, "New topic", "This is a short pitch of New topic."),
  (4, 1, 2, "Bad topic", "This is a short pitch of Bad topic."),
  (5, 2, 1, "New  meeting, new topic", "This is a short pitch of New meeting.");

insert into leanbean.vote (id, meeting_id, user_id, topic_id) values
  (1, 1, 1, 1),
  (2, 1, 1, 2),
  (3, 1, 2, 2),
  (4, 1, 2, 3);