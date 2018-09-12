package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.frontend.service.AddressService
import com.github.mikowiec.eplaza.model.Address
import com.github.mikowiec.eplaza.model.User
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.HashMap

import java.util.LinkedHashMap

@Controller(value = "addressAction")
class AddressAction : BaseAction() {
    private var address: Address? = null

    @Autowired
    private val addressService: AddressService? = null

    fun setAddress(address: Address) {
        this.address = address
    }

    fun list(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        val list = addressService!!.getAddress(tokenData.userid)
        val data = LinkedHashMap<String, Any>()
        data.put("data", list)
        this.jsonResult = ResponseTemplate.success(data)
        return SUCCESS
    }

    fun add(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        System.out.println(this.address!!.address)
        this.address!!.userid = tokenData.userid
        val result = addressService!!.saveAddress(this.address!!)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", this.address!!.address!! )
        if (result > 0) {
            this.jsonResult = ResponseTemplate.success(map)
        } else {
            this.jsonResult = ResponseTemplate.error(201, "Add address failed")
        }
        return SUCCESS
    }

    fun edit(): String {
        val tokenData = ServletActionContext.getRequest().getAttribute("tokenData") as User
        this.address!!.userid = tokenData.userid
        println(this.address!!.address)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", this.address!!.address!! )
        try {
            addressService!!.editAddress(this.address!!)
            this.jsonResult = ResponseTemplate.success(map)
        } catch (e: Exception) {
            System.out.println(e)
            this.jsonResult = ResponseTemplate.error(202, "failed to delete")
        }

        return SUCCESS
    }

    fun del(): String {
        println(this.address!!.address)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", this.address!!.address!! )
        try {
            addressService!!.delAddress(this.address!!)
            jsonResult = ResponseTemplate.success(map)
        } catch (e: Exception) {
            System.out.println(e)
            jsonResult = ResponseTemplate.error(202, "failed to delete")
        }

        return SUCCESS
    }
}
