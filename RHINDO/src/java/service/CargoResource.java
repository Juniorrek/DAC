package service;

import facade.Facade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Cargo;

@Path("cargos")
public class CargoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CargoResource
     */
    public CargoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregar() {
        List<Cargo> cargos = Facade.carregarCargo();
        
        GenericEntity<List<Cargo>> lista = new GenericEntity<List<Cargo>>(cargos) {};
        return Response.ok().entity(lista).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cargo carregar(@PathParam("id") int id) {
        Cargo cargo = Facade.carregarCargo(id);
        
        return cargo;
    }
}
