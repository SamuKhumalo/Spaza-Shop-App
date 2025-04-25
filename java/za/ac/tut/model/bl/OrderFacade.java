/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.model.bl;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.model.entity.Order;

/**
 *
 * @author samuk
 */
@Stateless
public class OrderFacade extends AbstractFacade<Order> implements OrderFacadeLocal {

    @PersistenceContext(unitName = "SpazaShopEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderFacade() {
        super(Order.class);
    }

    @Override
    public List<Order> findOrderWithinRange(Integer minOrder, Integer maxOrder) {

        Query query =em.createQuery("Select s FROM Order s WHERE s.totalValue>=?1 AND s.totalValue <=?1 ORDER BY s.totalValue desc");
        query.setParameter(1, minOrder);
        query.setParameter(2, maxOrder);
        List<Order> orders =query.getResultList();
        
        return orders;
        
    }
    
}
