package com.auth0.samples.authapi.springbootauthupdated;

import com.auth0.samples.authapi.springbootauthupdated.security.JWTAuthenticationFilter;
import com.auth0.samples.authapi.springbootauthupdated.security.WebSecurity;
import com.auth0.samples.authapi.springbootauthupdated.user.ApplicationUserRepository;
import com.auth0.samples.authapi.springbootauthupdated.user.UserController;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest({UserController.class})
public class TokenTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    ApplicationUserRepository applicationUserRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }

    @Test
    public void obtainTokenTest() throws Exception {
        //sign-up

        mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getContext()))
                .andExpect(status().isOk());
        //login

    }
    @Test
    public void loginPage() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getContext()))
                .andExpect(status().isOk());
    }

    private String getContext() throws JSONException {
        String username = "user1";
        String password = "password";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("password", password);
        jsonObject.put("username",username);
        return jsonObject.toString();
    }
}
