package com.knu.ntttt_server.user.security;

import com.knu.ntttt_server.core.exception.KnuException;
import com.knu.ntttt_server.core.response.ResultCode;
import com.knu.ntttt_server.user.model.User;
import com.knu.ntttt_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 현재는 nickname을 사용하고있다
     *
     * @param nickname the nickname identifying the user whose data is required.
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String nickname){
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new KnuException(ResultCode.BAD_REQUEST, "닉네임에 해당하는 유저를 찾을 수 없습니다."));
        return new org.springframework.security.core.userdetails.User(
                user.getNickname(), user.getPassword(), user.getGrantedAuthority());
    }
}
