package com.porcuon.modulotech.data.network.mapper

import com.porcuon.modulotech.data.network.api.ApiUser
import com.porcuon.modulotech.domain.model.User
import java.util.Date

private const val DEFAULT_DATE_MILLIS = 0L

class ApiUserMapper(
    private val apiAddressMapper: ApiAddressMapper
) {

    fun map(apiUser: ApiUser?) = User(
        id = apiUser?.id ?: -1,
        firstName = apiUser?.firstName.orEmpty(),
        lastName = apiUser?.lastName.orEmpty(),
        address = apiAddressMapper.map(apiUser?.address),
        dateOfBirth = Date(apiUser?.dateOfBirth ?: DEFAULT_DATE_MILLIS)
    )
}