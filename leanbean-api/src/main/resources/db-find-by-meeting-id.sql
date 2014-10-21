select
  m.id as meeting_id,
  m.title as meeting_title,
  m.duration as meeting_duration,
  m.user_id as meeting_user_id,
  mu.first_name as meeting_user_name,
  t.id as topic_id,
  t.title as topic_title,
  t.pitch as topic_pitch,
  t.user_id as topic_user_id,
  tu.first_name as topic_user_name,
  v.user_id as vote_user_id,
  vu.first_name as vote_user_name
from meeting as m
left outer join user as mu on m.user_id = mu.id
left join topic as t on m.id = t.meeting_id
left join user as tu on t.user_id = tu.id
left join vote as v on t.id = v.topic_id and m.id = v.meeting_id
left join user as vu on v.user_id = vu.id
where m.id = ?
order by m.id, t.id, v.id