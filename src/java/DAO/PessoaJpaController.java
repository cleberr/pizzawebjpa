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

    
}
