package com.javahand.taipeiattractions.i18n

import android.content.Context
import java.util.Locale

object LanguagePreference {
    private const val PREF_LANGUAGE_PREFERENCE = "LanguagePreference"
    private const val KEY_LANGUAGE_TAG = "LanguageTag"

    fun getLocale(context: Context): Locale {
        val prefLangPref = context.getSharedPreferences(
            PREF_LANGUAGE_PREFERENCE,
            Context.MODE_PRIVATE
        )

        val langTag = prefLangPref.getString(KEY_LANGUAGE_TAG, null )
            ?: Locale.getDefault().toLanguageTag()

        return Locale.forLanguageTag(langTag)
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
    } // fun setLocale( Locale, Context )
} // object DefaultLang