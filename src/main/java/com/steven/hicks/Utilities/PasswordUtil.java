package com.steven.hicks.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import org.apache.catalina.realm.MessageDigestCredentialHandler;

public class PasswordUtil
{

    private static int cryptoIterations = 200_000;

    public static String digestPassword(String clearTextPassword)
    {
        try
        {
            MessageDigestCredentialHandler credentialHandler = new MessageDigestCredentialHandler();
            credentialHandler.setAlgorithm("SHA-256");
            credentialHandler.setIterations(cryptoIterations);
            credentialHandler.setSaltLength(32);

            String digestPassword = credentialHandler.mutate(clearTextPassword);

            return digestPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e);
        }

        return null;
    }

    public static String hashAndSaltPassword(String clearTextPassword, String salt) throws NoSuchAlgorithmException
    {
        return hashPassword(clearTextPassword + salt);
    }

    public static String hashPassword(String clearTextPassword) throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("MD5");

        digest.update(clearTextPassword.getBytes());
        byte[] digestArray = digest.digest();

        StringBuilder stringBuilder = new StringBuilder(digestArray.length);
        for (byte b : digestArray)
        {
//            int v = b & 0xff;
//            if (v < 16)
//            {
//                stringBuilder.append('0');
//            }
//            stringBuilder.append(Integer.toHexString(v));
            stringBuilder.append(String.format("%02x", b & 0xff));
        }

        return stringBuilder.toString();
    }

    public static String getSalt()
    {
        Random r = new SecureRandom();
        byte[] bytesOfSalt = new byte[32];
        r.nextBytes(bytesOfSalt);

        return Base64.getEncoder().encodeToString(bytesOfSalt);
    }
}
