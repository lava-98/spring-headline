package com.lava.test;

import com.lava.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtHelper jwtHelper;
    @Test
    public void test01(){
        String token = jwtHelper.createToken(1L);
        System.out.println(token);
        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println(expiration);
        int i = jwtHelper.getUserId(token).intValue();
        System.out.println(i);


    }
}
