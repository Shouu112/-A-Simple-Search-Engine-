
package com.mycompany.ds;


public class ItemRank { 
    Vocabulary term;
    int position ;

    
    public ItemRank() {
        position = 0;
        term = new Vocabulary("");
    }

    public ItemRank(String term, int rank) {
        this.term = new Vocabulary(term);
        this.position = rank ;
    }
    
    public void setterm(Vocabulary term)
    {
        this. term = term; 
    }
    
    public Vocabulary getterm()
    {
         return term;
    }
    
    
    
    public int incrementPosition (){ 
        return ++position; }

    
    public int getposition (){
        return this.position; }
    
    @Override
    public String toString() {
        return "(" + term + ", " + position + ")" ;
    }
    
    
}
