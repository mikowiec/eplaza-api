package com.github.mikowiec.eplaza.admin.dao


import com.github.mikowiec.eplaza.model.SecondKill

interface SecondKillDao {

    fun findAll(): List<SecondKill>

    fun findByPage(page: Int, pageSize: Int): List<SecondKill>

    fun findById(id: Int): SecondKill?

    fun save(secondKill: SecondKill): Int

    fun update(secondKill: SecondKill)

    fun delete(secondKill: SecondKill)

}
