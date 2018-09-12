package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.admin.service.GoodsService
import com.github.mikowiec.eplaza.frontend.service.GoodsCommentService
import com.github.mikowiec.eplaza.model.Goods
import com.github.mikowiec.eplaza.model.GoodsComment
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import java.util.*

@Controller(value = "goodsCommentAction")
class GoodsCommentAction : BaseAction() {

    private var goodsComment: GoodsComment? = null

    private var goodsComments: List<GoodsComment>? = null

    @Autowired
    private val goodsCommentService: GoodsCommentService? = null

    @Autowired
    private val goodsService: GoodsService? = null

    override var jsonResult: Map<String, Any>? = null

    fun list(): String {
        if (!hasParam("goodsId")) {
            jsonResult = ResponseTemplate.error(-1, "param goodsId is required!")
            return SUCCESS
        }

        var count = 0

        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            if (hasParam("goodsId") && hasParam("commentLevel")) {
                val goodsId = Integer.parseInt(getParam("goodsId"))
                val commentLevel = Integer.parseInt(getParam("commentLevel"))

                goodsComments = goodsCommentService!!.findByGoodsIdAndCommentLevelAndPage(goodsId, commentLevel, page, pageSize)
                count = goodsCommentService!!.getCountByGoodsIdAndCommentLevel(goodsId, commentLevel)
            } else if (hasParam("goodsId")) {
                val goodsId = Integer.parseInt(getParam("goodsId"))

                goodsComments = goodsCommentService!!.findByGoodsIdAndPage(goodsId, page, pageSize)
                count = goodsCommentService!!.getCountByGoodsId(goodsId)
            }
        } else {
            if (hasParam("goodsId") && hasParam("commentLevel")) {
                val goodsId = Integer.parseInt(getParam("goodsId"))
                val commmentLevel = Integer.parseInt(getParam("commentLevel"))

                goodsComments = goodsCommentService!!.findByGoodsIdAndCommentLevel(goodsId, commmentLevel)
                count = goodsCommentService!!.getCountByGoodsIdAndCommentLevel(goodsId, commmentLevel)
            } else if (hasParam("goodsId")) {
                val goodsId = Integer.parseInt(getParam("goodsId"))

                goodsComments = goodsCommentService!!.findByGoodsId(goodsId)
                count = goodsCommentService!!.getCountByGoodsId(goodsId)
            }
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goodsComments!!)
        map.put("count", count)

        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun add(): String {
        if (this.goodsComment == null) {
            jsonResult = ResponseTemplate.error(-1, "param goodsComment is required!")
            return SUCCESS
        }

        val status = goodsCommentService!!.save(goodsComment!!)

        // Update related goods
        val goods = goodsService!!.findById(goodsComment!!.goodsId)
        val commentSet = HashSet(goods!!.goodsComments)
        commentSet.add(goodsComment)
        goods!!.goodsComments = commentSet
        goodsService!!.update(goods!!)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", goodsComment!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    fun setGoodsComment(goodsComment: GoodsComment) {
        this.goodsComment = goodsComment
    }

}
