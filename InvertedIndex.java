/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class InvertedIndex {

            LinkedList <SearchKeyword> invertedindex; //Word list of DocIDs List       

            public InvertedIndex() {
                invertedindex = new LinkedList <SearchKeyword>();
            }
            
            public int size()
            {
                return invertedindex.size();
            }

            public boolean Add (int id, String w) //add word to list
            {
                if (invertedindex.empty()) // 1- empty list
               {
                   SearchKeyword t = new SearchKeyword ();
                    t.setVocabulary(new Vocabulary (w));
                    t.insertId(id);
                    invertedindex.insert(t);
                    return true;
               }
               else
               {
                    invertedindex.findFirst(); 
                    while ( ! invertedindex.last())
                    {
                        if ( invertedindex.retrieve().vocab.word.equals(w))          
                        {
                            SearchKeyword t = invertedindex.retrieve(); //2- الكلمة موجودة
                            t.insertId(id);
                            invertedindex.update(t);
                            return false;
                        }
                       invertedindex.findNext();
                    }
                    
                    if ( invertedindex.retrieve().vocab.word.compareTo(w) == 0) //last one 
                    {
                        SearchKeyword t = invertedindex.retrieve();
                        t.insertId(id);
                        invertedindex.update(t);
                        return false;
                    }
                    else //(الكلمة ليست موجودة)
                    {
                        SearchKeyword t = new SearchKeyword ();
                        t.setVocabulary(new Vocabulary (w));
                        t.insertId(id);
                        invertedindex.insert(t);
                    }
                    return true;
           }
        }

        public boolean containsWord(String w) 
        {
               if (invertedindex.empty())
                   return false;

               invertedindex.findFirst();
               for ( int i = 0 ; i < invertedindex.size ; i++)
               {
                   if ( invertedindex.retrieve().vocab.word.equals(w))
                       return true;
                  invertedindex.findNext();
               }
               return false;
        }
        
        public boolean [] AndOrF (String s )
        {
            if (! s.contains(" OR ") && ! s.contains(" AND "))
            {
                boolean [] a1 = new boolean[50];
                s = s.toLowerCase().trim();
            
                if (this.containsWord (s))
                    a1 =  this.invertedindex.retrieve().fetchdocemunt();
                return a1;
            }
            
            else if (s.contains(" OR ") && s.contains(" AND "))
            {
                String [] AND_ORs = s.split(" OR ");
                boolean []  a1 = AndF (AND_ORs[0]);
               
                for ( int i = 1 ; i < AND_ORs.length ; i++  )
                {   
                    boolean [] a2 =AndF(AND_ORs[i]);
                    
                    for ( int j = 0 ; j < 50 ; j++ )
                        a1 [j] = a1[j] || a2[j];
                }
                return a1;
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
                o1 = this.invertedindex.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] o2 = new boolean [50];
                if (this.containsWord (ORs[i].toLowerCase().trim()))
                    o2 = this.invertedindex.retrieve().fetchdocemunt();
                
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
                a1 = this.invertedindex.retrieve().fetchdocemunt();

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] a2 = new boolean [50];
                if (this.containsWord (ANDs[i].toLowerCase().trim()))
                    a2 = this.invertedindex.retrieve().fetchdocemunt();
                
                for ( int j = 0 ; j < 50 ; j++)
                    a1 [j] = a1[j] && a2[j];
            }                

            return a1;
        }
       

        public void displayD()
        {
            if (this.invertedindex.empty())
                System.out.println("Empty");
            else
            {
                this.invertedindex.findFirst();
                
                while ( ! this.invertedindex.last())
                {
                    System.out.println(invertedindex.retrieve());
                    this.invertedindex.findNext();
                }
                System.out.println(invertedindex.retrieve());
            }
        }
}
