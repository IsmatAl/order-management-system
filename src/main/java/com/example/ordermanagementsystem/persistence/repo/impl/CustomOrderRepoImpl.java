package com.example.ordermanagementsystem.persistence.repo.impl;

import com.example.ordermanagementsystem.persistence.entity.Order;
import com.example.ordermanagementsystem.persistence.entity.Order_;
import com.example.ordermanagementsystem.persistence.repo.CustomOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;



public class CustomOrderRepoImpl implements CustomOrderRepo {

    @Autowired
    EntityManager em;

    private List<Order> findByParam(String columnName, Object param) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        Predicate pred = cb.equal(order.get(columnName), param);
        cq.where(pred);

        TypedQuery<Order> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Order> findByProduct(Long prodId) {
        return findByParam("id", prodId);
    }

    @Override
    public List<Order> findByDate(LocalDate dateOfSubmission) {
        return findByParam("dateOfSubmission", dateOfSubmission);
    }

    @Override
    public List<Order> findByCustomer(Long customerId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        Predicate pred = cb.equal(order.get(Order_.customer), customerId);
        cq.where(pred);
        TypedQuery<Order> query = em.createQuery(cq);
        return query.getResultList();
    }
}
