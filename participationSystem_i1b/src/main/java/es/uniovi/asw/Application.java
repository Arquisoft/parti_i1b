package es.uniovi.asw;

import java.util.Date;
import java.util.List;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sun.faces.config.ConfigureListener;

import es.uniovi.asw.infraestructure.Factories;
import es.uniovi.asw.kafka.KafkaSender;
import es.uniovi.asw.persistence.model.Administrator;
import es.uniovi.asw.persistence.model.Category;
import es.uniovi.asw.persistence.model.Citizen;
import es.uniovi.asw.persistence.model.Comment;
import es.uniovi.asw.persistence.model.Proposal;
import es.uniovi.asw.persistence.model.Vote;
import es.uniovi.asw.persistence.model.VoteComment;
import es.uniovi.asw.persistence.model.VoteProposal;

@SuppressWarnings("deprecation")
@SpringBootApplication
@ComponentScan(basePackages = { "es.uniovi.asw" })
@EnableJpaRepositories("es.uniovi.asw.persistence.repositories")
public class Application extends SpringBootServletInitializer implements ServletContextAware, CommandLineRunner {
	@Autowired
	private Factories factoria;
	@Autowired
	private KafkaSender sender;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		
		List<Vote> votes = factoria.getServicesFactory().getVoteService().findAllProposalVotes();
		
		for (Vote v : votes) {
			VoteProposal v1 = (VoteProposal) v;
			sender.sendDashboard(v1.getCitizen().getFirstName() + "," + v1.getProposal().getTitle() + ", " + "," + v1.getValue());
		}
		
		//todos los usuarios, todos los votos, todas las propuestas
//		// creates admin
//		Administrator admin = new Administrator("admin", "admin");
//
//		// creates citizens
//		Citizen nacho = new Citizen("Nacho", "Fernandez", new Date(), "emailNacho@test.com", "71729768J", "mi casa",
//				"española", 47);
//		nacho.setPassword("nacho");
//		Citizen gabriel = new Citizen("Gabriel", "del Reguero", new Date(), "torrente@test.com", "58430888D", "hermida",
//				"española", 48);
//		gabriel.setPassword("gabriel");
//		Citizen diego = new Citizen("Diego", "akks", new Date(), "emailprueba@test.com", "3423406F", "casa2",
//				"española", 49);
//		diego.setPassword("diego");
//		Citizen muhlah = new Citizen("asdd", "Muhlah", new Date(), "emailprueba2@test.com", "9738488H", "casa3",
//				"española", 50);
//		muhlah.setPassword("muhlah");
//
//		Category categoria1 = new Category("General");
//		Category categoria2 = new Category("Law");
//		Category categoria3 = new Category("Economy");
//
//		// save categories
//		if (factoria.getPersistenceFactory().getCategoryRepository().findByName(categoria1.getName()) == null) {
//			factoria.getServicesFactory().getCategoryService().save(categoria1);
//
//		}
//		if (factoria.getPersistenceFactory().getCategoryRepository().findByName(categoria2.getName()) == null) {
//			factoria.getServicesFactory().getCategoryService().save(categoria2);
//
//		}
//		if (factoria.getPersistenceFactory().getCategoryRepository().findByName(categoria3.getName()) == null) {
//			factoria.getServicesFactory().getCategoryService().save(categoria3);
//
//		}
//		// saves citizens
//		if (factoria.getServicesFactory().getCitizenService().findByEmail(diego.getEmail()) == null)
//			factoria.getServicesFactory().getCitizenService().save(diego);
//		if (factoria.getServicesFactory().getCitizenService().findByEmail(muhlah.getEmail()) == null)
//			factoria.getServicesFactory().getCitizenService().save(muhlah);
//		if (factoria.getServicesFactory().getCitizenService().findByEmail(nacho.getEmail()) == null)
//			factoria.getServicesFactory().getCitizenService().save(nacho);
//		if (factoria.getServicesFactory().getCitizenService().findByEmail(gabriel.getEmail()) == null)
//			factoria.getServicesFactory().getCitizenService().save(gabriel);
//		// save admin
//		if (factoria.getServicesFactory().getAdministratorService().checkLogin(admin.getUsername(),
//				admin.getPassword()) == null)
//			factoria.getServicesFactory().getAdministratorService().save(admin);
//
//		Proposal proposal1 = new Proposal("New law", "we want to create a new law that forces people to wear hats",
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(gabriel.getEmail()), 0, new Date(),
//				factoria.getPersistenceFactory().getCategoryRepository().findByName(categoria2.getName()));
//		Proposal proposal2 = new Proposal("New payment", "Everybody need to pay me 20 bucks",
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(diego.getEmail()), 0, new Date(),
//				factoria.getPersistenceFactory().getCategoryRepository().findByName(categoria3.getName()));
//
//		// save proposals
//		// save proposals
//		if (!factoria.getServicesFactory().getProposalService().alreadyExists(proposal1)) {
//			factoria.getServicesFactory().getProposalService().save(proposal1);
//		}
//		if (!factoria.getServicesFactory().getProposalService().alreadyExists(proposal2)) {
//			factoria.getServicesFactory().getProposalService().save(proposal2);
//
//		}
//
//		proposal2 = factoria.getPersistenceFactory().getProposalRepository().findByTitle(proposal2.getTitle());
//		proposal1 = factoria.getPersistenceFactory().getProposalRepository().findByTitle(proposal1.getTitle());
//
//		Comment comment1 = new Comment("Good idea", proposal2,
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(gabriel.getEmail()), new Date(), 0);
//		Comment comment2 = new Comment("I disagree", proposal1,
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(muhlah.getEmail()), new Date(), 0);
//
//		if (factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).isEmpty())
//			factoria.getServicesFactory().getCommentService().save(comment1);
//		if (factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal1).isEmpty())
//			factoria.getServicesFactory().getCommentService().save(comment2);
//
//		// save
//
//		VoteComment vote = new VoteComment(
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(diego.getEmail()),
//				factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal1).get(0));
//		VoteComment vote1 = new VoteComment(
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(muhlah.getEmail()),
//				factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).get(0));
//		VoteProposal vote2 = new VoteProposal(
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(muhlah.getEmail()), proposal1);
//		VoteProposal vote3 = new VoteProposal(
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(muhlah.getEmail()), proposal2);
//		VoteProposal vote4 = new VoteProposal(
//				factoria.getPersistenceFactory().getCitizenRepository().findByEmail(diego.getEmail()), proposal1);
//
//		// save votes
//		if (factoria.getServicesFactory().getVoteService()
//				.findCommentVotesByCitizen(
//						factoria.getServicesFactory().getCitizenService().findByEmail(muhlah.getEmail()))
//				.isEmpty()
//				&& factoria.getServicesFactory().getVoteService()
//						.findProposalVotesByCitizen(
//								factoria.getServicesFactory().getCitizenService().findByEmail(muhlah.getEmail()))
//						.isEmpty()) {
//			factoria.getServicesFactory().getVoteService().save(vote1);
//			int score1 = factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).get(0)
//					.getScore() + 1;
//			Comment comment = factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).get(0);
//			comment.setScore(score1);
//			factoria.getServicesFactory().getCommentService().save(comment);
//
//			factoria.getServicesFactory().getVoteService().save(vote2);
//			int score = proposal2.getScore() + 1;
//			proposal2.setScore(score);
//			factoria.getServicesFactory().getProposalService().save(proposal2);
//			factoria.getServicesFactory().getVoteService().save(vote3);
//			score = proposal1.getScore() + 1;
//			proposal1.setScore(score);
//			factoria.getServicesFactory().getProposalService().save(proposal1);
//		}
//		if (factoria.getServicesFactory().getVoteService()
//				.findCommentVotesByCitizen(
//						factoria.getServicesFactory().getCitizenService().findByEmail(diego.getEmail()))
//				.isEmpty()
//				&& factoria.getServicesFactory().getVoteService()
//						.findProposalVotesByCitizen(
//								factoria.getServicesFactory().getCitizenService().findByEmail(diego.getEmail()))
//						.isEmpty()) {
//			factoria.getServicesFactory().getVoteService().save(vote);
//			int score1 = factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).get(0)
//					.getScore() + 1;
//			Comment comment = factoria.getPersistenceFactory().getCommentRepository().findByProposal(proposal2).get(0);
//			comment.setScore(score1);
//			factoria.getServicesFactory().getCommentService().save(comment);
//
//			factoria.getServicesFactory().getVoteService().save(vote4);
//			int score = proposal1.getScore() + 1;
//			proposal1.setScore(score);
//			factoria.getServicesFactory().getProposalService().save(proposal1);
//		}
//
//		System.out.println("en este metodo creamos los usuarios y la base de datos si no esta creada");
	}

	@Bean
	public ServletRegistrationBean facesServletRegistraiton() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		registration.setName("Faces Servlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
		};
	}

	@Bean
	public WebMvcConfigurerAdapter forwardToIndex() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("redirect:/index.xhtml");
			}
		};
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}

}