package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Seoul

enum class SeoulAreaType(
    val title: String
) {
    WHOLE(
        title = Seoul.WHOLE
    ),
    GANGNAM_SEOCHO(
        title = Seoul.GANGNAM_SEOCHO
    ),
    JAMSIL_SONGPA_GANGDONG(
        title = Seoul.JAMSIL_SONGPA_GANGDONG
    ),
    KONKUK_UNIVERSITY_SEOUNGSU_WANGSIMNI(
        title = Seoul.KONKUK_UNIVERSITY_SEOUNGSU_WANGSIMNI
    ),
    JONGNO_JUNGGU(
        title = Seoul.JONGNO_JUNGGU
    ),
    HONGIK_UNIVERSITY_HAPJEONG_MAPO(
        title = Seoul.HONGIK_UNIVERSITY_HAPJEONG_MAPO
    ),
    YEONGDEUNGPO_YEOUIDO(
        title = Seoul.YEONGDEUNGPO_YEOUIDO
    ),
    YONGSAN_ITAEWON_HANNAM(
        title = Seoul.YONGSAN_ITAEWON_HANNAM
    ),
    YANGCHEON_GANGSEO(
        title = Seoul.YANGCHEON_GANGSEO
    ),
    SEONGBUK_NOWON_JUNGNANG(
        title = Seoul.SEONGBUK_NOWON_JUNGNANG
    ),
    GURO_GWANAK_DONGJAK(
        title = Seoul.GURO_GWANAK_DONGJAK
    )
}
