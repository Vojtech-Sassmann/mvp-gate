package cz.tyckouni.mvpgate.party.grpc.converter.sp

import cz.tyckouni.mvpgate.party.grpc.SpGrpc
import cz.tyckouni.mvpgate.party.grpc.SpsGrpc
import cz.tyckouni.mvpgate.party.grpc.converter.GrpcConverter
import cz.tyckouni.mvpgate.party.persistence.sp.Sp

/**
 * [GrpcConverter] for the Sp entities.
 */
interface SpGrpcConverter : GrpcConverter<Sp, SpGrpc> {

    fun toListGrpc(dbEntities: List<Sp>): SpsGrpc
}
