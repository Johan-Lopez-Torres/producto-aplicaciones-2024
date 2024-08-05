package com.example.location_feature.domain.usecase

import com.example.location_feature.domain.repository.AuthRepository
import com.example.location_feature.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            val loggedSuccessfully = authRepository.login(email, password)
            if (loggedSuccessfully) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Login error"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unexpected error occurred"))
        } finally {
            emit(Resource.Finished)
        }
    }
}