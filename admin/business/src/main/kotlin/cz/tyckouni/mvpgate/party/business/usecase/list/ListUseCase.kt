package cz.tyckouni.mvpgate.party.business.usecase.list

fun interface ListUseCase<T, S : Sort> {

    /**
     * Find a page of [T] by given [pageRequest]
     *
     * @return [Page] with list of found [T]
     */
    fun list(pageRequest: PageRequest<S>): Page<T>
}
