package cz.tyckouni.mvpgate.admin.business.usecase.create

import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName
import cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave
import cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest
import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationException
import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

internal class CreateSepInteractorTest {

    private val sepSave = mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepSave::class.java)
    private val sepExistsByName = mock(cz.tyckouni.mvpgate.admin.business.gateway.storage.sep.SepExistsByName::class.java)
    private val sepArgumentCaptor = argumentCaptor<Sep>()
    private val expectedSep = SepFactory.create("guid", "cool-sep", setOf("http://localhost:8000"))
    private val guidProvider = { "guid" }

    private val createSepInteractor = CreateSepInteractor(sepSave, sepExistsByName, guidProvider)

    @Test
    fun `create persists correct sep from given input input`() {
        Mockito.doNothing()
            .`when`(sepSave)
            .save(sepArgumentCaptor.capture())

        val createSepRequest = cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest(
            expectedSep.getName(),
            expectedSep.getRedirectUrls(),
        )

        val returnedSep = createSepInteractor.create(createSepRequest)
        val savedSep = sepArgumentCaptor.firstValue

        assertThat(savedSep)
            .usingRecursiveComparison()
            .isEqualTo(expectedSep)

        assertThat(returnedSep)
            .usingRecursiveComparison()
            .isEqualTo(savedSep)
    }

    @Test
    fun `create fails for empty name`() {
        val createSepRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest(" ", expectedSep.getRedirectUrls())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'name cannot be blank'")
    }

    @Test
    fun `create fails for empty redirectUrl`() {
        val createSepRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest(expectedSep.getName(), setOf())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'redirect urls cannot be empty'")
    }

    @Test
    fun `create fails for invalid redirectUrl`() {
        val createSepRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest(expectedSep.getName(), setOf("http:/invalid"))

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'invalid redirect URL: 'http:/invalid''")
    }

    @Test
    fun `create fails for duplicate name`() {
        val duplicateName = "bank"

        `when`(sepExistsByName.existsByName(duplicateName))
            .thenReturn(true)

        val createSepRequest =
            cz.tyckouni.mvpgate.admin.business.request.CreateSepRequest(duplicateName, expectedSep.getRedirectUrls())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessage("Validation failed: ['given name is not unique: 'bank'']")
    }
}
