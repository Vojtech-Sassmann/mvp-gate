package cz.tyckouni.mvpgate.party.grpc.converter.impl

import cz.tyckouni.mvpgate.party.grpc.SpGrpc
<<<<<<< HEAD:mvp-gate-party/src/main/kotlin/cz/tyckouni/mvpgate/party/grpc/converter/sp/SpGrpcConverterImpl.kt
import cz.tyckouni.mvpgate.party.grpc.SpsGrpc
import cz.tyckouni.mvpgate.party.persistence.sp.Sp
=======
import cz.tyckouni.mvpgate.party.grpc.converter.SpGrpcConverter
import cz.tyckouni.mvpgate.party.persistence.entity.Sp
>>>>>>> 06b88b7... refactor(party): simplified packages:mvp-gate-party/src/main/kotlin/cz/tyckouni/mvpgate/party/grpc/converter/impl/SpGrpcConverterImpl.kt
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
