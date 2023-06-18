package cz.tyckouni.mvpgate.admin.business.request

import cz.tyckouni.mvpgate.admin.business.usecase.create.CreateSepUseCase

/**
 * Input for the [CreateSepUseCase]
 */
data class CreateSepRequest(val name: String, val redirectUrls: Set<String>)
