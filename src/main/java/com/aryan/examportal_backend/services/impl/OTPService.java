package com.aryan.examportal_backend.services.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OTPService {
    private static final Integer EXPIRE_MINS = 10;
    private LoadingCache<String, Integer> otpCache;
    
    public OTPService(){
        super();
        otpCache = CacheBuilder.newBuilder().
        expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
        .build(new CacheLoader<String, Integer>() {
       

        @Override
        public Integer load(String key) throws Exception {
            
            return 0;
        }

       
     });
   }
    public int generateOTP(String key){
    	System.out.println("##Key="+key);
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
         }
        
         public int getOtp(String key){ 
        	 System.out.println("Key="+key);
        try{
         return  otpCache.get(key); 
        }catch (Exception e){
         return 0; 
        }
         }
        
        public void clearOTP(String key){ 
         otpCache.invalidate(key);
         }
}
