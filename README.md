LeanBean
========

LeanBean is an application for facilitating and participating in Lean Coffee meetings.

## API Reference

### GET: /v1/meeting/{meeting-id}

#### Fields
| Name       | Description | Type |
| ---------- | ----------- | ---- |
| meeting-id | Meeting ID. | Long |

```bash
curl -X GET localhost:8080/v1/meeting/1
```

#### Response
```json
{
  "id": 1,
  "title": "Weekly Manager Meeting",
  "topics": [
    {
      "id": 4,
      "user": {
        "id": 4,
        "name": "Bob",
        "email": "bob@wisobi.com"
      },
      "title": "Risk of developer churn",
      "pitch": "This is a short pitch of Risk of developer churn.",
      "votes": [
        {
          "id": 3,
          "user": {
            "id": 1,
            "name": "Alice",
            "email": "alice@wisobi.com"
          }
        }
      ]
    }
  ],
  "user": {
    "id": 1,
    "name": "Alice",
    "email": "alice@wisobi.com"
  },
  "duration": 0,
  "startDateTime": null
}
```

### POST: /v1/user/

#### Fields
| Name   | Description        | Type   |
| ------ | ------------------ | ------ |
| name   | User display name. | String |
| email  | User email.        | String | 

```bash
curl -X POST -H "Content-Type: application/json" -d '{"name": "Joe", "email": "joe@wisobi.com"}' localhost:8080/v1/user/
```

### POST: /v1/meeting/

#### Fields
| Name          | Description                        | Type   |
| ------------- | ---------------------------------- | ------ |
| title         | Meeting title.                     | String |
| duration      | Meeting length in minutes.         | String | 
| startDateTime | Meeting start date and time.       | Date   |
| userId        | User ID of who added the meeting.  | Long   |

```bash
curl -X POST -H "Content-Type: application/json" -d '{"title": "Meeting Title", "userId": 1, "duration": 0, "startDateTime": null}' localhost:8080/v1/meeting/
```

### POST: /v1/topic/

#### Fields
| Name      | Description                       | Type   |
| --------- | --------------------------------- | ------ |
| title     | Topic title.                      | String |
| pitch     | Short pitch of topic.             | String | 
| meetingId | Meeting ID of topic.              | Long   |
| userId    | User ID of who added the meeting. | Long   | 

```bash
curl -X POST -H "Content-Type: application/json" -d '{"title": "Topic Title", "pitch": "This i a pitch for Topic Title", "userId": 1, "meetingId": 1}' localhost:8080/v1/topic/
```

### POST: /v1/vote/

#### Fields
| Name    | Description           | Type |
| ------- | --------------------- | ---- |
| topicId | Topic ID of vote.     | Long |
| userId  | User ID of who voted. | Long | 

```bash
curl -X POST -H "Content-Type: application/json" -d '{"topicId": 1, "userId": 1}' localhost:8080/v1/vote/
```
