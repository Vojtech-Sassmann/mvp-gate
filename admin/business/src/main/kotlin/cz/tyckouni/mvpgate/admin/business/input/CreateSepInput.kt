package cz.tyckouni.mvpgate.admin.business.input

import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateSepUseCase

/**
 * Input for the [CreateSepUseCase]
 */
data class CreateSepInput(val name: String, val redirectUrls: Set<String>)
