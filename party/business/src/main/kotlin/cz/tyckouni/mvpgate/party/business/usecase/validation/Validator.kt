package cz.tyckouni.mvpgate.party.business.usecase.validation

/**
 * Used for multiple validations.
 *
 * You can chain multiple validations [validate] and at the end call the [handle]
 * method.
 */
class Validator(
    private val errors: MutableList<ValidationError> = ArrayList(),
) {
    /**
     * If the given value is true, add a new error with the given message
     */
    fun validate(ok: Boolean, errorMessage: String): Validator {
        if (!ok) {
            errors.add(ValidationError(errorMessage))
        }
        return this
    }

    /**
     * Checks, if contains any errors. If so [ValidationException] is thrown with these errors.
     *
     * @throws ValidationException if contains any errors
     */
    fun handle() {
        if (errors.isNotEmpty()) {
            throw ValidationException(errors)
        }
    }
}
