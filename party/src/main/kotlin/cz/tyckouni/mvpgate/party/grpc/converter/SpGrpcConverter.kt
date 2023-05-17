package cz.tyckouni.mvpgate.party.grpc.converter

import cz.tyckouni.mvpgate.party.grpc.SpGrpc
import cz.tyckouni.mvpgate.party.grpc.SpsGrpc
import cz.tyckouni.mvpgate.party.persistence.entity.Sp

/**
 * [GrpcConverter] for the Sp entities.
 */
interface SpGrpcConverter : GrpcConverter<Sp, SpGrpc> {

    fun toListGrpc(dbEntities: List<Sp>): SpsGrpc
}
