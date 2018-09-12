package com.github.mikowiec.eplaza.Interceptor

import com.opensymphony.xwork2.ActionContext
import com.opensymphony.xwork2.ActionInvocation
import com.opensymphony.xwork2.interceptor.AbstractInterceptor
import com.opensymphony.xwork2.util.ValueStack
import org.apache.struts2.ServletActionContext

import javax.servlet.http.HttpServletResponse
import java.util.HashMap

class postInterceptor : AbstractInterceptor() {
    /**
     * Only POST access interceptors are allowed
     * @param actionInvocation
     * @return
     * @throws Exception
     */
    @Override
    @Throws(Exception::class)
    override fun intercept(actionInvocation: ActionInvocation): String? {
        // Resolve cross-domain
        val res = ServletActionContext.getResponse()
        res.setHeader("Access-Control-Allow-Origin", "*")
        res.setHeader("Access-Control-Allow-Credentials", "true")
        res.setHeader("Access-Control-Allow-Methods", "*")
        res.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token")
        res.setHeader("Access-Control-Expose-Headers", "*")

        val method = ServletActionContext.getRequest().getMethod()
        if (method.equals("POST")) {
            actionInvocation.invoke()
            return null
        } else {
            val context = ActionContext.getContext()
            val jsonResult = HashMap<String, Any>()  //was HashMap<String, Object>
            jsonResult.put("rcode", 1)
            jsonResult.put("message", "just allow post method")
            jsonResult.put("result", "")
            val stack = context.getValueStack()
            stack.set("jsonResult", jsonResult)
            return "success"
        }
    }
}
