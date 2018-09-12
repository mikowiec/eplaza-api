package com.github.mikowiec.eplaza.frontend.dao.impl

import com.github.mikowiec.eplaza.frontend.dao.OrderDao
import com.github.mikowiec.eplaza.model.Order
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class OrderDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : OrderDao {
    var template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    @Transactional
    override fun save(order: Order): Int {
        val result = template.save(order)
        val integer = result as Integer
        return integer.toInt()
    }

    override fun list(userId: Int): List<Order> {
        val criteria = DetachedCriteria.forClass(Order::class.java)
        criteria.add(Restrictions.eq("userid", userId))
        return template.findByCriteria(criteria).map {it as Order}
    }
}
