package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.MerchantDao
import com.github.mikowiec.eplaza.admin.service.MerchantService
import com.github.mikowiec.eplaza.model.Merchant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MerchantServiceImpl : MerchantService {

    @Autowired
    private val merchantDao: MerchantDao? = null

    override fun findAll(): List<Merchant> {
        return merchantDao!!.findAll()
    }

    override fun findByPage(offset: Int, pageSize: Int): List<Merchant> {
        return merchantDao!!.findByPage(offset, pageSize)
    }

    override fun findById(id: Int): Merchant? {
        return merchantDao!!.findById(id)
    }

    override fun save(merchant: Merchant): Int {
        return merchantDao!!.save(merchant)
    }

    override fun isAllowLogin(merchantName: String, password: String): Merchant? {
        val merchants = merchantDao!!.getMerchantByMerchantName(merchantName)
        val pwd = if (merchants.size > 0) merchants.get(0).adminPass else "xasda"
        return if (pwd.equals(password)) {
            merchants.get(0)
        } else null
    }

    override fun update(merchant: Merchant) {
        merchantDao!!.update(merchant)
    }

    override fun delete(merchant: Merchant) {
        merchantDao!!.delete(merchant)
    }
}
