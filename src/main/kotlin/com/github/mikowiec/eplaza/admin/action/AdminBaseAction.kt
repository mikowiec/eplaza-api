package com.github.mikowiec.eplaza.admin.action


import com.opensymphony.xwork2.ActionSupport
import org.apache.struts2.ServletActionContext
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller

import javax.servlet.http.HttpServletRequest

@Controller
@Scope("prototype")
open class AdminBaseAction : ActionSupport() {

    var DEFAULT_PAGE = 1
    var DEFAULT_PAGE_SIZE = 15

    open var jsonResult: Map<String, Any>? = null

    // Get sort settings, default is ID
    protected val orderSetting: String
        get() = if (!hasParam("order")) {
            "id"
        } else getParam("order")

    // Get Page Configuration
    protected val pageSetting: Int
        get() {
            val req = ServletActionContext.getRequest()
            val paramPage = req.getParameter("page") ?: return DEFAULT_PAGE

            return Integer.parseInt(paramPage)
        }

    // Get PageSize configuration
    protected val pageSizeSetting: Int
        get() {
            val req = ServletActionContext.getRequest()
            val paramPageSize = req.getParameter("pageSize") ?: return DEFAULT_PAGE_SIZE

            return Integer.parseInt(paramPageSize)
        }

    //
    protected fun hasParam(paramName: String): Boolean {
        val req = ServletActionContext.getRequest()
        val paramValue = req.getParameter(paramName)

        return paramValue != null
    }

    // Check if there are Page related parameters
    protected fun hasPageSetting(): Boolean {
        return hasParam("page") || hasParam("pageSize")
    }

    // Get parameters
    protected fun getParam(paramName: String): String {
        val req = ServletActionContext.getRequest()
        return req.getParameter(paramName)
    }

}
