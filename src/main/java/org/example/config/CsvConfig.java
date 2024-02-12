package org.example.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.example.domain.Customer;
import org.example.domain.CustomerSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CsvConfig {
    @Bean
    public ObjectReader objectReader() {

        CsvMapper mapper = new CsvMapper();
        mapper.enable(CsvParser.Feature.TRIM_SPACES);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);

        return mapper.readerFor(Customer.class)
                .with(CustomerSchema.getSchema());
    }
}
