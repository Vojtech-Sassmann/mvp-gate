package cz.tyckouni.mvpgate.party.database.repository

import cz.tyckouni.mvpgate.party.database.entity.IdpJpa
import org.springframework.data.jpa.repository.JpaRepository

interface IdpJpaRepository : JpaRepository<IdpJpa, Long>
