package cz.tyckouni.mvpgate.admin.business.usecase.list.idp

import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpFind
import cz.tyckouni.mvpgate.admin.business.request.PageRequest
import cz.tyckouni.mvpgate.admin.business.usecase.list.ListIdpsInteractor
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.IdpSort
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Order
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.entity.IdpFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [ListIdpsInteractor]
 */
internal class ListIdpsInteractorTest {

    private val idpFind = mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpFind::class.java)
    private val listIdpsInteractor = ListIdpsInteractor(idpFind)
    private val pageRequestCaptor = argumentCaptor<cz.tyckouni.mvpgate.admin.business.request.PageRequest<IdpSort>>()

    @Test
    fun list() {
        val foundIdps = listOf(
            IdpFactory.create("guid", "name", "https://login"),
            IdpFactory.create("guid-o", "name-o", "https://login-o"),
        )
        val expectedPage = Page(foundIdps, 2)
        val pageRequest = cz.tyckouni.mvpgate.admin.business.request.PageRequest(0, 1, IdpSort.NAME, Order.ASCENDING)

        `when`(idpFind.find(pageRequestCaptor.capture()))
            .thenReturn(expectedPage)

        val result = listIdpsInteractor.list(pageRequest)

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expectedPage)
        assertThat(pageRequestCaptor.firstValue)
            .isEqualTo(pageRequest)
    }
}
