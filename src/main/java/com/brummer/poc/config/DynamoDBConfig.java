package com.brummer.poc.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
//@EnableDynamoDBRepositories (basePackages = "com.brummer.poc.dynamodb.repository")
public class DynamoDBConfig {
	
	@Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;
    
    @Value("${amazon.aws.region}")
    private String amazonAwsRegion;

    // works locally but not in AWS BEGIN
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
//        AmazonDynamoDB amazonDynamoDB
//            = AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAwsRegion))
//            .withCredentials(awsCredentialsProvider).build();
//        return amazonDynamoDB;
//    }
//
//    @Bean
//    public AWSCredentialsProvider awsCredentialsProvider() {
//        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
//    }
    // works locally but not in AWS END
    
    // new test BEGIN
//    private AWSCredentialsProvider awsDynamoDBCredentials() {
//        return new AWSStaticCredentialsProvider(
//            new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
//      }
//
//      @Primary
//      @Bean
//      public DynamoDBMapperConfig dynamoDBMapperConfig() {
//        return DynamoDBMapperConfig.DEFAULT;
//      }
//
//      @Bean
//      @Primary
//      public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
//                                           DynamoDBMapperConfig config) {
//        return new DynamoDBMapper(amazonDynamoDB, config);
//      }
//
//      @Bean
//      public AmazonDynamoDB amazonDynamoDB() {
//
//        return AmazonDynamoDBClientBuilder.standard()
//            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAwsRegion))
//            .withCredentials(awsDynamoDBCredentials()).build();
//      }
      // new test END
}
