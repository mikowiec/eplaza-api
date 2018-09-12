package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.GoodsService
import com.github.mikowiec.eplaza.model.Goods
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class GoodsAction : AdminBaseAction() {

    @Autowired
    private val goodsService: GoodsService? = null

    private var goods: Goods? = null
    private var goodses: List<Goods>? = null

    override var jsonResult: Map<String, Any>? = null

    fun list(): String {
        val orderKeys: String

        // Sort by default by total number of sales
        if (hasParam("order")) {
            orderKeys = getParam("order")
        } else {
            orderKeys = "salesNum"
        }

        val count: Int

        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            // Determine whether the user wants to find according
            // to the specified value according to the parameters.
            if (hasParam("goodsName")) {
                val goodsName = getParam("goodsName")
                goodses = goodsService!!.findByGoodsNameAndPage(goodsName, page, pageSize, orderKeys)
                count = goodsService!!.getCountByGoodsName(goodsName)
            } else if (hasParam("catId")) {
                val catId = Integer.parseInt(getParam("catId"))
                goodses = goodsService!!.findByCatIdAndPage(catId, page, pageSize, orderKeys)
                count = goodsService!!.getCountByCatId(catId)
            } else if (hasParam("merchantId")) {
                val merchantId = Integer.parseInt(getParam("merchantId"))
                goodses = goodsService!!.findByMerchantIdAndPage(merchantId, page, pageSize, orderKeys)
                count = goodsService!!.getCountByMerchantId(merchantId)
            } else {
                goodses = goodsService!!.findByPage(page, pageSize, orderKeys)
                count = goodsService!!.count
            }
        } else {
            if (hasParam("goodsName")) {
                val goodsName = getParam("goodsName")
                goodses = goodsService!!.findByGoodsName(getParam("goodsName"), orderKeys)
                count = goodsService!!.getCountByGoodsName(goodsName)
            } else if (hasParam("catId")) {
                val catId = Integer.parseInt(getParam("catId"))
                goodses = goodsService!!.findByCatId(Integer.parseInt(getParam("catId")), orderKeys)
                count = goodsService!!.getCountByCatId(catId)
            } else if (hasParam("merchantId")) {
                val merchantId = Integer.parseInt("merchantId")
                goodses = goodsService!!.findByMerchantId(Integer.parseInt(getParam("merchantId")), orderKeys)
                count = goodsService!!.getCountByMerchantId(merchantId)
            } else {
                goodses = goodsService!!.findAll(orderKeys)
                count = goodsService!!.count            }
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goodses!!)

        // Plus the total number of items
        map.put("goodsSum", count)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // List one
    fun get(): String {
        if (this.goods == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goods is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        goods = goodsService!!.findById(this.goods!!.id)
        map.put("data", goods!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.goods == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goods is required!")
            return SUCCESS
        }

        val status = goodsService!!.save(this.goods!!)
        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", goods!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.goods == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goods is required!")
            return SUCCESS
        }
        System.out.println(goods!!.goodsAttrs.size)  //getGoodsAttrs().size())
        System.out.println(goods!!.goodsName) //getGoodsName())
        System.out.println(goods!!.goodsImgs)  //getGoodsImgs())
        System.out.println(goods!!.goodsDesc)  //getGoodsDesc())

        goodsService!!.update(goods!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goods!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.goods == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goods is required!")
            return SUCCESS
        }

        goodsService!!.delete(this.goods!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setGoods(goods: Goods) {
        this.goods = goods
    }
}
