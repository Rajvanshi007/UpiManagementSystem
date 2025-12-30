
package com.tcs.model;

import java.time.LocalDateTime;

public class OtpData {

    private String otp;
    private LocalDateTime expiry;

    public OtpData(String otp, LocalDateTime expiry) {
        this.otp = otp;
        this.expiry = expiry;
    }

    public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public String getOtp() {
        return otp;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }
}