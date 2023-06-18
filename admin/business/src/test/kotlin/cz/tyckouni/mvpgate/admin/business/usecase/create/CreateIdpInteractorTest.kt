package cz.tyckouni.mvpgate.admin.business.usecase.create

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

        val createIdpInput = cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput(
            expectedIdp.getName(),
            expectedIdp.getLoginUrl(),
        )
        createIdpInteractor.create(createIdpInput)
        val savedIdp = idpArgumentCaptor.firstValue

        assertThat(savedIdp)
            .usingRecursiveComparison()
            .isEqualTo(expectedIdp)
    }

    @Test
    fun `fails for invalid login url`() {
        val createIdpInput =
            cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput(expectedIdp.getName(), "http://localhost:900 ")

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpInput) }
            .withMessageContaining("Validation failed: ['invalid login URL: 'http://localhost:900 '']")
    }

    @Test
    fun `fails for empty name`() {
        val createIdpInput =
            cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput("", expectedIdp.getLoginUrl())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpInput) }
            .withMessageContaining("Validation failed: ['name cannot be blank']")
    }

    @Test
    fun `fails for an existing idp with given name`() {
        val name = "orangeIdp"
        val createIdpInput =
            cz.tyckouni.mvpgate.admin.business.input.CreateIdpInput(name, expectedIdp.getLoginUrl())

        `when`(idpExistsByName.existsByName(name))
            .thenReturn(true)

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createIdpInteractor.create(createIdpInput) }
            .withMessageContaining("Validation failed: ['given name is not unique: 'orangeIdp'']")
    }
}
