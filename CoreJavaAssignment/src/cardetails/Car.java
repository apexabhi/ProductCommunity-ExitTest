package cardetails;

//Car Class
public class Car {
	private String carModel;			//variable to store model of car
	private String type;				//variable to store car type
	private String typeInsurance;		//variable to store insurance type
	private double cost;				//variable to store cost of car
	
	//constructor
	public Car(String cm, String t, String ti, double c) {
		carModel=cm;
		type=t;
		typeInsurance=ti;
		cost=c;
	}
	
	//getter for carModel
	public String getCarModel() {
		return carModel;
	}

	//setter for carModel
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	//getter for type of car
	public String getType() {
		return type;
	}

	//setter for type of car
	public void setType(String type) {
		this.type = type;
	}

	//getter for insurance type
	public String getTypeInsurance() {
		return typeInsurance;
	}

	//setter for insurance type
	public void setTypeInsurance(String typeInsurance) {
		this.typeInsurance = typeInsurance;
	}

	//getter for cost of car
	public double getCost() {
		return cost;
	}

	//setter for cost of car
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
