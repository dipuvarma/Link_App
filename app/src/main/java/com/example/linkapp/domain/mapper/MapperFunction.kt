package com.example.linkapp.domain.mapper

import com.example.linkapp.data.model.UserDetails
import com.example.linkapp.domain.model.UserDetailsUi


fun UserDetails.toUserDetailsUi(): UserDetailsUi {
    return UserDetailsUi(
        id = id,
        name = name,
        email = email,
        password = password
    )
}