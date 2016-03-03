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
        // Makes new WordLadderSolver
       
        ArrayList<String> input = new ArrayList<String>();
        try 
        {
           FileReader freader = new FileReader(args[0]);
           BufferedReader reader = new BufferedReader(freader);
           
           // stores each line in the ArrayList
           for (String s = reader.readLine(); s != null; s = reader.readLine()) 
           {
              input.add(s);
           }
        } 
        catch (FileNotFoundException e) 
        {
           System.err.println ("ERROR: File not found. Exiting..."); // file DNE
           e.printStackTrace();
           System.exit(-1);
        } 
        catch (IOException e) 
        {
           System.err.println ("ERROR: I/O exception. Exiting..."); // I/O exception
           e.printStackTrace();
           System.exit(-1);
        }
       
        if (input.size() == 0)
        {
           System.err.println("Null input size");
           return;
        }
        
        ArrayList<String> dictionary = new ArrayList<String>(); // dictionary is created in WordLadderSolver.java
        try 
        {
           FileReader freader2 = new FileReader(args[1]);
           BufferedReader reader2 = new BufferedReader(freader2);
           
           // stores each line in ArrayList
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
        
        
        for (int i = 0; i < input.size(); i++)
        {
        	// formatting
           String[] inputLine = input.get(i).split("\\s+"); 
           if (inputLine.length != 2){
              System.err.println("ERROR: Input words should have each line have just two words separated by one or more spaces");
           }
           
           for (int j = 0; j < inputLine.length; j++)
           {
              inputLine[j] = inputLine[j].toLowerCase();
           }
           WordLadderSolver wls = new WordLadderSolver(inputLine[0], inputLine[1], dictionary);
           List<String> ladder = new ArrayList<String>();
           System.out.println("**********");
           try
           {
              ladder = wls.computeLadder(inputLine[0], inputLine[1]);
           }
           catch (NoSuchLadderException e)
           {
              System.err.println("Ladder does not exist");
              System.err.println("**********");
              continue;
           }
           
           for (int j = 0; j < ladder.size(); j++)
           {
              System.out.println(ladder.get(j));
           }
           System.out.println("**********");
        }
       
       
    }
}