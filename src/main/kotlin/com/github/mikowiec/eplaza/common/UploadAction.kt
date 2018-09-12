package com.github.mikowiec.eplaza.common

import com.github.mikowiec.eplaza.utils.ResponseTemplate
import com.opensymphony.xwork2.ActionSupport
import org.apache.struts2.ServletActionContext
import org.apache.struts2.util.TokenHelper

import java.io.File
import java.util.HashMap

class UploadAction : ActionSupport() {

    private var uploadFile: File? = null
    private var uploadFileContentType: String? = null
    private var uploadFileFileName: String? = null

    //var jsonResult: Map<String, Object>
    var jsonResult: Map<String, Any>? = null

    fun upload(): String {
        if (uploadFile == null) {
            jsonResult = ResponseTemplate.error(-1, "No file!")
            return SUCCESS
        }

        // 1.Get the save path of the file
        val basePath = ServletActionContext.getServletContext().getRealPath("/uploads")
        val basePathFile = File(basePath)
        if (!basePathFile.exists()) {
            basePathFile.mkdirs()
        }

        // 2.Put the file name UUID
        var GUIDFileName = TokenHelper.generateGUID()
        if ("image/png".equals(uploadFileContentType)) {
            GUIDFileName = GUIDFileName + ".png"
        } else {
            GUIDFileName = GUIDFileName + ".jpg"
        }

        // 3.save document
        // Copy: Temporary files are still in use, wasting server disk space
        //FileUtils.copyFile(visitFile, new File(file,GUIDFileName));
        // Cut: Rename the temporary file to the specified location (better)
        uploadFile!!.renameTo(File(basePathFile, GUIDFileName))

        // 4. Return the result, return the file save path
        val map: HashMap<String, Any> = HashMap()
        map.put("filePath", "uploads/$GUIDFileName")
        jsonResult = ResponseTemplate.success(map)
        return SUCCESS
    }

    fun setUploadFile(uploadFile: File) {
        this.uploadFile = uploadFile
    }

    fun setUploadFileContentType(uploadFileContentType: String) {
        this.uploadFileContentType = uploadFileContentType
    }

    fun setUploadFileFileName(uploadFileFileName: String) {
        this.uploadFileFileName = uploadFileFileName
    }

}
