/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class RankSearchKeyword {
    
    Vocabulary word;
    int  [] documentsIDS ; 

    public RankSearchKeyword() {
        documentsIDS = new int [50];
        for (int i = 0 ; i < documentsIDS.length ; i++)
            documentsIDS [i] = 0;
        word = new Vocabulary("");
    }

    public RankSearchKeyword(String word, int [] documentsIDS) {
        this.word = new Vocabulary(word);
        this.documentsIDS = new int [documentsIDS.length];
        for(int i = 0 ; i < documentsIDS.length ; i++)
            this.documentsIDS [i] = documentsIDS[i];

    }
    
    public void AddDocument ( int documentsIDS)
    {
        this.documentsIDS[documentsIDS] ++; 
    }
    
    public int [] GetDocument()
    {
        int [] test = new int [documentsIDS.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = documentsIDS[i];
        return test;
    }
    
    @Override
    public String toString() {
        String docs = "";
        for (int i = 0, j = 0 ; i < documentsIDS.length; i++)
            if ( j == 0 && documentsIDS [i] != 0 )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( documentsIDS [i] != 0 )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }
        
        return word + "[" + docs + ']';
    }
    
      public void setVocabulary(Vocabulary word)
    {
        this. word = word; 
    }
    
    public Vocabulary getVocabulary()
    {
         return word;
    }   
    
}
