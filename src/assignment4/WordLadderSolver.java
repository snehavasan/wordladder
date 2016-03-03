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

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
	HashMap<Character, ArrayList<String>> originalWordList;
	int numberOfWords;
    boolean[][] dictionaryMatrix;
    
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
    public WordLadderSolver (HashMap<Character, ArrayList<String>> dictionary)
	{
		originalWordList = dictionary;
		int originalWordListSize = 0;
		
		for (ArrayList<String> list : originalWordList.values()) 
		{
		    originalWordListSize += list.size();
		}
    
		dictionaryMatrix = new boolean[originalWordListSize][originalWordListSize];
		
		Long start = System.nanoTime();
		
		for (ArrayList<String> list : originalWordList.values()) 
		{
			for (String word1 : list)
			{
				for(String word2 : list)
				{
					if(checkNextTo(word1,word2))
					{
						addEdge(list.indexOf(word1),list.indexOf(word2));
					}
				}
			}
		}
		
		Long stop = System.nanoTime();
		double time = ((double)stop - (double)start)/1000000;
		System.out.println("Graph execution time: " + time + " ms");
	}
    
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
        // implement this method
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
    boolean checkEdge(int p, int q) 
    {
        return dictionaryMatrix[p][q];
    }
    void addEdge(int p, int q) 
	{
        dictionaryMatrix[p][q] = true;
    }
	void removeEdge(int p, int q) 
	{
        dictionaryMatrix[p][q] = false;
    }
    
    public static boolean checkNextTo(String a, String b) 
    {
        assert a.length() == b.length();
        int differ = 0;
        for (int i = 0; i < a.length(); i++) 
        {
            if (a.charAt(i) != b.charAt(i)) differ++;
            if (differ > 1) return false;
        }
        return true;
    }
    
}