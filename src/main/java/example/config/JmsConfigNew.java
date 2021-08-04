//package example.config;
//
////import com.tgt.dist.ship.eventprocessor.utils.CustomJmsErrorHandler;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.RedeliveryPolicy;
//import org.apache.activemq.pool.PooledConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.connection.SingleConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.DeliveryMode;
//import javax.jms.Session;
//
//
//@Configuration
//public class JmsConfigNew {
//    @Value("${spring.activemq.broker-url}")
//    String BROKER_URL;
//
//    @Value("${spring.activemq.user}")
//    String BROKER_USERNAME;
//
//    @Value("${spring.activemq.password}")
//    String BROKER_PASSWORD;
//
////    @Value("${spring.activemq.client-id}")
//    String clientId = "example-03";
//
//
//    @Bean
//    public ConnectionFactory pooledConnectionFactory() {
//        return new PooledConnectionFactory(activeMQconnectionFactory());
//    }
//
//    @Bean
//    public SingleConnectionFactory singleConnectionFactory() {
//        SingleConnectionFactory connectionFactory = new SingleConnectionFactory(pooledConnectionFactory());
//        connectionFactory.setReconnectOnException(true);
//        return connectionFactory;
//    }
//
////    @Bean(name="session")
////    public Session session() throws JMSException {
////        return connectionFactory().createConnection().createSession(true, JmsProperties.AcknowledgeMode.CLIENT.getMode());
////    }
//
////    @Bean
////    public RedeliveryPolicy redeliveryPolicy() {
////        RedeliveryPolicy topicPolicy = new RedeliveryPolicy();
////        topicPolicy.setQueue("Consumer.myConsumer.VirtualTopic.MY_TOPIC");
////        return topicPolicy;
////    }
//
//
//    @Bean
//    public ActiveMQConnectionFactory activeMQconnectionFactory() {
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
//         //configure redelivery policy
//        redeliveryPolicy.setMaximumRedeliveries(1);
//        //redeliveryPolicy.setTopic("Virtual.topic");
//
//        //ActiveMQDestination destination = new ActiveMQDestination(new ActiveMQTopic(""));
//
//
//        //redeliveryPolicy.setDestination(new Destination(""));
////        <amq:connectionFactory id="jmsFactory" brokerURL="vm://localhost" redeliveryPolicy="#activeMQRedeliveryPolicy" />
//
////<amq:redeliveryPolicy id="activeMQRedeliveryPolicy" destination="#myDLQ" useExponentialBackOff="true" backOffMultiplier="3" maximumRedeliveries="4" />
////
////<amq:queue id="myDLQ" physicalName="DLQ.myDLQ" />
////
//
//
//        //redeliveryPolicy.setBackOffMultiplier(20.0);
//        //redeliveryPolicy.setQueue("VirtualTopic.MY_TOPIC");
//        //redeliveryPolicy.setInitialRedeliveryDelay(10);
//        connectionFactory.setBrokerURL(BROKER_URL);
//        connectionFactory.setPassword(BROKER_USERNAME);
//        connectionFactory.setUserName(BROKER_PASSWORD);
//        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
//        connectionFactory.setTrustAllPackages(Boolean.TRUE);
//
//        return connectionFactory;
//    }
//
//    //Use JmsTemplate only for publishing messages. Use Message Listener for receiving messages.
//    //Use SingleConnectionFactory for JmsTemplate.
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(singleConnectionFactory());
//        //template.setMessageConverter(messageConverter());
//        //template.setPubSubDomain(true);
//        //The following statements will persist the message in AMQ
//        template.setDeliveryMode(DeliveryMode.PERSISTENT);
//        template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//        //template.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
//        //template.setExplicitQosEnabled(true);
//        return template;
//    }
//
////    @Bean
////    public MessageConverter messageConverter() {
////        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
////        converter.setTargetType(MessageType.TEXT);
////        converter.setTypeIdPropertyName("_type");
////        converter.setObjectMapper(objectMapper());
////        return converter;
////    }
////
////    @Bean
////    public ObjectMapper objectMapper() {
////        ObjectMapper mapper = new ObjectMapper();
////        //mapper.registerModule(new JavaTimeModule());
////        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////        return mapper;
////    }
//
////    @Bean
////    public ActiveMQConnectionFactoryCustomizer configureRedeliveryPolicy() {
////        return connectionFactory ->
////        {
////            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
////            // configure redelivery policy
////            redeliveryPolicy.setMaximumRedeliveries(1);
////            connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
////        };
////    }
//
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(pooledConnectionFactory());
//        factory.setConcurrency("1");
//        //factory.setConcurrency("1-1"); ???
//        //factory.setPubSubDomain(true);
//        factory.setClientId(clientId);
//        //factory.setSubscriptionDurable(true);
//        factory.setSessionTransacted(true);
//        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
//        //factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
//        //TODO: error handle add
//        //factory.setErrorHandler(customErrorHandler());
//        return factory;
//    }
////TODO: error handle, do we really need this?
////    @Bean
////    public CustomJmsErrorHandler customErrorHandler() {
////        CustomJmsErrorHandler customErrorHandler = new CustomJmsErrorHandler();
////
////        return customErrorHandler;
////    }
//}
