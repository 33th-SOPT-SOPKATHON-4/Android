package com.sopt.soptkathon.android.bckk.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.sopt.soptkathon.android.bckk.BuildConfig
import java.security.GeneralSecurityException
import java.security.KeyStore

object SharedPreferenceContainer {
    private const val SP_USER_ID = "SP_USER_ID"

    private const val DEBUG_APP_PREFERENCES_NAME = "do_euijin_debug"
    private const val APP_PREFERENCES_NAME = "do_euijin"

    lateinit var sharedPreferencesInstance: SharedPreferences

    @Synchronized
    fun getAppPreferences(context: Context): SharedPreferences {
        if (!SharedPreferenceContainer::sharedPreferencesInstance.isInitialized) {
            sharedPreferencesInstance = if (BuildConfig.DEBUG) {
                context.getSharedPreferences(DEBUG_APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
            } else {
                try {
                    createEncryptedSharedPreferences(APP_PREFERENCES_NAME, context)
                } catch (e: GeneralSecurityException) {
                    deleteMasterKeyEntry()
                    deleteExistingPref(APP_PREFERENCES_NAME, context)
                    createEncryptedSharedPreferences(APP_PREFERENCES_NAME, context)
                }
            }
        }
        return sharedPreferencesInstance
    }

    private fun deleteExistingPref(fileName: String, context: Context) {
        context.deleteSharedPreferences(fileName)
    }

    private fun deleteMasterKeyEntry() {
        KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
            deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        }
    }

    private fun createEncryptedSharedPreferences(
        fileName: String,
        context: Context,
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            fileName,
            MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    fun setLocalUserId(value: String) {
        sharedPreferences.edit().putString(SP_USER_ID, value).apply()
    }

    fun getLocalUserId(): String? = sharedPreferences.getString(SP_USER_ID, "")
}

val sharedPreferences: SharedPreferences
    get() = SharedPreferenceContainer.sharedPreferencesInstance
