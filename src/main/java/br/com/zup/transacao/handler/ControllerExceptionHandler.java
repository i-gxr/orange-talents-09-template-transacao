package br.com.zup.transacao.handler;

import br.com.zup.transacao.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CartaoNaoEncontradoException.class)
    public Map<String, String> handlePaisJaExistenteError(BussinessException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message:", e.getMessage());
        return response;
    }

}
