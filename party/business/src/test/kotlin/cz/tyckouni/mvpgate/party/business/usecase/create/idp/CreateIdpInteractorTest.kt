package cz.tyckouni.mvpgate.party.business.usecase.create.idp

import cz.tyckouni.mvpgate.party.business.entity.CommonIdp
import cz.tyckouni.mvpgate.party.business.entity.Idp
import cz.tyckouni.mvpgate.party.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.party.business.gateway.Idps
import cz.tyckouni.mvpgate.party.business.usecase.validation.ValidationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [CreateIdpInteractor]
 */
internal class CreateIdpInteractorTest {

    private val idps = Mockito.mock(Idps::class.java)
    private val idpArgumentCaptor = argumentCaptor<Idp>()
    private val expectedIdp = CommonIdp("guid", "cool-idp", "http://localhost:8000")
    private val guidProvider = GuidProvider { expectedIdp.getGuid() }

    private val createIdpInteractor = CreateIdpInteractor(idps, guidProvider)

    @Test
    fun `assigns correct fields`() {
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

    @Test
    fun `fails for invalid login url`() {
        val createIdpRequest = CreateIdpRequest(expectedIdp.getName(), "http://localhost:900 ")

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpRequest) }
            .withMessageContaining("Validation failed: ['loginUrl is not a valid URL']")
    }

    @Test
    fun `fails for empty name`() {
        val createIdpRequest = CreateIdpRequest("", expectedIdp.getLoginUrl())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpRequest) }
            .withMessageContaining("Validation failed: ['name cannot be blank']")
    }
}
