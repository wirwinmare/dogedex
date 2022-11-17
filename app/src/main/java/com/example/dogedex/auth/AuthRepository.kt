package com.example.dogedex.auth

import com.example.dogedex.api.ApiResponseStatus
import com.example.dogedex.api.DogsApi.retrofitService
import com.example.dogedex.api.dto.DogDTOMapper
import com.example.dogedex.api.dto.LogInDTO
import com.example.dogedex.api.dto.SignUpDTO
import com.example.dogedex.api.dto.UserDTOMapper
import com.example.dogedex.api.makeNetworkCall
import com.example.dogedex.model.User
import java.lang.Exception

class AuthRepository {

    suspend fun logIn(
        email: String,
        password: String,
    ): ApiResponseStatus<User> {
        return makeNetworkCall {
            val logInDTO = LogInDTO(
                email,
                password,
            )
            val logInApiResponse = retrofitService.logIn(logInDTO)

            if(!logInApiResponse.isSuccess) {
                throw Exception(logInApiResponse.message)
            }

            val userDTO = logInApiResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }

    suspend fun signUp(
        email: String,
        password: String,
        confirmPassword: String,
    ): ApiResponseStatus<User> {
        return makeNetworkCall {
            val signUpDTO = SignUpDTO(
                email,
                password,
                confirmPassword,
            )
            val signUpApiResponse = retrofitService.signUp(
                signUpDTO
            )

            if(!signUpApiResponse.isSuccess) {
                throw Exception(signUpApiResponse.message)
            }

            val userDTO = signUpApiResponse.data.user
            val userDTOMapper = UserDTOMapper()
            userDTOMapper.fromUserDTOToUserDomain(userDTO)
        }
    }

}




