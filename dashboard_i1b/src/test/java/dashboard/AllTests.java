package dashboard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dashboard.service.CitizenTest;
import dashboard.service.CommentTest;
import dashboard.service.ProposalTest;
import dashboard.service.VoteTest;
import dashboard.service.VoterTest;

@RunWith(Suite.class)
@SuiteClasses({ CitizenTest.class, CommentTest.class, ProposalTest.class, VoterTest.class, VoteTest.class})
public class AllTests {

}
