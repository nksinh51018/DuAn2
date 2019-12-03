package com.nk.service;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nk.dao.impl.TaiKhoanImpl;
import com.nk.entity.TaiKhoan;
 
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private TaiKhoanService taiKhoanService;
    
    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan account = taiKhoanService.findTaiKhoanByTenTaiKhoan(username);
        System.out.println("Account= " + account.getTenDangNhap()+" - "+account.getMatKhau()+" - "+account.getVaiTro());
 
        String role = account.getVaiTro();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
 
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
 
        grantList.add(authority);
 
        boolean enabled = account.isEnable();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = (UserDetails) new User(account.getTenDangNhap(), //
                account.getMatKhau(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
 
        return userDetails;
    }
 
}