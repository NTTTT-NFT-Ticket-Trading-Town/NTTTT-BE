package com.knu.ntttt_server.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "USER_TABLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    //Todo: 프로필, 배경사진
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String walletAddr;
    @NotNull
    private String nickname;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    /**
     * 현재 권한 체계는 사용되지 않으므로 칼럼에서 제외하고
     * Spring Security 사용을 위해 임의로 채워넣습니다.
     */
    @NotNull
    @Transient
    private final Set<Authority> authorities = Set.of(Authority.MEMBER);

    @Builder
    public User(Long id, String walletAddr, String nickname, String password, String phoneNumber) {
        this.id = id;
        this.walletAddr = walletAddr;
        this.nickname = nickname;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /**
     * spring security의 UserDetails에 사용되는 권한으로 만들기
     */
    public Set<GrantedAuthority> getGrantedAuthority() {
        return getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toSet());
    }
}
