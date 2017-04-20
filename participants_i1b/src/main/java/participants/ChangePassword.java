package participants;

public interface ChangePassword {

	void changePasswordForUser(String email, String oldPassword, String newPassword) throws Exception;
}
