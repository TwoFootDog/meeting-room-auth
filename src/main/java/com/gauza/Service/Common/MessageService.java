package com.gauza.Service.Common;


import com.gauza.Dto.Common.ExceptionResDto;
import com.gauza.Dto.Common.ResDto;
import com.gauza.Dto.Common.ResListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/* 응답 메시지 중 공통적인 내용을 생성해주는 서비스(코드, 메시지, 성공여부) */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageSource messageSource;

    // Exception 이 발생할 경우 공통 메시지를 생성해주는 함수
    public void setExceptionMessage(ExceptionResDto resDto, String errorCode) {
        resDto.setCode(getMessage(errorCode+".code"));
        resDto.setMsg(getMessage(errorCode + ".msg"));
        resDto.setSuccess(false);
    }
    // 처리가 성공할 경우 메시지를 생성해주는 함수(결과값이 단일 건)
    public void setSuccessMessage(ResDto<?> resDto) {
        resDto.setCode(getMessage("success.code"));
        resDto.setMsg(getMessage("success.msg"));
        resDto.setSuccess(true);
    };

    // 처리가 성공할 경우 메시지를 생성해주는 함수(결과값이 여러 건(List))
    public void setSuccessMessage(ResListDto<?> resDto) {
        resDto.setCode(getMessage("success.code"));
        resDto.setMsg(getMessage("success.msg"));
        resDto.setSuccess(true);
    };

    // yaml 파일에서 응답코드를 찾아서 변환 시 사용
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
