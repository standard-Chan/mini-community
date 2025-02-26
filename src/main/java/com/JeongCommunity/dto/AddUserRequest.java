package com.JeongCommunity.dto;

/*
    사용자의 정보를 가져올 DTO. 이메일과 패스워드로 정보를 가져온 후, 해당 정보로 로그인을 처리한다.

    http로 받으면 해당 인스턴스에 값이 set메서드를 통해 자동으로 추가되므로 Setter는 꼭 적어줄 것.
    get도 마찬가지다.
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private String password;
}
