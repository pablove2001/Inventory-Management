package iteso.libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Record {
	public static void createTxtFile() {
		try {
			File myObj = new File("record.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static String[] array2dTo1d(String[][] arr) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) { 
				list.add(arr[i][j]); 
			}
		}

		String[] vector = new String[list.size()];
		for (int i = 0; i < vector.length; i++) {
			vector[i] = list.get(i);
		}

		return vector;
	}	

	public static void addNewRecord(String inOrOut, int idUser, String[] toAdd)  {
		String[][] inventory = recordInformation();

		String[][] info = new String[1][];
		info[0] = new String[5];

		info[0][0] = ""+(inventory.length+1);//id
		info[0][1] = inOrOut; //in or out
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); // date time        
		info[0][2] = dtf.format(LocalDateTime.now());
		info[0][3] = Users.getUserName(idUser);
		info[0][4] = ""+(toAdd.length/2);

		info = Inventory.appendArray2D(info.clone(), toAdd.clone());
		toAdd = array2dTo1d(info.clone());
		inventory = Inventory.appendArray2D(inventory.clone(), toAdd.clone());
		overwriteRecordTxt(inventory);
	}

	public static boolean idExists(int id) {
		String[][] information = recordInformation();

		if (id > information.length || id < 1) return false;
		return true;
	}

	public static String[][] toFrameSearch(int id){
		String[][] information = {};
		int quantity = 0;
		int thisId = 0;
		String[] arr = new String[2];
		try {
			File myObj = new File("record.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				thisId = Integer.parseInt(myReader.nextLine());				
				myReader.nextLine();
				myReader.nextLine();
				myReader.nextLine();
				quantity = Integer.parseInt(myReader.nextLine());
				for (int i = 0; i<quantity; i++) {
					arr[0] = myReader.nextLine();
					arr[1] = myReader.nextLine();
					
					if (thisId == id) information = Inventory.appendArray2D(information, arr);
				}				
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		
		return information;
	}

	public static String[][] toFrameRecord(int show){ //0 inputs, 1 outputs and 2 all
		String[][] information = {};
		int quantity = 0;
		try {
			File myObj = new File("record.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				ArrayList<String> listAdd = new ArrayList<String>();
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				quantity = Integer.parseInt(myReader.nextLine());
				for (int i = 0; i<quantity; i++) {
					myReader.nextLine();
					myReader.nextLine();
				}

				String[] toAdd = listAdd.toArray(new String[listAdd.size()]);

				if ((toAdd[1].equals("Input") && show == 0) || (toAdd[1].equals("Output") && show == 1) || (show == 2))
					information = Inventory.appendArray2D(information, toAdd);

				listAdd.removeAll(listAdd);
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		

		return information;	
	}

	public static void overwriteRecordTxt(String[][] records) {
		try {
			FileWriter myWriter = new FileWriter("record.txt");
			for (int i = 0; i < records.length; i++) {
				for (int j = 0; j < records[i].length; j++) {
					myWriter.write(records[i][j]);
					if (!((i == records.length-1) && (j == records[i].length-1))) {
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

	public static String[][] recordInformation() {
		String[][] information = {};

		int quantity = 0;
		try {
			File myObj = new File("record.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				ArrayList<String> listAdd = new ArrayList<String>();
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				listAdd.add(myReader.nextLine());
				quantity = Integer.parseInt(myReader.nextLine());
				listAdd.add(""+quantity);
				for (int i = 0; i<quantity; i++) {
					listAdd.add(myReader.nextLine());
					listAdd.add(myReader.nextLine());
				}

				String[] toAdd = listAdd.toArray(new String[listAdd.size()]);

				information = Inventory.appendArray2D(information, toAdd);
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}		

		return information;	
	}
}
