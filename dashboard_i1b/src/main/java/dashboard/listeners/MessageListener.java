package dashboard.listeners;

import java.io.IOException;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dashboard.MainController;
import dashboard.model.Voter;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    private static final Logger logger = Logger.getLogger(MessageListener.class);

    @KafkaListener(topics = "Dashboard")
    public void listen(String data) {
        logger.info("New message received: \"" + data + "\"");
        System.out.println("New message received: \"" + data + "\"");
        
        String[] message = data.split(",");
        
        Voter voter = new Voter(message[0], message[1], message[3], message[2]);
        if(message[1].equals("1"))
        	MainController.votersLike.add(voter);
        else
        	MainController.votersDislike.add(voter); 
        
        for (SseEmitter sseEmitter : MainController.getSseEmitters())
			try {
				sseEmitter.send("Message: " + data);
			} catch (IOException e) {
				e.printStackTrace();
			}
        System.out.println(data);
    }


}
