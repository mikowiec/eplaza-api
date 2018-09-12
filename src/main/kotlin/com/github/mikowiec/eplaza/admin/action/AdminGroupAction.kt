package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.AdminGroupService
import com.github.mikowiec.eplaza.model.AdminGroup
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import com.opensymphony.xwork2.Action
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
//import java.util.HashMap
import java.util.*

class AdminGroupAction : AdminBaseAction() {

    @Autowired
    private val adminGroupService: AdminGroupService? = null

    private var adminGroup: AdminGroup? = null
    private var adminGroups: List<AdminGroup>? = null

    //var jsonResult: Map<String, Any>  //was Map<String, Object>
    override var jsonResult: Map<String, Any>? = null

    // list all
    fun list(): String {
        // Pagination setting
        if (hasPageSetting()) {
            val page = pageSetting
             val pageSize = pageSizeSetting

            adminGroups = adminGroupService!!.findByPage(page, pageSize)
        } else {
            adminGroups = adminGroupService!!.findAll()
        }

        //var map: HashMap<String, List<AdminGroup>>? = null
        //var map: HashMap<String, Object>
        //val map = hashMapOf<Int, ArrayList<Proposition>>()
        //OK, jsonResult err:   var map = hashMapOf<String, List<AdminGroup>?>()
        //var map = hashMapOf<String, Object>()
        //map.put("data", adminGroups)
        val map: HashMap<String, Any> = HashMap()
        //map.put("data", adminGroups ?: return ERROR )  //OK
        map.put("data", adminGroups!! )
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // List one
    fun get(): String {
        if (this.adminGroup == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminGroup is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        adminGroup = adminGroupService!!.findById(this.adminGroup!!.id)
        map.put("data", adminGroup!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // Add to
    fun add(): String {
        if (this.adminGroup == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminGroup is required!")
            return SUCCESS
        }

        val status = adminGroupService!!.save(this.adminGroup!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", adminGroup!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.adminGroup == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminGroup is required!")
            return SUCCESS
        }

        adminGroupService!!.update(adminGroup!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", adminGroup!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.adminGroup == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminGroup is required!")
            return SUCCESS
        }

        adminGroupService!!.delete(this.adminGroup!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setAdminGroup(adminGroup: AdminGroup) {
        this.adminGroup = adminGroup
    }
}
