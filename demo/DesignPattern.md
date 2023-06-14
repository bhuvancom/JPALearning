# Note — This is just for learning purpose 

# Design Pattern
#### Design patterns are general solutions to routine problems in software design. Every pattern acts as a blueprint that allows customization to solve a given design problem in any code during the development of software modules. I have lined up the docket for this article as below:

1. Why do we need Design Patterns?
2. What are Design Patterns?
3. Structure of a Design Pattern
4. Types of Design Patterns
5. Creational Design Pattern
6. Structural Design Pattern
7. Behavioural Design Pattern
8. JEE Design Pattern
9. Overview of design patterns

### Why do we need Design Patterns?

A design pattern systematically names, motivates and explains a general design that addresses a recurring design problem in object-oriented systems. Design patterns are needed to represent some of the best practices followed and adopted in software development.

### What are Design Patterns?

Software Design Pattern can be defined as a software template or a description to solve a problem that occurs in multiple instances while designing a Software Application or a Software Framework.

The credit of Design Patterns goes to the Gang of Four. Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides were the authors of the book on the Java Design Patterns.

### Structure of a Design Pattern

Structure of any Design Pattern is considered to be highly organised. They are often documented in the form of a template such that, the users can visually identify the problem and instantaneously find the solution to it, based on the relationship between classes and objects. The Design Patterns Template described by the original authors of the Design Patterns is as follows:

![](https://miro.medium.com/v2/resize:fit:720/format:webp/1*dVz7jjbOUgp66hjza3SS2A.png)

### Types of Design Patterns

Typically, Java Design Patterns are divided into Four Categories and each of those are further classified as below:

![](https://miro.medium.com/v2/resize:fit:720/format:webp/0*ZvVEbKgNFZBJkSKu.png)

* Creational Design Patterns are concerned with the method of creating Objects.
* Structural Design Patterns deal with the composition of classes and objects which form larger structures.
* Behaviour Design Patterns are concerned with the responsibility and interaction between the objects.
* JEE Design Patterns are concerned with providing solutions to the Java EE-based software applications and frameworks.

### Creational Design Patterns

are preferred during the process of class initiation. The Creational Design Patterns are further classified as follows:

* Factory Pattern
* Abstract Factory Pattern
* Singleton Pattern
* Prototype Pattern
* Builder Pattern.
* Object Pool Pattern

Let us discuss some important Creational Java Design Patterns practically.

#### Factory Design Pattern: 
It follows the principle of “Define an interface or abstract class for creating an object but let the subclasses decide which class to instantiate“. The Factory Method Pattern is also known as Virtual Constructor.

#### Advantages:

It is designed to allow all the sub-classes to choose the type of objects so as to create them.
Example: We have three Cellular Network Plan which describes the call cost per minute. Here we have three different networks namely, abcNetwork, pqrNetwork, and xyzNetwork along with their charges per minute. 

```java
package FactoryDesignPattern;
 
abstract class CellularPlan {
         protected double rate;
         abstract void getRate();
         public void processBill(int minutes){
                System.out.println(minutes*rate);
         }
}
```

```java
package FactoryDesignPattern;
 
public class AbcNetwork extends cellularplan{
       public void getRate(){
              rate=1.50;
      }
}
```

```java
package FactoryDesignPattern;
 
public class PqrNetwork extends cellularplan{
        public void getRate(){
               rate=1.75;
        }
}
```
```java
package FactoryDesignPattern;
 
public class SelectNetworkFactory {
     public cellularplan getPlan(String planType){
          if(planType == null){
                  return null;
          }
          if(planType.equalsIgnoreCase("abcNetwork")) {
                  return new AbcNetwork();
          }
          else if(planType.equalsIgnoreCase("xyzNetwork")){
                  return new XyzNetwork();
          }
          else if(planType.equalsIgnoreCase("pqrNetwork")) {
                  return new PqrNetwork();
          }
     return null;
     }
}
```

```java
package FactoryDesignPattern;
import java.io.*;
 
public class phoneBill {
      public static void main(String args[])throws IOException{
            SelectNetworkFactory planFactory = new SelectNetworkFactory();
            System.out.print("Enter the name of network for which the bill will be generated: ");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            String networkName=br.readLine();
            System.out.print("Enter the number of minutes for bill will be calculated: ");
            int minutes=Integer.parseInt(br.readLine());
            Cellularplan p = PlanFactory.getPlan(networkName);
            System.out.print("Bill amount for "+networkName+" of "+minutes+" units is: ");
            p.getRate();
            p.processBill(minutes);
      }
}
```

#### Singleton Design Pattern:

It follows “define a class that has only one instance and provides a global point of access to it“. The class must ensure that only a single instance should be created and a single object can be used by all other classes.

#### Advantages:
It is designed to save memory.
The object is not created on every request, instead, a single instance is reused.
Example: We have a MySQL Database. Let us insert data into the database using one single object instance. Here, we have 4 different operations that are performed onto the database and all of those operations use one single object instance.

```java
class JDBCSingleton {
    private static JDBCSingleton jdbc;

    private JDBCSingleton() {
    }

    public static JDBCSingleton getInstance() {
        if (jdbc == null) {
            jdbc = new JDBCSingleton();
        }
        return jdbc;
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edureka?serverTimezone=Asia/Calcutta", "root", "");
        return con;
    }
}
```

## Structural Design Patterns

deal with the composition of classes and objects which form larger structures. They simplify the structure by identifying the relationship between the classes and objects. The Structural Design Patterns are further classified as follows:

* Facade Pattern
* Bridge Pattern
* Composite Pattern
* Decorator Pattern
* Adapter Pattern
* Flyweight Pattern
* Proxy Pattern
* Filter Pattern


#### Facade Design Pattern:
Describes a higher-level interface that makes the subsystem easier to use. Every Abstract Factory is a Facade Design Pattern.

#### Advantages:

It protects the end-users from the complex sub-system components.
Example: Now, let us use the Facade Design pattern to find out the cost of the franchise you wish to buy.

```java
//Franchise###########################################

package FacadeDesignPattern;
 
public interface Franchise {
      public void Option();
      public void Cost();
}

//KFC ################################################
public class KFC implements Franchise {
        public void Option() {
               System.out.println("KFC");
        }
public void Cost() {
        System.out.println("Rs 1,00,00,000");
        }
}

//McDonalds  ######################################
public class McDonalds implements Franchise {
       public void Option() {
             System.out.println("McDonalds");
       }
public void Cost() {
             System.out.println("Rs 90,00,000");
       }
}

//Dominos  ######################################
package FacadeDesignPattern;
 
public class Dominos implements Franchise {
       public void Option() {
            System.out.println("Dominos");
       }
public void Cost() {
            System.out.println("Rs 80,00,000");
       }
}

//FranchiseServiceReg   ################################
public class FranchiseServiceReg {
       private Franchise KFC;
       private Franchise McDonalds;
       private Franchise Dominos;
       public FranchiseServiceReg(){
              KFC = new KFC();
              McDonalds = new McDonalds();
              Dominos = new Dominos();
       }
       public void BuyKFCFranchise(){
              KFC.Option();
              KFC.Cost();
       } 
       public void BuyMcDonaldsFranchise(){
              McDonalds.Option();
              McDonalds.Cost();
       }
       public void BuyDominosFranchise(){
              Dominos.Option();
              Dominos.Cost();
       }
}

//Client     ####################################### 
public class Client {
         private static int choice;
         public static void main(String args[]) throws NumberFormatException, IOException{
                   do{
                              System.out.print("Welcome to Franchise Service Registration...!n");
                              System.out.print(" 1. KFC n");
                              System.out.print(" 2. McDonalds n");
                              System.out.print(" 3. Dominos n");
                              System.out.print(" 4. EXIT n");
                              System.out.print("Please Enter your Franchise Option Number: ");
                   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                   choice=Integer.parseInt(br.readLine());
                   FranchiseServiceReg sp=new FranchiseServiceReg();
 
         switch (choice) {
                   case 1: {
                           sp.BuyKFCFranchise();
                   }
                   break;
                  case 2: {
                          sp.BuyMcDonaldsFranchise();
                  }
                  break;
                   case 3: {
                          sp.BuyDominosFranchise();
                  }
                  break;
                  default: {
                          System.out.println("Please Check the input and try again");
                  }
          return;
          }
 
       }while(choice!=4);
    }
}
```

#### Adapter Design Pattern: 
Provides the interface according to client requirement while using the services of a class with a different interface. The Adapter Pattern is also known as Wrapper.

#### Advantages:

It is designed to enable two or more previously incompatible objects to interact with each other.

##### Example: 
Here is a simple Library Card example which is designed to issue a Library card to a new user of the library which includes all the details like a book holder ID, Account number and many more. 

```java

//Adapter #########################################################
package AdapterDesignPattern; 
public class Adapter {
       public static void main(String args[]){
             LibraryCard targetInterface=new BookHolder();
             targetInterface.giveLibraryDetails();
             System.out.print(targetInterface.getLibraryCard());
        }
}

//BookHolder #########################################################
public class BookHolder extends LibraryDetails implements LibraryCard{
          public void giveLibraryDetails(){
          try{
                    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("Enter the Library account holder name :");
                    String readername=br.readLine();
                    System.out.print("n");
                    System.out.print("Enter the account number of the library:");
                    long accno=Long.parseLong(br.readLine());
                    System.out.print("n");
                    System.out.print("Enter the Library name :");
                    String libraryname=br.readLine();
                    setAccHolderName(readername);
                    setAccNumber(accno);
                    setLibraryName(libraryname);
          }
          catch(Exception e){
                    e.printStackTrace();
          }
     }
     public String getLibraryCard() {
          long accno=getAccNumber();
          String accholdername=getAccHolderName();
          String lname=getLibraryName();
          return ("The Account number "+accno+" of "+accholdername+" in "+lname+ " Library is valid and authenticated for issuing the Library card. ");
      }
}

//LibraryCard #########################################################
public interface LibraryCard {
      public void giveLibraryDetails();
      public String getLibraryCard();
}

//library details #########################################################
public class LibraryDetails {
      private String LibraryName;
      private String BookHolderName;
      private long BookHolderID;
      public String getLibraryName() {
             return LibraryName;
       }
       public void setLibraryName(String LibraryName) {
             this.LibraryName = LibraryName;
       }
       public String getAccHolderName() {
             return BookHolderName;
       }
       public void setAccHolderName(String BookHolderName) {
             this.BookHolderName = BookHolderName;
       }
       public long getAccNumber() {
             return BookHolderID;
       }
       public void setAccNumber(long BookHolderID) {
             this.BookHolderID = BookHolderID;
       }
}
```

### Behavioural Design Patterns
are concerned with the responsibility and interaction between the objects. These Design Patterns make sure the objects are loosely coupled, yet, can easily communicate with each other. Behavioural Design Patterns are further classified as follows:
#### 1. Chain Of Responsibility Pattern
* Strategy Pattern
* Interpreter Pattern
* Iterator Pattern
* Mediator Pattern
* Memento Pattern
* Command Pattern
* State Pattern
* Observer Pattern
* Template Pattern
* Visitor Pattern


#### Strategy Design Pattern: 
It defines a family of functionality and encapsulates each one, and make them interchangeable. The Strategy Pattern is also known as Policy.

#### Advantages:
It is designed to provide a substitute for sub-classes.

##### Example:
we are going to consider a simple example of a calculator. We need to perform five different operations. We shall encapsulate all those five operations into Calculator class and execute the program using the Strategy Design Pattern.

```java

//Calculator $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
package StrategyDesignPattern;
 
public interface Calculator {
       public float calculation(float a, float b);
}

//Add $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class add implements Calculator{
       public float calculation(float a, float b) {
              return a+b;
       }
}

//Sub $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class sub implements Calculator{
       public float calculation(float a, float b) {
             return a-b;
      }
}

//Mul $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class mul implements Calculator{
       public float calculation(float a, float b) {
             return a*b;
       }
}

//Div $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class div implements Calculator{
      public float calculation(float a, float b) {
            return a/b;
      }
}

//Mod $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class mod implements Calculator{
        public float calculation(float a, float b) {
              return a%b;
        }
}

//Query $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
public class Query {
      private Calculator calci;
      public Query(Calculator calci){
               this.calci = calci;
      }
      public float executeStrategy(float num1, float num2){
               return calci.calculation(num1, num2);
      }
}

//Strategy $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ 
public class Strategy {
         public static void main(String[] args) throws NumberFormatException, IOException {
                  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                  System.out.print("Enter the first value: ");
                  float value1=Float.parseFloat(br.readLine());
                  System.out.print("Enter the second value: ");
                  float value2=Float.parseFloat(br.readLine());
                  Query query = new Query(new add());
                  System.out.println("Addition = " + query.executeStrategy(value1, value2));
                  query = new Query(new sub());
                  System.out.println("Subtraction = " + query.executeStrategy(value1, value2));
                  query = new Query(new mul());
                  System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
                  query = new Query(new div());
                  System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
                  query = new Query(new mod());
                  System.out.println("Multiplication = " + query.executeStrategy(value1, value2));
        }
}
```

#### Command Design Pattern: 
It encapsulates a request under an object as a command and passes it to the invoker object. Invoker object looks for the appropriate object which can handle this command and pass the command to the corresponding object and that object executes the command. It is also known as Action or Transaction.

#### Advantages:

It separates the object that invokes the operation with the object that actually performs the operation.

#### Example: 
This example demonstrates a simple command execution cycle where the user requires to exhibit switching on and off the various electronic devices in his houses like a bulb and stereo player. He invokes the command through an invoker object called a simple remote control.

```java

//Command #############################################
package CommandDesignPattern;
 
interface Command {
       public void execute();
}

//Light #############################################
class Light {
       public void on() {
             System.out.println("Light is on");
       }
public void off() {
       System.out.println("Light is off");
       }
}

//LightOnCommand  #############################################
class LightOnCommand implements Command {
         Light light;
         public LightOnCommand(Light light) {
                 this.light = light;
         }
         public void execute() {
                 light.on();
         }
}

//LightOffCommand #############################################
class LightOffCommand implements Command {
       Light light;
       public LightOffCommand(Light light) {
              this.light = light;
       }
       public void execute() {
              light.off();
       }
}

//Stereo #############################################
class Stereo {
       public void on() {
              System.out.println("Stereo is on");
       }
       public void off() {
              System.out.println("Stereo is off");
       }
       public void setCD() {
              System.out.println("Stereo is set " + "for CD input");
       }
       public void setDVD() {
             System.out.println("Stereo is set" + " for DVD input");
       }
       public void setRadio() {
             System.out.println("Stereo is set" + " for Radio");
       }
       public void setVolume(int volume) {
             System.out.println("Stereo volume set" + " to " + volume);
       }
}

//StereoOffCommand #############################################
class StereoOffCommand implements Command {
      Stereo stereo;
      public StereoOffCommand(Stereo stereo) {
             this.stereo = stereo;
      }
      public void execute() {
             stereo.off();
      }
}

//StereoOnWithCDCommand  #############################################
class StereoOnWithCDCommand implements Command {
         Stereo stereo;
         public StereoOnWithCDCommand(Stereo stereo) {
               this.stereo = stereo;
         }
         public void execute() {
               stereo.on();
               stereo.setCD();
               stereo.setVolume(11);
         }
}

//SimpleRemoteControl #############################################
class SimpleRemoteControl {
       Command slot;
       public SimpleRemoteControl() {
       }
       public void setCommand(Command command) {
               slot = command;
       }
       public void buttonWasPressed() {
               slot.execute();
       }
}

//RemoteControlTest #############################################
class RemoteControlTest {
        public static void main(String[] args) {
               SimpleRemoteControl remote = new SimpleRemoteControl();
               Light light = new Light();
               Stereo stereo = new Stereo();
               remote.setCommand(new LightOnCommand(light));
               remote.buttonWasPressed();
               remote.setCommand(new StereoOnWithCDCommand(stereo));
               remote.buttonWasPressed();
               remote.setCommand(new StereoOffCommand(stereo));
               remote.buttonWasPressed();
       }
}
```

#### Observer Design Pattern: 
It defines a one-to-one dependency so that when one object changes state, all its dependents are notified and updated automatically. The Memento pattern is also known as Dependents or Publish-Subscribe.

#### Advantages:

* It illustrates the coupling between the objects and the observer.
* It provides support for broadcast-type communication.

#### Example: 
We are going to execute a program using the Observer Design Pattern to display the current average score and the current predictable score of a cricket match.

```java

//Cricket Data ##########################################
package ObserverDesignPattern;
 
class CricketData{
       int runs, wickets;
       float overs;
       CurrentScoreDisplay currentScoreDisplay;
       AverageScoreDisplay averageScoreDisplay;
       public CricketData(CurrentScoreDisplay currentScoreDisplay, AverageScoreDisplay averageScoreDisplay){
              this.currentScoreDisplay = currentScoreDisplay;
              this.averageScoreDisplay = averageScoreDisplay;
       }
       private int getLatestRuns(){
              return 90;
       }
       private int getLatestWickets(){
              return 2;
       }
       private float getLatestOvers(){
             return (float)10.2;
       }
       public void dataChanged(){
             runs = getLatestRuns();
             wickets = getLatestWickets();
             overs = getLatestOvers();
             currentScoreDisplay.update(runs,wickets,overs);
             averageScoreDisplay.update(runs,wickets,overs);
       }
}

//Average Score Display ##########################################
class AverageScoreDisplay{
        private float runRate;
        private int predictedScore;
        public void update(int runs, int wickets, float overs){
                this.runRate = (float)runs/overs;
                this.predictedScore = (int) (this.runRate * 50);
                display();
        }
        public void display(){
                System.out.println("nAverage Score Display:n" + "Average Run Rate: " + runRate + "nPredictable Score: " + predictedScore);
        }
}

//Current Score Display ##########################################
class CurrentScoreDisplay{
     private int runs, wickets;
     private float overs;
     public void update(int runs,int wickets,float overs){
            this.runs = runs;
            this.wickets = wickets;
            this.overs = overs;
            display();
     }
public void display(){
            System.out.println("nCurrent Score Display: n" + "Current Runs: " + runs +"nWickets Lost:" + wickets + "nOvers Played: " + overs );
     }
}

//Main ##########################################
class Main{
        public static void main(String args[]){
               AverageScoreDisplay averageScoreDisplay = new AverageScoreDisplay();
               CurrentScoreDisplay currentScoreDisplay = new CurrentScoreDisplay();
               CricketData cricketData = new CricketData(currentScoreDisplay, averageScoreDisplay);
               cricketData.dataChanged();
        }
}
```

### JEE Design Patterns
are concerned with providing solutions to the Java EE-based software applications and frameworks. These patterns are widely used in Spring. JEE Design Patterns are further classified as follows:

* MVC Design Pattern
* Dependency Injection Pattern
* DAO Design Pattern
* Business Delegate Pattern
* Intercepting Filter Pattern
* Service Locator Pattern
* Transfer Object Pattern

Let us discuss some important JEE Design Patterns practically.

#### MVC Design Pattern: 
MVC Design Pattern is defined as follows: Models are basically objects used as blueprints for all of the objects that will be used in the application.
Views are used to represent the presentational aspect of the information and data located in the models.
Controllers control and act as both of the Models as well as Views. They serve as a connection between the Models and Views. Controllers are capable to instantiate, update and delete models. They load them with information and then send the data to the views to present to the end-user.

#### Advantages:
It supports multiple views for a model.

#### Example: 
We are going to use MVC Design Pattern to set and print the data of the school students.

```java
//MVC pattern #########################################

package MVCDesignPattern;

public class MVCPattern {
    public static void main(String[] args) {
        Student model = retriveStudentFromDatabase();
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setStudentName("Sandeep Shukla");
        controller.updateView();
    }

    private static Student retriveStudentFromDatabase() {
        Student student = new Student();
        student.setName("Karan Kumar");
        student.setRollNo("21CSE987");
        return student;
    }
}

//Student  #########################################
public class Student {
    private String rollNo;
    private String name;
    // getter setter
}

//StudentController #########################################
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentRollNo(String rollNo) {
        model.setRollNo(rollNo);
    }

    public String getStudentRollNo() {
        return model.getRollNo();
    }

    public void updateView() {
        view.printStudentDetails(model.getName(), model.getRollNo());
    }
}


//StudentView  #########################################
public class StudentView {
    public void printStudentDetails(String studentName, String studentRollNo) {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}
```

#### DAO Design Pattern: 
DAO is a pattern in which objects are dedicated to the communication with the Data Layer. These objects instantiate “SessionFactories” and handle all of the logic behind communicating with the database.

#### Advantages:
It uses common calls to retrieve objects.
#### Example: 
We are going display the Developer details of a certain IT company using the DAO Design Pattern.

```java
//Developer ##################################################

package Dao;

import java.util.List;
import java.util.ArrayList;
import java.util.List;

class Developer {
    private String name;
    private int DeveloperId;

    Developer(String name, int DeveloperId) {
        this.name = name;
        this.DeveloperId = DeveloperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeveloperId() {
        return DeveloperId;
    }

    public void setDeveloperId(int DeveloperId) {
        this.DeveloperId = DeveloperId;
    }
}

//DeveloperDAO  ##################################################
interface DeveloperDao {
    public List<Developer> getAllDevelopers();

    public Developer getDeveloper(int DeveloperId);

    public void updateDeveloper(Developer Developer);

    public void deleteDeveloper(Developer Developer);
}

//DeveloperDaoImpl ##################################################
class DeveloperDaoImpl implements DeveloperDao {
    List<Developer> Developers;

    public DeveloperDaoImpl() {
        Developers = new ArrayList<Developer>();
        Developer Developer1 = new Developer("Kiran", 101011);
        Developer Developer2 = new Developer("Vikrant", 124122);
        Developers.add(Developer1);
        Developers.add(Developer2);
    }

    public void deleteDeveloper(Developer Developer) {
        Developers.remove(Developer.getDeveloperId());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", deleted from database");
    }

    public List<Developer> getAllDevelopers() {
        return Developers;
    }

    public Developer getDeveloper(int DeveloperId) {
        return Developers.get(DeveloperId);
    }

    public void updateDeveloper(Developer Developer) {
        Developers.get(Developer.getDeveloperId()).setName(Developer.getName());
        System.out.println("DeveloperId " + Developer.getDeveloperId() + ", updated in the database");
    }
}

//DaoPatternDemo ##################################################
class DaoPatternDemo {
    public static void main(String[] args) {
        DeveloperDao DeveloperDao = new DeveloperDaoImpl();
        for (Developer Developer : DeveloperDao.getAllDevelopers()) {
            System.out.println("DeveloperId : " + Developer.getDeveloperId() + ", Name : " + Developer.getName());
        }
        Developer Developer = DeveloperDao.getAllDevelopers().get(0);
        Developer.setName("Lokesh");
        DeveloperDao.updateDeveloper(Developer);
        DeveloperDao.getDeveloper(0);
        System.out.println("DeveloperId : " + Developer.getDeveloperId() + ", Name : " + Developer.getName());
    }
}
```


### Overview of Design Patterns
* Java Design Patterns are designed to be reusable in multiple projects.
* They furnish solutions that help to define system architecture with ease.
* Design Patterns are capable to capture the software engineering experiences.
* They provide transparency to the design of an application.
* Design Patterns are provided by Expert Developers which prove that these are testified and well-proven solutions.
* Java Design Patterns provide clarity to the System Architecture and provide the possibility of building a better system.