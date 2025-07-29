import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


//branch 01


public class AppInitializationTry {
    //===========Data Base =================     (for access all around the project)
    static String [][] users =new String[3][2];
    static String [][] customers =new String[100][4];

    static String [][] items =new String[100][4];
    static String[][] orders =new String[100][5];
    //programme initialization
    public static void main(String[] args) {

        

        Scanner input=new Scanner(System.in);
        boolean exitState = false;

        String initializePageQuestion[]=
                {
                        "1)do you want to login",
                        "2)Are you new to here",
                        "3)Do you want to exit the page"
                };

        while(!exitState){
            for(String question : initializePageQuestion){
                System.out.println(question);
            }
            System.out.println("your chois :");
            int userInput=input.nextInt();

            switch (userInput){
                case 1:
                    if(login()){
                        printUi("Dashboard");
                        openDashboard();
                    }
                    printUi("Application");
                    break;
                case 2:
                    if(register()){
                        printUi("Application");
                        openDashboard();
                    }
                    break;
                case 3:
                    System.out.println("Good bye!.");
                    return;
                default:
                    System.out.println("wrong input");
                    return;//exit
            }


        }

        //programme initialization

    }

    //login process
    public static boolean login(){
        printUi("Login");
        Scanner input = new Scanner(System.in);
        System.out.println("please enter email");
        String email= input.nextLine();
        System.out.println("enter password");
        String password= input.nextLine();
        //===================================
        for(int i=0;i< users.length;i++){
            if(users[i][0]!=null && users[i][0].equals(email) ){
                if(users[i][1].equals(password)){
                    System.out.println("Welcome again!");
                    return true;
                }else{
                    System.out.println("password incorrect");
                    return false;
                }
            }
        }
        System.out.println("email 404 not found!");
        return false;

    }
    //login process

    //register process
    public static boolean register(){
        //[free?]  [1?,2?,3?] ===>if someplace empty==>inscert value
        if(users[users.length-1][0]!=null){  //get last eliment of array (length of array-1)
            System.out.println("User data base full !");
            return false;
        }

        Scanner input=new Scanner(System.in);
        System.out.println("Enter email :");
        String email = input.nextLine();
        System.out.println("Enter password :");
        String password = input.nextLine();


        for(int x=0 ;x<users.length;x++){
            if(users[x][0]==null){
                users[x][0]=email;
                users[x][1]=password;
                return true;

            }else{
                if(users[x][0].equalsIgnoreCase(email)){   //cheack previous emails with new enter email
                    System.out.println("email is already excist!");
                    return false;
                }

            }
        }
        return false;

    }
    //register process
    //dashbord process
    public static void openDashboard(){
        Scanner input= new Scanner(System.in);
        String dashBoardQuestions[]={
                "1) customer managrement",
                "2) item managrement",
                "3) order managrement",
                "4) Logout",
        };
        while(true){
            for (String question:dashBoardQuestions) {
                System.out.println(question);
            }
            int userInput= input.nextInt();

            switch (userInput){
                case 1:
                    customerManagement();
                    break;
                case 2:
                    itemManagement();
                    break;
                case 3:
                    placeNewOrder();
                    break;
                case 4:return;
                default:return;
            }
        }

    }
    //dashbord process

    //==================================   customer process
    public static void customerManagement(){
        Scanner input= new Scanner(System.in);
        String customerQuestions[]={
                "1) Save customer ",
                "2) Find customer ",
                "3) Update customer ",
                "4) Delete customer ",
                "5) Find all customers ",
                "6) Back to home",
        };
        while(true){
            for (String question:customerQuestions) {  //for each loop
                System.out.println(question);
            }
            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    saveCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:updateCustomer();
                    break;
                case 4:deleteCustomer();
                    break;
                case 5:allCustomers();
                    break;
                case 6:
                    return;
                default:return;
            }

        }

    }

    //save customer process
    public static void  saveCustomer(){
        Scanner input=new Scanner(System.in);
        while(true){
            String nic,name,address;
            double salary;
            System.out.println("enter NIC :");
            nic= input.nextLine();
            System.out.println("enter Name :");
            name= input.nextLine();
            System.out.println("enter Address :");
            address= input.nextLine();
            System.out.println("enter Salary :");
            salary= input.nextDouble();
            input.nextLine();                            // Consume the newline character frome chat gpt

            customerforloop:
            for (int i=0;i< customers.length;i++){
                if(customers[i][0]!=null){
                    if(customers[i][0].equals(nic)){
                        System.out.println("Already exist");
                        break customerforloop;
                    }
                }else{
                    customers[i][0]=nic;
                    customers[i][1]=name;
                    customers[i][2]=address;
                    customers[i][3]=String.valueOf(salary); //string <=double
                    //==========================
                    System.out.println("Customer saved !");
                    System.out.println("1) do you want to add new customer?");
                    System.out.println("2) Back to main menu");
                    int option = input.nextInt();
                    input.nextLine();                    // Consume the newline character frome chat gpt
                    switch (option){
                        case 1:
                            break customerforloop;
                        case 2:
                            return;
                        default:
                            return;
                    }

                }

            }

        }

    }
    //save customer process

    //customer process
    public static void printUi(String position){
        Date date = new Date();   //use Util
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat =new SimpleDateFormat("HH-mm-ss");

        String simpleDate= dateFormat.format(date);
        String simpleTime= timeFormat.format(date);
        System.out.println("========"+simpleDate+"===="+simpleTime+"==>"+position);
    }

    public static int findCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC");
        String nic =input.nextLine();

        for(int i=0;i < customers.length;i++){
            if(customers[i][0] !=null){
                if(customers[i][0].equals(nic)){
                    System.out.println("===========customer=============");
                    System.out.println("NIC :"+customers[i][0]);
                    System.out.println("Name :"+customers[i][1]);
                    System.out.println("Address :"+customers[i][2]);
                    System.out.println("Salary :"+customers[i][3]);
                    System.out.println("===========customer=============");
                    return i;
                }
            }
        }
        System.out.println("404-customer not found!");
        return -1;
    }

    public static void updateCustomer(){
        int customer_i=findCustomer();
        //====update============
        if(customer_i != -1){
            Scanner input=new Scanner(System.in);
            String newName,newAddress;
            double newSalary;

            System.out.println("enter Name to update:");
            newName= input.nextLine();
            System.out.println("enter Address to update:");
            newAddress= input.nextLine();
            System.out.println("enter Salary to update:");
            newSalary= input.nextDouble();


            customers[customer_i][1]=newName;
            customers[customer_i][2]=newAddress;
            customers[customer_i][3]=String.valueOf(newSalary);
            System.out.println("customer Updated");

            //====update============
        }


    }

    public static void deleteCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC");
        String nic =input.nextLine();

        for(int i=0;i < customers.length;i++){
            if(customers[i][0] !=null){
                if(customers[i][0].equals(nic)){
                    customers[i][0]=null;
                    customers[i][1]=null;
                    customers[i][2]=null;
                    customers[i][3]=null;
                    System.out.println("Customer Deleted");
                    return;
                }
            }
        }
    }
    //find all customers
    public static void allCustomers(){
        for(int i=0;i < customers.length;i++){

            if (customers[i][0] !=null){
                System.out.println("customer :"+(i+1));
                System.out.println("===========customer=============");
                System.out.println("NIC :"+customers[i][0]);
                System.out.println("Name :"+customers[i][1]);
                System.out.println("Address :"+customers[i][2]);
                System.out.println("Salary :"+customers[i][3]);
                System.out.println("===========customer=============");
                System.out.println("\n");

            }


        }
    }

    //========================================   customer process
    //=================    order process    ============
    public static void placeNewOrder(){

        Scanner input=new Scanner(System.in);
        System.out.println("enter customer NIC :");
        String nic =input.nextLine();

        String name,address;
        double salary;
        //============find customer===========
        for(int i=0;i < customers.length;i++){
            if(customers[i][0] !=null){
                if(customers[i][0].equals(nic)){
                    System.out.println("===========customer=============");
                    name=customers[i][1];
                    address=customers[i][2];
                    salary=Double.parseDouble(customers[i][3]);   //[double <= string   <wrapper class>
                    System.out.println("===========customer=============");

                }
            }
        }
        //============find customer===========
        //============find item===========
        System.out.println("Insert code");
        String code =input.nextLine();

        double uniPrice=0;
        String description;
        int quantityOnHand;

        for(int i=0;i < items.length;i++){
            if(items[i][0] !=null){
                if(items[i][0].equals(code)){
                    System.out.println("===========item=============");
                    description =items[i][1];
                    quantityOnHand =Integer.parseInt(items[i][2]);  //String=> Int
                    uniPrice=Double.parseDouble(items[i][3]);       //String=> Double
                    System.out.println("===========item=============");
                }
            }
        }
        //============find item===========
        System.out.println("Insert order code");
        String orderId =input.nextLine();
        for (int i=0;i< orders.length;i++){
            if(orders[i][0]!=null){
                if(orders[i][0].equals(orderId)){
                    System.out.println("order id exists");
                    return;
                }else{
                    Date date =new Date();
                    SimpleDateFormat f= new SimpleDateFormat("yyyy-mm-dd");
                    String selectedDate = f.format(date);
                    orders[i][0]=orderId;
                    orders[i][1]=nic;
                    orders[i][2]=code;
                    orders[i][3]=selectedDate;
                    orders[i][4]=String.valueOf(uniPrice);
                }
            }
        }
        System.out.println("Order Completed");
        //order place

    }
    //=================    order process    ============
    public static int finditem(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert code");
        String code =input.nextLine();

        //double uniPrice=0;

        for(int i=0;i < items.length;i++){
            if(items[i][0] !=null){
                if(items[i][0].equals(code)){
                    System.out.println("===========item=============");
                    System.out.println("code :"+items[i][0]);
                    System.out.println("description :"+items[i][1]);
                    System.out.println("quantity on hand :"+items[i][2]);
                    System.out.println("uniPrice :"+items[i][3]);
                    System.out.println("===========item=============");
                    //uniPrice=+Integer.parseInt(item[i][2]);
                    return i;
                }
            }
        }
        //===item find


      return 0;
    }

    //=================     item process    =============
    public static void itemManagement(){ //item management      - code,descriptin,qtyOnHand,uniPrice
        Scanner input=new Scanner(System.in);
        String itemQuestions[]={
                "1) Save item ",
                "2) Find item ",
                "3) Update item ",
                "4) Delete item ",
                "5) Find all items ",
                "6) Back to home",
        };
        while (true){
            for(int i=0;i<6;i++){
                System.out.println(itemQuestions[i]);
            }
            int userInput = input.nextInt();

            switch(userInput){
                case 1 :
                    saveItem();
                    break;
                case 2 :
                    finditem();
                    break;
                case 3 :
                    updateItem();
                    break;
                case 4 :
                    deleteItem();
                    break;
                case 5 :
                    findAllItems();
                    break;
                case 6 :
                    return;
                default:
                    return;
            }
        }
    }

    public static void saveItem(){
        int itemCode,qtyOnHand;
        double uniPrice;
        String description;
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Enter code");
            itemCode=input.nextInt();
            System.out.println("Enter qunatity on hand");
            qtyOnHand=input.nextInt();
            System.out.println("Enter unit Price ");
            uniPrice=input.nextDouble();
            input.nextLine();// Consume newline character left from previous input
            System.out.println("Enter description");
            description=input.nextLine();

            boolean itemExists = false; // Flag to check if item already exists
            itemsforloop:
            for(int i=0;i<items.length;i++){
                if(items[i][0] == null) {
                    // Found an empty slot, save the item here
                    items[i][0] = String.valueOf(itemCode);
                    items[i][1] = description;
                    items[i][2] = String.valueOf(qtyOnHand);
                    items[i][3] = String.valueOf(uniPrice);
                    System.out.println("Item saved !");
                    itemExists = true; // Update flag
                    break; // Exit the loop after saving the item
                } else if (items[i][0].equals(String.valueOf(itemCode))) {
                    System.out.println("Already exist");
                    break itemsforloop;
                }
            }

            // Check if item was added or if the stock is full
            if (!itemExists) {
                System.out.println("Item stock is full");
            } else {
                System.out.println("1) Do you want to add a new item?");
                System.out.println("2) Back to main menu");
                int option = input.nextInt();
                input.nextLine(); // Consume newline character left from previous input
                switch (option) {
                    case 1:
                        break;
                    case 2:
                        return;
                    default:
                        return; // Return to the main menu for any other option
                }
            }
        }
    }

    public static void updateItem(){
        int item_i=finditem();
        //====update============
        if(item_i != -1){
            Scanner input=new Scanner(System.in);
            String newCode,newDiscription;
            double newUnitPrice;
            int newQntOnHnad;

            System.out.println("enter qunatity on hand to update:");
            newQntOnHnad= input.nextInt();
            System.out.println("enter Unit Price to update:");
            newUnitPrice= input.nextDouble();
            input.nextLine();
            System.out.println("enter Discription to update:");
            newDiscription= input.nextLine();
            //input.nextLine();

            items[item_i][1]=newDiscription;
            items[item_i][2]=String.valueOf(newQntOnHnad);
            items[item_i][3]=String.valueOf(newUnitPrice);

            System.out.println("item Updated");

            //====update============
        }



    }

    public static void deleteItem(){
        Scanner input =new Scanner(System.in);
        System.out.println("enter code of you want to delete item :");
        String deleteItem =input.nextLine();

        for(int i=0;i<items.length;i++){
            if(items[i][0] != null){
                if(deleteItem.equals(items[i][0])){
                    items[i][0]=null;
                    items[i][1]=null;
                    items[i][2]=null;
                    items[i][3]=null;
                    System.out.println("item Deleted");
                }
            }

        }
    }

    public static void findAllItems(){
        for(int i=0;i < items.length;i++){

            if (items[i][0] !=null){
                System.out.println("Item :"+(i+1));
                System.out.println("===========item=============");
                System.out.println("Code:"+items[i][0]);
                System.out.println("Description :"+items[i][1]);
                System.out.println("Quantity on hand  :"+items[i][2]);
                System.out.println("Unite Price:"+items[i][3]);
                System.out.println("===========item=============");
                System.out.println("\n");

            }


        }
    }

    //=================     item process    =============
}
