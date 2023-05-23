package cz.tyckouni.mvpgate.party.business.usecase.list.idp

import cz.tyckouni.mvpgate.party.business.dao.idp.FetchIdpPage
import cz.tyckouni.mvpgate.party.business.entity.Idp
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

    private val fetchIdpPage = mock(FetchIdpPage::class.java)
    private val listIdpsInteractor = ListIdpsInteractor(fetchIdpPage)
    private val pageRequestCaptor = argumentCaptor<PageRequest>()

    @Test
    fun list() {
        val foundIdps = listOf(
            Idp("guid", "name", "https://login"),
            Idp("guid-o", "name-o", "https://login-o"),
        )
        val expectedPage = Page(foundIdps, 0, 1)
        val pageRequest = PageRequest(0, 1, "guid", Order.ASCENDING)

        `when`(fetchIdpPage.find(pageRequestCaptor.capture()))
            .thenReturn(expectedPage)

        val result = listIdpsInteractor.list(pageRequest)

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expectedPage)
        assertThat(pageRequestCaptor.firstValue)
            .isEqualTo(pageRequest)
    }
}
