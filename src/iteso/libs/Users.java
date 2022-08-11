package iteso.libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Users {

	final static String USER_SUPERADMIN = "superAdmin";
	final static String USER_ADMIN = "admin";
	final static String USER_EMPLOYEE = "employee";

	private Users() {}

	private static void createTxtFile() {
		try {
			File myObj = new File("users.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				try {
					FileWriter myWriter = new FileWriter("users.txt", true);
					myWriter.write("1\n");
					myWriter.write("SuperAdmin\n");
					String password = Encript.encript("12345678");
					myWriter.write(password+"\n");
					myWriter.write("superAdmin");
					myWriter.close();
					Record.createTxtFile();
					Inventory.createInventoryTxt();
				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static String getUserName(int id) {
		String[][] information = usersInformation();

		if (id > information.length || id <= 0) return "";
		return information[id-1][1];
	}

	public static int loginValidation(String userName, String password) throws NumberFormatException, UnsupportedEncodingException {
		createTxtFile();
		String[][] information = usersInformation();

		for (int i = 0; i<information.length; i++) {
			if (information[i][1].equals(userName)) {
				if (information[i][2].equals("delete")) return -1;
				if (Encript.decript(information[i][2]).equals(password)) 
					return Integer.parseInt(information[i][0]); // correct password
				return -2; // incorrect password
			}
		}
		return -1;// user name does not exist
	}

	public static boolean validatePassword(int id, String password) throws UnsupportedEncodingException {
		String[][] information = usersInformation();

		if (id > information.length || id <= 0) return false;
		if (Encript.decript(information[id-1][2]).equals(password)) return true;
		return false;
	}

	public static int idToTypeUser(int idUser) {
		String[][] users = usersInformation();
		if (idUser > users.length || idUser < 1) return -1;

		return TypeUserInt(users[idUser-1][3]);
	}

	public static int TypeUserInt(String typeUser) {
		if(typeUser.equals(USER_SUPERADMIN)) return 0;
		if(typeUser.equals(USER_ADMIN)) return 1;
		return 2;
	}

	public static void changePassword(int id, String newPassword) throws UnsupportedEncodingException {
		String[][] information = usersInformation();

		if (id > information.length || id <= 0) return;
		information[id-1][2] = Encript.encript(newPassword);

		overwriteUsersTxt(information);
	}

	public static void addNewUser(String[] toAdd) throws UnsupportedEncodingException {
		String[][] inventory = usersInformation();
		toAdd[0] = ""+(inventory.length+1);
		toAdd[2] = Encript.encript(toAdd[2]);
		inventory = Inventory.appendArray2D(inventory, toAdd);

		overwriteUsersTxt(inventory);
	}

	public static boolean nameAvailability(String userName) {
		String[][] information = usersInformation();

		for (int i = 0; i<information.length; i++) {
			if (information[i][1].equals(userName))
				return false;			
		}
		return true;
	}

	public static int userCanBeDeleted(int idUser, String userName) {
		// 0 correct, 1 not exist, 2 no permission
		String[][] information = usersInformation();
		// employee
		if (idToTypeUser(idUser) == 2) return 2;
		// admin
		if (idToTypeUser(idUser) == 1) {
			for (int i = 0; i<information.length; i++) {
				if (information[i][1].equals(userName) && (information[i][3].equals("admin") || information[i][3].equals("superAdmin")))
					return 2;
				if (information[i][1].equals(userName) && information[i][3].equals("employee"))
					return 0;
			}
			return 1;
		}
		// superAdmin
		if (idToTypeUser(idUser) == 0) {
			for (int i = 0; i<information.length; i++) {
				if (information[i][1].equals(userName) && information[i][3].equals("superAdmin"))
					return 2;
				if (information[i][1].equals(userName) && (information[i][3].equals("admin") || information[i][3].equals("employee")))
					return 0;
			}
			return 1;
		}
		return 2;
	}

	public static void deleteUser(String userName) {
		String[][] information = usersInformation();

		for (int i = 0; i<information.length; i++) {
			if (information[i][1].equals(userName)) {
				information[i][3]="delete";
			}
		}

		overwriteUsersTxt(information);
	}

	public static String[][] toFrameUsers(String type){
		String[][] information = {};
		String[] toAdd = new String[3];
		try {
			File myObj = new File("users.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				toAdd[0] = myReader.nextLine();
				toAdd[1] = myReader.nextLine();
				myReader.nextLine();
				toAdd[2] = myReader.nextLine();

				if (type.equals(toAdd[2]) || type.equals("all")) {
					information = Inventory.appendArray2D(information, toAdd);
				}
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		

		return information;	
	}

	public static void overwriteUsersTxt(String[][] users) {
		try {
			FileWriter myWriter = new FileWriter("users.txt");
			for (int i = 0; i < users.length; i++) {
				for (int j = 0; j < users[i].length; j++) {
					myWriter.write(users[i][j]);
					if (!((i == users.length-1) && (j == users[i].length-1))) {
						myWriter.write("\n");
					}
				}
			}

			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static String[][] usersInformation() {
		String[][] information = {};

		createTxtFile();

		int lineCounter = 0;
		String[] toAdd = new String[4];
		String presta;
		try {
			File myObj = new File("users.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				presta = myReader.nextLine();
				toAdd[lineCounter%4] = presta;
				if (lineCounter%4 == 3) {						
					information = Inventory.appendArray2D(information, toAdd);
				}
				lineCounter++;
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		

		return information;	
	}

}
