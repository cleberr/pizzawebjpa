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
import Entity.Funcionario;
import Entity.TipoPessoa;
import Entity.TelefonePessoa;
import java.util.ArrayList;
import java.util.List;
import Entity.Cliente;
import Entity.Entregador;
import Entity.Endereco;
import Entity.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import repository.exceptions.NonexistentEntityException;
import repository.exceptions.PreexistingEntityException;

/**
 *
 * @author cleber
 */
public class PessoaJpaController implements Serializable {

    public PessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pessoa pessoa) throws PreexistingEntityException, Exception {
        if (pessoa.getTelefonePessoaList() == null) {
            pessoa.setTelefonePessoaList(new ArrayList<TelefonePessoa>());
        }
        if (pessoa.getClienteList() == null) {
            pessoa.setClienteList(new ArrayList<Cliente>());
        }
        if (pessoa.getEntregadorList() == null) {
            pessoa.setEntregadorList(new ArrayList<Entregador>());
        }
        if (pessoa.getEnderecoList() == null) {
            pessoa.setEnderecoList(new ArrayList<Endereco>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario idUsuarioCadastro = pessoa.getIdUsuarioCadastro();
            if (idUsuarioCadastro != null) {
                idUsuarioCadastro = em.getReference(idUsuarioCadastro.getClass(), idUsuarioCadastro.getIdPessoa());
                pessoa.setIdUsuarioCadastro(idUsuarioCadastro);
            }
            TipoPessoa idTipo = pessoa.getIdTipo();
            if (idTipo != null) {
                idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipoPessoa());
                pessoa.setIdTipo(idTipo);
            }
            List<TelefonePessoa> attachedTelefonePessoaList = new ArrayList<TelefonePessoa>();
            for (TelefonePessoa telefonePessoaListTelefonePessoaToAttach : pessoa.getTelefonePessoaList()) {
                telefonePessoaListTelefonePessoaToAttach = em.getReference(telefonePessoaListTelefonePessoaToAttach.getClass(), telefonePessoaListTelefonePessoaToAttach.getIdTelefone());
                attachedTelefonePessoaList.add(telefonePessoaListTelefonePessoaToAttach);
            }
            pessoa.setTelefonePessoaList(attachedTelefonePessoaList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : pessoa.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdCliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            pessoa.setClienteList(attachedClienteList);
            List<Entregador> attachedEntregadorList = new ArrayList<Entregador>();
            for (Entregador entregadorListEntregadorToAttach : pessoa.getEntregadorList()) {
                entregadorListEntregadorToAttach = em.getReference(entregadorListEntregadorToAttach.getClass(), entregadorListEntregadorToAttach.getIdEntregador());
                attachedEntregadorList.add(entregadorListEntregadorToAttach);
            }
            pessoa.setEntregadorList(attachedEntregadorList);
            List<Endereco> attachedEnderecoList = new ArrayList<Endereco>();
            for (Endereco enderecoListEnderecoToAttach : pessoa.getEnderecoList()) {
                enderecoListEnderecoToAttach = em.getReference(enderecoListEnderecoToAttach.getClass(), enderecoListEnderecoToAttach.getIdEndereco());
                attachedEnderecoList.add(enderecoListEnderecoToAttach);
            }
            pessoa.setEnderecoList(attachedEnderecoList);
            em.persist(pessoa);
            if (idUsuarioCadastro != null) {
                idUsuarioCadastro.getPessoaList().add(pessoa);
                idUsuarioCadastro = em.merge(idUsuarioCadastro);
            }
            if (idTipo != null) {
                idTipo.getPessoaList().add(pessoa);
                idTipo = em.merge(idTipo);
            }
            for (TelefonePessoa telefonePessoaListTelefonePessoa : pessoa.getTelefonePessoaList()) {
                Pessoa oldIdPessoaOfTelefonePessoaListTelefonePessoa = telefonePessoaListTelefonePessoa.getIdPessoa();
                telefonePessoaListTelefonePessoa.setIdPessoa(pessoa);
                telefonePessoaListTelefonePessoa = em.merge(telefonePessoaListTelefonePessoa);
                if (oldIdPessoaOfTelefonePessoaListTelefonePessoa != null) {
                    oldIdPessoaOfTelefonePessoaListTelefonePessoa.getTelefonePessoaList().remove(telefonePessoaListTelefonePessoa);
                    oldIdPessoaOfTelefonePessoaListTelefonePessoa = em.merge(oldIdPessoaOfTelefonePessoaListTelefonePessoa);
                }
            }
            for (Cliente clienteListCliente : pessoa.getClienteList()) {
                Pessoa oldIdPessoaOfClienteListCliente = clienteListCliente.getIdPessoa();
                clienteListCliente.setIdPessoa(pessoa);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldIdPessoaOfClienteListCliente != null) {
                    oldIdPessoaOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldIdPessoaOfClienteListCliente = em.merge(oldIdPessoaOfClienteListCliente);
                }
            }
            for (Entregador entregadorListEntregador : pessoa.getEntregadorList()) {
                Pessoa oldIdPessoaOfEntregadorListEntregador = entregadorListEntregador.getIdPessoa();
                entregadorListEntregador.setIdPessoa(pessoa);
                entregadorListEntregador = em.merge(entregadorListEntregador);
                if (oldIdPessoaOfEntregadorListEntregador != null) {
                    oldIdPessoaOfEntregadorListEntregador.getEntregadorList().remove(entregadorListEntregador);
                    oldIdPessoaOfEntregadorListEntregador = em.merge(oldIdPessoaOfEntregadorListEntregador);
                }
            }
            for (Endereco enderecoListEndereco : pessoa.getEnderecoList()) {
                Pessoa oldIdPessoaOfEnderecoListEndereco = enderecoListEndereco.getIdPessoa();
                enderecoListEndereco.setIdPessoa(pessoa);
                enderecoListEndereco = em.merge(enderecoListEndereco);
                if (oldIdPessoaOfEnderecoListEndereco != null) {
                    oldIdPessoaOfEnderecoListEndereco.getEnderecoList().remove(enderecoListEndereco);
                    oldIdPessoaOfEnderecoListEndereco = em.merge(oldIdPessoaOfEnderecoListEndereco);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPessoa(pessoa.getIdPessoa()) != null) {
                throw new PreexistingEntityException("Pessoa " + pessoa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pessoa pessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa persistentPessoa = em.find(Pessoa.class, pessoa.getIdPessoa());
            Funcionario idUsuarioCadastroOld = persistentPessoa.getIdUsuarioCadastro();
            Funcionario idUsuarioCadastroNew = pessoa.getIdUsuarioCadastro();
            TipoPessoa idTipoOld = persistentPessoa.getIdTipo();
            TipoPessoa idTipoNew = pessoa.getIdTipo();
            List<TelefonePessoa> telefonePessoaListOld = persistentPessoa.getTelefonePessoaList();
            List<TelefonePessoa> telefonePessoaListNew = pessoa.getTelefonePessoaList();
            List<Cliente> clienteListOld = persistentPessoa.getClienteList();
            List<Cliente> clienteListNew = pessoa.getClienteList();
            List<Entregador> entregadorListOld = persistentPessoa.getEntregadorList();
            List<Entregador> entregadorListNew = pessoa.getEntregadorList();
            List<Endereco> enderecoListOld = persistentPessoa.getEnderecoList();
            List<Endereco> enderecoListNew = pessoa.getEnderecoList();
            if (idUsuarioCadastroNew != null) {
                idUsuarioCadastroNew = em.getReference(idUsuarioCadastroNew.getClass(), idUsuarioCadastroNew.getIdPessoa());
                pessoa.setIdUsuarioCadastro(idUsuarioCadastroNew);
            }
            if (idTipoNew != null) {
                idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipoPessoa());
                pessoa.setIdTipo(idTipoNew);
            }
            List<TelefonePessoa> attachedTelefonePessoaListNew = new ArrayList<TelefonePessoa>();
            for (TelefonePessoa telefonePessoaListNewTelefonePessoaToAttach : telefonePessoaListNew) {
                telefonePessoaListNewTelefonePessoaToAttach = em.getReference(telefonePessoaListNewTelefonePessoaToAttach.getClass(), telefonePessoaListNewTelefonePessoaToAttach.getIdTelefone());
                attachedTelefonePessoaListNew.add(telefonePessoaListNewTelefonePessoaToAttach);
            }
            telefonePessoaListNew = attachedTelefonePessoaListNew;
            pessoa.setTelefonePessoaList(telefonePessoaListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdCliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            pessoa.setClienteList(clienteListNew);
            List<Entregador> attachedEntregadorListNew = new ArrayList<Entregador>();
            for (Entregador entregadorListNewEntregadorToAttach : entregadorListNew) {
                entregadorListNewEntregadorToAttach = em.getReference(entregadorListNewEntregadorToAttach.getClass(), entregadorListNewEntregadorToAttach.getIdEntregador());
                attachedEntregadorListNew.add(entregadorListNewEntregadorToAttach);
            }
            entregadorListNew = attachedEntregadorListNew;
            pessoa.setEntregadorList(entregadorListNew);
            List<Endereco> attachedEnderecoListNew = new ArrayList<Endereco>();
            for (Endereco enderecoListNewEnderecoToAttach : enderecoListNew) {
                enderecoListNewEnderecoToAttach = em.getReference(enderecoListNewEnderecoToAttach.getClass(), enderecoListNewEnderecoToAttach.getIdEndereco());
                attachedEnderecoListNew.add(enderecoListNewEnderecoToAttach);
            }
            enderecoListNew = attachedEnderecoListNew;
            pessoa.setEnderecoList(enderecoListNew);
            pessoa = em.merge(pessoa);
            if (idUsuarioCadastroOld != null && !idUsuarioCadastroOld.equals(idUsuarioCadastroNew)) {
                idUsuarioCadastroOld.getPessoaList().remove(pessoa);
                idUsuarioCadastroOld = em.merge(idUsuarioCadastroOld);
            }
            if (idUsuarioCadastroNew != null && !idUsuarioCadastroNew.equals(idUsuarioCadastroOld)) {
                idUsuarioCadastroNew.getPessoaList().add(pessoa);
                idUsuarioCadastroNew = em.merge(idUsuarioCadastroNew);
            }
            if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
                idTipoOld.getPessoaList().remove(pessoa);
                idTipoOld = em.merge(idTipoOld);
            }
            if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
                idTipoNew.getPessoaList().add(pessoa);
                idTipoNew = em.merge(idTipoNew);
            }
            for (TelefonePessoa telefonePessoaListOldTelefonePessoa : telefonePessoaListOld) {
                if (!telefonePessoaListNew.contains(telefonePessoaListOldTelefonePessoa)) {
                    telefonePessoaListOldTelefonePessoa.setIdPessoa(null);
                    telefonePessoaListOldTelefonePessoa = em.merge(telefonePessoaListOldTelefonePessoa);
                }
            }
            for (TelefonePessoa telefonePessoaListNewTelefonePessoa : telefonePessoaListNew) {
                if (!telefonePessoaListOld.contains(telefonePessoaListNewTelefonePessoa)) {
                    Pessoa oldIdPessoaOfTelefonePessoaListNewTelefonePessoa = telefonePessoaListNewTelefonePessoa.getIdPessoa();
                    telefonePessoaListNewTelefonePessoa.setIdPessoa(pessoa);
                    telefonePessoaListNewTelefonePessoa = em.merge(telefonePessoaListNewTelefonePessoa);
                    if (oldIdPessoaOfTelefonePessoaListNewTelefonePessoa != null && !oldIdPessoaOfTelefonePessoaListNewTelefonePessoa.equals(pessoa)) {
                        oldIdPessoaOfTelefonePessoaListNewTelefonePessoa.getTelefonePessoaList().remove(telefonePessoaListNewTelefonePessoa);
                        oldIdPessoaOfTelefonePessoaListNewTelefonePessoa = em.merge(oldIdPessoaOfTelefonePessoaListNewTelefonePessoa);
                    }
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setIdPessoa(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Pessoa oldIdPessoaOfClienteListNewCliente = clienteListNewCliente.getIdPessoa();
                    clienteListNewCliente.setIdPessoa(pessoa);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldIdPessoaOfClienteListNewCliente != null && !oldIdPessoaOfClienteListNewCliente.equals(pessoa)) {
                        oldIdPessoaOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldIdPessoaOfClienteListNewCliente = em.merge(oldIdPessoaOfClienteListNewCliente);
                    }
                }
            }
            for (Entregador entregadorListOldEntregador : entregadorListOld) {
                if (!entregadorListNew.contains(entregadorListOldEntregador)) {
                    entregadorListOldEntregador.setIdPessoa(null);
                    entregadorListOldEntregador = em.merge(entregadorListOldEntregador);
                }
            }
            for (Entregador entregadorListNewEntregador : entregadorListNew) {
                if (!entregadorListOld.contains(entregadorListNewEntregador)) {
                    Pessoa oldIdPessoaOfEntregadorListNewEntregador = entregadorListNewEntregador.getIdPessoa();
                    entregadorListNewEntregador.setIdPessoa(pessoa);
                    entregadorListNewEntregador = em.merge(entregadorListNewEntregador);
                    if (oldIdPessoaOfEntregadorListNewEntregador != null && !oldIdPessoaOfEntregadorListNewEntregador.equals(pessoa)) {
                        oldIdPessoaOfEntregadorListNewEntregador.getEntregadorList().remove(entregadorListNewEntregador);
                        oldIdPessoaOfEntregadorListNewEntregador = em.merge(oldIdPessoaOfEntregadorListNewEntregador);
                    }
                }
            }
            for (Endereco enderecoListOldEndereco : enderecoListOld) {
                if (!enderecoListNew.contains(enderecoListOldEndereco)) {
                    enderecoListOldEndereco.setIdPessoa(null);
                    enderecoListOldEndereco = em.merge(enderecoListOldEndereco);
                }
            }
            for (Endereco enderecoListNewEndereco : enderecoListNew) {
                if (!enderecoListOld.contains(enderecoListNewEndereco)) {
                    Pessoa oldIdPessoaOfEnderecoListNewEndereco = enderecoListNewEndereco.getIdPessoa();
                    enderecoListNewEndereco.setIdPessoa(pessoa);
                    enderecoListNewEndereco = em.merge(enderecoListNewEndereco);
                    if (oldIdPessoaOfEnderecoListNewEndereco != null && !oldIdPessoaOfEnderecoListNewEndereco.equals(pessoa)) {
                        oldIdPessoaOfEnderecoListNewEndereco.getEnderecoList().remove(enderecoListNewEndereco);
                        oldIdPessoaOfEnderecoListNewEndereco = em.merge(oldIdPessoaOfEnderecoListNewEndereco);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pessoa.getIdPessoa();
                if (findPessoa(id) == null) {
                    throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.");
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
            Pessoa pessoa;
            try {
                pessoa = em.getReference(Pessoa.class, id);
                pessoa.getIdPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pessoa with id " + id + " no longer exists.", enfe);
            }
            Funcionario idUsuarioCadastro = pessoa.getIdUsuarioCadastro();
            if (idUsuarioCadastro != null) {
                idUsuarioCadastro.getPessoaList().remove(pessoa);
                idUsuarioCadastro = em.merge(idUsuarioCadastro);
            }
            TipoPessoa idTipo = pessoa.getIdTipo();
            if (idTipo != null) {
                idTipo.getPessoaList().remove(pessoa);
                idTipo = em.merge(idTipo);
            }
            List<TelefonePessoa> telefonePessoaList = pessoa.getTelefonePessoaList();
            for (TelefonePessoa telefonePessoaListTelefonePessoa : telefonePessoaList) {
                telefonePessoaListTelefonePessoa.setIdPessoa(null);
                telefonePessoaListTelefonePessoa = em.merge(telefonePessoaListTelefonePessoa);
            }
            List<Cliente> clienteList = pessoa.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setIdPessoa(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            List<Entregador> entregadorList = pessoa.getEntregadorList();
            for (Entregador entregadorListEntregador : entregadorList) {
                entregadorListEntregador.setIdPessoa(null);
                entregadorListEntregador = em.merge(entregadorListEntregador);
            }
            List<Endereco> enderecoList = pessoa.getEnderecoList();
            for (Endereco enderecoListEndereco : enderecoList) {
                enderecoListEndereco.setIdPessoa(null);
                enderecoListEndereco = em.merge(enderecoListEndereco);
            }
            em.remove(pessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pessoa> findPessoaEntities() {
        return findPessoaEntities(true, -1, -1);
    }

    public List<Pessoa> findPessoaEntities(int maxResults, int firstResult) {
        return findPessoaEntities(false, maxResults, firstResult);
    }

    private List<Pessoa> findPessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pessoa.class));
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

    public Pessoa findPessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pessoa> rt = cq.from(Pessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
