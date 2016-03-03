/**
 * Object that solves word ladder
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

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // declare class members here.
	private String prevWord;
	private String nextWord;
	private ArrayList<String> dict;
	
	// add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver(String str1, String str2, ArrayList<String> dictionary){
		for(int i = 0; i < dictionary.size(); i++){
			if(dictionary.get(i).substring(0, 1).equals("*")){
				dictionary.remove(i);
				i = i-1;
			}
			else{
				dictionary.set(i, dictionary.get(i).substring(0, 5));
			}
		}
		prevWord = str1;
		nextWord = str2;
		dict = dictionary;
	}

    // do not change signature of the method implemented from the interface

    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
       if (startWord.length() != 5 || endWord.length() != 5)
       {
          System.err.println("invalid input. words must be five characters long.");
          throw new NoSuchLadderException("one or both of the words were the wrong length");
       }
    	//returns a List<String> that contains the solution ladder. If no ladder is found, no such ladder exception is thrown
      boolean boolWordStart = false;
      boolean boolWordEnd = false;
      for (int i = 0; i < dict.size(); i++)
      {
         if (startWord.equals(dict.get(i)))
            boolWordStart = true;
         if (endWord.equals(dict.get(i)))
            boolWordEnd = true;
      }
      if (!boolWordStart)
      {
         System.err.println("Starting word not in the dictionary");
         throw new NoSuchLadderException("startWord is not in the dictionary");
      }
      if (!boolWordEnd)
      {
         System.err.println("Ending word not in the dictionary");
         throw new NoSuchLadderException("endWord is not in the dictionary");
      }
      
      
      List<String> ladder = new ArrayList<String>();
    	try{
    	ladder = makeLadder(startWord, endWord, 0);
    	}
    	catch (NoSuchLadderException i){
    		throw i;
    	}
    	if (ladder.size() == 0)
    	   throw new NoSuchLadderException("Ladder does not exist");
    	else return ladder;
    	
    }
    
    // TODO: Finish this implementation
    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
    
    public List<String> makeLadder(String from, String to, int position) throws NoSuchLadderException
    {
       List<String> possibles = new ArrayList<String>();
       List<String> finalList = new ArrayList<String>();
       // base case
       if (from.equals(to))
       {
          finalList.add(from);
          return finalList;
       }
       if (position == 5) // ensure 5 letter word
       {
          throw new NoSuchLadderException("No such ladder!");
       }
       
       
       
       for (int i = position; i < 5; i++) // ensure 5 letter word
       {
          String pre = from.substring(0,i);
          String post = from.substring(i+1);
          
          
          
          for (int j = 0; j < dict.size(); j++)
          {
             if (from.substring(i,i+1).equals(to.substring(i,i+1)));
             else if (dict.get(j).indexOf(pre) == 0 && dict.get(j).indexOf(post) == (i + 1) && !dict.get(j).substring(i, i+1).equals(from.substring(i,i+1)))
             {
                possibles.add(dict.get(j));
             }
          }
          
          for (String s : possibles)
          {
             //TODO: this loop is still a bit broken, need to have it hit the base case (which it doesn't) and the steps in between to get to the final answer list
             
        	 //finalList.addAll(makeLadder(s, to, i + 1))		// commented out right now to test loop
             for (int k = 0; k < 5; k++) //TODO: stackoverflow happens in this loop
             {
                if (k != i)
                {
                   finalList = makeLadder(s, to, k);
                   if (finalList.size() > 0 && finalList.get(finalList.size() - 1).equals(to))
                   {
                      finalList.add(0,from);
                      return finalList;
                   }
                }
             }
          }
          
       }

       return finalList;
    }
}