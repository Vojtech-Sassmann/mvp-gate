package cz.tyckouni.mvpgate.user.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "users")
open class User(
    @Column(nullable = false, unique = true)
    open val guid: UUID = UUID.randomUUID(),
    open var firstName: String,
    open var lastName: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (guid != other.guid) return false

        return true
    }

    override fun hashCode(): Int {
        return guid.hashCode()
    }
}
