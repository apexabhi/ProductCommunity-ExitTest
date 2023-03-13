//Importing packages
import java.util.*;
import insurance.cover.Insurancee;

//Main class and contains driver function of the project
public class Main {
	//Driver function
	public static void main(String[] args) {
		try {											//exception handling
		ArrayList<Insurancee> arr=new ArrayList<>();//data structure to store calculated insurance and details of cars
		
		Main.input(arr);										//function to input details of car

		Main.displayResult(arr);			//to display results
		}catch(Exception e) {
			System.out.println("Error");
		}

	}
	
	//function to input details of cars
	private static void input(ArrayList<Insurancee> o) {
		//variable to store user's input
		String carModel;			
		String type;
		String typeInsurance;
		String cost;
		Double temp;
		char response;
		
		Scanner sc=new Scanner(System.in);			//to take input
		
		//loop begins
		do {
			System.out.println("Enter the model of car");
			carModel=sc.nextLine();
			
			System.out.println("Enter the type of car");
			type=sc.nextLine();
			// for handling invalid input entered by user
			while((type.equalsIgnoreCase("hatchback")==false) && (type.equalsIgnoreCase("suv")==false)
					&& (type.equalsIgnoreCase("sedan")==false)) {
				System.out.println("Inavlid input! Please select b/w hatchback,sedan and suv");
				type=sc.nextLine();
			}
			
			System.out.println("Enter the type of insurance of car i.e (Basic/Premium)");
			typeInsurance=sc.nextLine();
			//for handling invalid input entered by user
			while((typeInsurance.equalsIgnoreCase("basic")==false) 
					&& (typeInsurance.equalsIgnoreCase("premium")==false)) {
				System.out.println("Inavlid input! Please select b/w Basic or premium type of insurance");
				typeInsurance=sc.nextLine();
			}
			
			System.out.println("Enter the cost of car");
			cost=sc.nextLine();
			temp=0.0;
			//for handling invalid input
			while(true) {
				try {
					temp=Double.parseDouble(cost);
					break;
				}
				catch(Exception e) {
					System.out.println("Invalid input! Please enter valid cost");
					cost=sc.nextLine();
				}
			}
			
			Insurancee obj= new Insurancee(carModel,type,typeInsurance,temp);
			obj.calcInsurance();	//for calculating insurance
			o.add(obj);			//adding car details to the list
			
			System.out.println("Do you want to enter details of more car? Give response in 'Y/y' or 'N/n' ");
			response=sc.next().charAt(0);
			
			while((response!='Y') && (response!='N') && (response!='y') && (response!='n')) {
				System.out.println("You have entered invalid response!");
				System.out.println("Please give valid response");
				response=sc.next().charAt(0);
			}
			
			sc.nextLine();
			
		}while((response=='Y') || (response=='y'));
		
		//loop ends
		sc.close();
	}
	
	//function to display the result
	private static void displayResult(ArrayList<Insurancee> t) {
		t.get(0).displayCriteria();
		System.out.println("The total insurance details for the given car details are:-");
		for(int i=0;i<t.size();i++) {
			System.out.println("------------------------------------------------------------------------");
			System.out.print("The model of the car-> ");
			System.out.println(t.get(i).getCarModel());
			System.out.println();
			System.out.print("The cost of the car-> ");
			System.out.printf("%.2f",t.get(i).getCost());
			System.out.println();
			System.out.println();
			System.out.print("The type of the car-> ");
			System.out.println(t.get(i).getType());
			System.out.println();
			System.out.print("The insurance type of the car-> ");
			System.out.println(t.get(i).getTypeInsurance());
			System.out.println();
			System.out.print("The insurance calculated for the car-> ");
			System.out.printf("%.2f",t.get(i).getTotalInsurance());
			System.out.println();
			System.out.println("------------------------------------------------------------------------");
		}
	}
	
}
