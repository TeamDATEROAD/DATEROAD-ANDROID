package org.sopt.dateroad.presentation.util.amplitude

import android.content.Context
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import org.sopt.dateroad.BuildConfig

object AmplitudeUtils {
    private lateinit var amplitude: Amplitude

    fun initAmplitude(context: Context) {
        amplitude = Amplitude(
            Configuration(
                apiKey = BuildConfig.AMPLITUDE_API_KEY,
                context = context
            )
        )
    }

    fun trackEvent(eventName: String) {
        amplitude.track(eventType = eventName)
    }

    fun <T> trackEventWithProperty(eventName: String, propertyName: String, propertyValue: T) {
        amplitude.track(
            eventType = eventName,
            eventProperties = mapOf(propertyName to propertyValue)
        )
    }

    fun trackEventWithProperties(eventName: String, properties: Map<String, Any>) {
        amplitude.track(eventType = eventName, eventProperties = properties)
    }
}
