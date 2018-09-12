package com.github.mikowiec.eplaza.frontend.action

import com.opensymphony.xwork2.ActionSupport
import org.apache.struts2.ServletActionContext
import org.springframework.context.annotation.Scope

import javax.servlet.http.HttpServletRequest

@Scope("prototype")
open class BaseAction : ActionSupport() {
    open var jsonResult: Map<String, Any>? = null
    var DEFAULT_PAGE = 1
    var DEFAULT_PAGE_SIZE = 15

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

    // Check if there are Page related parameters
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
