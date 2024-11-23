/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;


 public class SearchKeyword { 
    Vocabulary vocab;

   boolean[] docemuntID; 

    public SearchKeyword() {
        docemuntID = new boolean [50];
        int len=docemuntID.length ;
        
        
        for (int i = 0 ; i < len ; i++)
            docemuntID [i] = false; 
        vocab = new Vocabulary(""); 
    }
    
    
    

    public SearchKeyword(String vocab, boolean [] docemuntID) {
        this.vocab = new Vocabulary(vocab);
        this.docemuntID = new boolean [docemuntID.length];
        int len=docemuntID.length ;
        
        for (int j = 0 ; j < len ; j++)
            this.docemuntID[j] = docemuntID[j]; } 
    
    
    
    public boolean insertId ( int docID){
        if (! docemuntID[docID]){ 
            this.docemuntID[docID] = true;
            return true;}
        return false; }
    
    
    
    

    public void setVocabulary(Vocabulary vocab) {
        this.vocab = vocab;
    }
    
    public Vocabulary getVocabulary(){
         return vocab; }
    
    public boolean [] fetchdocemunt (){
        boolean [] result = new boolean [docemuntID.length];
        int reLen=result.length ;
        for ( int i = 0 ; i < reLen ; i++)
            result[i]=docemuntID[i];
        
        return result; //يرجع نسخة من الاراي 
    }
    
    
    
    @Override
    public String toString() { 
        String docs = "";
        for (int i = 0, j = 0 ; i < docemuntID.length; i++)
            if ( j == 0 && docemuntID [i]==true )
            {
                docs += " " + String.valueOf(i) ;
                j++;
            }
            else if ( docemuntID [i]==true )
            {
                docs += ", " + String.valueOf(i) ;
                j++;
            }
        
        return vocab + "[" + docs + ']';
    }
    
    
}
