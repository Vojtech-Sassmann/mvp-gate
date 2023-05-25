package cz.tyckouni.mvpgate.party.business.usecase.validation

/**
 * Class for URL validation
 */
class UrlValidator private constructor() {

    companion object {
        private val urlRegex = Regex(
            "^https?://(?:www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*$",
        )
        private val localhostRegex = Regex("https?://localhost(:\\d+)?")

        fun isValidUrl(value: String): Boolean {
            return localhostRegex.matches(value) || urlRegex.matches(value)
        }
    }
}
