package org.sopt.teamdateroad.data.mapper.todata

import org.sopt.teamdateroad.domain.model.EditProfile
import org.sopt.teamdateroad.domain.model.Profile

fun Profile.toEditProfile(): EditProfile = EditProfile(
    name = this.name,
    tags = this.tag,
    image = this.imageUrl
)
