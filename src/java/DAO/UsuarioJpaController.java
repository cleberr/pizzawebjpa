/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Pedido;
import Entity.Perfil;
import Entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import repository.exceptions.NonexistentEntityException;

/**
 *
 * @author cleber
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em = null;

    public void create(Usuario usuario) {
        if (usuario.getPedidoList() == null) {
            usuario.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            Perfil idPerfil = usuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdPerfil());
                usuario.setIdPerfil(idPerfil);
            }
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : usuario.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdPedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            usuario.setPedidoList(attachedPedidoList);
            em.persist(usuario);
            if (idPerfil != null) {
                idPerfil.getUsuarioList().add(usuario);
                idPerfil = em.merge(idPerfil);
            }
            for (Pedido pedidoListPedido : usuario.getPedidoList()) {
                Usuario oldIdUsuarioOfPedidoListPedido = pedidoListPedido.getIdUsuario();
                pedidoListPedido.setIdUsuario(usuario);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldIdUsuarioOfPedidoListPedido != null) {
                    oldIdUsuarioOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldIdUsuarioOfPedidoListPedido = em.merge(oldIdUsuarioOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Perfil idPerfil = usuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getUsuarioList().remove(usuario);
                idPerfil = em.merge(idPerfil);
            }
            List<Pedido> pedidoList = usuario.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setIdUsuario(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Usuario getUsuario(String login, String senha) {
        List<Usuario> u;
        System.out.println("senha    "+senha);
        Query query = em.createQuery("select u from Usuario u where login=:login and senha=:senha", Usuario.class);

        query.setParameter("login", login);
        query.setParameter("senha", senha);
        u = query.getResultList();
        if (u.isEmpty())
            return null;
        else if(u.size() == 1) return u.get(0);
        throw new NonUniqueResultException();
     
    }

    public int getUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
