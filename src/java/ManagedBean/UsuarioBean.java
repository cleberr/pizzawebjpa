/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import Entity.Usuario;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import DAO.UsuarioJpaController;
import java.io.Serializable;
import util.Cripto;

/**
 *
 * @author cleber
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void entra() throws NoSuchAlgorithmException, IOException {
        int erro = 0;
        Usuario u;
        if ("".equals(getUsuario().getLogin())) {
            addMessage("Login inválida");
            erro++;
        }
        if ("".equals(getUsuario().getSenha())) {
            addMessage("Senha inválida");
            erro++;

        }
        if (erro == 0) {
            //criptografar a senha
            Cripto c = new Cripto();
            UsuarioJpaController controller = new UsuarioJpaController(getEntityManager());
            u = controller.getUsuario(usuario.getLogin(), c.Md5(usuario.getSenha()));
            if (u == null) {
                addMessage("Login ou senha inválida");
                getUsuario().setSenha("");
            } else {
                this.usuario = u;
                addMessage(u.getLogin());
                FacesContext.getCurrentInstance().getExternalContext().redirect("teste.jsf"); 
            }
        }
    }

    public void addMessage(String message) {
        FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage("growl", ms);
    }

    private EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");

        return manager;
    }
}
