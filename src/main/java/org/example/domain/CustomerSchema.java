package org.example.domain;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Data
@Builder
public class CustomerSchema {
    private static CsvSchema schema;

    static {
        schema = CsvSchema.builder()
                .addColumn("customerReference")
                .addColumn("customerName")
                .addColumn("addressLine1")
                .addColumn("addressLine2")
                .addColumn("town")
                .addColumn("county")
                .addColumn("country")
                .addColumn("postcode")
                .build()
                .withColumnSeparator(',');
    }

    public static CsvSchema getSchema(){
        return schema;
    }
}
