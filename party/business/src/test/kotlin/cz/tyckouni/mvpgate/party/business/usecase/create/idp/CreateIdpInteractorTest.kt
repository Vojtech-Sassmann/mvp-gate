package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.dao.GuidProvider
import cz.tyckouni.mvpgate.party.business.dao.idp.SaveIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [CreateIdpInteractor]
 */
internal class CreateIdpInteractorTest {

    private val saveIdp = Mockito.mock(SaveIdp::class.java)
    private val idpArgumentCaptor = argumentCaptor<Idp>()
    private val expectedIdp = Idp("guid", "cool-idp", "https://login")
    private val guidProvider = GuidProvider { expectedIdp.guid }

    private val createIdpInteractor = CreateIdpInteractor(saveIdp, guidProvider)

    @Test
    fun `create assigns correct fields`() {
        Mockito.doNothing()
            .`when`(saveIdp)
            .save(idpArgumentCaptor.capture())

        val createIdpInput = CreateIdpInput(expectedIdp.name, expectedIdp.loginUrl)
        createIdpInteractor.create(createIdpInput)
        val savedIdp = idpArgumentCaptor.firstValue

        assertThat(savedIdp)
            .usingRecursiveComparison()
            .isEqualTo(expectedIdp)
    }
}
