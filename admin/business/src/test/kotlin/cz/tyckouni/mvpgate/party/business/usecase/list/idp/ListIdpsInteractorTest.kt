package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.entity.CommonIdp
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.list.Order
import cz.tyckouni.mvpgate.party.business.usecase.list.Page
import cz.tyckouni.mvpgate.party.business.usecase.list.PageRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [ListIdpsInteractor]
 */
internal class ListIdpsInteractorTest {

    private val idps = mock(Idps::class.java)
    private val listIdpsInteractor = ListIdpsInteractor(idps)
    private val pageRequestCaptor = argumentCaptor<PageRequest<IdpSort>>()

    @Test
    fun list() {
        val foundIdps = listOf(
            CommonIdp("guid", "name", "https://login"),
            CommonIdp("guid-o", "name-o", "https://login-o"),
        )
        val expectedPage = Page<Idp>(foundIdps, 2)
        val pageRequest = PageRequest(0, 1, IdpSort.NAME, Order.ASCENDING)

        `when`(idps.find(pageRequestCaptor.capture()))
            .thenReturn(expectedPage)

        val result = listIdpsInteractor.list(pageRequest)

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expectedPage)
        assertThat(pageRequestCaptor.firstValue)
            .isEqualTo(pageRequest)
    }
}
