# JavaJetty_Jersey
Homework Java with Jersey and Jetty

# Deployed to Heroku
URL: https://sleepy-peak-40154.herokuapp.com

# Load test
Summary:

Total:    3.2440 secs

Slowest:    2.7530 secs

Fastest:    0.4824 secs

Average:    1.4633 secs

Requests/sec:    30.8266


Total data:    10261 bytes

Size/request:    102 bytes


Response time histogram:

0.482 [1]    |■

0.709 [11]    |■■■■■■■■■■■■■■

0.937 [7]    |■■■■■■■■■

1.164 [32]    |■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

1.391 [0]    |

1.618 [0]    |

1.845 [21]    |■■■■■■■■■■■■■■■■■■■■■■■■■■

2.072 [10]    |■■■■■■■■■■■■■

2.299 [3]    |■■■■

2.526 [4]    |■■■■■

2.753 [11]    |■■■■■■■■■■■■■■


Latency distribution:

10% in 0.6279 secs

25% in 1.0021 secs

50% in 1.1301 secs

75% in 1.8604 secs

90% in 2.5329 secs

95% in 2.5883 secs

99% in 2.7530 secs

Details (average, fastest, slowest):

DNS+dialup:    0.4571 secs, 0.4824 secs, 2.7530 secs

DNS-lookup:    0.0352 secs, 0.0001 secs, 0.0709 secs

req write:    0.0001 secs, 0.0000 secs, 0.0002 secs

resp wait:    0.2824 secs, 0.2328 secs, 1.0339 secs

resp read:    0.0009 secs, 0.0000 secs, 0.0100 secs

Status code distribution:

[200]    69 responses

[429]    31 responses

# Note DTO
- String id;
- String content;
- long created_date;
- String created_by;
- Long lastupdated_date;
- String lastupdated_by;

# API USE
- Create new Note with data

  Method : POST.
  
  URL: api/add
  
  Header :
    Content-Type: application/type
    
  Body
      {
        "content" : "Note content",
        "created_by" : "username"
      }
- Get node with id

  Method: GET
  
  URL: api/note?id=
  
- Get all notes

  Method: GET
  
  URL: api/all
  
- Search note with id (quey param)

  Method: GET
  
  URL: api/search
  
  Query params:
  
    + created_by
    + from (long value only)
    + content
    
- Update note with id (path param)

  Method: PUT
  
  URL: api/update/{id}
  
  Body: 
    {
        "content" : "Note content",
        "lastupdated_by" : "username"
    }
    
- Delete note with id (path param)

  Method: DELETE
  
  URl: api/delete/{id}
  
