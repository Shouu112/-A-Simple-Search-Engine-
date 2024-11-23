/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;
import java.util.Scanner;
/**
 *
 * @author jojo0
 */
public class main {
       
     public static Scanner input = new Scanner (System.in);
     public static SearchEng Se = new SearchEng();
     
    public static int menu()
    {
        System.out.println("(1) Term Retrieval. ");
        System.out.println("(2) Boolean Retrieval. ");
        System.out.println("(3) Ranked Retrieval.");
        System.out.println("(4) Indexed Documents.");
        System.out.println("(5) Indexed Tokens.");
        System.out.println("(6) Exist.");
        
        System.out.println("Please enter your choice");
        int choice = input.nextInt();
        return choice;
    }

    public static void printBoolean(boolean [] result)
    {
        SearchKeyword t = new SearchKeyword ("", result);
        System.out.println(t);
    }

    public static void RetTerm()
    {
        int ch1 ;
        
        System.out.println("*** Retrieval Term *** ");
        
        System.out.println("(1) index");
        System.out.println("(2) inverted index");
        System.out.println("(3) inverted index using BST");
        System.out.println("(4) inverted index using AVL");
        
        System.out.println("Please enter your choice");
        ch1 = input.nextInt();
        
        System.out.println("Please enter Term :");
        String str = "";
        str = input.next();
        
        System.out.print("Result document IDs: ");
        printBoolean(Se.TermRet(str.trim().toLowerCase(), ch1 ));
        System.out.println("\n");

    }
    
    public static void BooleanRetMenu()
    {
            int ch1;
                    
        System.out.println("*** Boolean Retrieval *** ");
        System.out.println("(1) index");
        System.out.println("(2) inverted index");
        System.out.println("(3) inverted index using BST");
        System.out.println("(4) inverted index using AVL");
        
        System.out.println("Please enter your choice");
         ch1 = input.nextInt();

        System.out.println("Please enter boolean term ( AND / OR) : ");
        String s = input.nextLine();
        s = input.nextLine();
            
        System.out.print("Q#: ");
        System.out.println(s);

        System.out.print("document IDs: ");
        printBoolean(Se.BooleanRet(s.trim().toUpperCase(), ch1 ));
        System.out.println("\n");
    
    }

    public static void RankedRetMenu()
    {
        int ch1;
        
        System.out.println("*** Ranked Retrieval *** ");
        System.out.println("(1) index");
        System.out.println("(2) inverted index");
        System.out.println("(3) inverted index using BST");
        System.out.println("(4) inverted index using AVL");
        System.out.println("enter your choice");
        ch1 = input.nextInt();

        System.out.print("Please enter term: ");
        String s = input.nextLine();
        s = input.nextLine();

        System.out.println(" Q: " + s);
        System.out.println("DocID \t*Score*");
        
        
        switch (ch1)
        {
            case 1:
                System.out.println("get ranked from index list");
                Se.RankedIndex(s);
                break;
            case 2:
                System.out.println("get ranked from inverted index list");
                Se.RankedRetInvIndex(s);
                break;
            case 3:
                System.out.println("get ranked from BST");
                Se.RankedRetBST(s);
                break;
            case 4:
                System.out.println("get ranked from AVL");
                Se.RankedRetAVL(s);
                break;
        }
        System.out.println("\n");
    }
    
    public static void IndexedDocMenu()
    {
        System.out.println("*** Indexed Documents *** ");
        Se.IndexedDoc();
        System.out.println("");
        System.out.println("*****************************************************");

    }
    
    public static void IndexedTokensMenu()
    {
        System.out.println("******* Indexed Tokens ****** ");
        System.out.println("tokens: " );
        Se.IndexedTokens();
        System.out.println("==================================");
    }
    
    
    //main 
    public static void main(String[] args) {

    Se.LoadD("C:\\Users\\hp\\Downloads\\data\\stop.txt", "C:\\Users\\hp\\Downloads\\data\\dataset.csv");

        // TODO code application logic here
        int choice;
        
        do {
                choice = menu();
                switch (choice)
                {
                    //term Retrieval
                    case 1:
                            RetTerm();
                            break;

                    //Boolean Retrieval: to enter a Boolean query that will return a set of unranked documents  
                    case 2:
                            BooleanRetMenu();
                            break;
                            
                    //Ranked Retrieval: to enter a query that will return a ranked list of documents with their scores 
                    case 3:
                            RankedRetMenu();
                            break;
                    
                    //Indexed Documents: to show number of documents in the index 
                    case 4:
                            IndexedDocMenu();
                            break;
                    
                    //Indexed Tokens: to show number of vocabulary and tokens in the index  
                    case 5:
                            IndexedTokensMenu();
                            break;
                     
                    case 6:
                            break;
                            
                    default:       
                            System.out.println("try again!");
                }
        } while (choice != 6);
    }
    
}