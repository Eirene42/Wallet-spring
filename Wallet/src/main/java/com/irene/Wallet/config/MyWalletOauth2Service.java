package com.irene.Wallet.config;

import com.irene.Wallet.user.User;
import com.irene.Wallet.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyWalletOauth2Service extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String userEmail = fetchUserEmail(userRequest.getAccessToken().getTokenValue());
        if (userEmail == null || userEmail.length() < 0)
            return null;

        Map<String, Object> modifiedAttributes = new HashMap<>(oauth2User.getAttributes());

        String name = userEmail.split("@")[0].replace(".", " ").replace("i", "I").replace("m", "M");
        User newUser = new User(name,24,"Developer", userEmail, UUID.randomUUID().toString());
        Date date = new Date();
        newUser.setCreatedAt(date);
        newUser.setUpdatedAt(date);

        if (userRepository.findByEmail(userEmail) == null) {
            userRepository.save(newUser);
        }
        modifiedAttributes.put("email", userEmail);

        OAuth2User modifiedUser = new DefaultOAuth2User(
                oauth2User.getAuthorities(),
                modifiedAttributes,
                "email"
        );

        return modifiedUser;

    }


    private String fetchUserEmail(String accessToken) {
        String responseString=null;
        try {
            HttpClient httpclient = HttpClients.createDefault();

            HttpGet httpGet = new HttpGet("https://api.github.com/user/emails");
            httpGet.addHeader("Authorization", "token " + accessToken);


            HttpResponse response = httpclient.execute(httpGet);
            String responseEntity = EntityUtils.toString(response.getEntity());
            System.out.println(responseEntity);
            JSONArray  myObject = new JSONArray(responseEntity);
            JSONObject firstArray= (JSONObject) myObject.get(0);
            responseString = firstArray.get("email").toString();

            System.out.println(responseString);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

}
