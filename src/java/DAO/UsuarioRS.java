/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Entity.Usuario;
/**
 *
 * @author cleber
 */
public class UsuarioRS {

    private EntityManager manager;

    public UsuarioRS(EntityManager manager) {
        this.manager = manager;
    }

    public List<Usuario>getUsuarios(Integer s) {
        List<Usuario> u = new ArrayList<>();
        Query query =this.manager.createQuery("SELECT u FROM Usuario u ");
        u = query.getResultList();
       // System.out.print(u);
        return u;

    }
}
