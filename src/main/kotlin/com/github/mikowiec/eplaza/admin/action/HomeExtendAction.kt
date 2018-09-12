package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.HomeExtendService
import com.github.mikowiec.eplaza.model.HomeExtend
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class HomeExtendAction : AdminBaseAction() {

    @Autowired
    private val homeExtendService: HomeExtendService? = null

    private var homeExtend: HomeExtend? = null
    private var homeExtends: List<HomeExtend>? = null

    override var jsonResult: Map<String, Any>? = null

    // List all
    fun list(): String {
        // Pagination setting
        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            homeExtends = homeExtendService!!.findByPage(page, pageSize)
        } else {
            homeExtends = homeExtendService!!.findAll()
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", homeExtends!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // List one
    fun get(): String {
        if (this.homeExtend == null) {
            jsonResult = ResponseTemplate.error(-1, "Param homeExtend is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        homeExtend = homeExtendService!!.findById(this.homeExtend!!.id)
        map.put("data", homeExtend!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.homeExtend == null) {
            jsonResult = ResponseTemplate.error(-1, "Param homeExtend is required!")
            return SUCCESS
        }

        val status = homeExtendService!!.save(this.homeExtend!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", homeExtend!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.homeExtend == null) {
            jsonResult = ResponseTemplate.error(-1, "Param homeExtend is required!")
            return SUCCESS
        }

        homeExtendService!!.update(homeExtend!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", homeExtend!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.homeExtend == null) {
            jsonResult = ResponseTemplate.error(-1, "Param homeExtend is required!")
            return SUCCESS
        }

        homeExtendService!!.delete(this.homeExtend!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setHomeExtend(homeExtend: HomeExtend) {
        this.homeExtend = homeExtend
    }
}
