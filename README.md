# JavaJetty_Jersey
Homework Java with Jersey and Jetty

#Note DTO
- String id;
- String content;
- long created_date;
- String created_by;
- Long lastupdated_date;
- String lastupdated_by;

#API USE
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
  
