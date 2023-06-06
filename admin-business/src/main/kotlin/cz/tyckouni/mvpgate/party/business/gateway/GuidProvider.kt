package cz.tyckouni.mvpgate.party.business.gateway

fun interface GuidProvider {

    /**
     * Provides new guid
     *
     * @return new guid
     */
    fun newGuid(): String
}
