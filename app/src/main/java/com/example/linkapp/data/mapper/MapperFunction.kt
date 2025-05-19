package com.example.linkapp.data.mapper

import com.example.linkapp.data.model.UserDetails
import com.example.linkapp.domain.model.UserDetailsUi
import java.util.UUID


fun UserDetailsUi.toUserDetails(): UserDetails {
    return UserDetails(
        id = id ?: UUID.randomUUID().toString(),
        name = name,
        email = email,
        password = password
    )
}