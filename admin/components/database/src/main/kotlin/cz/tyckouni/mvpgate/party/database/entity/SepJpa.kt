package cz.tyckouni.mvpgate.party.database.entity

import cz.tyckouni.mvpgate.entity.Sep
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
    private var id: Long? = null,

    @Column(name = "guid", nullable = false, unique = true)
    private var guid: String,

    @Column(name = "name", nullable = false, unique = true)
    private var name: String,

    @ElementCollection(targetClass = String::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "sep_redirect_url", joinColumns = [JoinColumn(name = "sep_id")])
    @Column(name = "redirect_url", nullable = false)
    private var redirectUrls: Set<String> = HashSet(),
) : Sep {

    fun getId() = id

    override fun getGuid() = guid

    override fun getName() = name

    override fun getRedirectUrls() = redirectUrls

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
