package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.user.dto.LoginDto;
import com.knu.ntttt_server.user.dto.UserDto;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.repository.UserRepository;
import com.knu.ntttt_server.user.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * 회원 가입 기능입니다.
     */
    @Transactional
    public void join(UserDto dto) {
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "아이디가 중복입니다.");
        }
        if (userRepository.existsByWalletAddr(dto.getNickname())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "지갑 주소가 중복입니다.");
        }
        User user = dto.toEntityWithEncode(passwordEncoder);
        userRepository.save(user);
    }

    /**
     * 로그인 기능입니다.
     * todo: JWT 토큰 연결 (현재는 nickname 반환)
     */
    public String login(LoginDto dto) {
        User user = userRepository.findByNickname(dto.getNickname())
                .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "로그인 정보를 잘못 입력하셨습니다."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "로그인 정보를 잘못 입력하셨습니다.");
        }
        return jwtService.createToken(dto.getNickname());
    }

    /**
     * 유저 닉네임으로 지갑 주소를 찾는 기능입니다.
     */
    public String getWalletAddress(String nickname) {
        User user = userRepository.findByNickname(nickname)
            .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "해당 닉네임의 유저를 찾을 수 없습니다"));
        return user.getWalletAddr();
    }

    public User getUserDetail(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "해당 닉네임의 유저를 찾을 수 없습니다"));
        return user;
    }
}
