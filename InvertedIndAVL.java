/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class InvertedIndAVL {
 
            AVLTree <String, SearchKeyword> invertedindexAVL; 

            public InvertedIndAVL() {
                invertedindexAVL = new AVLTree <String, SearchKeyword>();
            }

            public int size()
            {
               return invertedindexAVL.size();
            }
                
            public boolean Add (int id, String w)
            {
               if (invertedindexAVL.empty())
               {
                   SearchKeyword t = new SearchKeyword ();
                   t.setVocabulary(new Vocabulary (w));
                   t.insertId(id);
                   invertedindexAVL.insert(w, t);
                   return true;
               }
               else
               {
                    if (invertedindexAVL.find(w))
                    {
                        SearchKeyword t = invertedindexAVL.retrieve();
                        t.insertId(id);
                        invertedindexAVL.update(t);
                        return false;
                        
                    }
                    
                   SearchKeyword t = new SearchKeyword ();
                   t.setVocabulary(new Vocabulary (w));
                   t.insertId(id);
                   invertedindexAVL.insert(w, t);
                    return true;
           }
        }

        public boolean containsWord(String w)
        {
               return invertedindexAVL.find(w);
        }
       
        public boolean [] AndOrF (String s )
        {
            if (! s.contains(" OR ") && ! s.contains(" AND "))
            {
                boolean [] a1 = new boolean[50];
                s = s.toLowerCase().trim();
            
                if (this.containsWord (s))
                    a1 =  this.invertedindexAVL.retrieve().fetchdocemunt();
                return a1;
            }
            
            else if (s.contains(" OR ") && s.contains(" AND "))
            {
                String [] AND_ORs = s.split(" OR ");
                boolean []  a1 = AndF (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] a2 =AndF (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        a1 [j] = a1[j] || a2[j];
                }
                return a1;
            }
            
            else  if (s.contains(" AND "))
                return AndF (s);
            
            return OrF (s);
        }
        
        public boolean [] OrF (String str)
        {
            String [] ORs = str.split(" OR ");
            boolean [] o1 = new boolean [50];
            
            if (this.containsWord (ORs[0].toLowerCase().trim()))
                o1 = this.invertedindexAVL.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] o2 = new boolean [50];
                if (this.containsWord (ORs[i].toLowerCase().trim()))
                    o2 = this.invertedindexAVL.retrieve().fetchdocemunt();
                
                for ( int j = 0 ; j < 50 ; j++)
                    o1 [j] = o1[j] || o2[j];
               
            }
            return o1;
        }
   
        
        public boolean [] AndF (String s)
        {
            String [] ANDs = s.split(" AND ");
            boolean [] a1 = new boolean [50];
            
            if (this.containsWord (ANDs[0].toLowerCase().trim()))
                a1 = this.invertedindexAVL.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] a2 = new boolean [50];
                if (this.containsWord (ANDs[i].toLowerCase().trim()))
                    a2 = this.invertedindexAVL.retrieve().fetchdocemunt();
                
                for ( int j = 0 ; j < 50 ; j++)
                    a1 [j] = a1[j] && a2[j];
            }
            return a1;
        }
        
         public void displayD()
        {
            invertedindexAVL.Traverse();
        }
        
        
}
