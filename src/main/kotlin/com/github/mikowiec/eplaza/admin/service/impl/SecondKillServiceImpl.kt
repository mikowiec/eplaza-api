package com.github.mikowiec.eplaza.admin.service.impl

import com.github.mikowiec.eplaza.admin.dao.SecondKillDao
import com.github.mikowiec.eplaza.admin.service.SecondKillService
import com.github.mikowiec.eplaza.model.SecondKill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SecondKillServiceImpl : SecondKillService {

    @Autowired
    private val secondKillDao: SecondKillDao? = null

    override fun findAll(): List<SecondKill> {
        return secondKillDao!!.findAll()
    }

    override fun findByPage(offset: Int, pageSize: Int): List<SecondKill> {
        return secondKillDao!!.findByPage(offset, pageSize)
    }

    override fun findById(id: Int): SecondKill? {
        return secondKillDao!!.findById(id)
    }

    override fun save(secondKill: SecondKill): Int {
        return secondKillDao!!.save(secondKill)
    }

    override fun update(secondKill: SecondKill) {
        secondKillDao!!.update(secondKill)
    }

    override fun delete(secondKill: SecondKill) {
        secondKillDao!!.delete(secondKill)
    }
}
