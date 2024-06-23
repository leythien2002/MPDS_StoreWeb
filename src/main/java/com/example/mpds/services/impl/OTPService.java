package com.example.mpds.services.impl;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> otpExpiry = new ConcurrentHashMap<>();
    private static final long OTP_EXPIRY_TIME = TimeUnit.MINUTES.toMillis(5);

    public void storeOTP(String key, String otp) {
        otpStorage.put(key, otp);
        otpExpiry.put(key, System.currentTimeMillis() + OTP_EXPIRY_TIME);
    }

    public String getOTP(String key) {
        if (System.currentTimeMillis() > otpExpiry.getOrDefault(key, 0L)) {
            otpStorage.remove(key);
            otpExpiry.remove(key);
            return null;
        }
        return otpStorage.get(key);
    }
    public void removeOTP(String key) {
        otpStorage.remove(key);
        otpExpiry.remove(key);
    }
}
