package cz.tyckouni.mvpgate.party.grpc.converter

import cz.tyckouni.mvpgate.party.persistence.entity.PartyEntity

/**
 * Converter between DB and gRPC entities.
 */
interface GrpcConverter<D : PartyEntity, G> {

    /**
     * Converts the given [dbEntity] into a gRPC entity.
     *
     * @return converted gRPC entity
     * @property D DB entity type
     * @property G gRPC type
     */
    fun toGrpc(dbEntity: D): G
}
