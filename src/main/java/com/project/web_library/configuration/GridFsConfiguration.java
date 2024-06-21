package com.project.web_library.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@RequiredArgsConstructor
public class GridFsConfiguration extends AbstractMongoClientConfiguration {

    // … further configuration omitted
    @Value("${spring.data.mongodb.database}")
    String database;
    @Value("${spring.data.mongodb.host}")
    String host;

    private final MappingMongoConverter mongoConverter;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mongoConverter);
    }


    @Override
    public String getDatabaseName() {
        return database;
    }
}
