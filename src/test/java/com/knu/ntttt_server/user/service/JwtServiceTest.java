package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.user.security.JwtService;
import org.junit.jupiter.api.Test;

class JwtServiceTest {

    @Test

    void createTokenTest() {
        JwtService jwtService = new JwtService("ALKelfgkasldkgaosekokseodkgsldmgwke");
        String token = jwtService.createToken("nickName");
        System.out.println(token);
    }

    @Test
    void parseTokenTest() {
        JwtService jwtService = new JwtService("ALKelfgkasldkgaosekokseodkgsldmgwke");
        String token = jwtService.createToken("nickName");
        System.out.println(jwtService.getSubject(token));
    }
}
