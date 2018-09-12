package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.GoodsCatService
import com.github.mikowiec.eplaza.model.GoodsCat
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class GoodsCatAction : AdminBaseAction() {

    @Autowired
    private val goodsCatService: GoodsCatService? = null

    private var goodsCat: GoodsCat? = null
    private var goodsCats: List<GoodsCat>? = null

    override var jsonResult: Map<String, Any>? = null

    // List all
    fun list(): String {
        goodsCats = goodsCatService!!.findAll()

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goodsCats!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // List one
    fun get(): String {
        if (this.goodsCat == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goodsCat is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        goodsCat = goodsCatService!!.findById(this.goodsCat!!.id)
        map.put("data", goodsCat!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.goodsCat == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goodsCat is required!")
            return SUCCESS
        }

        val status = goodsCatService!!.save(this.goodsCat!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", goodsCat!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.goodsCat == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goodsCat is required!")
            return SUCCESS
        }

        goodsCatService!!.update(goodsCat!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", goodsCat!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.goodsCat == null) {
            jsonResult = ResponseTemplate.error(-1, "Param goodsCat is required!")
            return SUCCESS
        }

        goodsCatService!!.delete(this.goodsCat!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setGoodsCat(goodsCat: GoodsCat) {
        this.goodsCat = goodsCat
    }
}
