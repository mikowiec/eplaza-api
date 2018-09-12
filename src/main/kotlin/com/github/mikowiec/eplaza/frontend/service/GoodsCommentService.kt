package com.github.mikowiec.eplaza.frontend.service

import com.github.mikowiec.eplaza.model.GoodsComment

interface GoodsCommentService {

    val count: Int

    fun findByGoodsId(goodsId: Int): List<GoodsComment>

    fun findByGoodsIdAndPage(goodsId: Int, page: Int, pageSize: Int): List<GoodsComment>

    fun findByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): List<GoodsComment>

    fun findByGoodsIdAndCommentLevelAndPage(goodsId: Int, commentLevel: Int, page: Int, pageSize: Int): List<GoodsComment>

    fun getCountByGoodsId(goodsId: Int): Int

    fun getCountByCommentLevel(commentLevel: Int): Int

    fun getCountByGoodsIdAndCommentLevel(goodsId: Int, commentLevel: Int): Int

    fun save(goodsComment: GoodsComment): Int

}
