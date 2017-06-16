package service;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Departamento;
import facade.Facade;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;

@Path("departamentos")
public class DepartamentoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DepartamentoResource
     */
    public DepartamentoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregar() {
        List<Departamento> departamentos = Facade.carregarDepartamento();
        
        GenericEntity<List<Departamento>> lista = new GenericEntity<List<Departamento>>(departamentos) {};
        return Response.ok().entity(lista).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Departamento carregar(@PathParam("id") int id) {
        Departamento departamento = Facade.carregarDepartamento(id);
        
        return departamento;
    }
}
