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
    KONDAE_SEONGSU_SEONGDONG(
        title = Seoul.KONDAE_SEONGSU_SEONGDONG
    ),
    GWANGIN_JUNGBANG(
        title = Seoul.GWANGIN_JUNGBANG
    ),
    JONGNO_JUNGRO(
        title = Seoul.JONGNO_JUNGRO
    ),
    EUNPYEONG_SEODAEMUN(
      title = Seoul.EUNPYEONG_SEODAEMUN
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
    YANGCHEON_GANGSEO_GURO(
        title = Seoul.YANGCHEON_GANGSEO_GURO
    ),
    DONGDAEMUN_SEONGBUK(
        title = Seoul.DONGDAEMUN_SEONGBUK
    ),
    NOWON_DOBONG_GANGBUK(
        title = Seoul.NOWON_DOBONG_GANGBUK
    ),
    GWANAK_DONGJAK_GEUMCHEON(
        title = Seoul.GWANAK_DONGJAK_GEUMCHEON
    );

    companion object {
        fun String.toSeoulAreaTitle(): String = entries.find { it.name == this }?.title ?: ""
        fun String.toSeoulAreaType(): SeoulAreaType? = entries.find { it.name == this }
        fun String.fromTitleToSeoulAreaType(): SeoulAreaType? = entries.find { it.title == this }
    }
}
