/**
 * Main function that ties all objects+logic together
 * Solves EE422C programming assignment #4
 * @authors Sneha Vasantharao, Jai Bock Lee
 * @version 1.1 2016-3-1
 * 
 * UTEID: sv8398, jbl932
 * Lab Section: 11-12:30pm, Lisa Hua
 * 
 */

package assignment4;

import java.util.*;
import java.io.*;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Make new WordLadderSolver obj       
        ArrayList<String> input = new ArrayList<String>();
        try 
        {
           FileReader freaderA = new FileReader(args[1]);
           BufferedReader readerA = new BufferedReader(freaderA);
           
           // Store lines in ArrayList
           for (String s = readerA.readLine(); s != null; s = readerA.readLine()) 
           {
              input.add(s);
           }
        } 
        
        // File DNE
        catch (FileNotFoundException e) 
        {
           System.err.println ("ERROR: File not found. Exiting..."); 
           e.printStackTrace();
           System.exit(-1);
        } 
        
        // I/O exception
        catch (IOException e) 
        {
           System.err.println ("ERROR: I/O exception. Exiting..."); 
           e.printStackTrace();
           System.exit(-1);
        }
        
        // null input exception
        if (input.size() == 0)
        {
           System.err.println("input size null");
           return;
        }
        
        // Dictionary will be constructed in WordLadderSolver
        ArrayList<String> dictionary = makeDict(args[0]);
        
        
        for (int i = 0; i < input.size(); i++)
        {
           // Separates lines based on whitespaces/blankspaces
           String[] inputLine = input.get(i).split("\\s+"); 
           if (inputLine.length != 2){
              System.err.println("ERROR: Input words should have each line have just two words separated by one or more spaces");
           }
           
           for (int j = 0; j < inputLine.length; j++)
           {
              inputLine[j] = inputLine[j].toLowerCase();
           }
           WordLadderSolver wls = new WordLadderSolver(dictionary);
           List<String> ladder = new ArrayList<String>();
           System.out.println("**********");
           try
           {
              ladder = wls.computeLadder(inputLine[0], inputLine[1]);
           }
           catch (NoSuchLadderException e)
           {
              System.out.println("Ladder does not exist");
              System.out.println("**********\n");
              continue;
           }
           
           for (int j = 0; j < ladder.size(); j++)
           {
              System.out.println(ladder.get(j));
           }
           
           if (wls.validateResult(inputLine[0], inputLine[1], ladder))
              System.out.println("The ladder is valid.");
           else System.out.println("The ladder is invalid.");
           System.out.println("**********\n");
        }
       
       
    }
    
    /******************************************************************************
	* Method Name: makeDict                                             
	* Purpose: Creates dictionary using methods from WordLadderSolver.java                                     
	* Returns: ArrayList<String> that contains the dictionary                                                          
	******************************************************************************/
    public static ArrayList<String> makeDict(String dict)
    {
       ArrayList<String> dictionary = new ArrayList<String>();
       try 
       {
          FileReader freader2 = new FileReader(dict);
          BufferedReader reader2 = new BufferedReader(freader2);
          
          // Stores each line in ArrayList
          for (String s = reader2.readLine(); s != null; s = reader2.readLine()) 
          {
             dictionary.add(s);
          }
       } 
       
       // File DNE
       catch (FileNotFoundException e) 
       {
          System.err.println ("ERROR: File not found. Exiting..."); 
          e.printStackTrace();
          System.exit(-1);
       } 
       
       // I/O exception
       catch (IOException e) 
       {
          System.err.println ("ERROR: I/O exception. Exiting..."); 
          e.printStackTrace();
          System.exit(-1);
       }
       
       return dictionary;
    }
}