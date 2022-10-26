package com.example.dogedex.doglist

import com.example.dogedex.model.Dog
import com.example.dogedex.api.ApiResponseStatus
import com.example.dogedex.api.DogsApi.retrofitService
import com.example.dogedex.api.dto.DogDTOMapper
import com.example.dogedex.api.makeNetworkCall

class DogRepository {

    suspend fun fetchDogs(): ApiResponseStatus<List<Dog>> {
        return makeNetworkCall {
            val dogListApiResponse = retrofitService.fetchAllDogs()
            val dogDTOList = dogListApiResponse.data.dogs
            val dogDTOMapper = DogDTOMapper()
            dogDTOMapper.fromDogDTOListToDogDomainList(dogDTOList)
        }
    }

}