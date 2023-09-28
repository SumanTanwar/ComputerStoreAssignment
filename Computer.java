package aug30;

/**
 * Computer class 
 * Assignment
 * @author Suman Tanwar
 * written by : Suman - 2297792
 * 
 */

public class Computer {

	private String brand;
	private String model;
	private long SN;
	private double price;
	private int noOfObject =0 ; //initialization of an integer object to count computers
	

	/**
	 * Parameterized Constructor  
	 * @param brand refers to String value
	 * @param model refers to String value
	 * @param sn refers to serial number value
	 * @param price refers to price value
	 */
	
	//Parameterized Constructor
	
	public Computer(String brand, String model, long sn, double price) 
	{

		noOfObject++;
		this.brand = brand;
		this.model = model;
		SN = sn;
		this.price = price;
	}
    
	  //Copy Constructor
	
	public Computer(Computer obj) {
		noOfObject++;
		this.brand = obj.brand;
		this.model = obj.model;
		SN = obj.SN;
		this.price = obj.price;
	}
    /**
     * method used to return the brand value 
     * @return type is String
     */
	public String getBrand() {
		return brand;
	}
   
	// Accessors & Mutators
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
   
	
	public String getModel() {
		return model;
	}
	
	
	public void setModel(String model) {
		this.model = model;
	}
	
	
	public long getSN() {
		return SN;
	}

	public void setSN(long sN) {
		SN = sN;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
   
	@Override
	public String toString() {
		return "Computer [brand=" + brand + ", model=" + model + ", SN=" + SN + ", price=" + price + "]";
	}
	/**
	 * showInfoByIndex method is used to print the information of computer
	 * @param index indicated the index of computer store in array 
	 */
	public void showInfoByIndex(int index)
	{
		System.out.println();
		System.out.println("Computer # " + index);
		System.out.println("Brand : " + this.brand);
		System.out.println("Computer Model : " + this.model);
		System.out.println("SN : " + this.SN);
		System.out.println("Computer Price : $" + this.price);
	}
	/**
	 * findNumberOfCreatedComputers used to get the number of object been created
	 * @return type is integer
	 */
	public int findNumberOfCreatedComputers()
	{
		return 	noOfObject;
	}
	
	/**
	 * Equals method compares two objects with each other 
	 * Computer objects are considered equal if they have the same brand, model and price
	 * @param obj takes the Computer object as the parameter
	 * @return type is boolean (if two computer are equals than it will return true else false)
	 */
	public boolean equals(Computer obj)
	{
		if(brand.equals(obj.brand) && price == obj.price && model.equals(obj.model))
		{
			return true;
		}
		return false;
	}

}
