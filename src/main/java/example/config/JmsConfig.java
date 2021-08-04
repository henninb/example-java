package example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.tgt.dist.ship.eventprocessor.utils.CustomJmsErrorHandler;

import example.utils.CustomJmsErrorHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.broker.region.policy.DeadLetterStrategy;
import org.apache.activemq.broker.region.policy.IndividualDeadLetterStrategy;
import org.apache.activemq.broker.region.policy.PolicyEntry;
import org.apache.activemq.broker.region.policy.PolicyMap;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQConnectionFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.*;

@Configuration
public class JmsConfig {
    @Value("${spring.activemq.broker-url}")
    String BROKER_URL;

    @Value("${spring.activemq.user}")
    String BROKER_USERNAME;

    @Value("${spring.activemq.password}")
    String BROKER_PASSWORD;

//    @Value("${spring.activemq.client-id}")
    String clientId = "example-01";

//    @Bean
//    DeadLetterStrategy deadLetterStrategy() {
//
//        IndividualDeadLetterStrategy dlq = new IndividualDeadLetterStrategy();      //Messages of each will get to their respective Dead Letter Queues. if Original queue = 'x', its DLQ = 'prefix + x'
//        dlq.setQueueSuffix(".dlq");
//        dlq.setUseQueueForQueueMessages(true);
//
//        return dlq;
//    }
//
//    @Bean
//    public BrokerService brokerService(@Autowired DeadLetterStrategy strategy) throws Exception {
//        BrokerService broker = new BrokerService();
//        TransportConnector connector = new TransportConnector();
//        connector.setUri(new URI("tcp://localhost:61616")); //default/embedded broker url: vm://localhost?broker.persistent=true
//        broker.addConnector(connector);
//
//        PolicyEntry entry = new PolicyEntry();
//        entry.setDestination(new ActiveMQQueue("*"));           //given DeadLetterStrategy will be applied to all types of Queues; ',' can also be used
//        entry.setDeadLetterStrategy(strategy);
//        PolicyMap map = new PolicyMap();
//        map.setPolicyEntries(Arrays.asList(entry));
//        broker.setDestinationPolicy(map);
//
//        return broker;
//    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();

        redeliveryPolicy.setInitialRedeliveryDelay(500);
        redeliveryPolicy.setBackOffMultiplier(2);
//        redeliveryPolicy.setUseExponentialBackOff(true);
        redeliveryPolicy.setMaximumRedeliveries(16);

        return redeliveryPolicy;
    }
//
//    @Bean
//    public ConnectionFactory connectionFactory(@Value("${spring.activemq.user}") final String username,
//                                               @Value("${spring.activemq.password}") final String password,
//                                               @Value("${spring.activemq.broker-url}") final String brokerUrl) {
//
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
//        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
//        return activeMQConnectionFactory;
//    }


    @Bean
    public ConnectionFactory pooledConnectionFactory() {
        return new PooledConnectionFactory(activeMQconnectionFactory());
    }

    @Bean
    public SingleConnectionFactory singleConnectionFactory() {
        SingleConnectionFactory connectionFactory = new SingleConnectionFactory(pooledConnectionFactory());
        connectionFactory.setReconnectOnException(true);
        return connectionFactory;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQconnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

        //RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
         //configure redelivery policy
        //redeliveryPolicy.setMaximumRedeliveries(1);

        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        connectionFactory.setTrustAllPackages(Boolean.TRUE);

        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(singleConnectionFactory());
//        template.setDeliveryMode(DeliveryMode.PERSISTENT);
//        template.setMessageConverter(messageConverter());
//        //template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//        return template;
//    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(singleConnectionFactory());
        template.setMessageConverter(messageConverter());
        template.setPubSubDomain(true);
        template.setDeliveryMode(DeliveryMode.PERSISTENT);
        template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        //template.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
        template.setExplicitQosEnabled(true);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(pooledConnectionFactory());
        factory.setConcurrency("1");
        factory.setClientId(clientId);
        factory.setSubscriptionDurable(true);
        factory.setSessionTransacted(true);
        factory.setPubSubDomain(true);

        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }
}
