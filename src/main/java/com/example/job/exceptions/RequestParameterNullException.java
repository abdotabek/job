package com.example.job.exceptions;

/*****
 *Это исключение генерируется, когда параметры тела запроса имеют значение NULL.
 * **/
public class RequestParameterNullException extends Exception {
    public RequestParameterNullException(String message) {
        super(message);
    }
}
