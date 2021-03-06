package utils;

import entities.ExtenuatingCircumstance;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class WsadUtils {

	private static final String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
	private static final String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String specialChar = "!@#$%^&*";
	private static final String intChar = "0123456789";
	private static Random r = new Random();

	/**
	 * generate password which has length of 8 and contains 4 type of character
	 * 
	 * @return password
	 */
	public static String generatePassword() {
		String password = "";
		System.out.println("Generating pass...");
		while (password.length() != 8) {
			int rPick = r.nextInt(4);
			if (rPick == 0) {
				int spot = r.nextInt(lowercaseChars.length());
				password += lowercaseChars.charAt(spot);
			} else if (rPick == 1) {
				int spot = r.nextInt(lowercaseChars.length());
				password += uppercaseChars.charAt(spot);
			} else if (rPick == 2) {
				int spot = r.nextInt(7);
				password += specialChar.charAt(spot);
			} else if (rPick == 3) {
				int spot = r.nextInt(9);
				password += intChar.charAt(spot);
			}
		}
		return password;
	}

	public static String createUsername(String firstname, String middlename, String lastname) {
		return firstname + getFirstLetter(lastname) + getFirstLetter(middlename);
	}

	private static String getFirstLetter(String str) {
		String firstLetter = "";
		if (null != str) {
			if (str.length() > 1)
				firstLetter = str.substring(0, 1);
			else
				firstLetter = str;
		}
		return firstLetter;
	}

	public static String buildMailContent(String username, String password) {
		StringBuilder content = new StringBuilder();
		content.append("<html>").append("<head></head>").append("<body>")
				.append("<h3>Your account has been created successfully</h3>").append("<p>Username: ").append(username)
				.append("</p>").append("<p>Password: ").append(password).append("</p>").append("</body>")
				.append("<html>");

		return content.toString();
	}
        
        public static String buildMailContentForResetPassword(String username, String password) {
		StringBuilder content = new StringBuilder();
		content.append("<html>").append("<head></head>").append("<body>")
				.append("<h3>Your password has been created successfully</h3>").append("<p>Username: ").append(username)
				.append("</p>").append("<p>Password: ").append(password).append("</p>").append("</body>")
				.append("<html>");

		return content.toString();
	}
        public static String buildMailContentForNewEC(ExtenuatingCircumstance ec) {
            StringBuilder content = new StringBuilder();
            content.append("<html>").append("<head></head>").append("<body>")
				.append("<h3>You have been assigned to a new Extenuating Circumstance: </h3>")
                                .append("<p><b>Title: </b>").append(ec.getTitle())
                                .append("</p><b>Description: </b>").append(ec.getDescription())
                                .append("<p/><p/><p>Please process it as soon as possible</p>")
				.append("<p>Regards,</p><p>Admin</p>").append("</body>")
				.append("<html>");
            return content.toString();
        }
       
        public static LocalDateTime GetCurrentDatetime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
}
