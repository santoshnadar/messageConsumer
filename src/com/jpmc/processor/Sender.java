package com.jpmc.processor;

import java.io.IOException;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Sender {

	public static void main(String[] args) throws NamingException, IOException {
		System.setProperty("java.naming.factory.initials", "com.sun.enterprice.naming.SerialInitContextFactory");
		System.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprice.naming");
		System.setProperty("java.naming.provider.url", "iiop://localhost:3700");
		InitialContext initcontext = new InitialContext();
		JMSContext jmscontext = ((ConnectionFactory) initcontext.lookup("salesQueueFactory")).createContext();

		Queue que = (Queue) initcontext.lookup("salesQueue");
		Receiver receiver = new Receiver();
		jmscontext.createConsumer(que).setMessageListener(receiver);

		JMSProducer jmsProducer = jmscontext.createProducer(); 

		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>12.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>11.56</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>13.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>14.54</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>15.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>grapes</product><price>16.12</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>17.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>18.53</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>19.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>cake</product><price>11.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>2.22</price><noOfSales>2</noOfSales></saleMessage>");
		
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>4.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>5.45</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>12.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>11.56</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>13.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 3</messageType><product>apple</product><price>14.54</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>15.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>16.12</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>17.00</price></saleMessage>");
		
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>18.53</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>19.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>2.22</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>grapes</product><price>4.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>5.45</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>12.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>11.56</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>13.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>cake</product><price>44.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>14.54</price><noOfSales>2</noOfSales></saleMessage>");
		
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>15.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>16.12</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>17.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 3</messageType><product>orange</product><adjustmentValue>18.53</adjustmentValue><operationType>Add</operationType></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>19.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>2.22</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>4.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>5.45</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 3</messageType><product>cake</product><adjustmentValue>1.5</adjustmentValue><operationType>Multiply</operationType></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>15.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>16.12</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>17.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>18.53</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 3</messageType><product>grapes</product><adjustmentValue>2.00</adjustmentValue><operationType>Subtract</operationType></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 1</messageType><product>apple</product><price>19.00</price></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>2.22</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>4.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>apple</product><price>5.45</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 2</messageType><product>orange</product><price>23.00</price><noOfSales>2</noOfSales></saleMessage>");
		jmsProducer.send(que, "<saleMessage><messageType>Type 3</messageType><product>meat</product><adjustmentValue>18.53</adjustmentValue><operationType>Add</operationType></saleMessage>");
		
		System.out.println("Sender Started.. ");
		System.in.read();

	}
}
