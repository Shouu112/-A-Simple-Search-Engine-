/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

public class Index {
    
      Document [] indxs;
      
      class Document {
            int id;
            LinkedList <String> index; 

            public Document() {
                id = 0;
                index = new LinkedList <String>();
            }

            public void Add (String w)
            {
                index.insert(w);
            }

           public boolean containsWord(String w)
           {
               if (index.empty())
                   return false;

               index.findFirst();
               for ( int i = 0 ; i < index.size ; i++)
               {
                   if ( index.retrieve().compareTo(w) == 0)
                       return true;
                  index.findNext();
               }
               return false;
           }
    }   

    public Index() {

        indxs = new Document [50];
        for ( int i = 0 ; i < indxs.length ; i++)
        {
            indxs [i] = new Document();
            indxs [i].id = i;
        }
    }
        
    public void Add ( int id, String data)
    {
            indxs[id].Add (data);
    }
    
    public void DisplayDoc (int id)
    {
        if ( indxs[id].index.empty())
            System.out.println("Empty Document");
        else
        {
            indxs[id].index.findFirst();
            for ( int i = 0; i< indxs[id].index.size ; i++)
            {
                System.out.print (indxs[id].index.retrieve() + " ");
                indxs[id].index.findNext();
            }
        }
    }
    
    
    
    
public  boolean [] getDocs (String s)
{
    boolean [] res = new boolean [50];
    for (int i = 0 ; i < res.length ; i++)
        res[i] = false;
    
    for (int i = 0 ; i < res.length ; i++)
        if (indxs[i].containsWord(s))
            res[i] = true;

    return res;
}

        public boolean [] AndOrF(String s )
        {
            if (! s.contains(" OR ") && ! s.contains(" AND "))
            {
                s = s.toLowerCase().trim();
                boolean [] a1 = getDocs(s.toLowerCase().trim());
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
  public boolean [] OrF (String s)
        {
            String [] ORs = s.split(" OR ");
            boolean [] o1 = getDocs(ORs[0].toLowerCase().trim());

            for ( int i = 1 ; i< ORs.length ; i++)
            {
                boolean [] o2 = getDocs(ORs[i].toLowerCase().trim());
                for ( int j = 0 ; j < 50 ; j++)
                    o1 [j] = o1[j] || o2[j];
            }
            return o1;
        }

        public boolean [] AndF (String s)
        {
            String [] ANDs = s.split(" AND ");
            boolean [] a1 = getDocs(ANDs[0].toLowerCase().trim());

            for ( int i = 1 ; i< ANDs.length ; i++)
            {
                boolean [] a2 = getDocs(ANDs[i].toLowerCase().trim());
                for ( int j = 0 ; j < 50 ; j++)
                    a1 [j] = a1[j] && a2[j];
            }                
            return a1;
        }
         
         
             
             
}