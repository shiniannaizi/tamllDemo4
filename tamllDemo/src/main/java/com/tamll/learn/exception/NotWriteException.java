package com.tamll.learn.exception;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class NotWriteException extends IOException{
    public NotWriteException(){
        super();
    }

    public static void handleException(IOException e, HttpServletRequest request){
        if (e instanceof NotWriteException){
            request.setAttribute("errMsg","响应发生了一个错误");
        }
    }
}
