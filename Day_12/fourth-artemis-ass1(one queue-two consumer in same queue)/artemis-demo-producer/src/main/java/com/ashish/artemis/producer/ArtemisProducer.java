package com.ashish.artemis.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArtemisProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtemisProducer.class);
    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination1}")
    String destinationQueue1;
    @Value("${jms.queue.destination2}")
    String destinationQueue2;

    public ArtemisProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /* @Retryable(
             value = {UncategorizedJmsException.class},
             maxAttempts = 10,
             backoff = @Backoff(random = true, delay = 1000, maxDelay = 8000,multiplier = 2)
     )*/
   /* @Retryable(value = { UncategorizedJmsException.class }, maxAttemptsExpression = "${artemis.retry.maxattempt}",
               backoff = @Backoff(random = true, delayExpression = "${artemis.retry.delay}",
                                  maxDelayExpression = "${artemis.retry.maxdelay}", multiplierExpression = "${artemis.retry.multiplier}"))
 */
    public void send(String msg) {
        LOGGER.info("Sending Data:");
        jmsTemplate.convertAndSend(destinationQueue1, msg);
        jmsTemplate.convertAndSend(destinationQueue2, msg);
        LOGGER.info("Data Sent:");
    }
}

