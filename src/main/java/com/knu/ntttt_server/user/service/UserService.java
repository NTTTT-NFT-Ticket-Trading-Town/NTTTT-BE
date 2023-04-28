package com.knu.ntttt_server.user.service;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.user.dto.UserDto;
import com.knu.ntttt_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입 기능입니다.
     */
    @Transactional
    public void join(UserDto dto) {
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new KnuException(ResultCode.BAD_REQUEST, "아이디가 중복입니다.");
        }
        userRepository.save(dto.toEntity());
    }
}
