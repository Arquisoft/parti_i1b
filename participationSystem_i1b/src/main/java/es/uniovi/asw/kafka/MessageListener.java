package es.uniovi.asw.kafka;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import es.uniovi.asw.log.LogManager;

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
	}

	@KafkaListener(topics = "ToLog")
	public void listenLog(String log) {

		LogManager man = new LogManager();
		man.addToLog(log);
	}
}
