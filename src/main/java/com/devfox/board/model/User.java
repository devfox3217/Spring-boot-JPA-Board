package com.devfox.board.model;

import com.devfox.board.util.TokenUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "B_USER")
@Data
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "accountnonexpired")
    private boolean accountNonExpired;

    @Column(name = "accountnonlocked")
    private boolean accountNonLocked;

    @Column(name = "credentialsnonexpired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "followboard")
    private String followBoard;

    @Column(name = "blockedboard")
    private String blockedBoard;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "profile")
    private String profile;

    @Column(name = "point")
    private int point;

    @Column(name = "level")
    private int level;

    @Column(name = "regdate")
    private Date regdate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        TokenUtil tokenUtil = new TokenUtil();
        List<String> tokenizedRole = tokenUtil.getTokenArray(roles);

        for (String s : tokenizedRole) {
            authorities.add(new SimpleGrantedAuthority(s));
        }

        return authorities;
    }

}
