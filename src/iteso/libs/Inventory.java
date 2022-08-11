package iteso.libs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Inventory {

	private Inventory() {

	}

	public static void createInventoryTxt() {
		try {
			File myObj = new File("inventory.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static String[][] appendArray2D(String[][] array, String[] toAdd){		
		String[][] newArray = new String[array.length+1][];
		for(int i = 0; i< array.length; i++) {
			newArray[i] = array[i];
		}		
		newArray[array.length] = toAdd.clone();
		return newArray;
	}

	public static void printArray2D(String[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.println();
		}
	}

	public static int compareStrings(String word1, String word2)
	{
		for(int i = 0; i < Math.min(word1.length(), word2.length()); i++)
		{
			if((int)word1.charAt(i) != (int)word2.charAt(i))//comparing unicode values
				return (int)word1.charAt(i) - (int)word2.charAt(i);
		}
		if(word1.length() != word2.length())//smaller word is occurs at the beginning of the larger word
			return word1.length() - word2.length();
		else
			return 0;
	}
	
	public static boolean isAdded(String[][] array, String str) {
		for (int i = 0; i<array.length; i++)
			if(array[i][0].equals(str)) return true;
		return false;
	}
	
	public static int getQuantityProduct(String name) {
		String[][] inventory = inventoryInformation(0, "", false);
		for (int i = 0; i<inventory.length; i++) {
			if (inventory[i][1].equals(name) && inventory[i][5].equals("active")) return Integer.parseInt(inventory[i][2]);
		}
		
		return 0;
	}
	
	public static String getIdproduct(String name) {
		String[][] inventory = inventoryInformation(0, "", false);
		for (int i = 0; i<inventory.length; i++) {
			if (inventory[i][1].equals(name) && inventory[i][5].equals("active")) return inventory[i][0];
		}
		
		return "0";
	}

	public static void inputsInventory(String[][] input) {		
		String[][] inventory = inventoryInformation(0, "", false);

		for(int i = 0; i< input.length; i++) {
			for(int j = 0; j< inventory.length; j++) {
				if(input[i][0].equals(inventory[j][1]) && inventory[j][5].equals("active")) {
					int amount = Integer.parseInt(inventory[j][2]) + Integer.parseInt(input[i][1]);
					inventory[j][2] = ""+amount;
					break;
				}
			}
		}

		Inventory.overwriteInventoryTxt(inventory);
	}

	public static void outputsInventory(String[][] input) {
		String[][] inventory = inventoryInformation(0, "", false);

		for(int i = 0; i< input.length; i++) {
			for(int j = 0; j< inventory.length; j++) {
				if(input[i][0].equals(inventory[j][1]) && inventory[j][5].equals("active")) {
					int amount = Integer.parseInt(inventory[j][2]) - Integer.parseInt(input[i][1]);
					inventory[j][2] = ""+amount;
					break;
				}
			}
		}

		Inventory.overwriteInventoryTxt(inventory);
	}

	public static boolean productActive(String name) {
		String[][] inventory = inventoryInformation(0, "", false);
		for (int i = 0; i<inventory.length; i++) {
			if (inventory[i][1].equals(name)) {
				if (inventory[i][5].equals("active"))return true;
			}

		}	
		return false;
	}

	public static void addNewProduct(String[] toAdd) {
		String[][] inventory = inventoryInformation(0, "", false);
		toAdd[0] = ""+(inventory.length+1);
		inventory = appendArray2D(inventory, toAdd);

		overwriteInventoryTxt(inventory);
	}

	public static void editProduct(String[] toEdit) {
		String[][] inventory = inventoryInformation(0, "", false);

		for(int i = 0; i< inventory.length; i++) {
			if(toEdit[0].equals(inventory[i][1]) && inventory[i][5].equals("active")) {
				toEdit[2] = inventory[i][2];
				addNewProduct(toEdit);

				inventory = inventoryInformation(0, "", false);
				inventory[i][2] = "0";
				inventory[i][5] = "deleted";
				overwriteInventoryTxt(inventory);
				return;
			}

		}
	}

	public static void deleteProduct(String name) {
		String[][] inventory = inventoryInformation(0, "", false);
		for (int i = 0; i<inventory.length; i++) {
			if (inventory[i][1].equals(name) && inventory[i][5].equals("active")) {
				inventory[i][5] = "deleted";
				overwriteInventoryTxt(inventory);
				return;
			}
		}
	}

	public static void overwriteInventoryTxt(String[][] inventory) {
		try {
			FileWriter myWriter = new FileWriter("inventory.txt");
			for (int i = 0; i < inventory.length; i++) {
				for (int j = 0; j < inventory[i].length; j++) {
					myWriter.write(inventory[i][j]);
					if (!((i == inventory.length-1) && (j == inventory[i].length-1))) {
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

	public static String[][] inventoryOrderBy(int orderBy, boolean ascending, int find, String findName, boolean active) {
		String[][] inventory = inventoryInformation(find, findName, active);

		for(int i = 0; i < inventory.length - 1; i++)
		{
			for(int j = i+1; j < inventory.length; j++)
			{
				if (ascending) {
					if(orderBy == 1) {
						if(compareStrings(inventory[i][orderBy], inventory[j][orderBy]) > 0)//words[i] is greater than words[j]
						{
							String temp[] = inventory[i];
							inventory[i] = inventory[j];
							inventory[j] = temp;
						}
					}
					else {
						if(Double.parseDouble(inventory[i][orderBy]) - Double.parseDouble(inventory[j][orderBy]) > 0)//words[i] is greater than words[j]
						{
							String temp[] = inventory[i];
							inventory[i] = inventory[j];
							inventory[j] = temp;
						}
					}
				}
				else {
					if(orderBy == 1) {
						if(compareStrings(inventory[i][orderBy], inventory[j][orderBy]) < 0)//words[i] is greater than words[j]
						{
							String temp[] = inventory[i];
							inventory[i] = inventory[j];
							inventory[j] = temp;
						}
					}
					else {
						if(Double.parseDouble(inventory[i][orderBy]) - Double.parseDouble(inventory[j][orderBy]) < 0)//words[i] is greater than words[j]
						{
							String temp[] = inventory[i];
							inventory[i] = inventory[j];
							inventory[j] = temp;
						}
					}
				}

			}
		}
		return inventory;
	}

	public static String[][] inventoryInformation(int find, String findName, boolean active) {
		String[][] information = {};

		// Line Counter
		int lineCounter = 0;		
		try {
			File myObj = new File("inventory.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				myReader.nextLine();
				lineCounter++;
			}
			myReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// empty
		if (lineCounter < 6) {
			String[][] empty = {};
			return empty;
		}

		// no empty		
		lineCounter = 0;
		String[] toAdd = new String[6];
		try {
			File myObj = new File("inventory.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				toAdd[lineCounter%6] = myReader.nextLine();
				if (lineCounter%6 == 5) {
					if (active) {
						if (toAdd[5].equals("active")) {
							if (findName.length()<=0) {
								information = Inventory.appendArray2D(information, toAdd);
							}
							else {
								if (find == 0) {
									if (toAdd[1].startsWith(findName)) {
										information = Inventory.appendArray2D(information, toAdd);
									}
								}
								else if (find == 1) {
									if (toAdd[1].contains(findName)) {
										information = Inventory.appendArray2D(information, toAdd);
									}

								}
								else if (find == 2) {
									if (toAdd[1].endsWith(findName)) {
										information = Inventory.appendArray2D(information, toAdd);
									}
								}
							}

						}						
					}
					else {
						information = Inventory.appendArray2D(information, toAdd);
					}

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
