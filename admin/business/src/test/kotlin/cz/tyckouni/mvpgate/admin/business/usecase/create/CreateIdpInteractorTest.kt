package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider
import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName
import cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave
import cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.entity.Idp
import cz.tyckouni.mvpgate.entity.IdpFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

/**
 * Unit test class for the [CreateIdpInteractor]
 */
internal class CreateIdpInteractorTest {

    private val idpSave = Mockito.mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpSave::class.java)
    private val idpExistsByName = Mockito.mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.idp.IdpExistsByName::class.java)
    private val idpArgumentCaptor = argumentCaptor<Idp>()
    private val expectedIdp = IdpFactory.create("guid", "cool-idp", "http://localhost:8000")
    private val guidProvider = cz.tyckouni.mvpgate.admin.business.gateway.GuidProvider { expectedIdp.getGuid() }

    private val createIdpInteractor =
        cz.tyckouni.mvpgate.admin.business.usecase.create.CreateIdpInteractor(idpSave, idpExistsByName, guidProvider)

    @Test
    fun `assigns correct fields`() {
        Mockito.doNothing()
            .`when`(idpSave)
            .save(idpArgumentCaptor.capture())

        val createIdpRequest = cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest(
            expectedIdp.getName(),
            expectedIdp.getLoginUrl(),
        )
        createIdpInteractor.create(createIdpRequest)
        val savedIdp = idpArgumentCaptor.firstValue

        assertThat(savedIdp)
            .usingRecursiveComparison()
            .isEqualTo(expectedIdp)
    }

    @Test
    fun `fails for invalid login url`() {
        val createIdpRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest(expectedIdp.getName(), "http://localhost:900 ")

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpRequest) }
            .withMessageContaining("Validation failed: ['invalid login URL: 'http://localhost:900 '']")
    }

    @Test
    fun `fails for empty name`() {
        val createIdpRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest("", expectedIdp.getLoginUrl())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpRequest) }
            .withMessageContaining("Validation failed: ['name cannot be blank']")
    }

    @Test
    fun `fails for an existing idp with given name`() {
        val name = "orangeIdp"
        val createIdpRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateIdpRequest(name, expectedIdp.getLoginUrl())

        `when`(idpExistsByName.existsByName(name))
            .thenReturn(true)

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpRequest) }
            .withMessageContaining("Validation failed: ['given name is not unique: 'orangeIdp'']")
    }
}
