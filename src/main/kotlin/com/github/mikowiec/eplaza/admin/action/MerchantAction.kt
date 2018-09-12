package com.github.mikowiec.eplaza.admin.action

import com.github.mikowiec.eplaza.admin.service.MerchantService
import com.github.mikowiec.eplaza.model.Merchant
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import com.github.mikowiec.eplaza.utils.Token
import com.github.mikowiec.eplaza.utils.sign
import org.apache.struts2.ServletActionContext
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletResponse
import java.util.HashMap
import java.util.LinkedHashMap

class MerchantAction : AdminBaseAction() {

    @Autowired
    private val merchantService: MerchantService? = null

    private var merchant: Merchant? = Merchant()
    private var merchants: List<Merchant>? = null

    override var jsonResult: Map<String, Any>? = null

    // List all
    fun list(): String {
        // Pagination setting
        if (hasPageSetting()) {
            val page = pageSetting
            val pageSize = pageSizeSetting

            merchants = merchantService!!.findByPage(page, pageSize)
        } else {
            merchants = merchantService!!.findAll()
        }

        val map: HashMap<String, Any> = HashMap()

        map.put("data", merchants!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun get(): String {
        if (this.merchant == null) {
            jsonResult = ResponseTemplate.error(-1, "Param merchant is required!")
            return SUCCESS
        }

        val map: HashMap<String, Any> = HashMap()
        merchant = merchantService!!.findById(this.merchant!!.id)
        map.put("data", merchant!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun add(): String {
        if (this.merchant == null) {
            jsonResult = ResponseTemplate.error(-1, "Param merchant is required!")
            return SUCCESS
        }

        val status = merchantService!!.save(this.merchant!!)
        System.out.println(status)

        if (status > 0) {
            val map: HashMap<String, Any> = HashMap()
            map.put("data", merchant!!)
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(-1, "add failed")
        }
        return SUCCESS
    }

    //
    fun update(): String {
        if (this.merchant == null) {
            jsonResult = ResponseTemplate.error(-1, "Param merchant is required!")
            return SUCCESS
        }

        merchantService!!.update(merchant!!)

        val map: HashMap<String, Any> = HashMap()
        map.put("data", merchant!!)
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    //
    fun delete(): String {
        if (this.merchant == null) {
            jsonResult = ResponseTemplate.error(-1, "Param merchant is required!")
            return SUCCESS
        }

        merchantService!!.delete(this.merchant!!)

        // Set status code
        val res = ServletActionContext.getResponse()
        res.setStatus(400)

        val map: HashMap<String, Any> = HashMap()
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    /**
     * registered
     * @return
     */
    fun signUp(): String {
        val encodePwd = sign.md5(merchant!!.adminPass!!)
        val map: HashMap<String, Any> = HashMap()
        merchant!!.adminPass = encodePwd
        val result = merchantService!!.save(merchant!!)
        if (result > 0) {
            jsonResult = ResponseTemplate.success(map)  //null)
        } else {
            jsonResult = ResponseTemplate.error(101, "Registration error")
        }
        return SUCCESS
    }

    /**
     * Landing
     * @return
     */
    fun login(): String {
        val merchant = merchantService!!.isAllowLogin(this.merchant!!.merchantName!!, this.merchant!!.adminPass!!)
        if (merchant != null) {
            val str = Token.createToken(merchant, 3600 * 60)
            //val data = LinkedHashMap()
            val data: LinkedHashMap<String, Any> = LinkedHashMap()
            data.put("username", merchant!!.merchantName!!)
            data.put("desc", merchant!!.merchantName!!)
            data.put("token", str!!)
            jsonResult = ResponseTemplate.success(data)
        } else {
            jsonResult = ResponseTemplate.error(102, "Login failed")
        }
        return SUCCESS
    }

    fun setMerchant(merchant: Merchant) {
        this.merchant = merchant
    }
}
