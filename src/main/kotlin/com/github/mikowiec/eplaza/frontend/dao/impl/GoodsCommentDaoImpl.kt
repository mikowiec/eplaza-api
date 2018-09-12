package com.github.mikowiec.eplaza.frontend.dao.impl

import com.github.mikowiec.eplaza.frontend.dao.GoodsCommentDao
import com.github.mikowiec.eplaza.model.GoodsComment
import org.hibernate.SessionFactory
import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.io.Serializable

@Repository
open class GoodsCommentDaoImpl @Autowired
constructor(sessionFactory: SessionFactory) : GoodsCommentDao {

    var template: HibernateTemplate

    override val count: Int
        get() {
            val criteria = DetachedCriteria.forClass(GoodsComment::class.java)
            criteria.setProjection(Projections.rowCount())
            val obj = template.findByCriteria(criteria).get(0)
            val longObj = obj as Long
            return longObj.toInt()
        }

    init {
        template = HibernateTemplate(sessionFactory)
    }

    override fun findByGoodsId(goodsId: Int): List<GoodsComment> {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        return template.findByCriteria(criteria).map {it as GoodsComment}
    }

    override fun findByGoodsIdAndPage(goodsId: Int, page: Int, pageSize: Int): List<GoodsComment> {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as GoodsComment}
    }

    override fun findByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): List<GoodsComment> {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        criteria.add(Restrictions.eq("commentLevel", commentLevel))
        return template.findByCriteria(criteria).map {it as GoodsComment}
    }

    override fun findByGoodsIdAndCommentLevelAndPage(goodsId: Int, commentLevel: Int, page: Int, pageSize: Int): List<GoodsComment> {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        criteria.add(Restrictions.eq("commentLevel", commentLevel))
        val offset = (page - 1) * pageSize
        return template.findByCriteria(criteria, offset, pageSize).map {it as GoodsComment}
    }

    override fun getCountByGoodsId(goodsId: Int): Int {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()
    }

    override fun getCountByCommentLevel(commentLevel: Int): Int {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("commentLevel", commentLevel))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()
    }

    override fun getCountByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): Int {
        val criteria = DetachedCriteria.forClass(GoodsComment::class.java)

        criteria.add(Restrictions.eq("goodsid", goodsId))
        criteria.add(Restrictions.eq("commentLevel", commentLevel))
        criteria.setProjection(Projections.rowCount())

        val obj = template.findByCriteria(criteria).get(0)
        val longObj = obj as Long
        return longObj.toInt()
    }

    @Transactional
    override fun save(goodsComment: GoodsComment): Int {
        val result = template.save(goodsComment)
        val integer = result as Integer
        return integer.toInt()
    }

}
