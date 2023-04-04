package cz.tyckouni.mvpgate.party.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

/**
 * Idp DB entity.
 */
@Entity(name = "idp")
class Idp(
    @Column(name = "name", nullable = false) var name: String,
) : PartyEntity()
