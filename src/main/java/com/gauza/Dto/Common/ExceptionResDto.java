package com.gauza.Dto.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResDto {
    boolean isSuccess;  // 성공여부
    String Code;     // 응답 코드
    String Msg;      // 응답 메시지
}
