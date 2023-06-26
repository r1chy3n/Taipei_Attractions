package com.javahand.taipeiattractions.i18n

import android.content.Context
import java.util.Locale

object LanguagePreference {
    lateinit var langCode: String

    private const val PREF_LANGUAGE_PREFERENCE = "LanguagePreference"
    private const val KEY_LANGUAGE_TAG = "LanguageTag"

    fun getLocale(context: Context): Locale {
        val prefLanguage = context.getSharedPreferences(
            PREF_LANGUAGE_PREFERENCE,
            Context.MODE_PRIVATE
        )

        val locale = prefLanguage.getString(KEY_LANGUAGE_TAG, null )?.let {
            Locale.forLanguageTag(it)
        } ?: Locale.getDefault()

        langCode = locale.toLanguageTag().lowercase()

        return locale
    } // getLocale(Context)

    fun setLocale(locale: Locale, context: Context) {
        val editLangPref = context.getSharedPreferences(
            PREF_LANGUAGE_PREFERENCE,
            Context.MODE_PRIVATE
        ).edit()

        editLangPref.putString(
            KEY_LANGUAGE_TAG,
            locale.toLanguageTag()
        ).apply()

        langCode = locale.toLanguageTag().lowercase()
    } // fun setLocale( Locale, Context )
} // object DefaultLang