package com.github.mikowiec.eplaza.Interceptor

import com.github.mikowiec.eplaza.model.User
import com.github.mikowiec.eplaza.utils.ResponseTemplate
import com.github.mikowiec.eplaza.utils.Token
import com.opensymphony.xwork2.ActionContext
import com.opensymphony.xwork2.ActionInvocation
import com.opensymphony.xwork2.interceptor.AbstractInterceptor
import com.opensymphony.xwork2.util.ValueStack
import org.apache.struts2.ServletActionContext

class authInterceptor : AbstractInterceptor() {

    @Override
    @Throws(Exception::class)
    override fun intercept(actionInvocation: ActionInvocation): String? {
        val token = ServletActionContext.getRequest().getHeader("x-access-token")

        if (token != null) {
            val user = Token.validToken(token, User::class.java)
            if (user != null) {
                ServletActionContext.getRequest().setAttribute("tokenData", user)
                actionInvocation.invoke()
                return null
            }
        }
        val context = ActionContext.getContext()
        val stack = context.getValueStack()
        stack.set("jsonResult", ResponseTemplate.error(1, "Please transfer the correct token"))
        return "success"
    }
}
