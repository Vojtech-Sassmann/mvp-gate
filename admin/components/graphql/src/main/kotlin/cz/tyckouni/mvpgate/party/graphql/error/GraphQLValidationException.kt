package cz.tyckouni.mvpgate.party.graphql.error

import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException

/**
 * Thrown if some validation fails because of a bad request.
 */
class GraphQLValidationException(
    val validationException: ValidationException,
) : RuntimeException()
