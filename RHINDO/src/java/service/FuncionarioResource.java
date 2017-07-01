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
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

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
    @Path("/departamento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response carregarDep(@PathParam("id") int id) {
        List<Funcionario> funcionarios = Facade.carregarFuncionario(id);
        
        GenericEntity<List<Funcionario>> lista = new GenericEntity<List<Funcionario>>(funcionarios) {};
        return Response.ok().entity(lista).build();
    }

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Funcionario carregar(@PathParam("cpf") String cpf) {
        Funcionario funcionario = Facade.carregarFuncionario(cpf);
        return funcionario;
    }
    
    @GET
    @Path("/{cpf}/{senha}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Funcionario carregar(@PathParam("cpf") String cpf, @PathParam("senha") String senha) {
        Funcionario funcionario = Facade.carregarFuncionario(cpf, senha);
        return funcionario;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void editar(Funcionario funcionario) {
        Facade.editarFuncionario(funcionario);
    }
}
