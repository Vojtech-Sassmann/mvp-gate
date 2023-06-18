package cz.tyckouni.mvpgate.admin.business.usecase.list.sep

import cz.tyckouni.mvpgate.admin.business.usecase.list.ListSepsInteractor
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Order
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.Page
import cz.tyckouni.mvpgate.admin.business.usecase.list.sort.SepSort
import cz.tyckouni.mvpgate.entity.SepFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

class ListSepsInteractorTest {

    private val sepFind = mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepFind::class.java)
    private val listSepsInteractor = ListSepsInteractor(sepFind)
    private val pageInputCaptor = argumentCaptor<cz.tyckouni.mvpgate.admin.business.input.PageInput<SepSort>>()

    @Test
    fun list() {
        val foundSeps = listOf(
            SepFactory.create("guid", "sep1", setOf("https://tvojemama.cz")),
            SepFactory.create("guid-007", "sep23", setOf("https://yourmother.com")),
        )
        val expectedPage = Page(foundSeps, 2)
        val pageInput = cz.tyckouni.mvpgate.admin.business.input.PageInput(0, 10, SepSort.NAME, Order.DESCENDING)

        `when`(sepFind.find(pageInputCaptor.capture()))
            .thenReturn(expectedPage)

        val result = listSepsInteractor.list(pageInput)

        assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expectedPage)
        assertThat(pageInputCaptor.firstValue)
            .isEqualTo(pageInput)
    }
}
