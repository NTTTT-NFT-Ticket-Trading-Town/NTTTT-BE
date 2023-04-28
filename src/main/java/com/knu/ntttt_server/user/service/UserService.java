package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.user.dto.LoginDto;
import com.knu.ntttt_server.user.dto.UserDto;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입 기능입니다.
     */
    @Transactional
    public void join(UserDto dto) {
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "아이디가 중복입니다.");
        }
        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toEntity());
    }

    /**
     * 로그인 가입 기능입니다.
     * todo: JWT 토큰 연결 (현재는 nickname 반환)
     */
    public String login(LoginDto dto) {
        User user = userRepository.findByNickname(dto.getNickname())
                .orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "로그인 정보를 잘못 입력하셨습니다."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "로그인 정보를 잘못 입력하셨습니다.");
        }

        return dto.getNickname();
    }
}