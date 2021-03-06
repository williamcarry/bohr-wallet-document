/**
 * Copyright (c) 2019 The BoHr Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.bohr.util.sign.crypto;

import org.bohr.util.sign.exception.CryptoException;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

/**
 * Hash generator
 */
public class Hash {
    public static final String HASH_ALGORITHM = "BLAKE2B-256";

    public static final int HASH_LEN = 32;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private Hash() {
    }

    /**
     * Generate the 256-bit hash.
     *
     * @param input
     * @return
     */
    public static byte[] h256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            return digest.digest(input);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * Merge two byte arrays and compute the 256-bit hash.
     *
     * @param one
     * @param two
     * @return
     */
    public static byte[] h256(byte[] one, byte[] two) {
        byte[] all = new byte[one.length + two.length];
        System.arraycopy(one, 0, all, 0, one.length);
        System.arraycopy(two, 0, all, one.length, two.length);

        return Hash.h256(all);
    }

    /**
     * Generate the 160-bit hash, using h256 and RIPEMD.
     *
     * @param input
     * @return
     */
    public static byte[] h160(byte[] input) {
        try {
            byte[] h256 = h256(input);

            RIPEMD160Digest digest = new RIPEMD160Digest();
            digest.update(h256, 0, h256.length);
            byte[] out = new byte[20];
            digest.doFinal(out, 0);
            return out;
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

}
