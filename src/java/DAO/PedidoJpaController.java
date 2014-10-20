/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Cliente;
import Entity.Usuario;
import Entity.ItemPedido;
import java.util.ArrayList;
import java.util.List;
import Entity.ContasReceber;
import Entity.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) throws PreexistingEntityException, Exception {
        if (pedido.getItemPedidoList() == null) {
            pedido.setItemPedidoList(new ArrayList<ItemPedido>());
        }
        if (pedido.getContasReceberList() == null) {
            pedido.setContasReceberList(new ArrayList<ContasReceber>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idCliente = pedido.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                pedido.setIdCliente(idCliente);
            }
            Usuario idUsuario = pedido.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                pedido.setIdUsuario(idUsuario);
            }
            List<ItemPedido> attachedItemPedidoList = new ArrayList<ItemPedido>();
            for (ItemPedido itemPedidoListItemPedidoToAttach : pedido.getItemPedidoList()) {
                itemPedidoListItemPedidoToAttach = em.getReference(itemPedidoListItemPedidoToAttach.getClass(), itemPedidoListItemPedidoToAttach.getIdPedidoItem());
                attachedItemPedidoList.add(itemPedidoListItemPedidoToAttach);
            }
            pedido.setItemPedidoList(attachedItemPedidoList);
            List<ContasReceber> attachedContasReceberList = new ArrayList<ContasReceber>();
            for (ContasReceber contasReceberListContasReceberToAttach : pedido.getContasReceberList()) {
                contasReceberListContasReceberToAttach = em.getReference(contasReceberListContasReceberToAttach.getClass(), contasReceberListContasReceberToAttach.getIdContaReceber());
                attachedContasReceberList.add(contasReceberListContasReceberToAttach);
            }
            pedido.setContasReceberList(attachedContasReceberList);
            em.persist(pedido);
            if (idCliente != null) {
                idCliente.getPedidoList().add(pedido);
                idCliente = em.merge(idCliente);
            }
            if (idUsuario != null) {
                idUsuario.getPedidoList().add(pedido);
                idUsuario = em.merge(idUsuario);
            }
            for (ItemPedido itemPedidoListItemPedido : pedido.getItemPedidoList()) {
                Pedido oldIdPedidoOfItemPedidoListItemPedido = itemPedidoListItemPedido.getIdPedido();
                itemPedidoListItemPedido.setIdPedido(pedido);
                itemPedidoListItemPedido = em.merge(itemPedidoListItemPedido);
                if (oldIdPedidoOfItemPedidoListItemPedido != null) {
                    oldIdPedidoOfItemPedidoListItemPedido.getItemPedidoList().remove(itemPedidoListItemPedido);
                    oldIdPedidoOfItemPedidoListItemPedido = em.merge(oldIdPedidoOfItemPedidoListItemPedido);
                }
            }
            for (ContasReceber contasReceberListContasReceber : pedido.getContasReceberList()) {
                Pedido oldIdPedidoOfContasReceberListContasReceber = contasReceberListContasReceber.getIdPedido();
                contasReceberListContasReceber.setIdPedido(pedido);
                contasReceberListContasReceber = em.merge(contasReceberListContasReceber);
                if (oldIdPedidoOfContasReceberListContasReceber != null) {
                    oldIdPedidoOfContasReceberListContasReceber.getContasReceberList().remove(contasReceberListContasReceber);
                    oldIdPedidoOfContasReceberListContasReceber = em.merge(oldIdPedidoOfContasReceberListContasReceber);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPedido(pedido.getIdPedido()) != null) {
                throw new PreexistingEntityException("Pedido " + pedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdPedido());
            Cliente idClienteOld = persistentPedido.getIdCliente();
            Cliente idClienteNew = pedido.getIdCliente();
            Usuario idUsuarioOld = persistentPedido.getIdUsuario();
            Usuario idUsuarioNew = pedido.getIdUsuario();
            List<ItemPedido> itemPedidoListOld = persistentPedido.getItemPedidoList();
            List<ItemPedido> itemPedidoListNew = pedido.getItemPedidoList();
            List<ContasReceber> contasReceberListOld = persistentPedido.getContasReceberList();
            List<ContasReceber> contasReceberListNew = pedido.getContasReceberList();
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                pedido.setIdCliente(idClienteNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                pedido.setIdUsuario(idUsuarioNew);
            }
            List<ItemPedido> attachedItemPedidoListNew = new ArrayList<ItemPedido>();
            for (ItemPedido itemPedidoListNewItemPedidoToAttach : itemPedidoListNew) {
                itemPedidoListNewItemPedidoToAttach = em.getReference(itemPedidoListNewItemPedidoToAttach.getClass(), itemPedidoListNewItemPedidoToAttach.getIdPedidoItem());
                attachedItemPedidoListNew.add(itemPedidoListNewItemPedidoToAttach);
            }
            itemPedidoListNew = attachedItemPedidoListNew;
            pedido.setItemPedidoList(itemPedidoListNew);
            List<ContasReceber> attachedContasReceberListNew = new ArrayList<ContasReceber>();
            for (ContasReceber contasReceberListNewContasReceberToAttach : contasReceberListNew) {
                contasReceberListNewContasReceberToAttach = em.getReference(contasReceberListNewContasReceberToAttach.getClass(), contasReceberListNewContasReceberToAttach.getIdContaReceber());
                attachedContasReceberListNew.add(contasReceberListNewContasReceberToAttach);
            }
            contasReceberListNew = attachedContasReceberListNew;
            pedido.setContasReceberList(contasReceberListNew);
            pedido = em.merge(pedido);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getPedidoList().remove(pedido);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getPedidoList().add(pedido);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getPedidoList().remove(pedido);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getPedidoList().add(pedido);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (ItemPedido itemPedidoListOldItemPedido : itemPedidoListOld) {
                if (!itemPedidoListNew.contains(itemPedidoListOldItemPedido)) {
                    itemPedidoListOldItemPedido.setIdPedido(null);
                    itemPedidoListOldItemPedido = em.merge(itemPedidoListOldItemPedido);
                }
            }
            for (ItemPedido itemPedidoListNewItemPedido : itemPedidoListNew) {
                if (!itemPedidoListOld.contains(itemPedidoListNewItemPedido)) {
                    Pedido oldIdPedidoOfItemPedidoListNewItemPedido = itemPedidoListNewItemPedido.getIdPedido();
                    itemPedidoListNewItemPedido.setIdPedido(pedido);
                    itemPedidoListNewItemPedido = em.merge(itemPedidoListNewItemPedido);
                    if (oldIdPedidoOfItemPedidoListNewItemPedido != null && !oldIdPedidoOfItemPedidoListNewItemPedido.equals(pedido)) {
                        oldIdPedidoOfItemPedidoListNewItemPedido.getItemPedidoList().remove(itemPedidoListNewItemPedido);
                        oldIdPedidoOfItemPedidoListNewItemPedido = em.merge(oldIdPedidoOfItemPedidoListNewItemPedido);
                    }
                }
            }
            for (ContasReceber contasReceberListOldContasReceber : contasReceberListOld) {
                if (!contasReceberListNew.contains(contasReceberListOldContasReceber)) {
                    contasReceberListOldContasReceber.setIdPedido(null);
                    contasReceberListOldContasReceber = em.merge(contasReceberListOldContasReceber);
                }
            }
            for (ContasReceber contasReceberListNewContasReceber : contasReceberListNew) {
                if (!contasReceberListOld.contains(contasReceberListNewContasReceber)) {
                    Pedido oldIdPedidoOfContasReceberListNewContasReceber = contasReceberListNewContasReceber.getIdPedido();
                    contasReceberListNewContasReceber.setIdPedido(pedido);
                    contasReceberListNewContasReceber = em.merge(contasReceberListNewContasReceber);
                    if (oldIdPedidoOfContasReceberListNewContasReceber != null && !oldIdPedidoOfContasReceberListNewContasReceber.equals(pedido)) {
                        oldIdPedidoOfContasReceberListNewContasReceber.getContasReceberList().remove(contasReceberListNewContasReceber);
                        oldIdPedidoOfContasReceberListNewContasReceber = em.merge(oldIdPedidoOfContasReceberListNewContasReceber);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdPedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            Cliente idCliente = pedido.getIdCliente();
            if (idCliente != null) {
                idCliente.getPedidoList().remove(pedido);
                idCliente = em.merge(idCliente);
            }
            Usuario idUsuario = pedido.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getPedidoList().remove(pedido);
                idUsuario = em.merge(idUsuario);
            }
            List<ItemPedido> itemPedidoList = pedido.getItemPedidoList();
            for (ItemPedido itemPedidoListItemPedido : itemPedidoList) {
                itemPedidoListItemPedido.setIdPedido(null);
                itemPedidoListItemPedido = em.merge(itemPedidoListItemPedido);
            }
            List<ContasReceber> contasReceberList = pedido.getContasReceberList();
            for (ContasReceber contasReceberListContasReceber : contasReceberList) {
                contasReceberListContasReceber.setIdPedido(null);
                contasReceberListContasReceber = em.merge(contasReceberListContasReceber);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
