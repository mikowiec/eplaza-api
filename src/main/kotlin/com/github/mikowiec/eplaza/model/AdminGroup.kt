package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.*

import javax.persistence.CascadeType
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.Table
import java.util.Date
import java.util.HashSet

/**
 * Background administrator grouping
 */
@Entity
@Table(name = "admin_group")
class AdminGroup {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    @Column(length = 30)
    var groupName: String? = null

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var updatedAt: Date? = null

    @ManyToMany(mappedBy = "adminGroups", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var adminUsers: Set<AdminUser> = HashSet()
}
