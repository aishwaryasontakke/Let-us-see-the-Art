/* 
 * ArtWork.java 
 * 
 * Version: 
 *       1 
 * 
 * Revisions: 
 *       3
 */

/**
 * This program allows to play the game ’Let’s see the art'.  
 *
 * @author      Soniya Rode
 * @author      Aishwarya Sontakke
 */

import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class ArtWork{

	//Arrayb to store the art work.
	static String [][] art=new String[36][100];
	
	//Array to store the guessed alphabets.
	static char result[]=new char[6];
	
	//Number of incorrect guesses allowed.
	static int counter=9;

	static int change=0;
	
	//Number of correct alphabets found.
	static int found=0;
	static boolean yes=true;

	/**
     * awork method changes the art work according to no of incorrect guesses.
     * @param 		- 		-
     * @return 		void	The method does not return any type. 
    */
	public static void awork( ){

		
		for( int i=change;i<change+4;i++){
			for(int j=0;j<100;j++){
				art[i][j]=".";
			}
			
		}
		change+=4;

	}

/**
 * aworkPrint method prints the artwork.
 * @param 	-		-
 * @return  void    The method does not return any type.
 */

	public static void aworkPrint(){
		for(int i=0;i<36;i++){
			for(int j=0;j<100;j++){
				System.out.print(art[i][j]);
			}
			System.out.println();
		}

	}

/**
 * guess method checks if the alphabet guessed is present in the word.
 * If present stores the guessed alphabets in the result array.
 * 
 * @param 	-		-
 * @return  void    The method does not return any type.
 */

	public static void guess(String guess,String ans) {
		boolean flag=false;
		//for each alphabet in the word checks if guessed letter is present.
		for(int k=0;k<ans.length();k++)
		{ 	
			if(ans.charAt(k)==guess.charAt(0)){	
				flag=true;
				//To store letter in the result array
				for(int index=0;index<ans.length();index++){	
					if(result[index]=='_'){
						if(index==k) {

						result[index]=guess.charAt(0);
						found++;
						
						}
						else{
							
							result[index]='_';
						}

					}
				}
				
			}

		}
		System.out.print(">");
		
		//Prints the guess result		
		for(int index=0;index<ans.length();index++){
			System.out.print(result[index]+" ");
		}
		System.out.println();
		
		//For incorrect guess, prints the changed artwork.
		if(!flag){
			counter--;
			awork();
			aworkPrint();

			

		}
	}

/**
 * The main program.
 *
 * @param    args    command line arguments (ignored)
 */

	public static void main(String[] args){

    	Scanner scan = null;

    	//Array to store the words from the file
    	String [] words= new String[6];
    	String play;
    	int k=0;
    	
    	//Try and catch is implemented for file not found exception.
		try{
			
			//Take inout from the file
    		scan = new Scanner(new File("words.txt"));
    		
    		//Store input from the file into the words[]
    		while(scan.hasNextLine()){
    			words[k]=scan.nextLine();
  				k++;
    		}
    	}

		catch(FileNotFoundException e){
			e.printStackTrace();
		}

		//While the player wants to continue playing.
		while(yes){

			//Initalizing the result array with _ values
			for(int index=0;index<6;index++){
				result[index]='_';
			}

			//Creating the initial art work.
			for(int i=0;i<36;i++){
				for(int j=0;j<100;j++){
					art[i][j]="X";
				}
			}

			//Printing the initial art work.
			aworkPrint();

			//String r is the word to be guessed.
			String r = words[new Random().nextInt(words.length)];
			
			System.out.print(">");
						
			for(k=0;k<r.length()-1;k++){
				System.out.print("_ ");
			}

			//Scanner object to take user ip.
			Scanner sc = new Scanner(System.in);
			System.out.println();
			
			//Continue calling the guess function until counter turns 0 or word is found.
			while(counter>0 && found<6) {
				System.out.println("Enter your guess : ");
				System.out.println("found "+found);
				guess(sc.nextLine(),r);
			}
			
				
			//If the user guesses the correct word.
			if(found==6){
				System.out.println("You guessed the word.");
				System.out.println("You WON");

			}
			else{
				System.out.println("You lost");
				System.out.println("GAME OVER");
			}
			
			System.out.println("Do you want to continue (Y/N)");
			play=sc.nextLine();

			//If the user does not want to continue playing.
			if(play.equals("N")||play.equals("n")){
				yes=false;
				System.exit(0);
			}
			else if(play.equals("y")||play.equals("Y")){
				//Reset the values for a new game.
				found=0;
				counter=9;
				change=0;
			}
			else{
				System.out.println("In correct input.");
				System.exit(0);

			}
		}
	}
}