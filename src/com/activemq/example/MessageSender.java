package com.activemq.example;

import javax.jms.JMSException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
 

public class MessageSender {
	// Url of the jms server Default broker is means that jms server is on localhost
	  private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	  //// default broker URL is : tcp://localhost:61616"
	  private static String subject="JCG_QUEUE";
	  
	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		// getting the jms connection from server
		ConnectionFactory conFac=new ActiveMQConnectionFactory(url);
		Connection connection=conFac.createConnection();
		connection.start();
		// Creating a session
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//Destination represent our queue 'JCG_QUEUE' on the JMS server
		//The queue will be created automatically on the server
		Destination destination = session.createQueue(subject);
		//MessageProducer is used for sending message to the queue
		MessageProducer producer = session.createProducer(destination);
		// Now we will creating the message
		TextMessage message = session.createTextMessage("Hello There IM Vijay");
		// now we are sending our message
		producer.send(message);
		System.out.println("JCG priting ' "+ message.getText() + "'");
		connection.close();

	}

}
