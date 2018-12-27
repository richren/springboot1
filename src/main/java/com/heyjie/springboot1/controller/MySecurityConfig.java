package com.heyjie.springboot1.controller;

import com.heyjie.springboot1.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用Action、Service、Repository等注入方法上标注@PreAuthorize的支持
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //https://blog.csdn.net/dream_an/article/details/79381459
        //密码不加密，正式项目中应该用BCryptPasswordEncoder等
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http://blog.csdn.net/u012702547/article/details/54319508
        //authenticated是针对所有已登录用户，permitAll()是所有访问者
        http.authorizeRequests()
            //注意antMatchers的顺序，是从上往下匹配的

            //Authority是权限项，Role是角色
            //不要.antMatchers("/**").hasRole("Teacher")。否则请求/favicon.ico、js等都会造成无权限的问题，因此要把需要权限的防到单独的目录路径下
            .antMatchers("/service/**").authenticated()///service/下的要进行权限验证，因此要把MainController等业务系统的Controller配置为@RequestMapping("/service/main") 等
            .anyRequest().permitAll()
            .and()
            //如果不指定failureUrl("/login/error")，则默认路径是loginPage?error
            //failureUrl是登陆失败（用户名或者密码错误）的页面
            //defaultSuccessUrl是在没有导向登录页面之后，登录成功后转向的页面，如果从别的页面重定向过来的，则登录成功后会跳转回去
            .formLogin()
            .loginPage("/login/index")
            .failureUrl("/login/error")
            .defaultSuccessUrl("/login/home")
            .and()
            .logout()
            .logoutUrl("/login/logout")
            .logoutSuccessUrl("/login/index")
            .invalidateHttpSession(true)
            .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**");//对于css、js等不处理
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails user = userRepository.findByUsername(username);
                if(user==null)
                {
                    throw new UsernameNotFoundException("username not found:"+username);
                }
                return user;
            }
        });
    }
}

