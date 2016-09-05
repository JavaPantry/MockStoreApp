package org.sporcic.extjs;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * A custom date serializer for Jackson JSON which will serialize a date in the
 * standard ISO format, like 2010-07-01T15:30:00
 */
public class ExtDateSerializer extends JsonSerializer<Date> {

    // the date format to use
    //private final static String FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final static String FORMAT = "yyyy-MM-dd";

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        DateFormat formatter = new SimpleDateFormat(FORMAT);
        String formattedDate = formatter.format(value);

        gen.writeString(formattedDate);
    }
}
