package es.uniovi.asw.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.uniovi.asw.infraestructure.Factories;
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

	private Citizen citizen;
	private static List<Proposal> list;
	private Proposal selectedProposal;
	private List<Comment> comments;
	private int score;
	private String textComment;
	private ArrayList<String> notificaciones = new ArrayList<String>();


	@PostConstruct
	public void init() {
		list= factoria.getServicesFactory().getProposalService().findAll();
		citizen=(Citizen) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	
    public void showNotifications() {
        FacesContext context = FacesContext.getCurrentInstance();
                 
        for (String s : getNotificaciones()) {
            context.addMessage(null, new FacesMessage("Notification",  s));
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
		this.list = list;
	}
	
	public List<Proposal> showProposals()
	{
		list = factoria.getServicesFactory().getProposalService().findAll();
		return list;
	}
	
	public String selectProposal(Proposal pr)
	{
		selectedProposal=pr;
		title=selectedProposal.getTitle();
		description=selectedProposal.getDescription();
		score=selectedProposal.getScore();
		
		return "goToView";
	}
	
	public void removeProposal(Proposal pr){
		factoria.getServicesFactory().getProposalService().delete(pr);
	}
	
	public String goToAddView(){
		return "addView";
	}
	
	public String goToListView(){
		System.out.println("back");
		return "listView";
	}
	
	public List<Comment> showComments(String sorting)
	{
	    
		comments = factoria.getServicesFactory().getProposalService().findCommentsByProposal(selectedProposal);

		if (sorting.equals("score")) {
			Collections.sort(comments, Comparator.comparing(Comment::getScore));
		}
		else if (sorting.equals("date")) {
			Collections.sort(comments, Comparator.comparing(Comment::getCreationDate));
		}
		
		return comments;
	}

	public void voteProposal(){
		//System.out.println("votando");
		
//		List<Vote> votes = factoria.getServicesFactory().getVoteService().findProposalVotesByCitizen(citizen);		
//		List<VoteProposal> votesProposal = new ArrayList<VoteProposal>();
//		for(Vote vote:votes)
//		{
//			if(((VoteProposal)vote).getProposal().equals(selectedProposal))
//			{
//				votesProposal.add((VoteProposal)vote);
//			}
//		}
//		selectedProposal.setVotes(votesProposal);
		Vote vote= new VoteProposal(citizen,selectedProposal);
		List<Vote> votes = factoria.getServicesFactory().getVoteService().findProposalVotesByCitizen(citizen);
		if(votes.contains(vote)){
			errorAlreadyVoteProposal();
		}
		else{
			factoria.getServicesFactory().getVoteService().save(vote);
			score = selectedProposal.getScore()+1;
			selectedProposal.setScore(score);
			factoria.getServicesFactory().getProposalService().save(selectedProposal);
		}
	}
	
	private void errorAlreadyVoteProposal() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "You already vote this proposal!"));
	}
	
	private void errorAlreadyVoteComment() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "You already vote this comment!"));		
	}

	public void voteComment(Comment comment){
		Vote vote= new VoteComment(citizen,comment);
		boolean found = false;
		List<Vote> votes = factoria.getServicesFactory().getVoteService().findCommentVotesByCitizen(citizen);
		for(Vote v: votes)
		{
			VoteComment v1 = (VoteComment) v ;
			VoteComment v2= (VoteComment) vote;
			if(v1.getComment().getId()==v2.getComment().getId())
			{
				found=true;
			}
		}
		if(found){
			errorAlreadyVoteComment();
		}
		else{
			factoria.getServicesFactory().getVoteService().save(vote);
			comment.setScore(comment.getScore()+1);
			factoria.getServicesFactory().getCommentService().save(comment);
		}
	}


	public void addComment(){
		Comment coment= new Comment(textComment, selectedProposal, citizen, new Date(), 0);
		textComment = "";
		factoria.getServicesFactory().getCommentService().save(coment);
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
				if (!p.getNotified()&&p.getScore()>ConfigurationController.getMinVotes()) {
					notificaciones.add("Proposal: "+p.getTitle()+" enters the acceptance phase");
					p.setNotified(true);
				}
			}
			return notificaciones;
	}
}
