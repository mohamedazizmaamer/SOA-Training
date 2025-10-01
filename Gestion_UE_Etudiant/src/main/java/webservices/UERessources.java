package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;




@Path("/ue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UERessources {
    //////    public Response sayHello(){
    //////        return Response
    //////                .status(200)
    //////                .entity("Hello World!")
    //////                .build();

    private UniteEnseignementBusiness business = new UniteEnseignementBusiness();

    @GET
    public Response getAllUEs() {
        return Response.ok(business.getListeUE()).build();
    }

    @GET
    @Path("/{code}")
    public Response getUEByCode(@PathParam("code") int code) {
        UniteEnseignement ue = business.getUEByCode(code);
        if (ue == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(ue).build();
    }

    @POST
    public Response addUE(UniteEnseignement ue) {
        boolean added = business.addUniteEnseignement(ue);
        return added ? Response.status(Response.Status.CREATED).entity(ue).build()
                : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        boolean updated = business.updateUniteEnseignement(code, ue);
        return updated ? Response.ok(ue).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        boolean deleted = business.deleteUniteEnseignement(code);
        return deleted ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
