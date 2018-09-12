package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp

import javax.persistence.*
import java.util.Date


/**
 * user
 */
@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var userid: Int = 0 // User id

    var username: String? = null // username

    var password: String? = null // password

    var mail: String? = null // mailbox

    var phone: String? = null // Mobile phone

    var status: Int = 0 // status

    @Column(updatable = false, name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    var createAt: Date? = null // Creation time

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    var updateAt: Date? = null // Change the time
}
