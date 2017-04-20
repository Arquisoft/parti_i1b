package dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import dashboard.model.Proposal;
import dashboard.model.Vote;
import dashboard.model.Voter;
import dashboard.persistence.VoteRepository;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	public static List<Voter> votersLike = new ArrayList<>();
	public static List<Voter> votersDislike = new ArrayList<>();
	public static List<String> messages = new ArrayList<>();
	private static List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());
	private static boolean isFirstAccess = true;

	@Autowired
	private VoteRepository voteRepository;

	public static List<SseEmitter> getSseEmitters() {
		return sseEmitters;
	}

	@RequestMapping("/messages")
	SseEmitter sendMessages() {
		SseEmitter emiter = new SseEmitter();
		synchronized (sseEmitters) {
			sseEmitters.add(emiter);
			emiter.onCompletion(() -> {
				synchronized (sseEmitters) {
					sseEmitters.remove(emiter);
				}
			});
			return emiter;
		}
	}

	@RequestMapping("/")
	public String landing(Model model) {
		if (isFirstAccess = true) {
			List<Vote> votes = voteRepository.findAll();
			for (Vote v : votes) {
				if (v.getValue()){
					if (v.getVotable() instanceof Proposal)
						votersLike.add(new Voter(v.getCitizen().getFirstName(), "like",
								((Proposal) v.getVotable()).getDescription(), "Un comentario"));
					else
						votersLike.add(new Voter(v.getCitizen().getFirstName(), "like",
								"", "Un comentario"));
				}
				else{
					if (v.getVotable() instanceof Proposal)
						votersDislike.add(new Voter(v.getCitizen().getFirstName(), "dislike",
								((Proposal) v.getVotable()).getDescription(), "Un comentario"));
					else
						votersDislike.add(new Voter(v.getCitizen().getFirstName(), "dislike",
								"", "Un comentario"));
				}
				
			}
			
			isFirstAccess = false;
		}

		System.out.println(
				"--------------------------------------------------------------VOTE (like) NUM: " + votersLike.size());
		System.out.println("--------------------------------------------------------------VOTE (dislike) NUM: "
				+ votersDislike.size());
		model.addAttribute("votersLike", votersLike);
		model.addAttribute("votersDislike", votersDislike);
		model.addAttribute("numberOfVotesLike", votersLike.size());
		model.addAttribute("numberOfVotesDislike", votersDislike.size());

		return "index";
	}

}