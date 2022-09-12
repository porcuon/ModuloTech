package com.porcuon.modulotech.data.mapper

import com.porcuon.modulotech.data.entity.AddressEntity
import com.porcuon.modulotech.domain.model.Address

const val NO_POSTAL_CODE = -1

class AddressMapper {

    fun map(addressEntity: AddressEntity?) = Address(
        city = addressEntity?.city.orEmpty(),
        postalCode = addressEntity?.postalCode ?: NO_POSTAL_CODE,
        street = addressEntity?.street.orEmpty(),
        streetCode = addressEntity?.streetCode.orEmpty(),
        country = addressEntity?.country.orEmpty()
    )

    fun map(address: Address) = AddressEntity(
        city = address.city,
        postalCode = address.postalCode,
        street = address.street,
        streetCode = address.streetCode,
        country = address.country
    )
}