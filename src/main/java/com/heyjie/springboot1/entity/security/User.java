package com.heyjie.springboot1.entity.security;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity(name="T_Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch=FetchType.EAGER)//http://blog.csdn.net/u010504064/article/details/47832721
    @JoinTable(name="T_User_Role", joinColumns= {@JoinColumn(name="userid")}
        ,inverseJoinColumns= {@JoinColumn(name="roleid")})//可以写成Userid，不能写成UserId，否则映射到User_Id字段，建议全小写
    private List<Role> roles= new LinkedList<>();//不要用Set


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> list = new HashSet<>();
        for(Role role : roles)
        {
            for(Authority a : role.getAuthorities())
            {
                //SimpleGrantedAuthority代表权限项
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(a.getName());
                list.add(authority);
            }
        }
        return list;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

