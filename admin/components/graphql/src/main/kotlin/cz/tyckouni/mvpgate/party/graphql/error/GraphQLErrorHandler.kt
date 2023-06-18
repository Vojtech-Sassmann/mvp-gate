package cz.tyckouni.mvpgate.party.graphql.error

import cz.tyckouni.mvpgate.admin.business.usecase.validation.ValidationError
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class GraphQLErrorHandler : DataFetcherExceptionResolverAdapter() {
    override fun resolveToMultipleErrors(ex: Throwable, env: DataFetchingEnvironment): MutableList<GraphQLError>? {
        if (ex is GraphQLValidationException) {
            return ex.validationException.errors
                .map(::convertValidationError)
                .toMutableList()
        }

        return null
    }

    private fun convertValidationError(validationError: ValidationError): GraphQLError {
        return GraphqlErrorBuilder.newError()
            .errorType(ErrorType.BAD_REQUEST)
            .message(validationError.message)
            .build()
    }
}
