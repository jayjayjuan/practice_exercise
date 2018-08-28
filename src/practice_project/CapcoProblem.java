package practice_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class CapcoProblem {
	
	//Capco problem --Start
	@Test
	@SuppressWarnings("resource")
	public void capcoProblem(){
		System.out.println("Capco problem");
		System.out.print("Input the number of rows: ");
		int numRows = new Scanner(System.in).nextInt();
		int numSeatsToBeTaken = 3;
		String[] allocSeats = {"1A", "1E", "1J", "2C", "2E", "2H", "3B", "3D", "3I", 
								"4G", "4H", "5B", "5C", "5E", "5F", "5I", "5J"};
		List<String> allocSeatsList = Arrays.asList(allocSeats);
		System.out.println("contains 5B?? "+ allocSeatsList.contains("5B"));
		System.out.print("Here are the allocated seats: " + allocSeats[0]);		
		for(int i=1; i<allocSeats.length; i++){
			System.out.print(", "+allocSeats[i]);
		}
		System.out.println(" ");
		
		//Get first the free seats
		List<String> freeSeats = getAllFreeSeat(allocSeats, numRows);
		
		//Get all possible combinations for the given number of seats
		List<String> allPossibleCombinationsS1 = getPossibleCombinationsByRangeOfChar('A', 'C', numSeatsToBeTaken);
		List<String> allPossibleCombinationsS2 = getPossibleCombinationsByRangeOfChar('D', 'G', numSeatsToBeTaken);
		List<String> allPossibleCombinationsS3 = getPossibleCombinationsByRangeOfChar('H', 'J', numSeatsToBeTaken);		
		
		//Get the total number of combinations
		int totalNumCombinations = 0;
		if(freeSeats != null && !freeSeats.isEmpty()){
			System.out.print("Here are the free seats: ");
			for(String s : freeSeats){
				System.out.print(s + ", ");
			}
			System.out.println(" ");
			
			for(int i=1; i<=numRows; i++){
				//number of combinations per sections
				StringBuilder section1 = new StringBuilder();				
				StringBuilder section2 = new StringBuilder();
				StringBuilder section3 = new StringBuilder();				
				
				//Get the free columns per row
				for(int j=0; j<freeSeats.size(); j++){
					//Get the allocated seat
					String allocSeat = freeSeats.get(j);
					String allocSeatRow = allocSeat.substring(0, (allocSeat.length()-1)); //allocated seat row
					String allocSeatCol = allocSeat.substring((allocSeat.length()-1), allocSeat.length()); //allocated seat column
					
					if(Integer.parseInt(allocSeatRow) == i){
						if(allocSeatCol.equals("A") || allocSeatCol.equals("B") || allocSeatCol.equals("C")){
							section1.append(allocSeatCol);
						} else if(allocSeatCol.equals("D") || allocSeatCol.equals("E") 
									|| allocSeatCol.equals("F") || allocSeatCol.equals("G")){
							section2.append(allocSeatCol);
						} else if(allocSeatCol.equals("H") || allocSeatCol.equals("I") || allocSeatCol.equals("J")){
							section3.append(allocSeatCol);
						}
					}
				}
				
				//Now check if the combinations of the free seats exist in the possible combinations
				//Section1
				if(section1.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section1.toString(), allPossibleCombinationsS1, numSeatsToBeTaken);														
				}
				//Section2
				if(section2.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section2.toString(), allPossibleCombinationsS2, numSeatsToBeTaken);				
				}
				//Section3
				if(section3.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section3.toString(), allPossibleCombinationsS3, numSeatsToBeTaken);							
				}
			}
		}
		
		
		System.out.println("Here is the total possible combinations for " + numSeatsToBeTaken + " seats: " +totalNumCombinations );
	}
	
	private List<String> getAllFreeSeat(String[] allocSeats, int numRows){
		List<String> freeSeats = new ArrayList<String>();
		for(int i=1; i<=numRows; i++){
			for(char j='A'; j<='J'; j++){
				StringBuffer freeSeat = new StringBuffer();
				freeSeat.append(i);
				freeSeat.append(j);
				boolean isFree = true;
				for(int k=0; k<allocSeats.length; k++){
					if(allocSeats[k].equals(freeSeat.toString())){
						isFree = false;
						break;
					}
				}
				
				if (isFree) {
					freeSeats.add(freeSeat.toString());
				}
			}
		}
		
		return freeSeats;
	}
	
	private List<String> getPossibleCombinationsByRangeOfChar(char start, char end, int numSeatsToBeTaken){
		List<String> allPossibleCombinations = new ArrayList<String>();
		for(char s=start; s<=end; s++){
			StringBuilder sb = new StringBuilder();
			for(char sub=s; sub<=end; sub++){				
				sb.append(sub);
				
				if(sb.toString().length() == numSeatsToBeTaken){
					allPossibleCombinations.add(sb.toString());
					break;
				}				
			}
		}
		
		return allPossibleCombinations;
	}	
	
	private int countTotalPossibleCombinationPerSection(String freeSeatCol, List<String> allPossibleCombinations, int numSeatsToBeTaken){
		int totalNumCombinations = 0;
		char[] freeSeatColChar = freeSeatCol.toCharArray();
		
		for(int s=0; s<freeSeatColChar.length; s++){
			StringBuilder sb = new StringBuilder();
			for(int sub=s; sub<freeSeatColChar.length; sub++){
				sb.append(freeSeatColChar[sub]);
				
				if(sb.toString().length() == numSeatsToBeTaken){
					for(int ii=0; ii<allPossibleCombinations.size(); ii++){
						if(sb.toString().equals(allPossibleCombinations.get(ii))){
							totalNumCombinations++;
							break;
						}
					}
					break;
				}				
			}
		}
		
		return totalNumCombinations;
	}
}
