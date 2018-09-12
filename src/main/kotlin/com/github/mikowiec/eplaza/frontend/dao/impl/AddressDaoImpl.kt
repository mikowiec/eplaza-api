package com.github.mikowiec.eplaza.frontend.dao.impl

import com.github.mikowiec.eplaza.frontend.dao.AddressDao
import com.github.mikowiec.eplaza.model.Address
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class AddressDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : AddressDao {

    var template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    @Transactional
    override fun save(address: Address): Int {
        val result = template.save(address)
        val integer = result as Integer
        return integer.toInt()
    }

    override fun list(userid: Int): List<Address> {
        val criteria = DetachedCriteria.forClass(Address::class.java)
        criteria.add(Restrictions.eq("userid", userid))
        return template.findByCriteria(criteria).map {it as Address}
    }

    @Transactional
    override fun delete(address: Address) {
        template.delete(address)
    }

    @Transactional
    override fun update(address: Address) {
        template.update(address)
    }
}
