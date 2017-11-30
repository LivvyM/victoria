package com.kingnet.common.fingerprintutil;

import android.annotation.TargetApi;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * Created by livvy on 17-2-6.
 */
@TargetApi(Build.VERSION_CODES.M)
public class FingerPrintUtil extends FingerprintManager.AuthenticationCallback {

    private static final String KEY_NAME = "kingnet_key";

    private Cipher mCipher;
    private KeyStore mKeyStore;
    private KeyGenerator mKeyGenerator;
    private final FingerprintManager mFingerprintManager;
    private CancellationSignal mCancellationSignal;

    private final Callback mCallback;

    private boolean mSelfCancelled;


    public FingerPrintUtil(FingerprintManager mFingerprintManager,Callback callback){
        this.mFingerprintManager = mFingerprintManager;
        this.mCallback = callback;
    }

    /**
     * Starts listening to {@link FingerprintManager}
     */
    public void startListening() throws SecurityException {
        if (initCipher()) {
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(mCipher);
            if (!isFingerprintAuthAvailable()) {
                mCallback.onError("");
                return;
            }
            mCancellationSignal = new CancellationSignal();
            mSelfCancelled = false;
            mFingerprintManager.authenticate(cryptoObject, mCancellationSignal, 0 /* flags */, this, null);
        }
    }

    /**
     * Stops listening to {@link FingerprintManager}
     */
    public void stopListening() {
        if (mCancellationSignal != null) {
            mSelfCancelled = true;
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
    }

    /**
     * Called by {@link FingerprintManager} if the authentication threw an error.
     */
    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Log.e("=====","onAuthenticationError");
        Log.e("=====","errMsgId" + errMsgId);
        if (!mSelfCancelled) {
            mCallback.onError(errString.toString());
        }
    }



    /**
     * Called by {@link FingerprintManager} if the user asked for help.
     */
    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Log.e("=====","onAuthenticationHelp:" + helpString.toString());
        Log.e("=====","helpMsgId:" + helpMsgId);
        mCallback.onError(helpString.toString());
    }

    /**
     * Called by {@link FingerprintManager} if the authentication failed (bad finger etc...).
     */
    @Override
    public void onAuthenticationFailed() {
        Log.e("=====","onAuthenticationFailed");
        mCallback.onError("");
    }

    /**
     * Called by {@link FingerprintManager} if the authentication succeeded.
     */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        mCallback.onAuthenticated();
    }



    private boolean initCipher() {
        try {
            if (mKeyStore == null) {
                mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            }
            createKey();
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME, null);
            mCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            mCipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (NoSuchPaddingException | KeyStoreException | CertificateException | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            return false;
        }
    }

    public void createKey() {
        // The enrolling flow for fingerprint. This is where you ask the user to set up fingerprint
        // for your flow. Use of keys is necessary if you need to know if the set of
        // enrolled fingerprints has changed.
        try {
            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            mKeyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            mKeyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT
                    | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    // Require the user to authenticate with a fingerprint to authorize every use of the key
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            mKeyGenerator.generateKey();
        } catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isFingerprintAuthAvailable() throws SecurityException {
        return mFingerprintManager.isHardwareDetected()
                && mFingerprintManager.hasEnrolledFingerprints();
    }

    public interface Callback {
        void onAuthenticated();

        void onError(String msg);
    }
}
