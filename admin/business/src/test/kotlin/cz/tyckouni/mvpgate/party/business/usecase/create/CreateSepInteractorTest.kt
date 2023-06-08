package cz.tyckouni.mvpgate.party.business.usecase.create

import cz.tyckouni.mvpgate.entity.Sep
import cz.tyckouni.mvpgate.entity.SepFactory
import cz.tyckouni.mvpgate.party.business.gateway.Seps
import cz.tyckouni.mvpgate.party.business.usecase.validation.ValidationException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.argumentCaptor

internal class CreateSepInteractorTest {

    private val seps = mock(Seps::class.java)
    private val sepArgumentCaptor = argumentCaptor<Sep>()
    private val expectedSep = SepFactory.create("guid", "cool-sep", setOf("http://localhost:8000"))
    private val guidProvider = { "guid" }

    private val createSepInteractor = CreateSepInteractor(seps, guidProvider)

    @Test
    fun `create persists correct sep from given input input`() {
        Mockito.doNothing()
            .`when`(seps)
            .save(sepArgumentCaptor.capture())

        val createSepRequest = CreateSepRequest(expectedSep.getName(), expectedSep.getRedirectUrls())

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
        val createSepRequest = CreateSepRequest(" ", expectedSep.getRedirectUrls())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'name cannot be blank'")
    }

    @Test
    fun `create fails for empty redirectUrl`() {
        val createSepRequest = CreateSepRequest(expectedSep.getName(), setOf())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'redirect urls cannot be empty'")
    }

    @Test
    fun `create fails for invalid redirectUrl`() {
        val createSepRequest = CreateSepRequest(expectedSep.getName(), setOf("http:/invalid"))

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessageContaining("'invalid redirect URL: 'http:/invalid''")
    }

    @Test
    fun `create fails for duplicate name`() {
        val duplicateName = "bank"

        `when`(seps.existsByName(duplicateName))
            .thenReturn(true)

        val createSepRequest = CreateSepRequest(duplicateName, expectedSep.getRedirectUrls())

        assertThatExceptionOfType(ValidationException::class.java)
            .isThrownBy { createSepInteractor.create(createSepRequest) }
            .withMessage("Validation failed: ['given name is not unique: 'bank'']")
    }
}
