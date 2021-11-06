package com.devfox.board.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TokenUtil {

    public List<String> getTokenArray(String tokenString) {

        List<String> tokenArray = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(tokenString, ",");

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            tokenArray.add(token);
        }
        return tokenArray;
    }

}
