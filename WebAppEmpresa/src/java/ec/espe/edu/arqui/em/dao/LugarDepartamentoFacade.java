/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.em.dao;

import ec.espe.edu.arqui.em.model.LugarDepartamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres Vr
 */
@Stateless
public class LugarDepartamentoFacade extends AbstractFacade<LugarDepartamento> {

    @PersistenceContext(unitName = "WebAppEmpresaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LugarDepartamentoFacade() {
        super(LugarDepartamento.class);
    }
    
}
