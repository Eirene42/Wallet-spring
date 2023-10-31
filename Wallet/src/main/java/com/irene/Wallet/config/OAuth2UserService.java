package com.irene.Wallet.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OAuth2UserService implements GrantedAuthoritiesMapper {

    private final SimpleAuthorityMapper delegate=new SimpleAuthorityMapper();
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        authorities.forEach(System.out::println);
        return delegate.mapAuthorities(authorities);
    }
}
