import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MonthlyWorkingStatistics  extends Buyer{

    public void InitializecarInventory( ArrayList<Vehicle> inventoryList, int num_car_inventory)
    {
      
        for (int i = 0; i <num_car_inventory; i++)
          {  
            
           inventoryList.add(new RegularCars());
           inventoryList.add(new PickUpCars());
           inventoryList.add(new PerformanceCars());    
           inventoryList.add(new ElectricCars());
           inventoryList.add(new MonsterTrucks());
           inventoryList.add(new Motorcycles());
          }
               System.out.println("i m here");
              
       // Based on condition of the cars reduce their Cost Price and selling Price
          for (Vehicle car : inventoryList) {

              System.out.println("name :"+car.getVehicleName()+"  Car type :"+ car.VehicleType()+"  Vehicle Condition : "+ car.VehicleCondition());
            if (car.VehicleCondition().equals("Used"))            {               
                 car.setCarPrice(car.getVehicleCost()*0.8);  
                 car.setVehicleSP(car.getVehicleCost());          }
            else if (car.VehicleCondition().equals("Broken"))            {
                car.setCarPrice(car.getVehicleCost()*0.5);   
                car.setVehicleSP(car.getVehicleCost());          }
            else {car.setVehicleSP(car.getVehicleCost()); }
            // if electric car is like new then add range
           if (car.VehicleType().equals("ElectricCars") && car.VehicleCondition().equals("Like New"))
           {
            car.setVehicleRange(car.getVehicleRange()+100);
           }
            
          //  System.out.println("name :"+car.getVehicleName()+" Vehicle Cost : "+ car.getVehicleCost());
             
        }
        Collections.shuffle(inventoryList); // shuffling the order of vehicles stored in inventory 

    }
    public void start () throws IOException, ParseException{
    FileWriter writer = new FileWriter("SimResults.txt", false); // storing the output in the file
        
        // Creating employees
        ArrayList<Staff> s = new ArrayList<>(); 
        for (int k = 0; k <3; k++)
        {  
           // s.add(new Intern());
            s.add(new Mechanic());
            s.add(new Salesperson());
            s.add(new Driver()); // new drivers are added
        }

        //Adding interns wtith three different wash strategy
        s.add(new Intern(new ChemicalStrategy()));
        s.add(new Intern(new DetailedStrategy()));
        s.add(new Intern(new ElbowGreaseStrategy()));

        //to use later to check if each staff can wash or fix two vehicles per day
        for (Staff worker : s) {
              worker.setWorkCount(0);
              worker.setStaffStatus("Working"); // initializing as all ae active workers
              worker.setStaffHealthStatus("Healthy");
        }

       /*  for (Staff w : s) {
            if (w.getStaffType().equals("Intern"))
            {
                System.out.println("Wash behaviour"+w.getWashBehaviour());
            }
        }*/

        System.out.println("Startng------------------");
        for (Staff person : s) {
            System.out.println("Name: "+person.getStaffname()+"           Type: "+person.staffType() +"          Status:      "+person.getStaffStatus());
    }
       
        
         // Add vehicle list to inventory 
         ArrayList<Vehicle> inventoryList = new ArrayList<>();  // example of Identity 
         int num_car_inventory = 10;  
       InitializecarInventory(inventoryList, num_car_inventory);
           
        int pc =0;
        int puc =0;
        int rc =0 ; 
        int ec=0;
        int mt=0;
        int mc=0;

        // Creating vehicle list consisting 4 of each type
        ArrayList<Vehicle> v = new ArrayList<Vehicle>(); // example of Identity 
        for (Vehicle cars : inventoryList) {
            if (cars.VehicleType().equals("PerformanceCar") && pc <4)
            {  v.add(cars);
                pc ++;
            }
            if (cars.VehicleType().equals("PickUpCar") && puc <4)
            {  v.add(cars);
                puc ++;
            }
            if (cars.VehicleType().equals("RegularCar") && rc <4)
            {  v.add(cars);
                rc ++;
            }
            if (cars.VehicleType().equals("ElectricCars") && ec <4)
            {  v.add(cars);
                ec ++;
            }
            if (cars.VehicleType().equals("MonsterTrucks") && mt <4)
            {  v.add(cars);
                mt ++;
            }
            if (cars.VehicleType().equals("Motorcycles") && mc <4)
            {  v.add(cars);
                mc ++;
            }
        
        }
 
        ArrayList<Staff> allStaffs = new ArrayList<>(); 
        //List of all Staffs to create inventory
        allStaffs.addAll(s);

        //Initialiszing starting budget
        Double operatingBudget = 10000.00;
        System.out.println("size " + s.size());

        //initiate thetracker at the begining
        Tracker tracker = new Tracker ();
        //Running FNCD for 30 days
        for (int i = 1; i <31; i++)
        {  
           //Initiating the logger at the beginning
           Logger logger = new Logger();
           logger.getDate(i);
           String dateString = "2023-01-"+i;
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           Date  date = dateFormat.parse(dateString);
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           String dayWeekText = new SimpleDateFormat("EEEE").format(date);
           tracker.setTrackerDescription("\n Tracker : Day "+i);

        // Checking if Sunday or not as FNC is closed on Sunday
        if (dayWeekText.equals("Sunday"))
        {  
            writer.write  ("*** FNCD only runs for Racing Today *** Day "+ i+" "+dayWeekText+ " ***\n")  ;    
            System.out.println("*** FNCD only runs for Racing Today *** Day "+ i+" "+dayWeekText+ " *** "); 
            logger.setDescription("*** FNCD only runs for Racing Today *** Day "+ i+" "+dayWeekText+ " *** ");
            writer.write("....Current budget $"+operatingBudget+")\n");
            System.out.println("......Current budget $"+operatingBudget+")");
            tracker.setTrackerDescription("Current budget $"+operatingBudget);
    
            
            FNCD.racing(v,inventoryList,s,writer, logger); // Racing occurs on Sunday
           
             
        }
        else { 
            writer.write ("*** FNCD Day "+ i+" "+dayWeekText+ " ***\n")  ;    
            System.out.println("*** FNCD Day "+ i+" "+dayWeekText+ " *** ");   
            logger.setDescription("*** FNCD Day "+ i+" "+dayWeekText+ " *** ")  ;  
            operatingBudget= FNCD.opening(operatingBudget, s,allStaffs, v, inventoryList, writer,logger, tracker);
            FNCD.washing(s, v, writer, logger);
            FNCD.repairing( s, v, writer,logger);
            operatingBudget = FNCD.selling(operatingBudget, s, v, dayWeekText, inventoryList, writer, logger, tracker);
            if (dayWeekText.equals("Wednesday")) // Racing occurs on wednesday
            {
                FNCD.racing(v,inventoryList,s,writer, logger);
            }
            
            operatingBudget= FNCD.ending(operatingBudget, s ,allStaffs, writer,logger, tracker);
             System.out.println("size of inventory day end"+ inventoryList.size());

           System.out.println("size of staff after" + s.size());


            writer.write("\n");
            System.out.println("\n");
            writer.write("Name: \t      No Of Working Days:\t            Total Normal Salary:\t            Total Bonus: \t         Working Status :\t              Health Status : \n");
            System.out.println("Name: \t      No Of Working Days:\t            Total Normal Salary:\t            Total Bonus: \t         Working Status :\t          Health Status : \n");
            
            for (Staff person : allStaffs) {
      
              writer.write(person.getStaffname()+"\t         "+person.getWorkingDays()+"\t                               "+person.getTotalStaffSalary()
              +"\t                      "  +person.getTotalStaffBonus()+"\t               "+person.getStaffStatus() + "\t               "+person.getStaffHealthStatus() + "\n");
             System.out.println(person.getStaffname()+"\t         "+person.getWorkingDays()+"\t                               "+person.getTotalStaffSalary()
             +"\t                      "  +person.getTotalStaffBonus()+"\t               "+person.getStaffStatus()+ "\t               "+person.getStaffHealthStatus());
            }  
           
            writer.write("\n");
            System.out.println("\n");
            writer.write("Name: \t     Cost Price:\t            Sale Price:\t            Condition: \t         Cleanliness : \t           Status:  \t             Races Won: \n");
            System.out.println("Name: \t     Cost Price:\t            Sale Price:\t            Condition: \t         Cleanliness : \t           Status:  \t                Races Won: \n");
            
            for (Vehicle car : inventoryList) {
              //  car.setVehicleSP(car.getVehicleCost());
              writer.write(car.getVehicleName()+"\t         "+car.getVehicleCost()+"\t           "+car.getVehicleSP()    +"\t            "  +car.VehicleCondition()+"\t              "+car.VehicleCleanliness() + "\t             "+car.getVehicleStatus()+"\t             " +car.getRaceCount()+ "\n");
             System.out.println(car.getVehicleName()+"\t         "+car.getVehicleCost()+"\t           "+car.getVehicleSP()    +"\t            "  +car.VehicleCondition()+"\t              "+car.VehicleCleanliness() + "\t             "+car.getVehicleStatus()+"\t             "+car.getRaceCount());
            
            } 
 
          /*  for (Staff pp : s) {
            logger.setDescription(pp.getStaffname()+"  "+pp.getStaffType() + "  "+pp.getStaffHealthStatus());
            }*/
            
            
            
       }
       logger.writeToFile();
    }
    tracker.writeToFile();   
    writer.close();
    }  
}

