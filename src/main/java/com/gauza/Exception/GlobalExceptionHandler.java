package com.gauza.Exception;


import com.gauza.Dto.Common.ExceptionResDto;
import com.gauza.Service.Common.MessageService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/* Exception을 공통적으로 처리해주는 Handler. 특정 Exception을 발생시키면 수행 */
@RequiredArgsConstructor
@RestControllerAdvice
public class  GlobalExceptionHandler {

    private final MessageService messageService;    // 공통 메시지 처리를 위한 서비스
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class); // 로깅 객체

    @ExceptionHandler(value = ServiceException.class)   // ServiceException 발생 시 수행
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ExceptionResDto handleServiceException(ServiceException e, HttpServletRequest req) {
        ExceptionResDto exceptionResDto = new ExceptionResDto();
        messageService.setExceptionMessage(exceptionResDto, e.getCode());
        logger.error("@@@Service Fail - UUID : {}, Error Code : {}, Error Log Message : {}"
                , req.getHeader("UUID")
                , e.getCode()
                , e.getLogMessage());

        if (e.getE()!=null) {
            logger.error("@@@Service Fail Exception - UUID : {}, Exception message : {}, Exception StackTrace : {}"
                    , req.getHeader("UUID")
                    , e.getE().getMessage()
                    , e.getE().getStackTrace());
        }
        return exceptionResDto;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResDto handleValidationException(MethodArgumentNotValidException e, HttpServletRequest req) {
        ExceptionResDto exceptionResDto = new ExceptionResDto();
        messageService.setExceptionMessage(exceptionResDto,e.getAllErrors().get(0).getDefaultMessage());

        logger.error("@@@Service Fail - UUID : {}, Error Code : {}, Error Log Message : {}"
                , req.getHeader("UUId")
                , exceptionResDto.getCode()
                , exceptionResDto.getMsg());
        return exceptionResDto;
    }

}

