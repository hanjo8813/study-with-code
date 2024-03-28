package com.example.studybatchlecture.test.arch.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig extends BasicBatchConfigurer {

    private final DataSource dataSource;

    protected BatchConfig(BatchProperties properties, DataSource dataSource,
            TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    @Override
    public JobRepository createJobRepository() throws Exception {
        Jackson2ExecutionContextStringSerializer serializer = new Jackson2ExecutionContextStringSerializer("[Ljava.lang.Long;", "[J");
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(getTransactionManager());
        jobRepositoryFactoryBean.setSerializer(serializer);
        return jobRepositoryFactoryBean.getObject();
    }
}
