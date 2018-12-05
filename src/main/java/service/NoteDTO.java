package service;
import java.util.Date;
public class NoteDTO {
    static int count;
    private String id;
    private String content;
    private long created_date;
    private String created_by;
    private long lastupdated_date;
    private String lastupdated_by;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public long getCreated_date() {
        return created_date;
    }

    public NoteDTO() {
        count ++;
        this.id = count + "";
        this.created_date = new Date().getTime();
    }

    public NoteDTO(String content, String created_by) {
        count ++;
        this.id = count + "";
        this.content = content;
        this.created_by = created_by;
        this.created_date = new Date().getTime();
    }

    public void updateContent(String content, String lastupdated_by){
        this.content = content;
        this.lastupdated_by = lastupdated_by;
        this.lastupdated_date = new Date().getTime();
    }

    public String getId() {return id;}

    public String toString(){
        return "{\"id\":\"" + id + "\"," +
                "\"content\":\"" + content + "\"," +
                "\"created_date\":" + created_date + "," +
                "\"created_by\":\"" + created_by + "\"," +
                "\"lastupdated_date\":" + lastupdated_date + "," +
                "\"lastupdated_by\":\"" + lastupdated_by +
                "\"}";
    }

    public NoteDTO getNote() {
        return this;
    }
}
