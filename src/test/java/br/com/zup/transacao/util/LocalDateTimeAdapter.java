package br.com.zup.transacao.util;

import com.google.gson.*;

import java.lang.reflect.*;
import java.time.*;
import java.time.format.*;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

    @Override
    public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
    }

}
