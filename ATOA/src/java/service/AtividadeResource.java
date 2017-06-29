/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import facade.Facade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Folha;

/**
 * REST Web Service
 *
 * @author Fornalha
 */
@Path("atividades")
public class AtividadeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AtividadeResource
     */
    public AtividadeResource() {
    }

    /**
     * Retrieves representation of an instance of service.AtividadeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/fechar/{mes}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fechar(@PathParam("mes") int mes) {
        List<Folha> folhas = Facade.fecharFolha(mes);
        
        GenericEntity<List<Folha>> lista = new GenericEntity<List<Folha>>(folhas) {};
        return Response.ok().entity(lista).build();
    }
    
    @GET
    @Path("/horas_trabalhadas/{de}/{ate}/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response horas_trabalhadas(@PathParam("de") String de, @PathParam("ate") String ate, @PathParam("cpf") String cpf) {
        List<Folha> folhas = Facade.horas_trabalhadas(de, ate, cpf);
        
        GenericEntity<List<Folha>> lista = new GenericEntity<List<Folha>>(folhas) {};
        return Response.ok().entity(lista).build();
    }
}
