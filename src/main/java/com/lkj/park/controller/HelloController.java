package com.lkj.park.controller;


import com.lkj.park.dto.OAuthTokenDTO;
import com.lkj.park.dto.GitHubUserDTO;
import com.lkj.park.provider.OAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {
    @Autowired
    private OAuthToken OAuthToken;

    @GetMapping("/hello")
    public String hello() {
        return "index";
    }

    @GetMapping("/callback")
    public String getOAuth(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        OAuthTokenDTO oAuthTokenDTO = new OAuthTokenDTO();
        oAuthTokenDTO.setClient_id("b18e01a3307b9205857d");
        oAuthTokenDTO.setClient_secret("488604a949cd8ebd182ec2d386be3838f7d984da");
        oAuthTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        oAuthTokenDTO.setCode(code);
        oAuthTokenDTO.setState(state);
        String accessToken = OAuthToken.getToken(oAuthTokenDTO);
        GitHubUserDTO gitHubUserDTO = OAuthToken.getUser(accessToken);
        return "redirect:/hello";
    }
}
