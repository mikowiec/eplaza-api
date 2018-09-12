package com.github.mikowiec.eplaza.utils

import java.util.LinkedHashMap

object ResponseTemplate {
    fun success(result: Map<String, Any>): Map<String, Any> {
        val data = LinkedHashMap<String, Any>()
        data.put("rcode", 0)
        data.put("message", "success")
        data.put("result", result)
        return data
    }

    fun error(rcode: Int, message: String): Map<String, Any> {
        val data = LinkedHashMap<String, Any>()
        data.put("rcode", rcode)
        data.put("message", message)
        return data
    }
}
