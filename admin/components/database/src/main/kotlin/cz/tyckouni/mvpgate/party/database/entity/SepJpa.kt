package cz.tyckouni.mvpgate.party.database.entity

import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn

@Entity(name = "sep")
class SepJpa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "guid", nullable = false, unique = true)
    var guid: String,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @ElementCollection(targetClass = String::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "sep_redirect_url", joinColumns = [JoinColumn(name = "sep_id")])
    @Column(name = "redirect_url", nullable = false)
    var redirectUrls: Set<String> = HashSet(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SepJpa

        return guid == other.guid
    }

    override fun hashCode(): Int {
        return guid.hashCode()
    }
}
