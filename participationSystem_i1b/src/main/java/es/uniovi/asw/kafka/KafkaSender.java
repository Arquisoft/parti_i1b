package es.uniovi.asw.kafka;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import es.uniovi.asw.persistence.model.Comment;
import es.uniovi.asw.persistence.model.Proposal;
import es.uniovi.asw.persistence.model.VoteComment;
import es.uniovi.asw.persistence.model.VoteProposal;

@ManagedBean
public class KafkaSender {
	
	private static final Logger logger = Logger.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message Send \"" + data + "\" to topic " + topic);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Error on message \"" + data + "\", stacktrace " + ex.getMessage());
            }
        });
    }
    //user,proposal,comment,like/dislike
    public void sendProposal(Proposal proposal)
    {
    	send("Proposal","New Proposal: "+proposal.getTitle()+"\n");
    }
    
    public void sendComment(Comment com)
    {
    	send("Comment","New Comment: "+com.toString()+"\n");
    }
    
    public void sendVoteComment(VoteComment com)
    {
    	send("VoteComment","New vote for Comment: "+com.getComment().toString()+"\n");
    }
    
    public void sendVoteProposal(VoteProposal com)
    {
    	send("VoteProposal","New vote for Proposal: "+com.getProposal().getTitle()+"\n");
    }
    
    public void sendDashboard(String text)
    {
    	send("Dashboard",text);
    }
    
    
    
    
//    public void sendTestVoter(Voter voter) {
//        send("Voter", voter.toString());
//    }
}
