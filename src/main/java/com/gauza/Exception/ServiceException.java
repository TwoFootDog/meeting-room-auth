package com.gauza.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    private boolean isSuccess;    // 응답 성공여부(true/false)
    private String code;           // 응답 코드 번호 (>= 0 : 정상, < 0 비정상)
    private String logMessage;      // 로그메시지
    private Exception e;

    public ServiceException(boolean isSuccess, String code, String logMessage) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.logMessage = logMessage;
    }
}
