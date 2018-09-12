package com.github.mikowiec.eplaza.frontend.service.impl

import com.github.mikowiec.eplaza.frontend.dao.GoodsCommentDao
import com.github.mikowiec.eplaza.frontend.service.GoodsCommentService
import com.github.mikowiec.eplaza.model.GoodsComment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoodsCommentServiceImpl : GoodsCommentService {

    @Autowired
    private val goodsCommentDao: GoodsCommentDao? = null

    override val count: Int
        get() = goodsCommentDao!!.count

    override fun findByGoodsId(goodsId: Int): List<GoodsComment> {
        return goodsCommentDao!!.findByGoodsId(goodsId)
    }

    override fun findByGoodsIdAndPage(goodsId: Int, page: Int, pageSize: Int): List<GoodsComment> {
        return goodsCommentDao!!.findByGoodsIdAndPage(goodsId, page, pageSize)
    }

    override fun findByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): List<GoodsComment> {
        return goodsCommentDao!!.findByGoodsIdAndCommentLevel(goodsId, commentLevel)
    }

    override fun findByGoodsIdAndCommentLevelAndPage(goodsId: Int, commentLevel: Int, page: Int, pageSize: Int): List<GoodsComment> {
        return goodsCommentDao!!.findByGoodsIdAndCommentLevelAndPage(goodsId, commentLevel, page, pageSize)
    }

    override fun getCountByGoodsId(goodsId: Int): Int {
        return goodsCommentDao!!.getCountByGoodsId(goodsId)
    }

    override fun getCountByCommentLevel(commentLevel: Int): Int {
        return goodsCommentDao!!.getCountByCommentLevel(commentLevel)
    }

    override fun getCountByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): Int {
        return goodsCommentDao!!.getCountByGoodsIdAndCommentLevel(goodsId, commentLevel)
    }

    override fun save(goodsComment: GoodsComment): Int {
        return goodsCommentDao!!.save(goodsComment)
    }
}
