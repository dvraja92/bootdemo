package com.example.bootdemo.rest.security;

import com.example.bootdemo.Constants;
import com.example.bootdemo.config.ApplicationProperties;
import com.example.bootdemo.rest.request.LoginUserDTO;
import com.example.bootdemo.rest.security.token.SecuredAccessToken;
import com.example.bootdemo.util.DateUtil;
import com.example.bootdemo.util.SecurityUtil;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {

    private static final String SIGNING_KEY = Constants.SIGNING_KEY;
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = (5 * 60 * 60);
    private static long tokenExpiryTime;

    @Autowired
    public JwtTokenUtil( ApplicationProperties applicationProperties) {
        tokenExpiryTime = applicationProperties.getTokenExpiryTime();
    }



    /**
     * @param token : token
     * @return : SecuredAccessToken
     */
    public SecuredAccessToken getDecodedToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        String payload = claims.get("payload", String.class);
        return SecurityUtil.decodeToken(payload, SecuredAccessToken.class);
    }

    /**
     * @param token : token
     * @return : date
     * @implNote : It is method get expire time from token
     */
    public Date getExpirationDateFromToken(String token) {
        SecuredAccessToken decodedToken = getDecodedToken(token);
        return decodedToken.getExpiryTime();
    }

    /**
     * @param token : token
     * @return : claims
     * @implNote : It is method decrypt token by SIGNING_KEY through
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @param token : token
     * @return : boolean
     * @implNote :It is method check token is valid then return true otherwise return false;
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * @param user : user
     * @return : token
     * @implNote : It is method generate token combination for  username,date,domain name and signingkey;
     */
    public String generateToken(LoginUserDTO user) {

        Claims claims = Jwts.claims().setSubject(user.getEmail());

        claims.put("scopes", Lists.newArrayList(new SimpleGrantedAuthority ("ROLE_ADMIN")));
        DateTime expiryTime = DateUtil  .parseLongToDate(DateUtil.nowMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000);


        claims.put("payload", SecurityUtil.encodeToken(new SecuredAccessToken(user, new Date(), new Date(expiryTime.getMillis()))));


        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(Constants.APP_NAME)
                .setIssuedAt(DateUtil.now())
                .setExpiration(DateUtil.parseLongToDate(DateUtil.nowMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000).toDate())
                .signWith( SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    /**
     * @param token       : token
     * @param userDetails : userDetails
     * @return : boolean
     * @implNote : It is method validate token using UserDetails object and token expired time;
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        SecuredAccessToken securedAccessToken = getDecodedToken(token);

        final String username = securedAccessToken.getCurrentLoggedInUser().getEmail();


                return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

}