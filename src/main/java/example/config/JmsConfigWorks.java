//package example.config;
//
////import com.tgt.dist.ship.eventprocessor.utils.CustomJmsErrorHandler;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.RedeliveryPolicy;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.jms.ConnectionFactory;
//
//
//@Configuration
//public class JmsConfigWorks {
//    @Value("${spring.activemq.broker-url}")
//    String BROKER_URL;
//
//    @Value("${spring.activemq.user}")
//    String BROKER_USERNAME;
//
//    @Value("${spring.activemq.password}")
//    String BROKER_PASSWORD;
//
//    String clientId = "example-01";
//
//
////    @Bean
////    DeadLetterStrategy deadLetterStrategy() {
////
////        IndividualDeadLetterStrategy dlq = new IndividualDeadLetterStrategy();      //Messages of each will get to their respective Dead Letter Queues. if Original queue = 'x', its DLQ = 'prefix + x'
////        dlq.setQueueSuffix(".dlq");
////        dlq.setUseQueueForQueueMessages(true);
////
////        return dlq;
////    }
////
////    @Bean
////    public BrokerService brokerService(@Autowired DeadLetterStrategy strategy) throws Exception {
////        BrokerService broker = new BrokerService();
////        TransportConnector connector = new TransportConnector();
////        connector.setUri(new URI("tcp://localhost:61616")); //default/embedded broker url: vm://localhost?broker.persistent=true
////        broker.addConnector(connector);
////
////        PolicyEntry entry = new PolicyEntry();
////        entry.setDestination(new ActiveMQQueue("*"));           //given DeadLetterStrategy will be applied to all types of Queues; ',' can also be used
////        entry.setDeadLetterStrategy(strategy);
////        PolicyMap map = new PolicyMap();
////        map.setPolicyEntries(Arrays.asList(entry));
////        broker.setDestinationPolicy(map);
////
////        return broker;
////    }
//
////    @Bean
////    public RedeliveryPolicy redeliveryPolicy() {
////        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
////        redeliveryPolicy.setMaximumRedeliveries(1);
////        return redeliveryPolicy;
////    }
////
////    @Bean
////    public ConnectionFactory connectionFactory(@Value("${spring.activemq.user}") final String username,
////                                               @Value("${spring.activemq.password}") final String password,
////                                               @Value("${spring.activemq.broker-url}") final String brokerUrl) {
////
////        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
////        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
////        return activeMQConnectionFactory;
////    }
//
//
////    @Bean
////    public ConnectionFactory pooledConnectionFactory() {
////        return new PooledConnectionFactory(activeMQconnectionFactory());
////    }
////
////    @Bean
////    public SingleConnectionFactory singleConnectionFactory() {
////        SingleConnectionFactory connectionFactory = new SingleConnectionFactory(pooledConnectionFactory());
////        connectionFactory.setReconnectOnException(true);
////        return connectionFactory;
////    }
////
////    @Bean
////    public ActiveMQConnectionFactory activeMQconnectionFactory() {
////        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
////        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
////         //configure redelivery policy
////        redeliveryPolicy.setMaximumRedeliveries(1);
////
////        connectionFactory.setBrokerURL(BROKER_URL);
////        connectionFactory.setPassword(BROKER_USERNAME);
////        connectionFactory.setUserName(BROKER_PASSWORD);
////        //connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
////        connectionFactory.setTrustAllPackages(Boolean.TRUE);
////
////        return connectionFactory;
////    }
////
////    @Bean
////    public JmsTemplate jmsTemplate() {
////        JmsTemplate template = new JmsTemplate();
////        template.setConnectionFactory(singleConnectionFactory());
////        template.setDeliveryMode(DeliveryMode.PERSISTENT);
////        //template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
////        return template;
////    }
////
////    @Bean
////    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
////        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
////        factory.setConnectionFactory(pooledConnectionFactory());
////        //factory.setConcurrency("1");
////        factory.setClientId(clientId);
////        //factory.setSubscriptionDurable(true);
////        factory.setSessionTransacted(true);
////        //factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
////        return factory;
////    }
//
//}
