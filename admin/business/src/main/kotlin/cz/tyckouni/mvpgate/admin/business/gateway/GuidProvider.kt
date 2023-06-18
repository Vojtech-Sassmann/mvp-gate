package cz.tyckouni.mvpgate.admin.business.gateway

fun interface GuidProvider {

    /**
     * Provides new guid
     *
     * @return new guid
     */
    fun newGuid(): String
}
