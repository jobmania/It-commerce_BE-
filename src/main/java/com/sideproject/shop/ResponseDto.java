package com.sideproject.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    private T data;
    private boolean result;
    private Status status;

    public static <T> ResponseDto<T> ok(T data,String message){
        return new ResponseDto<>(data, true, new Status("200",message));
    }

    public static <T> ResponseDto<T> fail(String code,String message){
        return new ResponseDto<>(null, false, new Status(code,message));
    }

    @Getter
    @AllArgsConstructor
    static class Status {
        private String code;
        private String message;
    }


}
