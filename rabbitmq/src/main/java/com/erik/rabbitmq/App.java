package com.erik.rabbitmq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class App {
	
	public App() throws Exception{
		
		QueueConsumer consumer = new QueueConsumer("");
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		
		Producer producer = new Producer("queue");
		
		for (int i = 0; i < 100; i++) {
			HashMap<String, Integer> message = new HashMap<String, Integer>();
			message.put("message number", i);
			producer.sendMessage(message);
			System.out.println("Message Number "+ i +" sent.");
		}
		producer.close();
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception{
	  new App();
	  ;
	}
}
