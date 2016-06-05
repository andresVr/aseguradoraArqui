/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espe.edu.arqui.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ec.espe.edu.arqui.model.Adquisicion;

/**
 *
 * @author Andres Vr
 */
@Stateless
public class AdquisicionFacade extends AbstractFacade<Adquisicion> {

    @PersistenceContext(unitName = "WebAppAdquisicionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdquisicionFacade() {
        super(Adquisicion.class);
    }
    
}
