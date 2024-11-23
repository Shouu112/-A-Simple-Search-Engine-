/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class InvertedIndexBST {
            BST < String , SearchKeyword> invertedindexBST; 
            int count = 0;

            public InvertedIndexBST() {
                invertedindexBST = new BST< String , SearchKeyword> ();
            }

            public int size()
            {
                return invertedindexBST.count;
            }
            
            public boolean Add (int id, String w)
            {
               if (invertedindexBST.empty())
               {
                   count ++;
                   SearchKeyword b = new SearchKeyword ();
                   b.setVocabulary(new Vocabulary (w));
                   b.insertId(id);
                   invertedindexBST.insert(w, b);
                   return true;
               }
               else
               {
                    if (invertedindexBST.find(w))
                    {
                        SearchKeyword b = this.invertedindexBST.retrieve();
                        b.insertId(id);
                        invertedindexBST.update(b);
                        return false;
                        
                    }
                    
                   count ++;
                   SearchKeyword b = new SearchKeyword ();
                   b.setVocabulary(new Vocabulary (w));
                   b.insertId(id);
                   invertedindexBST.insert(w, b);
                    return true;
           }
        }

        public boolean containsWord(String w)
        {
               return invertedindexBST.find(w);
        }
        
        public void displayD()
        {
            invertedindexBST.Traverse();
        }
        
        
        public boolean [] AndOrF (String s )
        {
            if (! s.contains(" OR ") && ! s.contains(" AND "))
            {
                boolean [] a = new boolean[50];
                s = s.toLowerCase().trim();
            
                if (this.containsWord (s))
                    a =  this.invertedindexBST.retrieve().fetchdocemunt();
                return a;
            }
            
            else if (s.contains(" OR ") && s.contains(" AND "))
            {
                String [] AND_ORs = s.split(" OR ");
                boolean []  a = AndF (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] a2 =AndF (AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        a [j] = a[j] || a2[j];
                }
                return a;
            }
            
            else  if (s.contains(" AND "))
                return AndF (s);
            
            return OrF (s);
        }
        
            public boolean [] OrF (String s)
        {
            String [] ORs = s.split(" OR ");
            boolean [] o1 = new boolean [50];
            
            if (this.containsWord (ORs[0].toLowerCase().trim()))
                o1 = this.invertedindexBST.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] o2 = new boolean [50];
                if (this.containsWord (ORs[i].toLowerCase().trim()))
                    o2 = this.invertedindexBST.retrieve().fetchdocemunt();
                
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
                a1 = this.invertedindexBST.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] a2 = new boolean [50];
                if (this.containsWord (ANDs[i].toLowerCase().trim()))
                    a2 = this.invertedindexBST.retrieve().fetchdocemunt();
                
                for ( int j = 0 ; j < 50 ; j++)
                    a1 [j] = a1[j] && a2[j];
            }
            return a1;
        }
        
    
   
    
}
