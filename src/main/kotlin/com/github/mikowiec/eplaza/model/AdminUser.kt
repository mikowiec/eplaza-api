package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.*

import javax.persistence.CascadeType
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.Table
import java.util.Date
import java.util.HashSet

/**
 * Background administrator
 */
@Entity
@Table(name = "admin_user")
class AdminUser {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    @Column(length = 30)
    var username: String? = null

    @Column(length = 64)
    var password: String? = null

    @ColumnDefault(value = "false")
    var admin: Boolean? = null

    @Column(length = 255)
    var email: String? = null

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var updatedAt: Date? = null


    @ManyToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinTable(
            name = "admin_users_groups",
            joinColumns = arrayOf(JoinColumn(name = "admin_user_id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "admin_group_id")))
    @Fetch(value = FetchMode.SUBSELECT)
    var adminGroups: Set<AdminGroup> = HashSet()
}
