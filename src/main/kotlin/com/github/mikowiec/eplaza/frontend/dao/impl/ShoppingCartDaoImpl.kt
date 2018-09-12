package com.github.mikowiec.eplaza.frontend.dao.impl

import com.github.mikowiec.eplaza.frontend.dao.ShoppingCartDao
import com.github.mikowiec.eplaza.model.ShoppingCart
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Disjunction
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class ShoppingCartDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : ShoppingCartDao {
    var template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    @Transactional
    override fun saveShoppingCart(shoppingCart: ShoppingCart): Int {
        val result = template.save(shoppingCart)
        val integer = result as Integer
        return integer.toInt()
    }

    override fun getShoppingCartByUserId(userid: Int): List<ShoppingCart> {
        val criteria = DetachedCriteria.forClass(ShoppingCart::class.java)
        criteria.add(Restrictions.eq("userId", userid))
        return template.findByCriteria(criteria).map {it as ShoppingCart}
    }

    @Transactional
    override fun delShoppingCart(cart: IntArray) {
        val criteria = DetachedCriteria.forClass(ShoppingCart::class.java)
        val dis = Restrictions.disjunction()
        for (item in cart) {
            System.out.println(item)
            dis.add(Restrictions.eq("id", item))
        }
        criteria.add(dis)
        val list = template.findByCriteria(criteria) as List<ShoppingCart>
        template.deleteAll(list)
    }

}
