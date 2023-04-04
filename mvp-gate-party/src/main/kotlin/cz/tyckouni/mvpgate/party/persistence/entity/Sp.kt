package cz.tyckouni.mvpgate.party.persistence.entity

import jakarta.persistence.Entity

/**
 * Sp DB entity.
 */
@Entity
class Sp(
    var name: String,
) : PartyEntity()
