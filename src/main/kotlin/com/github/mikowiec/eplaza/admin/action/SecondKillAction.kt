package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.SecondKillService
import com.github.mikowiec.eplaza.model.SecondKill
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class SecondKillAction : AdminBaseAction() {

    @Autowired
    private val secondKillService: SecondKillService? = null

    private var secondKill: SecondKill? = null
    private var secondKills: List<SecondKill>? = null

    override var jsonResult: Map<String, Any>? = null

    // List all
    fun list(): String {
        // Pagination setting
        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            secondKills = secondKillService!!.findByPage(page, pageSize)
        } else {
            secondKills = secondKillService!!.findAll()
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", secondKills!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun get(): String {
        if (this.secondKill == null) {
            jsonResult = ResponseTemplate.error(-1, "Param secondKill is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        secondKill = secondKillService!!.findById(this.secondKill!!.id)
        map.put("data", secondKill!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.secondKill == null) {
            jsonResult = ResponseTemplate.error(-1, "Param secondKill is required!")
            return SUCCESS
        }

        val status = secondKillService!!.save(this.secondKill!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", secondKill!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.secondKill == null) {
            jsonResult = ResponseTemplate.error(-1, "Param secondKill is required!")
            return SUCCESS
        }

        secondKillService!!.update(secondKill!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", secondKill!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.secondKill == null) {
            jsonResult = ResponseTemplate.error(-1, "Param secondKill is required!")
            return SUCCESS
        }

        secondKillService!!.delete(this.secondKill!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setSecondKill(secondKill: SecondKill) {
        this.secondKill = secondKill
    }
}
