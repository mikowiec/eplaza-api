package com.github.mikowiec.eplaza.frontend.action

import com.github.mikowiec.eplaza.frontend.service.UserService
import com.github.mikowiec.eplaza.model.User
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import com.github.mikowiec.eplaza.utils.Token
import com.github.mikowiec.eplaza.utils.sign
import com.opensymphony.xwork2.ActionSupport

import java.util.HashMap
import java.util.LinkedHashMap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

@Controller(value = "userAction")
@Scope("prototype")
class UserAction : ActionSupport() {
    @Autowired
    private val userService: UserService? = null

    var jsonResult: Map<String, Any>? = null

    @JvmField var user: User? = null
    @JvmField var phone: String = ""
    @JvmField var password: String = ""

    /**
     * Determine if the phone exists
     * @return
     */
    val isExist: String
        get() {
            val result = userService!!.isExist(this.phone)
            System.out.println(result)
            val data: HashMap<String, Any> = HashMap()
            //val data = HashMap<String, Object>()
            data.put("isExist", result)
            jsonResult = ResponseTemplate.success(data)
            return SUCCESS
        }


    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun setUser(user: User) {
        //this.user = user
        this.user = user
    }


    /**
     * Test demo
     * @return
     */
    fun add(): String {
        val map: HashMap<String, Any> = HashMap()
        //        int result = userService.saveUser(user);
        //        System.out.println(result);
        map.put("rcode", 0)
        System.out.println("111")
        map.put("name", "Gavin")
        var str: String? = null
        try {
            str = Token.createToken(map, 30000)
        } catch (e: Exception) {
            System.out.println(e)
        }

        System.out.println(str)
        val string = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjoie1wiY3JlYXRlQXRcIjoxNTI5ODQzMjgwMDAwLFwicGFzc3dvcmRcIjpcImUxMGFkYzM5NDliYTU5YWJiZTU2ZTA1N2YyMGY4ODNlXCIsXCJwaG9uZVwiOlwiMTMyMzg1MTIzNjZcIixcInN0YXR1c1wiOjAsXCJ1cGRhdGVBdFwiOjE1Mjk4NDMyODAwMDAsXCJ1c2VyaWRcIjozMSxcInVzZXJuYW1lXCI6XCJHYXZpblwifSIsImV4cCI6MTUyOTg2NjU2NX0.kC7sTZxTn5-PS2HyYFdm8xJ7J8G81aFZLFZlXS5b50w"
        val obj = Token.validToken(string, User::class.java)
        println(obj!!.userid)
        jsonResult = map
        return SUCCESS
    }

    /**
     * registered
     * @return
     */
    fun signUp(): String {
        val encodePwd = sign.md5(this.user!!.password!!)
        user!!.password = encodePwd
        val result = userService!!.saveUser(user!!)
        val map: HashMap<String, Any> = HashMap()
        map.put("data", userService!!)
        if (result > 0) {
            jsonResult = ResponseTemplate.success(map)
        } else {
            jsonResult = ResponseTemplate.error(101, "registration error")
        }
        return SUCCESS
    }

    /**
     * Landing
     * @return
     */
    fun login(): String {
        val user = userService!!.isAllowLogin(this.phone, this.password)
        if (user != null) {
            val str = Token.createToken(user, 3600 * 60)
            //val data = LinkedHashMap()
            val data: LinkedHashMap<String, Any> = LinkedHashMap()
            data.put("username", user!!.username!!)
            data.put("phone", user!!.phone!!)
            data.put("mail", user!!.mail!!)
            data.put("token", str!!)
            jsonResult = ResponseTemplate.success(data)
        } else {
            jsonResult = ResponseTemplate.error(102, "Login failed")
        }
        return SUCCESS
    }
}
