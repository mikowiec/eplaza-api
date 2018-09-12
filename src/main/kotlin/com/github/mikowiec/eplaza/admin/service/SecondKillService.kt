package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.SecondKill

interface SecondKillService {

    fun findAll(): List<SecondKill>

    fun findByPage(offset: Int, pageSize: Int): List<SecondKill>

    fun findById(id: Int): SecondKill?

    fun save(secondKill: SecondKill): Int

    fun update(secondKill: SecondKill)

    fun delete(secondKill: SecondKill)

}
