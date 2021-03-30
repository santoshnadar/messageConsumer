package com.jpmc.processor;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.jpmc.dto.SaleMessage;

public class Receiver implements MessageListener {
	// Constants
	private static final String TYPE_1 = "Type 1";
	private static final String TYPE_3 = "Type 3";
	private static final String ADD = "Add";
	private static final String SUBTRACT = "Subtract";
	private static final String MULTIPLY = "Multiply";

	// To store type-1 & type-2 messages
	private Map<String, List<SaleMessage>> productMap = new HashMap<>();
	// To store type-3 message
	private Map<String, List<SaleMessage>> productMapType3 = new HashMap<>();

	// To count
	private static int counter;
	// To display report - for meaningful report order (cosmetic purpose)
	private static int tenthCounter;

	@Override
	public void onMessage(Message message) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(SaleMessage.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SaleMessage saleMessage = (SaleMessage) jaxbUnmarshaller
					.unmarshal(new StringReader(message.getBody(String.class)));

			counter++;
			System.out.println("Message count:-"+counter);

			// Filter type 3 message and store
			if (TYPE_3.equalsIgnoreCase(saleMessage.getMessageType())) {
				if (!productMapType3.containsKey(saleMessage.getProduct())) {
					productMapType3.put(saleMessage.getProduct(), new ArrayList<>());
				}
				productMapType3.get(saleMessage.getProduct()).add(saleMessage);
			}
			// Store type-1 & type-2
			else {
				if (!productMap.containsKey(saleMessage.getProduct())) {
					productMap.put(saleMessage.getProduct(), new ArrayList<>());
				}
				if (TYPE_1.equalsIgnoreCase(saleMessage.getMessageType())) {
					// Set type-1 no.of sales as 1,
					// likely this might not be set as default in the message
					saleMessage.setNoOfSales(1);
				}

				// Calculate total price for each message (type-1 & type-2)
				saleMessage
						.setTotalPrice(BigDecimal.valueOf(saleMessage.getNoOfSales()).multiply(saleMessage.getPrice()));
				productMap.get(saleMessage.getProduct()).add(saleMessage);
			}

			// Print product total on every 10th message
			if (counter % 10 == 0) {
				printNewline();
				displayReport("In generateEveryTenthIntervalReport - Report no.: " + ++tenthCounter);
				System.out.println();
			}
			
			// Perform adjustment calculation on 50th message
			if (counter % 50 == 0) {
				printNewline();
				System.out.println("----------------------------------------------------------------");
				System.out.println("Pausing the message listener, it will not accept any new message");
				System.out.println("----------------------------------------------------------------");
				generateFiftieththIntervalReport();
				
				synchronized (this){
					this.wait();
				}
			}

		} catch (JAXBException | JMSException | InterruptedException e) {
			System.out.println("Exception while processing the queue message :");
			e.printStackTrace();
		}
	}

	/**
	 * Perform adjustment operation on each type-1 & type-2 messages based on
	 * Type 3 messages
	 */
	private void generateFiftieththIntervalReport() {
		printNewline();
		System.out.println(" In generateFiftieththIntervalReport ");

		productMapType3.entrySet().forEach(e -> {
			if (productMap.containsKey(e.getKey())) {
				List<SaleMessage> salesList = productMap.get(e.getKey());

				e.getValue().stream().forEach(v -> {
					calculateSales(v, salesList);
				});
			}
		});
		displayReport("*************************************");
	}

	private void displayReport(String caller) {

		System.out.println(caller);
		productMap.entrySet().stream().forEach(e -> {
			SaleMessage tempForTotal = new SaleMessage();
			tempForTotal.setTotalPrice(BigDecimal.ZERO);
			e.getValue().stream().forEach(v -> {
				// Sum total no. of sales
				tempForTotal.setNoOfSales(tempForTotal.getNoOfSales() + v.getNoOfSales());
				// Sum total prices
				tempForTotal.setTotalPrice(tempForTotal.getTotalPrice().add(v.getTotalPrice()));

			});

			System.out.println("product name: " + e.getKey() + "\t Total no. of sale:" + tempForTotal.getNoOfSales()
					+ "\t Total Price: " + tempForTotal.getTotalPrice());
			
		});

	}

	/**
	 * Perform adjustment operation on sales messages by given type-3 message
	 */
	private void calculateSales(SaleMessage salesType3, List<SaleMessage> salesList) {
		if (ADD.equalsIgnoreCase(salesType3.getOperationType())) {
			// total_price = each_sales ( total_price + (no.of_sales * adjustment_value))
			salesList.stream().forEach(saleMessage -> saleMessage.setTotalPrice(saleMessage.getTotalPrice()
					.add(BigDecimal.valueOf(saleMessage.getNoOfSales()).multiply(salesType3.getAdjustmentValue()))));
		} else if (SUBTRACT.equalsIgnoreCase(salesType3.getOperationType())) {
			// total_price = each_sales ( total_price - (no.of_sales * adjustment_value))
			salesList.stream().forEach(saleMessage -> saleMessage.setTotalPrice(saleMessage.getTotalPrice().subtract(
					BigDecimal.valueOf(saleMessage.getNoOfSales()).multiply(salesType3.getAdjustmentValue()))));
		} else if (MULTIPLY.equalsIgnoreCase(salesType3.getOperationType())) {
			// total_price = each_sales (price * no.of_sales * adjustment_value)
			salesList.stream()
					.forEach(saleMessage -> saleMessage
							.setTotalPrice(saleMessage.getPrice().multiply(salesType3.getAdjustmentValue())
									.multiply(BigDecimal.valueOf(saleMessage.getNoOfSales()))));
		}

	}

	/**
	 * Cosmetic purpose to print new line
	 */
	private void printNewline() {
		System.out.println();
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("Receiver started..");
	}

}
