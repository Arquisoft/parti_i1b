package es.uniovi.asw.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.kafka.KafkaSender;
import es.uniovi.asw.persistence.model.Citizen;
import es.uniovi.asw.persistence.model.Comment;
import es.uniovi.asw.persistence.model.Proposal;
import es.uniovi.asw.persistence.model.Vote;
import es.uniovi.asw.persistence.model.VoteComment;
import es.uniovi.asw.persistence.model.VoteProposal;

@Component("ProposalListController")
@Scope("session")
public class ProposaListController {

	private String title;
	private String description;
	@Autowired
	private Factories factoria;
	@Autowired
	private KafkaSender sender;

	private Citizen citizen;
	private static List<Proposal> list;
	private Proposal selectedProposal;
	private List<Comment> comments;
	private int score;
	private String textComment;
	private ArrayList<String> notificaciones = new ArrayList<String>();

	@PostConstruct
	public void init() {
		list = factoria.getServicesFactory().getProposalService().findAll();
		citizen = (Citizen) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}

	public void showNotifications() {
		FacesContext context = FacesContext.getCurrentInstance();

		for (String s : getNotificaciones()) {
			context.addMessage(null, new FacesMessage("Notification", s));
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public List<Proposal> getList() {
		return list;
	}

	public Proposal getSelectedProposal() {
		return selectedProposal;
	}

	public void setSelectedProposal(Proposal selectedProposal) {
		this.selectedProposal = selectedProposal;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public void setList(List<Proposal> list) {
		ProposaListController.list = list;
	}

	public List<Proposal> showProposals() {
		list = factoria.getServicesFactory().getProposalService().findAll();
		return list;
	}

	public String selectProposal(Proposal pr) {
		selectedProposal = pr;
		title = selectedProposal.getTitle();
		description = selectedProposal.getDescription();
		score = selectedProposal.getScore();

		return "goToView";
	}

	public void removeProposal(Proposal pr) {
		factoria.getServicesFactory().getProposalService().delete(pr);
	}

	public String goToAddView() {
		return "addView";
	}

	public String goToListView() {
		System.out.println("back");
		return "listView";
	}

	public List<Comment> showComments(String sorting) {
		System.out.println("SHOW COMMENTS:");
		comments = factoria.getServicesFactory().getProposalService().findCommentsByProposal(selectedProposal);
		System.out.println("Unordered comments:");
		for(int i = 0; i<comments.size();i++){
			System.out.println(comments.get(i));
		}
		if ("score".equals(sorting)) {
			Collections.sort(comments, Comparator.comparing(Comment::getScore));
			System.out.println("\nOrdered comments:");
			for(int i = 0; i<comments.size();i++){
				System.out.println(comments.get(i));
			}
		} else if ("date".equals(sorting)) {
			Collections.sort(comments, Comparator.comparing(Comment::getCreationDate));
		}
		
		return comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizen == null) ? 0 : citizen.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((notificaciones == null) ? 0 : notificaciones.hashCode());
		result = prime * result + score;
		result = prime * result + ((selectedProposal == null) ? 0 : selectedProposal.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((textComment == null) ? 0 : textComment.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProposaListController other = (ProposaListController) obj;
		if (citizen == null) {
			if (other.citizen != null)
				return false;
		} else if (!citizen.equals(other.citizen))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (notificaciones == null) {
			if (other.notificaciones != null)
				return false;
		} else if (!notificaciones.equals(other.notificaciones))
			return false;
		if (score != other.score)
			return false;
		if (selectedProposal == null) {
			if (other.selectedProposal != null)
				return false;
		} else if (!selectedProposal.equals(other.selectedProposal))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (textComment == null) {
			if (other.textComment != null)
				return false;
		} else if (!textComment.equals(other.textComment))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public void voteProposal(int votoValue) {
		// System.out.println("votando");
		citizen = (Citizen) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		// List<Vote> votes =
		// factoria.getServicesFactory().getVoteService().findProposalVotesByCitizen(citizen);
		// List<VoteProposal> votesProposal = new ArrayList<VoteProposal>();
		// for(Vote vote:votes)
		// {
		// if(((VoteProposal)vote).getProposal().equals(selectedProposal))
		// {
		// votesProposal.add((VoteProposal)vote);
		// }
		// }
		// selectedProposal.setVotes(votesProposal);
		Vote vote = new VoteProposal(citizen, selectedProposal, votoValue);
		List<Vote> votes = factoria.getServicesFactory().getVoteService().findProposalVotesByCitizen(citizen);
		if (votes.contains(vote)) {
			errorAlreadyVoteProposal();
		} else {
			factoria.getServicesFactory().getVoteService().save(vote);
			score = selectedProposal.getScore() + votoValue;
			selectedProposal.setScore(score);
			factoria.getServicesFactory().getProposalService().save(selectedProposal);
			if (comments.isEmpty()) {
				sender.sendDashboard(
						citizen.getFirstName() + "," + selectedProposal.getTitle() + ", " + "," + votoValue);
			} else {
				sender.sendDashboard(citizen.getFirstName() + "," + selectedProposal.getTitle() + "," + comments.get(0)
						+ "," + votoValue);

			}
			// System.out.println(votoValue+"raaaaaaaaaaaaa"+selectedProposal.getScore());
			sender.sendToLog("New vote for proposal: " + selectedProposal.getTitle());
		}
	}

	private void errorAlreadyVoteProposal() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "You already vote this proposal!"));
	}

	private void errorAlreadyVoteComment() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "You already vote this comment!"));
	}

	public void voteComment(Comment comment) {
		Vote vote = new VoteComment(citizen, comment, +1);
		boolean found = false;
		List<Vote> votes = factoria.getServicesFactory().getVoteService().findCommentVotesByCitizen(citizen);
		for (Vote v : votes) {
			VoteComment v1 = (VoteComment) v;
			VoteComment v2 = (VoteComment) vote;
			if (v1.getComment().getId() == v2.getComment().getId()) {
				found = true;
			}
		}
		if (found) {
			errorAlreadyVoteComment();
		} else {
			factoria.getServicesFactory().getVoteService().save(vote);
			comment.setScore(comment.getScore() + 1);
			factoria.getServicesFactory().getCommentService().save(comment);
			sender.sendToLog("New vote for a comment created in " + selectedProposal.getTitle());
		}
	}

	public void addComment() {
		Comment coment = new Comment(textComment, selectedProposal, citizen, new Date(), 0);
		textComment = "";
		factoria.getServicesFactory().getCommentService().save(coment);
		sender.sendComment(coment);
		sender.sendToLog("New comment created in " + selectedProposal.getTitle());
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTextComment() {
		return textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}

	public ArrayList<String> getNotificaciones() {
		notificaciones.clear();
		for (Proposal p : list) {
			if (!p.getNotified() && p.getScore() > factoria.getServicesFactory().getConfigurationService().actualConfiguration().getMinVotesToAcceptProposal()) {
				notificaciones.add("Proposal: " + p.getTitle() + " enters the acceptance phase");
				p.setNotified(true);
			}
		}
		return notificaciones;
	}
}
