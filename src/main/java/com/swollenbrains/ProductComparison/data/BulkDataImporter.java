package com.swollenbrains.ProductComparison.data;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulkDataImporter {

    private final DataReaderProvider dataReaderProvider;
    private final DataProcessorProvider dataProcessorProvider;
    private final DataWriterProvider dataWriterProvider;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BulkDataImporter(DataReaderProvider dataReaderProvider, DataProcessorProvider dataProcessorProvider, DataWriterProvider dataWriterProvider, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.dataReaderProvider = dataReaderProvider;
        this.dataProcessorProvider = dataProcessorProvider;
        this.dataWriterProvider = dataWriterProvider;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    public Job getImportJob(BulkDataSource bulkDataSource) {
        //@formatter:off
        return jobBuilderFactory.get("readFrom"+bulkDataSource.name())
                .incrementer(new RunIdIncrementer())
                .start(bulkDataImportStep(bulkDataSource))
                .build();
        //@formatter:on
    }

    private Step bulkDataImportStep(BulkDataSource bulkDataSource) {
        //@formatter:off
        return stepBuilderFactory.get("step1")
                .chunk(10)
                .reader(dataReaderProvider.getItemReader(bulkDataSource))
                .processor(dataProcessorProvider.getItemProcessor(bulkDataSource))
                .writer(dataWriterProvider.getItemWriter(bulkDataSource))
                .build();
        //@formatter:on
    }

}
