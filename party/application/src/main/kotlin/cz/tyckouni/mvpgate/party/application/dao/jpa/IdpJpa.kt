package cz.tyckouni.mvpgate.party.application.dao.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "idp")
class IdpJpa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "guid", nullable = false)
    var guid: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "login_url", nullable = false)
    var loginUrl: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IdpJpa) return false

        if (guid != other.guid) return false

        return true
    }

    override fun hashCode(): Int {
        return guid.hashCode()
    }
}
