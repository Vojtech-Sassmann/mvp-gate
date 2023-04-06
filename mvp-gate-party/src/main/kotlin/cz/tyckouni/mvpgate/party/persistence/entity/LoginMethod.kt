package cz.tyckouni.mvpgate.party.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

/**
 * Login method JPA entity.
 */
@Entity(name = "login_method")
class LoginMethod(
    @ManyToOne
    @JoinColumn(name = "sp_id", nullable = false)
    var sp: Sp,

    @ManyToOne
    @JoinColumn(name = "idp_id", nullable = false)
    var idp: Idp,
) : PartyEntity()
