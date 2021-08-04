<address-setting match="#">
   <dead-letter-address>DLA</dead-letter-address>
   <max-delivery-attempts>3</max-delivery-attempts>
   <auto-create-dead-letter-resources>true</auto-create-dead-letter-resources>
   <dead-letter-queue-prefix></dead-letter-queue-prefix> <!-- override the default -->
   <dead-letter-queue-suffix>.DLQ</dead-letter-queue-suffix>
</address-setting>


                <policyEntry queue=">">
                  <deadLetterStrategy>
                    <individualDeadLetterStrategy queuePrefix="DLQ." useQueueForQueueMessages="true"/>
                  </deadLetterStrategy>
                </policyEntry>


<plugins>
            <redeliveryPlugin fallbackToDeadLetter="true" sendToDlqIfMaxRetriesExceeded="true">
                <redeliveryPolicyMap>
                    <redeliveryPolicyMap>
                        <redeliveryPolicyEntries>
                            <redeliveryPolicy queue="TestQueue" maximumRedeliveries="3" redeliveryDelay="10000"/>
                                                </redeliveryPolicyEntries>
                                                <defaultEntry>
                            <redeliveryPolicy maximumRedeliveries="5" initialRedeliveryDelay="5000" redeliveryDelay="10000"/>
                        </defaultEntry>
                    </redeliveryPolicyMap>
                </redeliveryPolicyMap>
            </redeliveryPlugin>
        </plugins>



<amq:connectionFactory id="jmsFactory" brokerURL="vm://localhost" redeliveryPolicy="#activeMQRedeliveryPolicy" />

<amq:redeliveryPolicy id="activeMQRedeliveryPolicy" destination="#myDLQ" useExponentialBackOff="true" backOffMultiplier="3" maximumRedeliveries="4" />

<amq:queue id="myDLQ" physicalName="DLQ.myDLQ" />
