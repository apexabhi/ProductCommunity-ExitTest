//Importing packages
package insurance.cover;

//Insurance Class 
public class Insurancee extends cardetails.Car {
	private String str;			//variable to show criteria
	private double totalInsurance;		//to store insurance calculated
	private final String[] types= {"hatchback","suv","sedan"};    //to compare which type of car user entered
	
	//constructor
	public Insurancee(String cm, String t, String ti, double c) {
		super(cm,t,ti,c);
		totalInsurance=0;
	}
	
	//getter for TotalInsurance
	public double getTotalInsurance() {
		return totalInsurance;
	}

	//setter for TotalInsurance
	public void setTotalInsurance(double totalInsurance) {
		this.totalInsurance = totalInsurance;
	}

	//function to display the criteria how insurance is calculated
	public void displayCriteria() {
		str="***\nThe insurance calculation rules for 3 types are as follows: \n"
				+ "Hatchback: 5% of Car cost price\n" + 
				"Sedan: 8% of Car cost price\n" + 
				"SUV: 10% of Car cost price\n" + 
				"If insurance is premium, increase the premium value by 20%\n***\n";
		System.out.print(str);
	}
	
	//function to calculate insurance
	public void calcInsurance() {
		String s=getType();
		String i=getTypeInsurance();
		if(s.equalsIgnoreCase(types[0])) {
			totalInsurance=0.05*getCost();
		}
		else if(s.equalsIgnoreCase(types[2])) {
			totalInsurance=0.08*getCost();
		}
		else if(s.equalsIgnoreCase(types[1])) {
			totalInsurance=0.1*getCost();
		}
		
		if(i.equalsIgnoreCase("premium")) {
			double temp=0.2*totalInsurance;
			totalInsurance+=temp;
		}
		totalInsurance=Math.round(totalInsurance * 100);
		totalInsurance=totalInsurance/100;
	}	
}
