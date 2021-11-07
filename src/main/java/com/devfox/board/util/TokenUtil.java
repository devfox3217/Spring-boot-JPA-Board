package com.devfox.board.util;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Tokenizer를 활용한 String형 분리 Util
 * @author 이진솔
 * @since 2021.11.05
 */
public class TokenUtil {

    /**
     * String을 받아 ','으로 구분하여 List형태로 출력
     *
     * @param tokenString 외부에서 받아오는 String
     * @return Tokenizer로 나누어 List형으로 반환
     */
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
