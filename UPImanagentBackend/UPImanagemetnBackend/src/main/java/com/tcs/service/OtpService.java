package com.tcs.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private static class OtpData {
        String otp;
        long expiry;
    }

    private final Map<String, OtpData> store = new ConcurrentHashMap<>();

    public void generateOtp(String username) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        OtpData data = new OtpData();
        data.otp = otp;
        data.expiry = System.currentTimeMillis() + (2 * 60 * 1000); // 2 min

        store.put(username, data);

        // 🔥 MOCK SMS
        System.out.println("OTP for " + username + " : " + otp);
    }

    public boolean validateOtp(String key, String otp) {

        System.out.println("🔍 OTP VALIDATE KEY: " + key);
        System.out.println("🔍 OTP ENTERED: " + otp);
        System.out.println("🔍 STORE KEYS: " + store.keySet());

        OtpData data = store.get(key);

        if (data == null) {
            System.out.println("❌ NO OTP FOUND FOR KEY");
            return false;
        }

        if (System.currentTimeMillis() > data.expiry) {
            System.out.println("❌ OTP EXPIRED");
            return false;
        }

        boolean match = data.otp.equals(otp);
        System.out.println("✅ OTP MATCH: " + match);

        if (match) store.remove(key);

        return match;
    }

}
