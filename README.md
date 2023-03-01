# OOAD_Project 3.2

# Team Members : Shaily Goyal, Shreya Maitra

<h2>Java Version :17.0.6</h2>

<h1>Changes made compare to previous UML are following:</h1>

<h3>Vehicle class:</h3> datatype added: carCleanliness(),carType(),carStatus() and 
            methods added: VehicleWashBonus(),VehicleRepairBonus(),VehicleSaleBonus()
      
<h3>Buyer class:</h3> methods added: getBuyingAbility(),getBuyingIntention(),getDesiredVehicle()

<h3>PerformanceCars class, RegularCars class, PickupCars class:</h3> methods added: VehicleWashBonus(), VehicleRepairBonus(), VehicleSaleBonus()

<h3>Staff class:</h3> method added: StaffType()

<h3>Salesperson class, Mechanic class, Intern class:</h3> methods added: StaffType(),determineStaffSalary()

<h3>FNCD class:</h3> methods added: hireIntern(), checkOperatingBudget()

<h2>Below is the updated UML daigram:</h2>

![pp3 (1)](https://user-images.githubusercontent.com/111729856/222281948-7293f8bf-a3c4-4102-9e4e-7420d538abbf.png)

# UML Changes
We are only considering Staff and Vehicle as the abstract class . In project 2 we had not considered Vehicle also as an abstract class.
getVehicleAddon() is the method used for Decorator Pattern. writeToFile() method is used for the Observer pattern

# Assumptions : 

We are starting the simulation considering that there are three types of staffs of each type Intern, Salerperson, Mechanic, Driver. 
Also we are considering that the inventory has 10 Vehicles of each type. The store vehicle stock has 4 of each types of Cars i.e. Regular Cars, Pick Up Cars, Performance Cars, Electric Cars, Monster Trucks, Motorcycles. Also we are considering that one sales person can sell multiple cars. Also it is likely that one of each type of Mechanic, Salesperson, Intern can quit and Injured Drivers leave. Buyer can have only one addon

<h2>References:</h2>
<h3>https://www.w3schools.com/</h3>
<h3>https://www.geeksforgeeks.org/</h3>
<h3>https://www.tutorialspoint.com/</h3>
<h3>https://www.javatpoint.com/ </h3>
