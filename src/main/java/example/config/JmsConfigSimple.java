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
//public class JmsConfigSimple {
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
//    public RedeliveryPolicy redeliveryPolicy() {
//        RedeliveryPolicy topicPolicy = new RedeliveryPolicy();
//        topicPolicy.setMaximumRedeliveries(1);
//        return topicPolicy;
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory(@Value("${spring.activemq.user}") final String username,
//                                               @Value("${spring.activemq.password}") final String password,
//                                               @Value("${spring.activemq.broker-url}") final String brokerUrl) {
//
//        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(username, password, brokerUrl);
//        cf.setRedeliveryPolicy(redeliveryPolicy());
//        return cf;
//    }
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
