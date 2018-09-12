package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp

import javax.persistence.*
import java.util.Date

/**
 * Merchant
 */
@Entity
@Table(name = "merchant")
class Merchant {

    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // the shop's name
    var merchantName: String? = null

    // shop address
    var merchantPlace: String? = null

    // Store description
    var merchantDesc: String? = null

    // Background login password
    var adminPass: String? = null

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var updatedAt: Date? = null
}
