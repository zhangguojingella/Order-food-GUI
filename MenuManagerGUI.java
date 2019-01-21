/*
 * Class MenuManagerGUI
 * author:Guojing Zhang
 * pitt id: guz23
 * created:03/05/2018
 * Final Project
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class MenuItem {
//a super class of menu,containing appetizer, side, entree, beer
    private String name;
    private String description;
    private int calories;
    private double price;

    public MenuItem(String n, String desc, int cal, double p) {
        name = n;
        description = desc;
        calories = cal;
        price = p;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        name  = n;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String newDesc) {
        description = newDesc;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public void setCalories(int newCal) {
        calories = newCal;
    }    

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }//encapusulation

    public String toString() {
        return "name: "+ getName() + ". Description: "+ getDescription()+ 
                ". Calories: " + getCalories() +  ". Price: "+ getPrice();
    }
}//end of MenuItem

class Appetizer extends MenuItem {

    public Appetizer(String n, String desc, int cal, double p) {
        super(n, desc, cal, p);
    }

    public String toString() {
        return "Appetizer: " + super.toString();
    }
}//end Appetizer

class Entree extends MenuItem {

    public Entree(String n, String desc, int cal, double p) {
        super(n, desc, cal, p);
    }

    public String toString() {
        return "Entree: " + super.toString();
    }
}//end Entree

class Side extends MenuItem {

    public Side(String n, String desc, int cal, double p) {
        super(n, desc, cal, p);
    }

    public String toString() {
        return "Side: " + super.toString();
    }
}//end Side

class Beer extends MenuItem {

    public Beer(String n, String desc, int cal, double p) {
        super(n, desc, cal, p);
    }

    public String toString() {
        return "Beer: " + super.toString();
    }
}//end Beer

class Menu {

    private String name;
    private Appetizer appetizer;
    private Entree entree;
    private Side side;
    private Beer beer;

    public Menu(String n) {
        name = n;
        appetizer = null;
        entree = null;
        side = null;
        beer = null;
    }//constructor overloading 

    public Menu(String n, Entree ent, Side sid) {
        name = n;
        entree = ent;
        side = sid;
        appetizer = null;
        beer = null;
    }

    public Menu(String n, Entree ent, Side sid, Appetizer appet, Beer br) {
        name = n;
        entree = ent;
        side = sid;
        appetizer = appet;
        beer = br;
    }

    public int totalCalories() {
        int n = 0;
        if (appetizer != null) {
            n += appetizer.getCalories();
        }
        if (side != null) {
            n += side.getCalories();
        }
        if (entree != null) {
            n += entree.getCalories();
        }
        if (beer != null) {
            n += beer.getCalories();
        }
        return n;
    }//counting the total calories of one menu
    
    public double totalPrice(){
        double price = appetizer.getPrice()+
                entree.getPrice()+side.getPrice()+beer.getPrice();
        return price;
    }
    
    public String description() {
        if (appetizer != null) 
            return appetizer.getDescription();
        if (appetizer == null) 
            return "N/A";        
        if (entree == null)
            return "N/A";
        if (entree != null)
            return entree.getDescription();
        if (side == null) 
            return "N/A";
        if (side != null)
            return side.getDescription();
        if (beer == null) 
            return "N/A";
        else
            return beer.getDescription();
        }
    
    public String getName(){
        return name;
    }//return the name of the menu
    
    public Appetizer getA(){
        return appetizer;
    }

    public Entree getE(){
        return entree;
    }

    public Side getS(){
        return side;
    }    

    public Beer getB(){
        return beer;
    }    
    
    public String toString() {
        description();
        return "The name of menu is " + name + ", contains " + totalCalories() + " cal";
    }

}//end menu

class MenuManager {

    ArrayList<Appetizer> appetizers = new ArrayList<Appetizer>();
    ArrayList<Entree> entrees = new ArrayList<Entree>();
    ArrayList<Side> sides = new ArrayList<Side>();
    ArrayList<Beer> beers = new ArrayList<Beer>();//fields, all null
    public MenuManager(String dishesFile){
        ArrayList<MenuItem> menu = FileManager.readItems(dishesFile);
        //read the ArrayList by calling the readItems method
        for(MenuItem element:menu){//loop the ArrayList
            if(element instanceof Appetizer)
                appetizers.add((Appetizer) element);
            if(element instanceof Entree)
                entrees.add((Entree) element);
            if(element instanceof Side)
                sides.add((Side) element); 
            if(element instanceof Beer)
                beers.add((Beer) element);
        }
    }//seperate the MenuItem ArrayList into 4 arrayList,constructor
    public Menu randomMenu(String name){
        Appetizer menuA = appetizers.get((int)(Math.floor(Math.random()*appetizers.size())));
        Entree menuE = entrees.get((int)(Math.floor(Math.random()*entrees.size())));
        Side menuS = sides.get((int)(Math.floor(Math.random()*sides.size())));
        Beer menuB = beers.get((int)(Math.floor(Math.random()*beers.size())));
        Menu randMenu = new Menu(name,menuE,menuS,menuA,menuB);
        return randMenu;
    } 
    public Menu minCaloriesMenu(String name){
        Appetizer minA = (Appetizer)minC(appetizers.toArray());
        Entree minE = (Entree)minC(entrees.toArray());
        Side minS = (Side)minC(sides.toArray());
        Beer minB = (Beer)minC(beers.toArray());
        Menu minMenu = new Menu(name,minE,minS,minA,minB);
        return minMenu;
    }
    public Menu maxCaloriesMenu(String name){
        Appetizer maxA = (Appetizer)maxC(appetizers.toArray());
        Entree maxE = (Entree)maxC(entrees.toArray());
        Side maxS = (Side)maxC(sides.toArray());
        Beer maxB = (Beer)maxC(beers.toArray());
        Menu maxMenu = new Menu(name,maxE,maxS,maxA,maxB);
        return maxMenu;        
    }
    public MenuItem minC(Object [] I){//input is arraylist.toarray();
        int temp = 10000;
        MenuItem low = null;
        for(Object element : I){
            MenuItem a = (MenuItem) element;
            if(temp > a.getCalories()){
                temp = a.getCalories();
                low = a;
            }
        }
        return low;
    }//compare the element in the array and get the minCalories menuItem
    public MenuItem maxC(Object [] I){//input is arraylist.toarray();
        int temp = 0;
        MenuItem max = null;
        for(Object element : I){
            MenuItem a = (MenuItem) element;
            if(temp < a.getCalories()){
                temp = a.getCalories();
                max = a;
            }
        }
        return max;
    }
    public ArrayList<Appetizer> getAppetizer(){
        return appetizers;
    }
    public ArrayList<Entree> getEntree(){
        return entrees;
    }
    public ArrayList<Side> getSide(){
        return sides;
    }
    public ArrayList<Beer> getBeer(){
        return beers;
    }
    public void setAppetizer(Appetizer a){
        appetizers.add(a);
    }
    public void setEntree(Entree e){
        entrees.add(e);
    }
    public void setSide(Side s){
        sides.add(s);
    }
    public void setBeer(Beer b){
        beers.add(b);
    }    
}//end MenuManager

class FileManager {
    public static ArrayList<MenuItem> readItems(String fileName) {
        BufferedReader file = null;
        try{
            file = new BufferedReader(new FileReader(fileName));
            String line = null;
            ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
            while ( (line = file.readLine()) != null) {
                String[] part = line.split("@@");
                if(part[1].equals("appetizer"))       
                    menu.add(new Appetizer(part[0],
                            part[2],
                            Integer.parseInt(part[3]),
                            Double.parseDouble(part[4])));
                else if(part[1].equals("entree"))
                    menu.add(new Entree(part[0],
                            part[2],
                            Integer.parseInt(part[3]),
                            Double.parseDouble(part[4])));
                else if(part[1].equals("side"))
                    menu.add(new Side(part[0],
                            part[2],
                            Integer.parseInt(part[3]),
                            Double.parseDouble(part[4])));
                else if(part[1].equals("beer"))
                    menu.add(new Beer(part[0],
                            part[2],
                            Integer.parseInt(part[3]),
                            Double.parseDouble(part[4])));
                }
            
            return menu;
        }
        
        catch (IOException e) {
          e.printStackTrace();
        }
        return null;
    }//one method that read all menu in the txt,and return one ArrayList Of MenuItem
    public static void writeMenu(String fileName, ArrayList<Menu> menus){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            for(Menu itemMenu:menus){

                String MenuT = itemMenu.getName()+"@@"+itemMenu.getA().toString()+"@@"+
                        itemMenu.getE().toString()+"@@"+itemMenu.getS().toString()+"@@"+
                        itemMenu.getB().toString()+"@@"+"totalCalories: "+
                        itemMenu.totalCalories()+"@@"+"totalPrice: "+itemMenu.totalPrice();
                fos.write(MenuT.getBytes()); 
                fos.write("\r\n".getBytes()); 
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }//save the menu order to a txt file
}//end fileManager


public class MenuManagerGUI {
    private JFrame parentFrame,nameFrame,childFrame;
    private JPanel P1,P2,P3;
    private JLabel Created_Menus;
    private JLabel A,E,S,B,aJLabel,sJLabel,eJLabel,bJLabel;
    private JComboBox ca,cb,ce,cs;
    private JLabel nameLabel;
    private JTextField text;
    private JButton DetailsButton,Deleteall,SaveButton;    
    private JButton create,randomButton,min,max,makeSure;
    private JLabel totalC,totalP;
    private String nameString;
    private DefaultListModel nameList;
    private int indexA,indexB,indexE,indexS;
    private JList aList;
    private Menu newMenu,selectedMenu;
    private ArrayList<Menu> nameArr = new ArrayList<Menu>();
    private int type;
    private JTextArea tAppe,tEnt,tBeer,tSide,totalPrice,totalCal;
    
    private MenuManager MenuM = new MenuManager("test/dishes.txt");
    //a MenuManager object

    public MenuManagerGUI() {
        ParentFrame();//main frame
        nameFrame();//frame to input name of menu
	childFrame();//detail frame
    }//constructor of MenuManagerGUI

    private void ParentFrame(){
        
        parentFrame = new JFrame("Menu Manager");
        parentFrame.setBounds(90, 0, 1080, 740);
	parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	parentFrame.getContentPane().setLayout(null);
                
        P1 = new JPanel();
	P1.setBounds(20, 20, 500, 345);
        P1.setBorder(BorderFactory.createTitledBorder("Build your own Menu")); 
        P1.setLayout(null);
        parentFrame.getContentPane().add(P1);//panel 1 with name
                
        P2 = new JPanel();
	P2.setBounds(20, 365, 500, 345);
        P2.setLayout(null);
        P2.setBorder(BorderFactory.createTitledBorder("Or generate a Menu"));                
        parentFrame.getContentPane().add(P2);//panel 2, 3 buttons

        Created_Menus = new JLabel("Created Menus: ");
        Created_Menus.setBounds(545,10,200,45);
        parentFrame.getContentPane().add(Created_Menus);
                
        P3 = new JPanel();
        P3.setBounds(Created_Menus.getX(), Created_Menus.getY()+Created_Menus.getHeight(), 510, 600);
        P3.setLayout(null);
        P3.setBorder(BorderFactory.createTitledBorder(""));
        parentFrame.getContentPane().add(P3);//panel 3, with List of menus

        DetailsButton = new JButton("Details");
        DetailsButton.setBounds(545,665,158,45);
        parentFrame.getContentPane().add(DetailsButton);
        DetailsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                selectedMenu = nameArr.get(aList.getSelectedIndex());
                childFrame.setVisible(true);
                childFrame.setTitle(selectedMenu.getName());
                //name of the selected menu is the title of Details window
                tAppe.setText(selectedMenu.getA().toString());
                tEnt.setText(selectedMenu.getE().toString());
                tSide.setText(selectedMenu.getS().toString());
                tBeer.setText(selectedMenu.getB().toString());
                totalCal.setText(String.valueOf(selectedMenu.totalCalories()));
                totalPrice.setText(String.valueOf(selectedMenu.totalPrice()));
            }
        });//details button, make childframe visible         
                
        Deleteall = new JButton("Delete all");
        Deleteall.setBounds(DetailsButton.getX()+20+DetailsButton.getWidth(),665,158,45);
        parentFrame.getContentPane().add(Deleteall);
        Deleteall.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                aList.removeAll();
                nameList.clear();
                nameArr.clear();
            }
        });//clear List of menus,clear ArrayList of the Menus
        
                
        SaveButton = new JButton("Save to file");
        SaveButton.setBounds(Deleteall.getX()+20+Deleteall.getWidth(),665,158,45);
        parentFrame.getContentPane().add(SaveButton);
        SaveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                FileManager.writeMenu("test/All_menu.txt", nameArr);
            }
        });//calling writemenu method in FileManager class
        //and output the menus to All_menu.txt file
                
        A = new JLabel("Appetizer");
        A.setBounds(25,20,100,45);
        P1.add(A);
                
        E = new JLabel("Entree");
        E.setBounds(25,85,100,45);
        P1.add(E);

        S = new JLabel("Side");
        S.setBounds(25, 150, 100, 45);
        P1.add(S);
                
        B = new JLabel("Beer");
        B.setBounds(25, 215, 100, 45);
        P1.add(B);
        // 4 label in panel 1
    
        ca = new JComboBox();
	ca.setBounds(A.getX()+A.getWidth(), A.getY(), 475-A.getX()-A.getWidth(), 45);
	for(MenuItem name:MenuM.getAppetizer())
            ca.addItem(name.getName());//add all menuItem of appetizer to this combobox
        P1.add(ca);
        ca.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                indexA = ca.getSelectedIndex();
            }//get the selected MenuItem
        });  
               
        ce = new JComboBox();
	ce.setBounds(E.getX()+E.getWidth(), E.getY(), 475-E.getX()-E.getWidth(), 45);
	for(MenuItem name:MenuM.getEntree())
            ce.addItem(name.getName());
        P1.add(ce);  
        ce.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                indexE = ce.getSelectedIndex();
            }
        });        
                               
        cs = new JComboBox();
	cs.setBounds(S.getX()+S.getWidth(), S.getY(), 475-S.getX()-S.getWidth(), 45);
	for(MenuItem name:MenuM.getSide())
            cs.addItem(name.getName());
        P1.add(cs);  
        cs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                indexS = cs.getSelectedIndex();
            }
        });        
                                
        cb = new JComboBox();
        cb.setBounds(B.getX()+B.getWidth(),B.getY(), 475-B.getX()-B.getWidth(), 45);
	for(MenuItem name:MenuM.getBeer())
            cb.addItem(name.getName());
        P1.add(cb); 
        cb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                indexB = cb.getSelectedIndex();
            }
        });        
                
        create = new JButton("Create Menu with these dishes");
        create.setBounds(B.getX(),B.getY()+55,475-B.getX(),45);
        P1.add(create);
        create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nameFrame.setVisible(true);//set nameFrame visible to input name 
                type = 1;//to seperate which button the user pushed
            }
        });                
                
        randomButton = new JButton("Generate a Random Menu");
        randomButton.setBounds(25,45,475-B.getX(),45);
        P2.add(randomButton); 
	randomButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nameFrame.setVisible(true);
                type = 2;
            }
        }); 
        
        min = new JButton("Generate a minimum Colories Menu");
        min.setBounds(25,randomButton.getY()+randomButton.getHeight()+62,475-B.getX(),45);
        P2.add(min);
        min.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nameFrame.setVisible(true);
                type = 3;
            }
        }); 
        
        max = new JButton("Generate a Maximum Colories Menu");
        max.setBounds(25,min.getY()+min.getHeight()+62,475-B.getX(),45);
        P2.add(max);
        max.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nameFrame.setVisible(true);
                type = 4;
            }
        });//these four menu use the same nameFrame
        //so add type variable to know which button was pushed.
       
        nameList = new DefaultListModel();      
        aList = new JList(nameList);
        aList.setBounds(0,0,510,600);
        P3.add(aList);
        //add list at right side
    }//end parentFrame
    
    private void nameFrame(){
        nameFrame = new JFrame("Menu name");
        nameFrame.setBounds(200, 200, 400, 260);
        nameFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        nameFrame.getContentPane().setLayout(null);
        nameFrame.setVisible(false);
        
        nameLabel = new JLabel("Please enter the name of the menu: ");
        nameLabel.setBounds(25, 20, 300, 45);
        nameFrame.add(nameLabel);
        
        text = new JTextField(100);
        text.setBounds(20, 80, 300, 60);
        nameFrame.add(text);
        
        nameFrame.add(new JLabel());
        makeSure = new JButton("ok");
        makeSure.setBounds(20, 160, 300, 45);
        nameFrame.add(makeSure);
        makeSure.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                nameString = text.getText();//get the content the user input
                nameList.addElement(nameString);
                text.setText("");//when open it next time,empty it.
                if (type == 1){//when the user pushed "create menu"button
                newMenu = new Menu(nameString, MenuM.getEntree().get(indexE),
                        MenuM.getSide().get(indexS), MenuM.getAppetizer().get(indexA),
                        MenuM.getBeer().get(indexB));
                }
                else if(type == 2)//when the user pushed generate random menu button
                    newMenu = MenuM.randomMenu(nameString);
                else if(type == 3)//pushed minimum Calories Menu button
                    newMenu = MenuM.minCaloriesMenu(nameString);
                else//pushed maximum Calories Menu button
                    newMenu = MenuM.maxCaloriesMenu(nameString);
                nameArr.add(newMenu);//add the new menu to the ArrayList
                nameFrame.setVisible(false);
            }
        });
    }//end nameFrame
    
    private void childFrame(){
        childFrame = new JFrame();
        childFrame.setBounds(200, 200, 600, 500);
        childFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        childFrame.getContentPane().setLayout(null);
        childFrame.setVisible(false);
        
        aJLabel = new JLabel("Appetizer");
        aJLabel.setBounds(25, 25, 100, 45);
        childFrame.add(aJLabel);        
        
        eJLabel = new JLabel("Entree");
        eJLabel.setBounds(25, 95, 100, 45);
        childFrame.add(eJLabel);
        
        sJLabel = new JLabel("Side");
        sJLabel.setBounds(25, 165, 100, 45);
        childFrame.add(sJLabel);

        bJLabel = new JLabel("Beer");
        bJLabel.setBounds(25, 235, 100, 45);
        childFrame.add(bJLabel);        
        
        totalC = new JLabel("Total calories: ");
        totalC.setBounds(25, 305, 100, 45);
        childFrame.add(totalC); 
        
        totalP = new JLabel("Total prices: $ ");
        totalP.setBounds(25, 375, 100, 45);
        childFrame.add(totalP);
        
        tAppe = new JTextArea(10, 15);
        tAppe.setTabSize(4);
        tAppe.setFont(new Font("", Font.PLAIN,13));
        tAppe.setLineWrap(true);
        tAppe.setBounds(130, 25, 450, 45);
        tAppe.setEditable(false);
        childFrame.add(tAppe);
        
        tEnt = new JTextArea(10, 15);
        tEnt.setTabSize(4);
        tEnt.setFont(new Font("", Font.PLAIN,13));
        tEnt.setLineWrap(true);
        tEnt.setBounds(130, 95, 450, 45);
        tEnt.setEditable(false);
        childFrame.add(tEnt);
        
        tSide = new JTextArea(10, 15);
        tSide.setTabSize(4);
        tSide.setFont(new Font("", Font.PLAIN,13));
        tSide.setLineWrap(true);
        tSide.setBounds(130, 165, 450, 45);
        tSide.setEditable(false);
        childFrame.add(tSide);
        
        tBeer = new JTextArea(10, 15);
        tBeer.setTabSize(4);
        tBeer.setFont(new Font("", Font.PLAIN,13));
        tBeer.setLineWrap(true);
        tBeer.setBounds(130, 235, 450, 45);
        tBeer.setEditable(false);
        childFrame.add(tBeer);
        
        totalCal = new JTextArea(10, 15);
        totalCal.setTabSize(4);
        totalCal.setFont(new Font("", Font.PLAIN,13));
        totalCal.setLineWrap(true);
        totalCal.setBounds(130, 305, 450, 45);
        totalCal.setEditable(false);
        childFrame.add(totalCal);        
        
        totalPrice = new JTextArea(10, 15);
        totalPrice.setTabSize(4);
        totalPrice.setFont(new Font("", Font.PLAIN,13));
        totalPrice.setLineWrap(true);
        totalPrice.setBounds(130, 375, 450, 45);
        totalPrice.setEditable(false);
        childFrame.add(totalPrice);   
    }//end childFrame,shows the details of one menu,after pushed Details button
        
    public static void main(String[] args) {
        MenuManagerGUI window = new MenuManagerGUI();
        //create a MenuManagerGUI object calling the constructor
        window.parentFrame.setVisible(true);
        //set the main frame visible
    }//end main
}//end MenuManagerGUI



