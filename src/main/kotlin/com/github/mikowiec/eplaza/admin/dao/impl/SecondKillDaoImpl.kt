package com.github.mikowiec.eplaza.admin.dao.impl

import com.github.mikowiec.eplaza.admin.dao.SecondKillDao
import com.github.mikowiec.eplaza.model.SecondKill
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class SecondKillDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : SecondKillDao {
    private val template: HibernateTemplate

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findAll(): List<SecondKill> {
        val criteria = DetachedCriteria.forClass(SecondKill::class.java)
        return template.findByCriteria(criteria).map {it as SecondKill}
        }

    override fun findById(id: Int): SecondKill? {
        val criteria = DetachedCriteria.forClass(SecondKill::class.java)
        criteria.add(Restrictions.eq("id", id))
        val resultList = template.findByCriteria(criteria) as List<SecondKill>
        return if (resultList.size === 0) {
            null
        } else resultList[0]
    }

    override fun findByPage(page: Int, pageSize: Int): List<SecondKill> {
        val criteria = DetachedCriteria.forClass(SecondKill::class.java)
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as SecondKill}
    }

    @Transactional
    override fun save(secondKill: SecondKill): Int {
        val result = template.save(secondKill)
        return (result as Integer).toInt()
    }

    @Transactional
    override fun update(secondKill: SecondKill) {
        template.update(secondKill)
    }

    @Transactional
    override fun delete(secondKill: SecondKill) {
        template.delete(secondKill)
    }

}
