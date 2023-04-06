package cz.tyckouni.mvpgate.party.grpc.converter.impl

import cz.tyckouni.mvpgate.party.grpc.SpGrpc
import cz.tyckouni.mvpgate.party.grpc.SpsGrpc
import cz.tyckouni.mvpgate.party.grpc.converter.SpGrpcConverter
import cz.tyckouni.mvpgate.party.persistence.entity.Sp
import org.springframework.stereotype.Component

/**
 * Basic implementation of the [SpGrpcConverter].
 */
@Component
class SpGrpcConverterImpl : SpGrpcConverter {
    override fun toGrpc(dbEntity: Sp): SpGrpc {
        return SpGrpc.newBuilder()
            .setGuid(dbEntity.guid.toString())
            .setName(dbEntity.name)
            .build()
    }

    override fun toListGrpc(dbEntities: List<Sp>): SpsGrpc {
        return SpsGrpc.newBuilder()
            .addAllSps(dbEntities.map { toGrpc(it) })
            .build()
    }
}
