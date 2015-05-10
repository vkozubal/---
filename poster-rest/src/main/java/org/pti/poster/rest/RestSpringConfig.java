package org.pti.poster.rest;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.pti.poster.model.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

@Configuration
@ComponentScan
public class RestSpringConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        
        builder.featuresToEnable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS,
                DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        
        builder.indentOutput(true).dateFormat(new SimpleDateFormat(Constants.DATE_PATTERN));
        
        // to do write test BigInteger format
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigInteger.class, new ToStringSerializer());
        builder.modules(module);
        
        return builder;
    }
}
