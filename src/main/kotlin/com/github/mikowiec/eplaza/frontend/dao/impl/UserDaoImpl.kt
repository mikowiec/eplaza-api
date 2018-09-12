package com.github.mikowiec.eplaza.frontend.dao.impl

import com.github.mikowiec.eplaza.frontend.dao.UserDao
import com.github.mikowiec.eplaza.model.User
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class UserDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : UserDao {
    var template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    @Transactional
    override fun saveUser(user: User): Int {
        val result = template.save(user)
        val integer = result as Integer
        return integer.toInt()
    }

    override fun getUserByPhone(phone: String): List<User> {
        val criteria = DetachedCriteria.forClass(User::class.java)
        criteria.add(Restrictions.eq("phone", phone))
        return template.findByCriteria(criteria, 0, 1).map {it as User}
    }
}
