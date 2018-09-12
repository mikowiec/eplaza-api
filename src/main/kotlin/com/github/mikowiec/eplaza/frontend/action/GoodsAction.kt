package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.admin.service.GoodsService
import com.github.mikowiec.eplaza.model.Goods
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

import java.util.HashMap

@Controller(value = "uGoodsAction")
class GoodsAction : BaseAction() {
    @Autowired
    private val goodsService: GoodsService? = null

    private var goods: Goods? = null
    private var goodses: List<Goods>? = null

    private var goodsId: Int = 0
    fun setGoodsId(goodsId: Int) {
        this.goodsId = goodsId
    }

    fun list(): String {
        val orderKeys: String

        // Sort by default by total number of sales
        if (hasParam("order")) {
            orderKeys = getParam("order")
        } else {
            orderKeys = "-salesNum"
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
                System.out.println("merchantId")
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
                System.out.println("merchantId")
                goodses = goodsService!!.findByMerchantId(Integer.parseInt(getParam("merchantId")), orderKeys)
                count = goodsService!!.getCountByMerchantId(merchantId)
            } else {
                goodses = goodsService!!.findAll(orderKeys)
                count = goodsService!!.count
            }
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goodses!!)

        // Plus the total number of items
        map.put("goodsSum", count)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun one(): String {
        val map: HashMap<String, Any> = HashMap()
        goods = goodsService!!.findById(goodsId)
        map.put("data", goods!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }
}
