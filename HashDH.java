
	import java.util.List;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Scanner;


	public class HashDH {
		/*
		 * vCount: Keeps track of the how many values have been inserted into the table
		 * avgComp: sum of the collisions
		 * traversals: the number of collisions an inserted value faced before being successfully inserted 
		 * table: creating a table to hold the strings
		 * resize: set to true when we need to resize the function
		 */
		public static int vCount = 0;
		public static float avgComp = 0;
		public static int traversals = 0;
		public static String table[] = new String [2003];
		public static boolean resize = false;
		
		//returns a position in the table for a certain value
		//.hashCode() is a java function that takes a string and returns a unique integer
		public static int hashFunc(String key) {

			return Math.abs(key.hashCode()) % table.length;
		}

		//Second Hash Function used to determine step size
		public static int hashFunc2(String key) {

			int hash =  (Math.abs(key.hashCode())%7 + 1);
			return hash;
		}
		//checks how full the table is
		public static float checkLoads(){
			return (float) vCount / table.length;
		}
		//insert function calls search function which finds an empty spot in the table and returns that 
		//index, we also add to the total values inserted count and run the resize function to see if we need to 
		//change the size of table
		public static void insert(String key){
			int k = search(key);
			table [k] = key;
			if (!resize) {
				vCount++;
				resize();
			}
				
		}
		//implementation of Double Hashing equation to find a spot in the table
		//total number of collisions and the total number of collisions are kept on track here as well
		public static int search (String key){
			traversals = 0;
			int k = hashFunc (key);
			int k2 = hashFunc2 (key);
			int i = 0;
			while (table[k] != null){
				
				k = (int) ((k + k2) % table.length);
				i++;
				traversals++;
				
			}
			avgComp = avgComp + traversals;

			return k;
		}
		//The resize function is run when a value is inserted, we check how full the table is currently 
		//If the table becomes more than 60% full we increase the size by a factor 1.5
		//If the table is only about 25% full we can safely cut the size of the table in half without 
		//any negative side effects.
		public static void resize (){
			resize = true;
			String temp [] = table;
			if (checkLoads() >=0.6) {
				table = new String [(int) (temp.length * 1.5)];
				for (int i  =0; i < temp.length; i++){
					if (temp[i] != null){
						insert(temp[i]);
					}
				}
			}
			else if (checkLoads() <= 0.25 && temp.length > 2000){
				table = new String [temp.length/2];
				for (int i = 0; i < temp.length; i++){
					if (temp[i] != null){
						insert (temp[i]);
					}
				}
			}
			resize = false;
		}

		//IO function to read values from a text file and put each line as the element of an array
		public static String [] getAliases (String filename){
			Scanner sc = null;
			try {
				sc = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			List<String> lines = new ArrayList<String>();
			
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine());
			}
			
		
			String[] arr = lines.toArray(new String[0]);
			sc.close();
			return arr;
		}
		public static void main (String [] args){
			
			String [] Aliases = getAliases ("Alias.txt");
			int i = 0;
			while (i < 2000){
				insert(Aliases[i]);
				i++;
			}
			System.out.println ("Size of the table: " + table.length);
			System.out.println("The average number of camparisons: " + avgComp/2000);

		}
		
	}
