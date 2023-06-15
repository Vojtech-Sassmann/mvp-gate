package cz.tyckouni.mvpgate.party.database.entity

import cz.tyckouni.mvpgate.entity.Idp
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "idp")
class IdpJpa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,

    @Column(name = "guid", nullable = false)
    private var guid: String,

    @Column(name = "name", nullable = false)
    private var name: String,

    @Column(name = "login_url", nullable = false)
    private var loginUrl: String,
) : Idp {

    override fun getGuid(): String {
        return guid
    }

    override fun getName(): String {
        return name
    }

    override fun getLoginUrl(): String {
        return loginUrl
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IdpJpa) return false

        return guid == other.guid
    }

    override fun hashCode(): Int {
        return guid.hashCode()
    }
}
