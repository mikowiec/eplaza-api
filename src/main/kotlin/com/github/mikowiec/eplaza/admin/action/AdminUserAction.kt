package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.AdminUserService
import com.github.mikowiec.eplaza.model.AdminUser
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class AdminUserAction : AdminBaseAction() {

    @Autowired
    private val adminUserService: AdminUserService? = null

    private var adminUser: AdminUser? = null
    private var adminUsers: List<AdminUser>? = null

    override var jsonResult: Map<String, Any>? = null

    // List all
    fun list(): String {
        // Pagination setting
        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            adminUsers = adminUserService!!.findByPage(page, pageSize)
        } else {
            adminUsers = adminUserService!!.findAll()
        }

        val map: HashMap<String, Any> = HashMap()
        map.put("data", adminUsers!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    // List one
    fun get(): String {
        if (this.adminUser == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminUser is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        adminUser = adminUserService!!.findById(this.adminUser!!.id)
        map.put("data", adminUser!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.adminUser == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminUser is required!")
            return SUCCESS
        }

        val status = adminUserService!!.save(this.adminUser!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", adminUser!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.adminUser == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminUser is required!")
            return SUCCESS
        }

        adminUserService!!.update(adminUser!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", adminUser!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.adminUser == null) {
            jsonResult = ResponseTemplate.error(-1, "Param adminUser is required!")
            return SUCCESS
        }

        adminUserService!!.delete(this.adminUser!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setAdminUser(adminUser: AdminUser) {
        this.adminUser = adminUser
    }
}
