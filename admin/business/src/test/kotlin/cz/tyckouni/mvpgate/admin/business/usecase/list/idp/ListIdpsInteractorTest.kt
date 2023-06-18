package cz.tyckouni.mvpgate.admin.business.usecase.list.idp

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
    private val pageInputCaptor = argumentCaptor<cz.tyckouni.mvpgate.admin.business.input.PageInput<IdpSort>>()

    @Test
    fun list() {
        val foundIdps = listOf(
            IdpFactory.create("guid", "name", "https://login"),
            IdpFactory.create("guid-o", "name-o", "https://login-o"),
        )
        val expectedPage = Page(foundIdps, 2)
        val pageInput = cz.tyckouni.mvpgate.admin.business.input.PageInput(0, 1, IdpSort.NAME, Order.ASCENDING)

        `when`(idpFind.find(pageInputCaptor.capture()))
            .thenReturn(expectedPage)

        val result = listIdpsInteractor.list(pageInput)

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expectedPage)
        assertThat(pageInputCaptor.firstValue)
            .isEqualTo(pageInput)
    }
}
