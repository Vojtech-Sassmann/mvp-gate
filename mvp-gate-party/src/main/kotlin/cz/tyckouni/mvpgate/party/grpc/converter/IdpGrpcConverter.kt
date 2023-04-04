package cz.tyckouni.mvpgate.party.grpc.converter

import cz.tyckouni.mvpgate.party.grpc.IdpGrpc
import cz.tyckouni.mvpgate.party.persistence.entity.Idp

/**
 * [GrpcConverter] for the Idp entities.
 */
interface IdpGrpcConverter : GrpcConverter<Idp, IdpGrpc>
