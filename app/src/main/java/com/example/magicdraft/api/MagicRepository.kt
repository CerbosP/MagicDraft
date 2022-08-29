package com.example.magicdraft.api

import com.example.magicdraft.model.states.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface MagicRepository {
    suspend fun getSets(): Flow<UIState>
    suspend fun drawBooster(code: String): Flow<UIState>
    suspend fun findCard(name: String): Flow<UIState>
    suspend fun retrieveCard(name: String): Flow<UIState>
}

class MagicRepositoryImpl @Inject constructor(
    private val magicApi: MagicApi
): MagicRepository {

    override suspend fun getSets(): Flow<UIState> =
        flow {
            try {
                val response = magicApi.getSets()
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed network call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun drawBooster(code: String): Flow<UIState> =
        flow {
            try {
                val response = magicApi.drawBooster(code = code)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed network call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun findCard(name: String): Flow<UIState> =
        flow {
            try {
                val response = magicApi.findCard(name = name)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed network call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun retrieveCard(name: String): Flow<UIState> =
        flow {
            try {
                val response = magicApi.findCard(name = name)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed network call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }
}