package com.jetpackcompose.compose_path_android.presentation.encryption

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyPair
import java.security.KeyPairGenerator
import javax.crypto.Cipher

class EncryptionManager(context: Context) {

    val rsaKey by lazy { generateRSAKeyPair() }

    private fun generateRSAKeyPair(): KeyPair {
        val keyPairGenerator =
            KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        keyPairGenerator.initialize(
            KeyGenParameterSpec.Builder(
                "app_rsa_key",
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            ).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .setKeySize(2048)
                .build()
        )
        return keyPairGenerator.generateKeyPair()
    }

    fun encryptData(data: String) : String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, rsaKey.public)
        val value = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(value, Base64.DEFAULT)
    }

    fun decryptData(data: String): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, rsaKey.private)
        val value = Base64.decode(data.toByteArray(), Base64.DEFAULT)
        return String(cipher.doFinal(value))
    }


}