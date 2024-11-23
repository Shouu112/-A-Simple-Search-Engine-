/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;


public class ItemRank { //كلمة وعدد تكراراتها بنفس الدوكيومنت
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
    
    
    
    public int incrementPosition (){ //يزيد بواحد لما يلقى كلمة معينة في دوكيومنت معين
        return ++position; }

    
    public int getposition (){
        return this.position; }
    
    @Override
    public String toString() {
        return "(" + term + ", " + position + ")" ;
    }
    
    
}
