package readerblog.mates.readerblog.security.oauth2;

import readerblog.mates.readerblog.enums.AuthProvider;
import readerblog.mates.readerblog.exception.OAuth2AuthenticationProcessingException;
import readerblog.mates.readerblog.security.oauth2.user.FacebookOAuth2UserInfo;
import readerblog.mates.readerblog.security.oauth2.user.GithubOAuth2UserInfo;
import readerblog.mates.readerblog.security.oauth2.user.GoogleOAuth2UserInfo;
import readerblog.mates.readerblog.security.oauth2.user.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}