package com.ashish.rabbitmq.service;

import com.ashish.rabbitmq.model.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routingkey.name}")
  private String routingkey;

//  @Retryable(value = { UncategorizedJmsException.class }, maxAttemptsExpression = "${retry.maxattempt}",
//          backoff = @Backoff(random = true, delayExpression = "${retry.delay}",
//                  maxDelayExpression = "${retry.maxdelay}", multiplierExpression = "${retry.multiplier}"))
  public void send(Employee company) {
    rabbitTemplate.convertAndSend(exchange, routingkey, company);
    System.out.println("Send msg = " + company);
  }
/*
  @RabbitListener(queues = "${rabbitmq.queue.name}")
  public void recievedMessage(Employee employee) {
    System.out.println("Recieved Message From RabbitMQ: " + employee);
  }*/

}
