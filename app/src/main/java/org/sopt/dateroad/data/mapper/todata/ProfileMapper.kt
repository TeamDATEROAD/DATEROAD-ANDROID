package org.sopt.dateroad.data.mapper.todata

import org.sopt.dateroad.domain.model.EditProfile
import org.sopt.dateroad.domain.model.Profile

fun Profile.toEditProfile(): EditProfile = EditProfile(
    name = this.name,
    tags = this.tag,
    image = this.imageUrl,
    isDefaultImage = true
)
