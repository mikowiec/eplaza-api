package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.AdminUserDao
import com.github.mikowiec.eplaza.model.AdminUser
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class AdminUserDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : AdminUserDao {

    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<AdminUser> {
        val criteria = DetachedCriteria.forClass(AdminUser::class.java)
        return template.findByCriteria(criteria).map {it as AdminUser}
    }

    override fun findById(id: Int): AdminUser? {
        val criteria = DetachedCriteria.forClass(AdminUser::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<AdminUser>

        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    override fun findByPage(page: Int, pageSize: Int): List<AdminUser> {
        val criteria = DetachedCriteria.forClass(AdminUser::class.java)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as AdminUser}
        }

    @Transactional
    override fun save(adminUser: AdminUser): Int {
        val result = template.save(adminUser)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(adminUser: AdminUser) {
        template.update(adminUser)
    }

    @Transactional
    override fun delete(adminUser: AdminUser) {
        template.delete(adminUser)
    }
}
