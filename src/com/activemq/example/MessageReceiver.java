package com.activemq.example;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {
// URL of jms server 
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	// default broker URL is : tcp://localhost:61616"
	private static String subject= "JCG_QUEUE";
	
	public static void main(String[] args)throws JMSException {
		// TODO Auto-generated method stub
		// getting jms connnection from server
		ConnectionFactory conFac = new ActiveMQConnectionFactory();
		Connection connection = conFac.createConnection();
		connection.start();
		// creating a session 
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		   
        //Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		Destination destination = session.createQueue(subject);
		
		// MessageConsumer is used for receiving
		MessageConsumer consumer = session.createConsumer(destination);
		
		// Here we will receive the message
		Message message = consumer.receive();
		
		// casting the text message
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			System.out.println("Recieved Message '" +textMessage.getText()+ "'");
		}
		
	}

}
