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
	private ArrayList<String> dict;
	private List<String> soln;
	
	// add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver(ArrayList<String> dictionary){
		for(int i = 0; i < dictionary.size(); i++){
			if(dictionary.get(i).substring(0, 1).equals("*")){
				dictionary.remove(i);
				i = i - 1;
			}
			
			else{
				dictionary.set(i, dictionary.get(i).substring(0, 5));
			}
		}
		dict = dictionary;
		soln = new ArrayList<String>();
	}

    // do not change signature of the method implemented from the interface

	/******************************************************************************
	* Method Name: computeLadder                  
	* Purpose: Processes solution ladder                                     
	* Returns: List<String> that has solution ladder; if not, exception thrown                                                          
	******************************************************************************/
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
       if (startWord.length() != 5 || endWord.length() != 5)
       {
          System.out.println("ERROR: Word length must be five characters");
          throw new NoSuchLadderException("ERROR: Input word(s) are wrong length");
       }
      boolean boolWStart = false;
      boolean boolWEnd = false;
      for (int i = 0; i < dict.size(); i++)
      {
         if (startWord.equals(dict.get(i)))
            boolWStart = true;
         if (endWord.equals(dict.get(i)))
            boolWEnd = true;
      }
      if (!boolWStart)
      {
         System.out.println("Starting word not in dictionary");
         throw new NoSuchLadderException("startWord is not in dictionary");
      }
      if (!boolWEnd)
      {
         System.out.println("Ending word not in dictionary");
         throw new NoSuchLadderException("endWord is not in dictionary");
      }
      
      
      List<String> ladder = new ArrayList<String>();
    	try{
    	ladder = makeLadder(startWord, endWord, -1);
    	}
    	catch (NoSuchLadderException i){
    		throw i;
    	}
    	
    	if (ladder.size() == 0)
    	{
         throw new NoSuchLadderException("Ladder does not exist.");
    	}
    	
    	if (!ladder.get(0).equals(startWord))
    	   ladder.add(0, startWord);
    	
    	cutLadder(ladder);
    	return ladder;
    	
    }
    
    
    /******************************************************************************
	* Method Name: validateResult                                       
	* Purpose: Checks result                                     
	* Returns: Boolean to confirm                                                      
	******************************************************************************/
    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
       
       if (wordLadder.size() == 0 || wordLadder == null)
       {
          System.out.println("Empty ladder has been inputted; no ladder exists.");
          return true;
       }
       if (!wordLadder.get(0).equals(startWord))
          return false;
       if (wordLadder.size() > 0 && !wordLadder.get(wordLadder.size()-1).equals(endWord))
          return false;
       for (int i = 0; i < wordLadder.size() - 1; i++)
       {
          // Check for repeated words
          for (int j = 0; j < wordLadder.size(); j++)
          {
             if (j != i && wordLadder.get(i).equals(wordLadder.get(j)))
                return false;
          }
          
          if (!boolDiff(wordLadder.get(i), wordLadder.get(i+1)))
             return false;
       }
       return true;
       
    }

    // add additional methods here
    
    /******************************************************************************
	* Method Name: makeLadder                                         
	* Purpose: Constructs the ladder                                     
	* Returns: Word ladder with word list                                                          
	******************************************************************************/
    public List<String> makeLadder(String prev, String next, int pos) throws NoSuchLadderException
    {
       
       List<String> choices = new ArrayList<String>();
       
       if (prev.equals(next))
       {
          soln.add(next);
          return soln;
       }
       
       // base case
       if (boolDiff(prev, next))
       {
          soln.add(next);
          return soln;
       }
       
       
       for (int j = 0; j < dict.size(); j++)
       {

             if (boolDiff(prev, dict.get(j)) && !soln.contains(dict.get(j)) && indexSet(prev, dict.get(j)) != pos)
             {
                choices.add(dict.get(j));
             }
       }
       
       for (String s : choices)
       {
          soln.add(s);
          makeLadder(s, next, indexSet(prev, s));
          
          //  if there is something in the list and the last element is the target word
          if (soln.size() > 0 && soln.get(soln.size() - 1).equals(next)) 
          {
             soln.add(0,prev); //tack this from on the front
             return soln; //back up one level
          }
          for (int p = soln.indexOf(s); p < soln.size() ; p++)
          {
             soln.remove(p);
             p = p - 1;
          }
       }
       
       return soln;
       
    }

    /******************************************************************************
	* Method Name: boolDiff                                             
	* Purpose: Detects if strings are different                                     
	* Returns: Whether s1 = s2                                                          
	******************************************************************************/
    public boolean boolDiff(String s1, String s2)
    {
       int differences = 0;
       
       for (int i = 0; i < 5; i++)
       {
         if (!s1.substring(i,i+1).equals(s2.substring(i,i+1)))
         {
            differences = differences - 1;
         }
       }
       if (differences == 1) { return true; }
       return false;
    }
    
    /******************************************************************************
	* Method Name: indexSet                                             
	* Purpose: Sets index just set to; Precondition: s1 and s2 are 1 letter off                                     
	* Returns: Integer index, returns -1 if same                                                          
	******************************************************************************/
    public int indexSet(String s1, String s2) 
    {
       int index = -1;
       
       for (int i = 0; i < 5; i++)
       {
          if (!s1.substring(i,i+1).equals(s2.substring(i,i+1)))
             index = i;
       }
       
       return index;
    }
    
    /******************************************************************************
	* Method Name: cutLadder                                             
	* Purpose: Shortens ladder                                     
	* Returns: None                                                          
	******************************************************************************/
    public void cutLadder(List<String> ladder)
    {
       for (int i = 0; i < ladder.size(); i++)
       {
          for (int j = 0; j < ladder.size(); j++)
          {
             if (boolDiff(ladder.get(i), ladder.get(j)))
             {
                int k = i + 1;
                while (k < j)
                {
                   ladder.remove(k);
                   j = j - 1;
                }
             }
          }
       }
    }
}