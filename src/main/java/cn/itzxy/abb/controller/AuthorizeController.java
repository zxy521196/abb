package cn.itzxy.abb.controller;

import cn.itzxy.abb.dto.AccessTokenDto;
import cn.itzxy.abb.dto.GithubUser;
import cn.itzxy.abb.mapper.UserMapper;
import cn.itzxy.abb.model.Publish;
import cn.itzxy.abb.model.User;
import cn.itzxy.abb.provider.GithubProvider;
import cn.itzxy.abb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;
    @GetMapping(value="/callback")
    public String callback(@RequestParam(value="code") String code,
                           @RequestParam(value="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        if(githubUser!=null){
            request.getSession().setAttribute("githubuser",githubUser);
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatar_url());
            user.setBio(githubUser.getBio());
            user.setAccountId(githubUser.getId());
            userService.createUser(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/index";
        }else{
            return "redirect:/index";
        }
    }
}
