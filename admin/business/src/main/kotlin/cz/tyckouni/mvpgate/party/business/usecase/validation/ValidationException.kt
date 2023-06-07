package cz.tyckouni.mvpgate.party.business.usecase.validation

/**
 * Thrown if some validation fails.
 *
 * Can contain multiple errors in the [errors] field
 */
class ValidationException internal constructor(
    val errors: List<ValidationError>,
) : RuntimeException("Validation failed: ${errors.map { error -> "'${error.message}'" }}")
