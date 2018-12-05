package service;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("/")
public class Controller {
    static List<NoteDTO> Notes = new ArrayList<NoteDTO>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response forFun() {
        return Response.ok("You are here!!!!", MediaType.TEXT_PLAIN).build();
    }
    //Create note /api/add
    @POST
    @Path("/api/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(String body) {
        try {
            Gson gson = new Gson();
            NoteDTO note;
            note = gson.fromJson(body, NoteDTO.class);
            Notes.add(note);
            return Response.ok(new Gson().toJson(note)).build();
        }
        catch (JsonParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad request: " + body).build();
        }
    }
    //Get note with id api/note?id=1
    @GET
    @Path("/api/note")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteById(@QueryParam("id") String id) {
        if(id == null || id.trim().length() == 0) {
            return Response.serverError().entity("ID is ").build();
        }
        for (int i = 0; i < Notes.size(); i++) {
            if (Notes.get(i).getId().equalsIgnoreCase(id)) {
                return Response.ok(Notes.get(i).toString(), MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("No result for ID: " + id).build();
    }
    //Get all notes api/all
    @GET
    @Path("/api/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteList() {
        System.out.println("All Notes");
        return Response.ok(new Gson().toJson(Notes), MediaType.APPLICATION_JSON).build();
    }

    //Search note with query para @GET
    @Path("/api/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchNotesByCreatedBy(
            @DefaultValue("")   @QueryParam("created_by")   String user,
            @DefaultValue("")   @QueryParam("content")      String content,
            @DefaultValue("-1") @QueryParam("from_date")    long from
    ) {
        List<NoteDTO> result = new ArrayList<NoteDTO>();
        for (int i = 0; i < Notes.size(); i++) {
            NoteDTO currentNote = Notes.get(i);
            if ((user.trim().length() == 0 || currentNote.getCreated_by().equalsIgnoreCase(user)) &&
                    (content.trim().length() == 0 || currentNote.getContent().equalsIgnoreCase(content)) &&
                    (currentNote.getCreated_date() > from))
            {
                result.add(Notes.get(i));
            }
        }
        if (result.size() > 0)
            return Response.ok(new Gson().toJson(result), MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("No result").build();
    }
    //Update Noto api/update/{}
    @PUT
    @Path("/api/update{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") String id, String body) {
        if(id == null || id.trim().length() == 0) {
            return Response.serverError().entity("ID is null").build();
        }
        try {
            Gson gson = new Gson();
            NoteDTO note;
            note = gson.fromJson(body, NoteDTO.class);
            for (int i = 0; i < Notes.size(); i++) {
                if (Notes.get(i).getId().equalsIgnoreCase(id)) {
                    Notes.get(i).updateContent(note.getContent(), note.getCreated_by());
                    return Response.ok(Notes.get(i).toString(), MediaType.APPLICATION_JSON).build();
                }
            }
            return Response.status(404).entity("No result for ID: " + id).build();
        }
        catch (JsonParseException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad request: " + body).build();
        }
    }
    //Delete note at id api/delete/{id}
    @DELETE
    @Path("/api/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNote(@PathParam("id") String id) {
        if(id == null || id.trim().length() == 0) {
            return Response.serverError().entity("ID is Null").build();
        }
        for (int i = 0; i < Notes.size(); i++) {
            if (Notes.get(i).getId().equalsIgnoreCase(id)) {
                Notes.remove(i);
                return Response.status(200).entity("Delete Success!!!").build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("No result for ID: " + id).build();
    }
}