package cz.tyckouni.mvpgate.party.business.dao

fun interface GuidProvider {

    /**
     * Provides new guid
     *
     * @return new guid
     */
    fun newGuid(): String
}
