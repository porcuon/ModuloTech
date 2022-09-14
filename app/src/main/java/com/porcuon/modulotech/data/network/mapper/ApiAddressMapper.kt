package com.porcuon.modulotech.data.network.mapper

import com.porcuon.modulotech.data.network.api.ApiAddress
import com.porcuon.modulotech.domain.model.Address

class ApiAddressMapper {

    fun map(apiAddress: ApiAddress?) = Address(
        city = apiAddress?.city.orEmpty(),
        postalCode = apiAddress?.postalCode ?: -1,
        street = apiAddress?.street.orEmpty(),
        streetCode = apiAddress?.streetCode.orEmpty(),
        country = apiAddress?.country.orEmpty()
    )
}