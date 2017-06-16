package service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Funcionario;
import facade.Facade;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;

@Path("funcionarios")
public class FuncionarioResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioResource
     */
    public FuncionarioResource() {
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Funcionario carregar(@PathParam("cpf") String cpf) {
        Funcionario funcionario = Facade.carregarFuncionario(cpf);
        
        return funcionario;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editar(Funcionario funcionario) {
        Facade.editarFuncionario(funcionario);
    }
}
