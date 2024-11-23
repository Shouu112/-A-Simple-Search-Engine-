/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;


 public class SearchKeyword { //يطلع اراي من عدد الدوكيومنت اللي موجودة فيها هذه الكلمة تكون كلها فولز ولما تطلع تصير ترو
    Vocabulary vocab;

   boolean[] docemuntID; //اراي بوليين تايب ;

    public SearchKeyword() {
        docemuntID = new boolean [50];
        int len=docemuntID.length ;
        
        
        for (int i = 0 ; i < len ; i++)
            docemuntID [i] = false; //تعريف الاراي ووضع قيمة ابتدائية وهي فولز
        vocab = new Vocabulary(""); //انشاء الكلمة
    }
    
    
    

    public SearchKeyword(String vocab, boolean [] docemuntID) {
        this.vocab = new Vocabulary(vocab);
        this.docemuntID = new boolean [docemuntID.length];
        int len=docemuntID.length ;
        
        for (int j = 0 ; j < len ; j++)
            this.docemuntID[j] = docemuntID[j]; } //نسخ الاراي
    
    
    
    public boolean insertId ( int docID){
        if (! docemuntID[docID]){ //يستقبل عدد دوكيومنت اللي فيه الكلمة ويرجعها ترو
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
    public String toString() { //تنسيق ظهور الكلمة مع سترينق من دوكيومنت الي تظهر فيها مع مراعاة الكلمة في بداية الدوكيومنت بدون فاصلة
        String docs = "";
        for (int i = 0, j = 0 ; i < docemuntID.length; i++)//يمشي على الدوكيومنتز
            if ( j == 0 && docemuntID [i]==true )//يمشي على الكلمات في الدوكيومنت الواحد ويرجع رقم الدوكيومنت اللي ظهرت فيه الكلمة بترو
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
