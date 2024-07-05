package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class GyeonggiAreaType(
    @StringRes val nameRes: Int
) {
    WHOLE(
        nameRes = R.string.gyeonggi_area_whole
    ),
    SEOUNGNAM(
        nameRes = R.string.gyeonggi_area_seongnam
    ),
    SUWON(
        nameRes = R.string.gyeonggi_area_suwon
    ),
    GOYANG_PAJU(
        nameRes = R.string.gyeonggi_area_goyang_paju
    ),
    KIMPO(
        nameRes = R.string.gyeonggi_area_kimpo
    ),
    YOUNGIN_HWASEONG(
        nameRes = R.string.gyeonggi_area_yongin_hwaseong
    ),
    ANYANG_GWACHEON(
        nameRes = R.string.gyeonggi_area_anyang_gwacheon
    ),
    POCHEON_YANGJU(
        nameRes = R.string.gyeonggi_area_pocheon_yangju
    ),
    NAMYANGJU_UIJEONGBU(
        nameRes = R.string.gyeonggi_area_namyangju_uijeongbu
    ),
    GWANGJU_INCHEON_YEOJU(
        nameRes = R.string.gyeonggi_area_gwangju_icheon_yeoju
    ),
    GAPYEONG_YANGPYEONG(
        nameRes = R.string.gyeonggi_area_gapyeong_yangpyeong
    ),
    GUNPO_UIWANG(
        nameRes = R.string.gyeonggi_area_gunpo_uiwang
    ),
    HANAM_GURI(
        nameRes = R.string.gyeonggi_area_yongin_hanam_guri
    ),
    SIHEUNG_GWANGMYEONG(
        nameRes = R.string.gyeonggi_area_siheung_gwangmyeong
    ),
    BUCHEON_ANSAN(
        nameRes = R.string.gyeonggi_area_bucheon_ansan
    ),
    DONGDUCHEON_YEONCHEON(
        nameRes = R.string.gyeonggi_area_dongducheon_yeoncheon
    ),
    PYEONGTEAK_OSAN_ANSEONG(
        nameRes = R.string.gyeonggi_area_pyeongtaek_osan_anseong
    )
}
