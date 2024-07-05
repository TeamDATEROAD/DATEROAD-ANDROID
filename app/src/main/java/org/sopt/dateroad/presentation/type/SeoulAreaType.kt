package org.sopt.dateroad.presentation.type

import androidx.annotation.StringRes
import org.sopt.dateroad.R

enum class SeoulAreaType(
    @StringRes name: Int
) {
    WHOLE(
        name = R.string.seoul_area_whole
    ),
    GANGNAM_SEOCHO(
        name = R.string.seoul_area_gangnam_seocho
    ),
    JAMSIL_SONGPA_GANGDONG(
        name = R.string.seoul_area_jamsil_songpa_gangdong
    ),
    KONKUK_UNIVERSITY_SEOUNGSU_WANGSIMNI(
        name = R.string.seoul_area_konkuk_university_seongsu_wangsimni
    ),
    JONGNO_JUNGGU(
        name = R.string.seoul_area_jongno_junggu
    ),
    HONGIK_UNIVERSITY_HAPJEONG_MAPO(
        name = R.string.seoul_area_hongik_university_hapjeong_mapo
    ),
    YEONGDEUNGPO_YEOUIDO(
        name = R.string.seoul_area_yeongdeungpo_yeouido
    ),
    YONGSAN_ITAEWON_HANNAM(
        name = R.string.seoul_area_yongsan_itaewon_hannam
    ),
    YANGCHEON_GANGSEO(
        name = R.string.seoul_area_yangcheon_gangseo
    ),
    SEONGBUK_NOWON_JUNGNANG(
        name = R.string.seoul_area_seongbuk_nowon_jungnang
    ),
    GURO_GWANAK_DONGJAK(
        name = R.string.seoul_area_guro_gwanak_dongjak
    )
}