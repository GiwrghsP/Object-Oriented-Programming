public abstract class User {

    private String name;
    private String phone;

    User(String n, String p) {
        name = n;
        phone = p;
    }
    public String getName() {
        return name;
    }
    public void setAdmin(String name, String phone) {
        Admin admin = new Admin(name, phone);
    }
    public String getPhone() {
        return phone;
}
}
public class Admin extends User {

    private booleanisAdmin = true;

    Admin(String name, String phone) {
super(name, phone);
    }

}

import java.util.*;
public class Donator extends User {

   private Offers offersList;
   Donator(String name, String phone) {
        super(name, phone);
offersList = new Offers();
   }

   public Offers getoffersList() {
       return offersList;
}
   //οι wrapper μέθοδοι για την add και commit
public void donatorAdd(RequestDonationrd, Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
offersList.add(rd, o);
   }

   public void donatorCommit(Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
offersList.commit(o);
   }
}


import java.util.*;
public class Beneficiary extends User {

    private int noPersons = 1;
    private RequestDonationListreceivedList;
    private Requests requestsList;

    Beneficiary(String name, String phone) {
        super(name, phone);
requestsList = new Requests();
receivedList = new RequestDonationList();
    }

    public int getnoPersons() {
        return noPersons;
    }

    public void setnoPersons(int p) {
noPersons = p;
    }

    public RequestDonationListgetrecievedList() {
        return receivedList;
    }

    public Requests getrequestsList() {
        return requestsList;
    }
    //οι wrapper μέθοδοιγιατις add, modify, commit και remove
    public void beneficiaryRequestsAdd(RequestDonationrd, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
requestsList.add(rd, this, o);
    }

    public void beneficiaryRequestsModify(RequestDonationrd, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, NegativeQuantity {
requestsList.modify(rd, this, o);
    }

    public void beneficiaryRequestsCommit(Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
requestsList.commit(this, o);
    }

    public void beneficiaryRemove(RequestDonationrd) {
requestsList.remove(rd);
    }
}

public abstract class Entity {

    private String name;
    private String description;
    privateintid;
privatestaticintnum_entities = 0;    //στατική μεταβλητή για να παίρνουν αυτόματα τα αντικείμενα id όταν δημιουργούνται

public Entity(String name, String description) {
        this.name = name;
this.description = description;
        this.id = ++num_entities;
    }

    public String getName() {
        return name;
    }

    public String getEntityInfo() {
        return "Id: " + id + ". " + name + " is " + description + ".";
    }

    public int getId() {
        return id;
    }

    public abstract String getDetails();

    public String toString() {
        String s = getEntityInfo();
        s = s.concat(getDetails());
return s;
    }

}


public class Material extends Entity {

    final private double level1; 
    final private double level2; 
    final private double level3;

    public String getDetails() {
       return " " + getName() + " is a Material object and has level1 = " + level1 + ", level2 = " + level2 + ", level3 = " + level3 + ".";
    }
    public Material(String name, String description, double l1, double l2, double l3) {
        super(name, description);
        level1 = l1;
        level2 = l2;
        level3 = l3;
    }
    public double getLevel1() {
        return level1;
    }
    public double getLevel2() {
        return level2;
    }
    public double getLevel3() {
return level3;
    }
}


public class Service extends Entity {

   public Service(String name, String description) {
       super(name, description);
   }
   public String getDetails() {

       return " " + getName() + " is a Service object.";
}
}


public class RequestDonation {

    private Entity entity;
    private double quantity;

    public RequestDonation(Entity e, double q) {
        entity = e;
        quantity = q;
    }

    public Entity getEntity() {
        return entity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double q) {
quantity = q;
    }
}


import java.util.*;
public class Organization {

    private String name;
    private Admin admin;
    private List<Entity>entityList;
    List<Donator>donatorList;
    List<Beneficiary>beneficiaryList;
RequestDonationListcurrentDonations;

    public Organization(String name) {
        this.name = name;
entityList = new ArrayList<Entity>();
donatorList = new ArrayList<Donator>();
beneficiaryList = new ArrayList<Beneficiary>();
currentDonations = new RequestDonationList();
    }

    public List<Entity>getentityList() {
        return entityList;
    }

    public List<Donator>getdonatorList() {
        return donatorList;
    }

    public List<Beneficiary>getbeneficiaryList() {
        return beneficiaryList;
    }

    public RequestDonationListgetCurrentDonations() {
        return currentDonations;
    }

    public String getName() {
        return name;
    }

    public void setAdmin(Admin admin) {
this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void addEntity(Entity e) throws AddEntityWithSameId {
boolean flag = true;
        if(entityList.size() > 0) {
            for(Entity entity : entityList) {
                if(entity.getId() == e.getId()) {
                    flag = false;
                    throw new AddEntityWithSameId(); 
                }
            }
        }
        if(flag) {
entityList.add(e);
        }
    }

    public void removeEntity(Entity e) {
entityList.remove(e);
    }

    public void insertDonator(Donator d) throws UserAlreadyExists {
boolean flag = true;
        if(donatorList.size() > 0) {
            for(Donator donator : donatorList) {
                if(donator.getName().equals(d.getName())) { //συγκρίνουμετον Donator πουδώθηκεωςόρισμαμετους Donators στηdonatorListωςπροςτοόνομα
                    flag = false;
                    throw new UserAlreadyExists(d);
                }
            }
        }
        if(flag) {
donatorList.add(d);
        }
    }

    public void removeDonator(Donator d) {
donatorList.remove(d);
    }

    public void insertBeneficiary(Beneficiary b) throws UserAlreadyExists{
boolean flag = true;
        if(beneficiaryList.size() > 0) {
            for(Beneficiary beneficiary : beneficiaryList) {  //συγκρίνουμετον Beneficiary πουδώθηκεωςόρισμαμετους Beneficiaries στηbeneficiaryListωςπροςτοόνομα
                if(beneficiary.getName().equals(b.getName())) {
                    flag = false;
                    throw new UserAlreadyExists(b);
                }
            }
        }
        if(flag) {
beneficiaryList.add(b);
        }
    }

    public void removeBeneficiary(Beneficiary b) {
beneficiaryList.remove(b);
    }

    public void listEntities() {
System.out.println("Materials: ");
        for(Entity e : entityList) {
            if(e.getClass().equals(Material.class)) {
System.out.println(e.getEntityInfo());
            }
        }
System.out.println("\nServices: ");
        for(Entity e : entityList) {
            if(e.getClass().equals(Service.class)) {
System.out.println(e.getEntityInfo());
}
}
    }

    public void listBeneficiaries() {
        int count = 1;
        for(Beneficiary b : beneficiaryList) {
System.out.println(count + ". " + b.getName());
            ++count;
        }
    }

    public void listDonators() {
        int count = 1;
        for(Donator d : donatorList) {
System.out.println(count + ". " + d.getName());
++count;
        }
    }
}


import java.util.*;
public class RequestDonationList {

    private List<RequestDonation>rdEntities = new ArrayList<RequestDonation>();

    public List<RequestDonation>getrdEntities() {
        return rdEntities;
    }

    public RequestDonation get(int id) {
RequestDonation r = null;
        for(RequestDonationrd : rdEntities) {
            if(rd.getEntity().getId() == id) {
                r = rd;
            }
        }
        return r; 
    }

    public void add(RequestDonationrd, Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity {
        if(rd.getQuantity() >= 0) {
boolean flag = false;
if(get(rd.getEntity().getId()) != null) {   //καλούμε τη μέθοδο get() και δίνουμε σαν όρισμα το id του RequestDonation που παίρνει η συνάρτηση add() 
flag = true;                            //για να δούμε αν το rd περιέχεται στην λίστα rdEntities
rd.setQuantity(rd.getQuantity() + get(rd.getEntity().getId()).getQuantity());  //αλλάζουμε το Quantity του rd και μετά καλούμε την modify
modify(rd);
            }
if(flag == false) {     //αν flag == false τότε το rd δεν περιέχεται στην rdEntities
for(Entity e : o.getentityList()) {
                    if(rd.getEntity().getId() == e.getId()) {
                        flag = true;
rdEntities.add(rd);
                        break;
                    }
                }
                if(flag == false) {
                    throw new EntityOfRequestDoesNotExits();
                }
            }
        } else {
                throw new NegativeQuantity();
        }
    }

    public void remove(RequestDonationrd) {
rdEntities.remove(rd);
    }

    public void modify(RequestDonationrd) throws NegativeQuantity {
        if(rd.getQuantity() >= 0) {
            for(int i=0; i<rdEntities.size(); i++) {
                if(rdEntities.get(i).getEntity().getId() == rd.getEntity().getId()) {  //προσπελαύνουμετααντικείμενατηςrdEntitiesμέχριναβτούμετοrd
rdEntities.get(i).setQuantity(rd.getQuantity());
                    break;
                }
            }
        } else {
        throw new NegativeQuantity();
        }
    }

    public void monitor() {
        for(RequestDonationrd : rdEntities) {
System.out.println("Entity: " + rd.getEntity().getName());
System.out.println("Quantity: " + rd.getQuantity() + "\n");
        }
    }

    public void reset() {
rdEntities.clear();
}
}


import java.util.*;
public class Requests extends RequestDonationList {
    public void add(RequestDonationrd, Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity {
RequestDonation rd2 = o.getCurrentDonations().get(rd.getEntity().getId());
        if((rd2 != null) && (rd.getQuantity() <= rd2.getQuantity()) && (rd.getQuantity() >= 0)) { 
            if(validRequestDonation(rd, b)) {
super.add(rd, o);
            } else {
                throw new WrongBeneficiaryQuantity2();
            }
        } else {
            throw new WrongBeneficiaryQuantity1();
        }
    }
    public void modify(RequestDonationrd, Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, NegativeQuantity {
        if((rd.getQuantity() <= o.getCurrentDonations().get(rd.getEntity().getId()).getQuantity()) && (rd.getQuantity() >=0)) {  //ελέγχουμεανηποσότητατου Request 
//είναι μικρότερη από αυτή που υπάρχει ή αρνητική
if(validRequestDonation(rd, b)) {
super.modify(rd);
            } else {
                throw new WrongBeneficiaryQuantity2();
            }
        } else {
            throw new WrongBeneficiaryQuantity1();
        }
    }
    public booleanvalidRequestDonation(RequestDonationrd, Beneficiary b) {
        if(rd.getEntity().getClass().equals(Material.class)) {  //ελέγχουμετηκλάσητου Entity απότοRequestDonation
            double total;
            if(b.getrecievedList().get(rd.getEntity().getId()) != null) {   //ελέγχουμεαν o Beneficiary έχειλάβει donation απόαυτότο Material
                total = b.getrecievedList().get(rd.getEntity().getId()).getQuantity() + rd.getQuantity();
            } else {
                total = rd.getQuantity();
            }
            if(b.getnoPersons() == 1) {
                if(((Material)rd.getEntity()).getLevel1() < total) {
                    return false;
                } else {
                    return true;
                }
            } else if(b.getnoPersons() <= 4) {
                if(((Material)rd.getEntity()).getLevel2() < total) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if(((Material)rd.getEntity()).getLevel3() < total) {
                    return false;
                } else {
                    return true;
                }
            }   
        } else {
            return true;
        }
    }
    public void commit(Beneficiary b, Organization o) throws WrongBeneficiaryQuantity1, WrongBeneficiaryQuantity2, EntityOfRequestDoesNotExits, NegativeQuantity { 
        List<RequestDonation> list = new ArrayList<RequestDonation>();
        for(RequestDonationrd : getrdEntities()) {
RequestDonation rd2 = o.getCurrentDonations().get(rd.getEntity().getId());   //παίρνουμετοRequestDonationτουοργανισμού
            if((rd2 != null) && (rd.getQuantity() <= rd2.getQuantity())) { 
                if(validRequestDonation(rd, b)) {
                    double q = rd.getQuantity();   //τοαποθηκεύουμεγιατηνεκτύπωσηπαρακάτω
                    rd.setQuantity(o.getCurrentDonations().get(rd.getEntity().getId()).getQuantity() - rd.getQuantity());
o.getCurrentDonations().modify(rd);
list.add(rd);   //δεν μπορούμε να τα διαγράψουμε εδώ γιατί θα προκύψει εξαίρεση για αυτό τα αποθηκεύουμε σε μία λίστα και τα διαγράφουμε μετά
b.getrecievedList().add(rd, o);
System.out.println("Request for " + q + " of " + rd.getEntity().getName() + " has been successful.");
                } else {
                    throw new WrongBeneficiaryQuantity2(rd);
                }
            } else {
                throw new WrongBeneficiaryQuantity1(rd);
            }
        }
        for(RequestDonationrd : list) {
            remove(rd);
}
    }   
}


public class Offers extends RequestDonationList {
    public void commit(Organization o) throws EntityOfRequestDoesNotExits, NegativeQuantity{
       for(RequestDonationrd : getrdEntities()) {
           if(o.getCurrentDonations().get(rd.getEntity().getId()) == null) {
o.getCurrentDonations().add(rd, o);
           } else {
o.getCurrentDonations().modify(rd); 
}
       }
       reset();
    }
}


public class AddEntityWithSameId extends Exception {
    public AddEntityWithSameId() {
System.out.println("An entity with this id already exists.");
}
}

public class EntityOfRequestDoesNotExits extends Exception{
    public EntityOfRequestDoesNotExits() {
System.out.println("This entity does not exist in the organization.");
}
}

public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(User u) {
System.out.println("User " + u.getName() + " already exists.");
}
}

public class NegativeQuantity extends Exception{
    public NegativeQuantity() {
System.out.println("You cannot insert negative quantity.");
}
}

public class WrongMenuOption extends Exception {
    public WrongMenuOption() {
System.out.println("You must enter one of the options.");
}
}

public class WrongBeneficiaryQuantity1 extends Exception{
    public WrongBeneficiaryQuantity1() {
System.out.println("You must enter the right quantity for your request. The organization does not support this quantity.");
    }
    public WrongBeneficiaryQuantity1(RequestDonationrd) {
System.out.println("Request for " + rd.getQuantity() + " of " + rd.getEntity().getName() + " did not succeed because this quantity exceedes the one offered by the organization");
    }
}
public class WrongBeneficiaryQuantity2 extends Exception{
    public WrongBeneficiaryQuantity2() {
System.out.println("You are not entitled to this quantity.");
    }
    public WrongBeneficiaryQuantity2(RequestDonationrd) {
System.out.println("Request for " + rd.getQuantity() + " of " + rd.getEntity().getName() + " did not succeed because you are not entitled to that quantity.");
}
}


import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.io.*;
import java.util.*;
public class Menu {
    private static Organization o;
    public Menu(Organization org) {
        o = org;
startMenu();
    }
    private void startMenu() {
System.out.println("Insert your phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        if(phone.matches("[0-9]+") && (phone.length() >= 8)) {
            if(o.getAdmin().getPhone().equals(phone)) {
adminMenu(o.getAdmin());
            } else {
boolean flag = false;
                for(Donator d : o.getdonatorList()) {
                    if(d.getPhone().equals(phone)) {
                        flag = true;
donatorMenu(d);
                        break;
                    }
                }
                if(flag == false) {
                    for(Beneficiary b : o.getbeneficiaryList()) {
                        if(b.getPhone().equals(phone)) {
                            flag = true;
beneficiaryMenu(b);
                            break;
                        }
                    }
                }
                if(flag == false) {
registerMenu(phone);
                }
            }
        } else {
System.out.println("You must give correct phone number format.");
startMenu();
        }
    }
    private void adminMenu(Admin a) {
        try {
        System.out.println("############################################################");
System.out.println("Welcome " + a.getName() + " to the " + o.getName() + "\n");
System.out.println("1. View\n2. Monitor Organization\n3. Back\n4. Logout\n5. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option == 1) {
            adminMenu_1(a);
        } else if(option == 2) {
            adminMenu_2(a);
        } else if(option == 3) {
System.out.println("Can't go back from this point.");
adminMenu(a);
        } else if(option == 4) {
startMenu();
        } else if(option == 5) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        }catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
adminMenu(a);
        } catch(WrongMenuOption e) {
adminMenu(a);
        }
    }
    private void adminMenu_1(Admin a) {
        try {
System.out.println("1. View\n\t1. Material\n\t2. Services\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option == 1) {
            adminMenu_1_1(a);
        } else if(option == 2) {
            adminMenu_1_2(a);
        } else if(option == 3) {
adminMenu(a);
        } else if(option == 4) {
startMenu();
        } else if(option == 5) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            adminMenu_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_1(a);
        }
    }
    private void adminMenu_2(Admin a) {
        try {
System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\tb. List Donators\n\tc.Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b : o.getbeneficiaryList()) {
b.getrecievedList().reset();  
            }
adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
adminMenu(a);
            } else if(opt == 4) {
startMenu();
            } else if(opt == 5) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer or one of the letters.");
            adminMenu_2(a);
        }catch(WrongMenuOption e) {
            adminMenu_2(a);
        }
    }
    private void adminMenu_1_1(Admin a) {
        try {
            HashMap<Integer, Material> map = new HashMap<Integer, Material>();
            int count = 1;
            for(Entity e : o.getentityList()) {
                if(e.getClass().equals(Material.class)) {
map.put(count, (Material)e);
System.out.print(count + ". " + e.getName());
                    ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                    if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                    } else {
System.out.println(" (0)");
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt); 
            if(option <= 0 || option >= count) {
                throw new WrongMenuOption();
            } else {
System.out.println(map.get(option).getDetails());
adminMenu(a);
            }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            adminMenu_1_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_1_1(a);
        }
    }
    private void adminMenu_1_2(Admin a) {
        try {
            HashMap<Integer, Service> map = new HashMap<Integer, Service>();
            int count = 1;
            for(Entity e : o.getentityList()) {
                if(e.getClass().equals(Service.class)) {
map.put(count, (Service)e);
System.out.print(count + ". " + e.getName());
                    ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                    if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                    } else {
System.out.println(" (0)");
                    }
                }
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt); 
            if(option <= 0 || option >= count) {
                throw new WrongMenuOption();
            } else {
System.out.println(map.get(option).getDetails());
adminMenu(a);
            }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            adminMenu_1_2(a);
        } catch(WrongMenuOption e) {
            adminMenu_1_2(a);
        }
    }
    private void adminMenu_2_1_1(Admin a) {
        try {
o.listBeneficiaries();
        if(o.getbeneficiaryList().size() == 0) {
System.out.println("There are no beneficiaries.");
adminMenu(a);
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option <= 0 || option >o.getbeneficiaryList().size()) {
            throw new WrongMenuOption();
        } else {
            adminMenu_2_1_2(a, o.getbeneficiaryList().get(option-1));
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            adminMenu_2_1_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_2_1_1(a);
        }
    }
    private void adminMenu_2_1_2(Admin a, Beneficiary b) {
        try {
System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\t\tRecieved Entities: ");
System.out.print("\t\t\t");
        if(b.getrecievedList().getrdEntities().size() > 0) {
            for(RequestDonationrd : b.getrecievedList().getrdEntities()) {
System.out.print(rd.getEntity().getName() + "(" + rd.getQuantity() + ") ");
            }
        } else {
System.out.print("No entities received.");
        }
System.out.println("\n\t\ti. Clear recievedList\n\t\tii. Delete Beneficiary\n\tb. List Donators\n\tc. Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
b.getrecievedList().reset();
System.out.println("recievedList has been cleared");
adminMenu(a);
        } else if(option.equals("ii")) {
o.removeBeneficiary(b);
System.out.println("Beneficiary has been deleted");
adminMenu(a);
        } else if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b2 : o.getbeneficiaryList()) {
                b2.getrecievedList().reset(); 
            }
adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
                adminMenu_2_1_1(a);
            } else if(opt == 4) {
startMenu();
            } else if(opt == 5) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer or one of the letters.");
            adminMenu_2_1_2(a, b);
        }catch(WrongMenuOption e) {
            adminMenu_2_1_2(a, b);
        }
    }
    private void adminMenu_2_2_1(Admin a) {
        try {
o.listDonators();
        if(o.getdonatorList().size() == 0) {
System.out.println("There are no donators.");
adminMenu(a);
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option <= 0 || option >o.getdonatorList().size()) {
            throw new WrongMenuOption();
        } else {
            adminMenu_2_2_2(a, o.getdonatorList().get(option-1));
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            adminMenu_2_2_1(a);
        } catch(WrongMenuOption e) {
            adminMenu_2_2_1(a);
        }
    }
    private void adminMenu_2_2_2(Admin a, Donator d) {
        try {
System.out.println("1. View\n2. Monitor Organization\n\ta. List Beneficiaries\n\t\tb. List Donators\n\t\tWished offers: ");
System.out.print("\t\t\t");
        if(d.getoffersList().getrdEntities().size() > 0) {
            for(RequestDonationrd : d.getoffersList().getrdEntities()) {
System.out.print(rd.getEntity().getName() + "(" + rd.getQuantity() + ") ");
            }
        } else {
System.out.print("No offers.");
        }
System.out.println("\n\t\ti. Delete Donator\n\tc. Reset Beneficiaries Lists\n3. Back\n4. Logout\n5. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
o.removeDonator(d);
System.out.println("Donator has been deleted");
adminMenu(a);
        } else if(option.equals("a")) {
            adminMenu_2_1_1(a);
        } else if(option.equals("b")) {
            adminMenu_2_2_1(a);
        } else if(option.equals("c")) {
            for(Beneficiary b : o.getbeneficiaryList()) {
b.getrecievedList().reset(); 
            }
adminMenu(a);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                adminMenu_1(a);
            } else if(opt == 2) {
                adminMenu_2(a);
            } else if(opt == 3) {
                adminMenu_2_1_1(a);
            } else if(opt == 4) {
startMenu();
            } else if(opt == 5) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer or one of the letters.");
            adminMenu_2_2_2(a, d);
        }catch(WrongMenuOption e) {
            adminMenu_2_2_2(a, d);
        }
    }
    private void donatorMenu(Donator d) {
        try {
        System.out.println("############################################################");
System.out.println("Welcome " + d.getName() + " to the " + o.getName() + "\n");
System.out.println("1. Add Offer\n2. Show Offers\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            donatorMenu_1(d);
        } else if(option == 2) {
            donatorMenu_2_1(d);
        } else if(option == 3) {
d.donatorCommit(o);
System.out.println("Your offers have been commited.");
donatorMenu(d);
        } else if(option == 4) {
System.out.println("Can't go back from this point.");
donatorMenu(d);
        } else if(option == 5) {
startMenu();
        } else if(option == 6) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
donatorMenu(d);
        } catch(WrongMenuOption e) {
donatorMenu(d);
        } catch(EntityOfRequestDoesNotExits er) {
donatorMenu(d);
        } catch(NegativeQuantity nq) {
donatorMenu(d);
        }
    }
    private void beneficiaryMenu(Beneficiary b) { 
        try {
        System.out.println("############################################################");
System.out.println("Welcome " + b.getName() + " to the " + o.getName() + "\n");
System.out.println("1. Add Request\n2. Show Requests\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        System.out.println("\n############################################################");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            beneficiaryMenu_1(b);
        } else if(option == 2) {
            beneficiaryMenu_2_1(b);
        } else if(option == 3) {
b.beneficiaryRequestsCommit(o);
beneficiaryMenu(b);
        } else if(option == 4) {
System.out.println("Can't go back from this point.");
beneficiaryMenu(b);
        } else if(option == 5) {
startMenu();
        } else if(option == 6) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
beneficiaryMenu(b);
        } catch(EntityOfRequestDoesNotExits er) {
beneficiaryMenu(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
beneficiaryMenu(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
beneficiaryMenu(b);
        } catch(WrongMenuOption e) {
beneficiaryMenu(b);
        } catch(NegativeQuantity nq) {
beneficiaryMenu(b);
        }
    }
    private void beneficiaryMenu_1(Beneficiary b) {
        try {
System.out.println("1. Add Request\n\t1. Material\n\t2. Services\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            beneficiaryMenu_1_1(b); 
        } else if(option == 2) {
            beneficiaryMenu_1_2(b);
        } else if(option == 3) {
b.beneficiaryRequestsCommit(o);
beneficiaryMenu(b);
        } else if(option == 4) {
beneficiaryMenu(b);
        } else if(option == 5) {
startMenu();
        } else if(option == 6) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            beneficiaryMenu_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1(b);
        }catch(NegativeQuantity nq) {
            beneficiaryMenu_1(b);
        }
    }
    private void beneficiaryMenu_1_1(Beneficiary b) { 
        try {
        HashMap<Integer, Material> map = new HashMap<Integer, Material>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Material.class)) {
map.put(count, (Material)e);
System.out.print(count + ". " + e.getName());
                ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                } else {
System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
System.out.println("\n" + map.get(option).getEntityInfo());
System.out.println("Do you want to request this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
RequestDonationrd = new RequestDonation(map.get(option), quantity);
b.beneficiaryRequestsAdd(rd, o); 
beneficiaryMenu(b);
            } else if(option2.equals("n")) {
beneficiaryMenu(b);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            beneficiaryMenu_1_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1_1(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_1_1(b);
        }
    }
    private void beneficiaryMenu_1_2(Beneficiary b) {
        try {
        HashMap<Integer, Service> map = new HashMap<Integer, Service>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Service.class)) {
map.put(count, (Service)e);
System.out.print(count + ". " + e.getName());
                ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                } else {
System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt); 
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
System.out.println("\n" + map.get(option).getEntityInfo());
System.out.println("Do you want to request this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q); 
RequestDonationrd = new RequestDonation(map.get(option), quantity);
b.beneficiaryRequestsAdd(rd, o); 
beneficiaryMenu(b);
            } else if(option2.equals("n")) {
beneficiaryMenu(b);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            beneficiaryMenu_1_2(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_1_2(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_1_2(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_1_2(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_1_2(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_1_2(b);
        }
    }
    private void beneficiaryMenu_2_1(Beneficiary b) {
        try {
System.out.println("1. Add Request\n2. Show Requests\n\ta. Select Request\n\tb. Clear Requests\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            beneficiaryMenu_2_2(b);
        } else if(option.equals("b")) {
b.getrequestsList().reset();
System.out.println("Your requests have been cleared.");
beneficiaryMenu(b);
        } else if(option.equals("c")) {
b.beneficiaryRequestsCommit(o);
beneficiaryMenu(b);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                beneficiaryMenu_1(b);
            } else if(opt == 2) {
                beneficiaryMenu_2_1(b);
            } else if(opt == 3) {
b.getrequestsList().commit(b, o);
beneficiaryMenu(b);
            } else if(opt == 4) {
beneficiaryMenu(b);
            } else if(opt == 5) {
startMenu();
            } else if(opt == 6) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer or one of the letters.");
            beneficiaryMenu_2_1(b);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_2_1(b);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_2_1(b);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_2_1(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_1(b);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_2_1(b);
        }
    }
    private void beneficiaryMenu_2_2(Beneficiary b) {
        try {
        HashMap<Integer, RequestDonation> map = new HashMap<Integer, RequestDonation>();
        if(b.getrequestsList().getrdEntities().isEmpty()) {
System.out.println("There are no requests at the moment.");
beneficiaryMenu(b);
        } else {
            int count = 0;
            for(RequestDonationrd : b.getrequestsList().getrdEntities()) {
                ++count;
map.put(count, rd);
System.out.println(count + ". " + rd.getEntity().getName() + "(" + rd.getQuantity() + ")");
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            if(option <= 0 || option > count) {
                throw new WrongMenuOption();
            } else {
                beneficiaryMenu_2_3(b, map.get(option));
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            beneficiaryMenu_2_2(b);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_2(b);
        }
    }
    private void beneficiaryMenu_2_3(Beneficiary b, RequestDonationrd) {
        try {
System.out.println("1. Add Request\n2. Show Requests\n\ta.\n\t" + rd.getEntity().getName() + 
        "(" + rd.getQuantity() +")\n\t\ti. Delete this request\n\t\tii. Modify this request\n\tb. Clear Requests\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
b.beneficiaryRemove(rd);
System.out.println("Request removed");
beneficiaryMenu(b);
        } else if(option.equals("ii")) {
System.out.println("Enter new quantity");
            String op = scanner.nextLine();
            double opt = Double.parseDouble(op);
RequestDonation rd2 = new RequestDonation(rd.getEntity(), opt);
b.beneficiaryRequestsModify(rd2, o);
beneficiaryMenu(b);
        } else if(option.equals("a")) {
            beneficiaryMenu_2_2(b);
        } else if(option.equals("b")) {
b.getrequestsList().reset();
System.out.println("Your requests have been cleared.");
beneficiaryMenu(b);
        } else if(option.equals("c")) {
b.beneficiaryRequestsCommit(o);
beneficiaryMenu(b);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                beneficiaryMenu_1(b);
            } else if(opt == 2) {
                beneficiaryMenu_2_1(b);
            } else if(opt == 3) {
b.beneficiaryRequestsCommit(o);
beneficiaryMenu(b);
            } else if(opt == 4) {
beneficiaryMenu(b);
            } else if(opt == 5) {
startMenu();
            } else if(opt == 6) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give a double.");
            beneficiaryMenu_2_3(b, rd);
        } catch(EntityOfRequestDoesNotExits er) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongBeneficiaryQuantity2 w2) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongBeneficiaryQuantity1 w1) {
            beneficiaryMenu_2_3(b, rd);
        } catch(WrongMenuOption e) {
            beneficiaryMenu_2_3(b, rd);
        } catch(NegativeQuantity nq) {
            beneficiaryMenu_2_3(b, rd);
        }
    }
    private void registerMenu(String phone) {
        try {
System.out.println("Do you wish to be a donator or a beneficiary?");
System.out.println("\t1. Donator\n\t2. Beneficiary");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
System.out.println("Please enter your name: ");
            String name = scanner.nextLine();
            Donator d = new Donator(name, phone);
o.insertDonator(d);
donatorMenu(d);
        } else if(option == 2) {
System.out.println("Please enter your name: ");
            String name = scanner.nextLine();
            Beneficiary b = new Beneficiary(name, phone);
o.insertBeneficiary(b);
beneficiaryMenu(b);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
registerMenu(phone);
        } catch(WrongMenuOption e) {
registerMenu(phone);
        } catch(UserAlreadyExists u) {
startMenu();
        }
    }
    private void donatorMenu_1(Donator d) {
        try {
System.out.println("1. Add Offer\n\t1. Material\n\t2. Services\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option == 1) {
            donatorMenu_1_1(d); 
        } else if(option == 2) {
            donatorMenu_1_2(d);
        } else if(option == 3) {
d.donatorCommit(o);
System.out.println("Your offers have been commited.");
donatorMenu(d);
        } else if(option == 4) {
donatorMenu(d);
        } else if(option == 5) {
startMenu();
        } else if(option == 6) {
System.exit(0);
        } else {
            throw new WrongMenuOption();
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            donatorMenu_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_1(d);
        }
    }
    private void donatorMenu_2_1(Donator d) {
        try {
System.out.println("1. Add Offer\n2. Show Offers\n\ta. Select Offer\n\tb. Clear Offers\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("a")) {
            donatorMenu_2_2(d);
        } else if(option.equals("b")) {
d.getoffersList().reset();
System.out.println("Your offers have been cleared.");
donatorMenu(d);
        } else if(option.equals("c")) {
d.donatorCommit(o);
donatorMenu(d);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                donatorMenu_1(d);
            } else if(opt == 2) {
                donatorMenu_2_1(d);
            } else if(opt == 3) {
d.donatorCommit(o);
System.out.println("Your offers have been commited.");
donatorMenu(d);
            } else if(opt == 4) {
donatorMenu(d);
            } else if(opt == 5) {
startMenu();
            } else if(opt == 6) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer or one of the letters.");
            donatorMenu_2_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_2_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_2_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_2_1(d);
        }
    }
    private void donatorMenu_2_2(Donator d) {
        try {
        HashMap<Integer, RequestDonation> map = new HashMap<Integer, RequestDonation>();
        if(d.getoffersList().getrdEntities().isEmpty()) {
System.out.println("There are no offers at the moment.");
donatorMenu(d);
        } else {
            int count = 0;
            for(RequestDonationrd : d.getoffersList().getrdEntities()) {
                ++count;
map.put(count, rd);
System.out.println(count + ". " + rd.getEntity().getName() + "(" + rd.getQuantity() + ")");
            }
            Scanner scanner = new Scanner(System.in);
            String opt = scanner.nextLine();
            int option = Integer.parseInt(opt);
            if(option <= 0 || option > count) {
                throw new WrongMenuOption();
            } else {
                donatorMenu_2_3(d, map.get(option));
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            donatorMenu_2_2(d);
        } catch(WrongMenuOption e) {
            donatorMenu_2_2(d);
        }
    }
    private void donatorMenu_2_3(Donator d, RequestDonationrd) {
        try {
System.out.println("1. Add Offer\n2. Show Offers\n\ta.\n\t" + rd.getEntity().getName() + 
        "(" + rd.getQuantity() +")\n\t\ti. Delete this offer\n\t\tii. Modify this offer\n\tb. Clear Offers\n\tc. Commit\n3. Commit\n4. Back\n5. Logout\n6. Exit");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if(option.equals("i")) {
d.getoffersList().remove(rd);
System.out.println("Offer removed");
donatorMenu(d);
        } else if(option.equals("ii")) {
System.out.println("Enter new quantity");
            String q = scanner.nextLine();
            double opt = Double.parseDouble(q); 
RequestDonation rd2 = new RequestDonation(rd.getEntity(), opt);
d.getoffersList().modify(rd2);
donatorMenu(d);
        } else if(option.equals("a")) {
            donatorMenu_2_2(d);
        } else if(option.equals("b")) {
d.getoffersList().reset();
System.out.println("Your offers have been cleared.");
            donatorMenu_1(d);
        } else if(option.equals("c")) {
d.donatorCommit(o);
donatorMenu(d);
        } else {
            int opt = Integer.parseInt(option);
            if(opt == 1) {
                donatorMenu_1(d);
            } else if(opt == 2) {
                donatorMenu_2_1(d);
            } else if(opt == 3) {
d.donatorCommit(o);
System.out.println("Your offers have been commited.");
donatorMenu(d);
            } else if(opt == 4) {
donatorMenu(d);
            } else if(opt == 5) {
startMenu();
            } else if(opt == 6) {
System.exit(0);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give a double.");
            donatorMenu_2_3(d, rd);
        } catch(WrongMenuOption e) {
            donatorMenu_2_3(d, rd);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_2_3(d, rd);
        } catch(NegativeQuantity nq) {
            donatorMenu_2_3(d, rd);
        }
    }
    private void donatorMenu_1_1(Donator d) {
        try {
        HashMap<Integer, Material> map = new HashMap<Integer, Material>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Material.class)) {
map.put(count, (Material)e);
System.out.print(count + ". " + e.getName());
                ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                } else {
System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
System.out.println("\n" + map.get(option).getEntityInfo());
System.out.println("Do you want to offer this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
RequestDonationrd = new RequestDonation(map.get(option), quantity);
d.donatorAdd(rd, o); 
donatorMenu(d);
            } else if(option2.equals("n")) {
donatorMenu(d);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give a double.");
            donatorMenu_1_1(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1_1(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1_1(d);
        } catch(NegativeQuantity nq) {
            donatorMenu_1_1(d);
        }
    }
    private void donatorMenu_1_2(Donator d) {
        try {
        HashMap<Integer, Service> map = new HashMap<Integer, Service>();
        int count = 1;
        for(Entity e : o.getentityList()) {
            if(e.getClass().equals(Service.class)) {
map.put(count, (Service)e);
System.out.print(count + ". " + e.getName());
                ++count;
RequestDonationrq = o.getCurrentDonations().get(e.getId());
                if(rq != null) {
System.out.println(" (" + rq.getQuantity() + ")");
                } else {
System.out.println(" (0)");
                }
            }
        }
        Scanner scanner = new Scanner(System.in);
        String opt = scanner.nextLine();
        int option = Integer.parseInt(opt);
        if(option >= count || option < 1) {
            throw new WrongMenuOption();
        } else {
System.out.println("\n" + map.get(option).getEntityInfo());
System.out.println("Do you want to offer this material? (y/n)");
            String option2 = scanner.nextLine();
            if(option2.equals("y")) {
System.out.println("Insert quantity: ");
                String q = scanner.nextLine();
                double quantity = Double.parseDouble(q);
RequestDonationrd = new RequestDonation(map.get(option), quantity);
d.donatorAdd(rd, o); 
donatorMenu(d);
            } else if(option2.equals("n")) {
donatorMenu(d);
            } else {
                throw new WrongMenuOption();
            }
        }
        } catch(NumberFormatExceptionnfe) {
System.out.println("You must give an integer.");
            donatorMenu_1_2(d);
        } catch(WrongMenuOption e) {
            donatorMenu_1_2(d);
        } catch(EntityOfRequestDoesNotExits er) {
            donatorMenu_1_2(d);
        } catch(NegativeQuantity nq) {
donatorMenu_1_2(d);
        }
    }
}



public class Main {
    public static void Main(String[] args) {
        Organization o = new Organization("Donation System");
        Material milk = new Material("milk", "a white liquid", 2, 6, 11);
        Material sugar = new Material("sugar", "white and sweet", 1, 3, 5.5);
        Material rice = new Material("rice", "big on carbs", 2, 5.5, 10);
        Service MedicalSupport = new Service("MedicalSupport", "provided by a doctor");
        Service NurserySupport = new Service("NurserySupport", "provided by a nurse");
        Service BabySitting = new Service("BabySitting", "provided by an experienced babysitter");
        Admin admin = new Admin("John", "6930184620");
        Beneficiary b1 = new Beneficiary("Maria", "6939143220");
        Beneficiary b2 = new Beneficiary("Paul", "6981563100");
        Donator d1 = new Donator("George", "6992470137");
        Donator d2 = new Donator("Lisa", "6132870130");
o.setAdmin(admin);

        try {
o.addEntity(milk);
o.addEntity(sugar);
o.addEntity(rice);
o.addEntity(MedicalSupport);
o.addEntity(NurserySupport);
o.addEntity(BabySitting);
o.insertBeneficiary(b1);
o.insertBeneficiary(b2);
o.insertDonator(d1);
o.insertDonator(d2);
RequestDonation rd1 = new RequestDonation(milk, 20);
RequestDonation rd2 = new RequestDonation(rice, 15);
RequestDonation rd3 = new RequestDonation(MedicalSupport, 8);
RequestDonation rd4 = new RequestDonation(NurserySupport, 15);
RequestDonation rd5 = new RequestDonation(sugar, 15);
RequestDonation rd6 = new RequestDonation(MedicalSupport, 1);
RequestDonation rd7 = new RequestDonation(milk, 2);
            d1.donatorAdd(rd1, o);
            d1.donatorAdd(rd3, o);
            d1.donatorAdd(rd5, o);
            d1.donatorCommit(o);
            d2.donatorAdd(rd2, o);
            d2.donatorAdd(rd4, o);

            b1.beneficiaryRequestsAdd(rd7, o);
            b1.beneficiaryRequestsAdd(rd6, o);

            Menu menu = new Menu(o);
        } catch(UserAlreadyExists u) {
        } catch(AddEntityWithSameId a) {
        } catch(EntityOfRequestDoesNotExits e) {
        } catch(WrongBeneficiaryQuantity1 w1) {   
        } catch(WrongBeneficiaryQuantity2 w2) {
        } catch(NegativeQuantity nq) {}

}
}



