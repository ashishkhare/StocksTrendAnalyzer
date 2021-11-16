package com.aksoft.equities.config;

import com.aksoft.equities.entity.StockInfo;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class StockInfoBatchConfig {

    private static final String[] STOCK_INFO_HEADER_COLUMNS = {"Date", "Open", "High", "Low", "Close", "Adj Close", "Volume", "Name", "ISIN"};

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<StockInfo> itemReader,
                   ItemProcessor<StockInfo,StockInfo> itemProcessor,
                   ItemWriter<StockInfo> itemWriter) {
        Step step = stepBuilderFactory.get("StockInfo-Step")
                .<StockInfo,StockInfo>chunk(1000)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return jobBuilderFactory.get("StockInfo_"+System.currentTimeMillis())
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<StockInfo> fileItemReader(@Value("${input}") Resource file){
        FlatFileItemReader<StockInfo> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(file);
        fileItemReader.setName("CSV Reader");
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setLineMapper(getLineMapper());
        return fileItemReader;
    }

    @Bean
    public LineMapper<StockInfo> getLineMapper() {
        DefaultLineMapper<StockInfo> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(STOCK_INFO_HEADER_COLUMNS);

        BeanWrapperFieldSetMapper<StockInfo> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(StockInfo.class);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return defaultLineMapper;
    }
}
