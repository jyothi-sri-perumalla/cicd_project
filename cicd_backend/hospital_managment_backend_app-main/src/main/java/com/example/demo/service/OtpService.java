package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    
    private final String FAST2SMS_API_KEY = "jH1yRLkEFuDsl5i63OXGaNBe8pdmIfVvwZhrQo2bqxcMSC9t7UfPcKpz2RWuO4LDoBE1Vgn6GwhbkaCm";
    private final String FAST2SMS_URL = "https://www.fast2sms.com/dev/bulkV2";
    
    // Store OTPs temporarily (in production, use Redis or database)
    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Map<String, Long> otpExpiry = new ConcurrentHashMap<>();
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
    
    public boolean sendOtp(String mobileNumber, String otp) {
        System.out.println("🔧 === TRYING QUICK SMS FIRST ===");
        boolean quickSmsSuccess = sendOtpQuickSms(mobileNumber, otp);
        
        if (!quickSmsSuccess) {
            System.out.println("⚠️ Quick SMS failed, trying alternative method...");
            return sendOtpAlternative(mobileNumber, otp);
        }
        
        return quickSmsSuccess;
    }
    
    private boolean sendOtpQuickSms(String mobileNumber, String otp) {
        try {
            // Remove +91 prefix if present and ensure it starts with 91
            String formattedNumber = mobileNumber.replaceAll("^\\+", "");
            if (!formattedNumber.startsWith("91")) {
                formattedNumber = "91" + formattedNumber;
            }
            
            // Remove 91 prefix for the API (Fast2SMS expects just the 10-digit number)
            String cleanNumber = formattedNumber.startsWith("91") ? formattedNumber.substring(2) : formattedNumber;
            
            System.out.println("🔧 === OTP SERVICE DEBUG ===");
            System.out.println("📱 Original mobile: " + mobileNumber);
            System.out.println("📱 Formatted mobile: " + formattedNumber);
            System.out.println("📱 Clean mobile for API: " + cleanNumber);
            System.out.println("🔢 OTP to send: " + otp);
            System.out.println("🔑 API Key (first 20 chars): " + FAST2SMS_API_KEY.substring(0, Math.min(20, FAST2SMS_API_KEY.length())) + "...");
            System.out.println("🌐 API URL: " + FAST2SMS_URL);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Cache-Control", "no-cache");
            
            // Use Quick SMS API format according to Fast2SMS documentation
            String messageText = "Your OTP for Hospital Login is " + otp + ". Valid for 5 minutes. Do not share with anyone.";
            
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("authorization", FAST2SMS_API_KEY);
            params.add("message", messageText);
            params.add("language", "english");
            params.add("route", "q"); // Quick SMS route
            params.add("numbers", cleanNumber);
            
            System.out.println("📝 === REQUEST PARAMETERS ===");
            System.out.println("authorization: " + FAST2SMS_API_KEY.substring(0, 10) + "...");
            System.out.println("message: " + messageText);
            System.out.println("language: english");
            System.out.println("route: q");
            System.out.println("numbers: " + cleanNumber);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            System.out.println("� === SENDING REQUEST ===");
            System.out.println("Making POST request to: " + FAST2SMS_URL);
            
            ResponseEntity<String> response = restTemplate.postForEntity(FAST2SMS_URL, request, String.class);
            
            System.out.println("� === RESPONSE RECEIVED ===");
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Status: " + response.getStatusCode().value());
            System.out.println("Response Headers: " + response.getHeaders());
            System.out.println("Response Body: " + response.getBody());
            
            // Check if response indicates success
            String responseBody = response.getBody();
            boolean isSuccess = response.getStatusCode() == HttpStatus.OK;
            
            if (responseBody != null) {
                // Parse JSON response to check for errors
                if (responseBody.contains("\"return\":true") || responseBody.contains("success")) {
                    System.out.println("✅ Fast2SMS API reported success!");
                    isSuccess = true;
                } else if (responseBody.contains("error") || responseBody.contains("fail")) {
                    System.out.println("❌ Fast2SMS API reported error in response body");
                    System.out.println("Error details: " + responseBody);
                    isSuccess = false;
                }
            }
            
            // Store OTP with 5 minutes expiry regardless of SMS status (for testing)
            otpStorage.put(mobileNumber, otp);
            otpExpiry.put(mobileNumber, System.currentTimeMillis() + 300000); // 5 minutes
            System.out.println("💾 OTP stored in memory for verification: " + otp);
            
            System.out.println("🔧 === END DEBUG ===");
            
            return isSuccess;
            
        } catch (Exception e) {
            System.err.println("❌ === ERROR IN OTP SERVICE ===");
            System.err.println("Error Type: " + e.getClass().getSimpleName());
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback: Store OTP for testing even if SMS fails
            otpStorage.put(mobileNumber, otp);
            otpExpiry.put(mobileNumber, System.currentTimeMillis() + 300000);
            System.out.println("🔧 Fallback: OTP stored for testing - Mobile: " + mobileNumber + ", OTP: " + otp);
            System.out.println("⚠️ Note: SMS may have failed, but you can use the OTP above for testing");
            
            return true; // Return true for testing purposes
        }
    }
    
    private boolean sendOtpAlternative(String mobileNumber, String otp) {
        try {
            System.out.println("🔧 === TRYING ALTERNATIVE SMS METHOD ===");
            
            // Remove +91 prefix if present and ensure it starts with 91
            String formattedNumber = mobileNumber.replaceAll("^\\+", "");
            if (!formattedNumber.startsWith("91")) {
                formattedNumber = "91" + formattedNumber;
            }
            
            // Remove 91 prefix for the API
            String cleanNumber = formattedNumber.startsWith("91") ? formattedNumber.substring(2) : formattedNumber;
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            // Try different route - using old format as backup
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("authorization", FAST2SMS_API_KEY);
            params.add("variables_values", otp);
            params.add("route", "otp");
            params.add("numbers", cleanNumber);
            params.add("sender_id", "FSTSMS");
            
            System.out.println("📝 === ALTERNATIVE REQUEST ===");
            System.out.println("Using OTP route with variables_values");
            System.out.println("numbers: " + cleanNumber);
            System.out.println("otp: " + otp);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(FAST2SMS_URL, request, String.class);
            
            System.out.println("📡 Alternative Response: " + response.getStatusCode());
            System.out.println("📦 Alternative Body: " + response.getBody());
            
            // Store OTP regardless
            otpStorage.put(mobileNumber, otp);
            otpExpiry.put(mobileNumber, System.currentTimeMillis() + 300000);
            
            return response.getStatusCode() == HttpStatus.OK;
            
        } catch (Exception e) {
            System.err.println("❌ Alternative method also failed: " + e.getMessage());
            
            // Final fallback - just store OTP
            otpStorage.put(mobileNumber, otp);
            otpExpiry.put(mobileNumber, System.currentTimeMillis() + 300000);
            System.out.println("🔧 FINAL FALLBACK: OTP stored - " + otp);
            System.out.println("⚠️ SMS service unavailable, but you can use OTP: " + otp);
            
            return true;
        }
    }
    
    public boolean verifyOtp(String mobileNumber, String enteredOtp) {
        String storedOtp = otpStorage.get(mobileNumber);
        Long expiry = otpExpiry.get(mobileNumber);
        
        if (storedOtp == null || expiry == null) {
            return false;
        }
        
        if (System.currentTimeMillis() > expiry) {
            // OTP expired
            otpStorage.remove(mobileNumber);
            otpExpiry.remove(mobileNumber);
            return false;
        }
        
        boolean isValid = storedOtp.equals(enteredOtp);
        
        if (isValid) {
            // Remove OTP after successful verification
            otpStorage.remove(mobileNumber);
            otpExpiry.remove(mobileNumber);
        }
        
        return isValid;
    }
    
    public void clearOtp(String mobileNumber) {
        otpStorage.remove(mobileNumber);
        otpExpiry.remove(mobileNumber);
    }
    
    public boolean checkWalletBalance() {
        try {
            System.out.println("💰 === CHECKING FAST2SMS WALLET BALANCE ===");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("authorization", FAST2SMS_API_KEY);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            // Use wallet check endpoint
            String walletUrl = "https://www.fast2sms.com/dev/wallet";
            ResponseEntity<String> response = restTemplate.postForEntity(walletUrl, request, String.class);
            
            System.out.println("💰 Wallet Response: " + response.getStatusCode());
            System.out.println("💰 Wallet Data: " + response.getBody());
            
            return response.getStatusCode() == HttpStatus.OK;
            
        } catch (Exception e) {
            System.err.println("❌ Wallet check failed: " + e.getMessage());
            return false;
        }
    }
    
    public String getApiKeyStatus() {
        return "Current API Key: " + FAST2SMS_API_KEY.substring(0, 10) + "...";
    }
    
    public String checkSmsDeliveryStatus() {
        try {
            System.out.println("📊 === CHECKING SMS DELIVERY CAPABILITIES ===");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("authorization", FAST2SMS_API_KEY);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            
            // Check account balance and limits
            String walletUrl = "https://www.fast2sms.com/dev/wallet";
            ResponseEntity<String> walletResponse = restTemplate.postForEntity(walletUrl, request, String.class);
            
            System.out.println("💰 Wallet Status: " + walletResponse.getStatusCode());
            System.out.println("💰 Wallet Details: " + walletResponse.getBody());
            
            // Try to send a test SMS to a dummy number to check API status
            params.clear();
            params.add("authorization", FAST2SMS_API_KEY);
            params.add("message", "API Test - Please ignore");
            params.add("route", "q");
            params.add("numbers", "9999999999"); // Dummy number for testing
            
            HttpEntity<MultiValueMap<String, String>> testRequest = new HttpEntity<>(params, headers);
            ResponseEntity<String> testResponse = restTemplate.postForEntity(FAST2SMS_URL, testRequest, String.class);
            
            System.out.println("🧪 Test SMS Status: " + testResponse.getStatusCode());
            System.out.println("🧪 Test SMS Response: " + testResponse.getBody());
            
            return "Wallet: " + walletResponse.getBody() + " | Test: " + testResponse.getBody();
            
        } catch (Exception e) {
            System.err.println("❌ SMS delivery check failed: " + e.getMessage());
            return "Error checking SMS delivery: " + e.getMessage();
        }
    }
    
    public boolean testSmsWithRealNumber(String mobileNumber) {
        try {
            System.out.println("🧪 === TESTING SMS TO REAL NUMBER ===");
            System.out.println("Target: " + mobileNumber);
            
            // Clean and format number
            String cleanNumber = mobileNumber.replaceAll("^\\+91", "").replaceAll("^91", "");
            if (cleanNumber.length() != 10) {
                System.err.println("❌ Invalid mobile number length: " + cleanNumber.length());
                return false;
            }
            
            String testOtp = "123456";
            String message = "Your test OTP is: " + testOtp + ". This is a test message from Hospital App.";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("authorization", FAST2SMS_API_KEY);
            params.add("message", message);
            params.add("route", "q");
            params.add("numbers", cleanNumber);
            
            System.out.println("📱 Sending to: " + cleanNumber);
            System.out.println("📝 Message: " + message);
            System.out.println("🔑 Using route: q (Quick SMS)");
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(FAST2SMS_URL, request, String.class);
            
            System.out.println("📡 Response Status: " + response.getStatusCode());
            System.out.println("📦 Response Body: " + response.getBody());
            
            // Check for specific error patterns
            String responseBody = response.getBody();
            if (responseBody != null) {
                if (responseBody.contains("Invalid API Key")) {
                    System.err.println("❌ API Key is invalid!");
                } else if (responseBody.contains("insufficient balance")) {
                    System.err.println("❌ Insufficient balance in Fast2SMS account!");
                } else if (responseBody.contains("\"return\":true")) {
                    System.out.println("✅ Test SMS sent successfully!");
                    return true;
                } else if (responseBody.contains("\"return\":false")) {
                    System.err.println("❌ SMS sending failed - check response for details");
                }
            }
            
            return response.getStatusCode() == HttpStatus.OK;
            
        } catch (Exception e) {
            System.err.println("❌ Test SMS failed: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
