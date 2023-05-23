package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.dao.GuidProvider
import cz.tyckouni.mvpgate.party.business.dao.idp.Idps
import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [CreateIdpInteractor]
 */
internal class CreateIdpInteractorTest {

    private val idps = Mockito.mock(Idps::class.java)
    private val idpArgumentCaptor = argumentCaptor<Idp>()
    private val expectedIdp = CommonIdp("guid", "cool-idp", "https://login")
    private val guidProvider = GuidProvider { expectedIdp.getGuid() }

    private val createIdpInteractor = CreateIdpInteractor(idps, guidProvider)

    @Test
    fun `create assigns correct fields`() {
        Mockito.doNothing()
            .`when`(idps)
            .save(idpArgumentCaptor.capture())

        val createIdpRequest = CreateIdpRequest(expectedIdp.getName(), expectedIdp.getLoginUrl())
        createIdpInteractor.create(createIdpRequest)
        val savedIdp = idpArgumentCaptor.firstValue

        assertThat(savedIdp)
            .usingRecursiveComparison()
            .isEqualTo(expectedIdp)
    }
}
