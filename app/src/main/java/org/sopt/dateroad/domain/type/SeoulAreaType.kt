package org.sopt.dateroad.domain.type

import org.sopt.dateroad.domain.util.Seoul

enum class SeoulAreaType(
    val title: String
) {
    SEOUL_ENTIRE(
        title = Seoul.SEOUL_ENTIRE
    ),
    GANGNAM_SEOCHO(
        title = Seoul.GANGNAM_SEOCHO
    ),
    JAMSIL_SONGPA_GANGDONG(
        title = Seoul.JAMSIL_SONGPA_GANGDONG
    ),
    KONDAE_SUNGSOO_WANGSIMNI(
        title = Seoul.KONDAE_SUNGSOO_WANGSIMNI
    ),
    JONGNO_JUNGRO(
        title = Seoul.JONGNO_JUNGRO
    ),
    HONGDAE_HAPJEONG_MAPO(
        title = Seoul.HONGDAE_HAPJEONG_MAPO
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
    SEONGBUK_NOWON_JUNGBANG(
        title = Seoul.SEONGBUK_NOWON_JUNGBANG
    ),
    GURO_GWANAK_DONGJAK(
        title = Seoul.GURO_GWANAK_DONGJAK
    );

    companion object {
        fun String.toSeoulAreaTitle(): String = entries.find { it.name == this }?.title ?: ""
        fun String.toSeoulAreaType() = entries.find { it.name == this }?.name
    }
}
