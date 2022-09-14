package com.porcuon.modulotech.data.database.mapper

import com.porcuon.modulotech.data.database.entity.AddressEntity
import com.porcuon.modulotech.domain.model.Address

class AddressEntityMapper {

    fun map(addressEntity: AddressEntity?) = Address(
        city = addressEntity?.city.orEmpty(),
        postalCode = addressEntity?.postalCode ?: -1,
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