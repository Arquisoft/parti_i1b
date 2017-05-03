package es.uniovi.asw.kafka;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

	private static final Logger logger = Logger.getLogger(MessageListener.class);

	/*
	 * un metodo para cada topic
	 */
	@KafkaListener(topics = "test")
	public void listen(String data) {
		logger.info("New message received: \"" + data + "\"");

		String[] message = data.split(",");

		//        Voter voter = new Voter(message[0], message[1], message[2], message[3]);
		//        if(message[1].equals("like"))
		//        	MainController.votersLike.add(voter);
		//        else
		//        	MainController.votersDislike.add(voter); 
		//        
		//        for (SseEmitter sseEmitter : MainController.getSseEmitters())
		//			try {
		//				sseEmitter.send("Message: " + data);
		//			} catch (IOException e) {
		//				e.printStackTrace();
		//			}
		//        System.out.println(data);
	}
}
