//package participants.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
////import DBManagements.UpdateInfo;
////import DBManagements.impl.UpdateInfoImpl;
//import model.Citizen;
//import model.CitizenController;
//import model.CitizenDao;
//import participants.ChangePassword;
//
//public class ChangePasswordImpl implements ChangePassword{
//
//	@Autowired
//	CitizenDao citizenDao;
//	
//	@Override
//	public void changePasswordForUser(String email, String oldPassword, String newPassword) throws Exception {
//		Citizen user = CitizenController.
//		
//		if(user.equals(null))
//			throw new Exception("User not found");
//		
//		if(!user.getPassword().equals(oldPassword)){
//			throw new Exception("The password is wrong.");
//		}
//		
//		user.setPassword(newPassword);
//		
////		UpdateInfo ui = new UpdateInfoImpl();
////		
////		ui.updatePasswordOfUser(email, newPassword);
//	}
//
//}
