package cz.tyckouni.mvpgate.party.grpc.converter.sp

import cz.tyckouni.mvpgate.party.grpc.SpGrpc
import cz.tyckouni.mvpgate.party.grpc.SpsGrpc
import cz.tyckouni.mvpgate.party.persistence.sp.Sp
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
        var builder = SpsGrpc.newBuilder()

        builder.addAllSps(
            dbEntities.map { toGrpc(it) },
        )

        return builder.build()
    }
}
