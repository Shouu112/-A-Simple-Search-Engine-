/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

 class DocR {
            int id;
            LinkedList <String> index; 

            public DocR() {
                id = 0;
                index = new LinkedList <String>();
            }

            public void Add (String w)
            {
                index.insert(w);
            }

           public boolean containsWord (String word)
           {
               if (index.empty())
                   return false;

               index.findFirst();
               for ( int i = 0 ; i < index.size ; i++)
               {
                   if ( index.retrieve().compareTo(word) == 0)
                       return true;
                  index.findNext();
               }
               return false;
           }
    }       

public class IndexRanked {
class Termfreq
    {
        int id = 0;
        int fr = 0;
    }

    DocR [] indxs;
    Termfreq [] freqs;

    
    public IndexRanked() {
        freqs = new Termfreq [50];
        indxs = new DocR [50];
        for ( int i = 0 ; i < indxs.length ; i++)
        {
            indxs [i] = new DocR();
            indxs [i].id = i;
        }
    }
        
    public void Add ( int id, String d)
    {
            indxs[id].Add(d);
    }
   

  public void DisplayDocByID (int id)
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
  
      public  boolean [] get_Doc (String s)
{
    boolean [] res = new boolean [50];
    for (int i = 0 ; i < res.length ; i++)
        res[i] = false;
    
    for (int i = 0 ; i < res.length ; i++)
        if (indxs[i].containsWord(s))
            res[i] = true;

    return res;
}
      
  
        public void TF(String s) 
        {
            s = s.toLowerCase().trim();
            String [] w = s.split(" ");
            freqs = new Termfreq[50];
            for ( int i = 0 ; i < 50 ; i++ )  
            {
                freqs[i] = new Termfreq();
                freqs[i].id = i;
                freqs[i].fr = 0;
            }
            
            for ( int docs = 0 ; docs <50 ; docs++)
            {                                     
                for ( int i = 0 ; i < w.length ; i++)
                {
                    indxs[docs].index.findFirst();
                    int count = 0; 
                    for ( int x = 0 ; x < indxs[docs].index.size() ; x++ )  
                    {
                        if (indxs[docs].index.retrieve().compareTo(w[i])==0)
                            count ++;
                        indxs[docs].index.findNext();
                    }
                    
                    if (count > 0)
                        freqs[docs].fr += count; 
                }
            }
            
            Sorted(freqs, 0, freqs.length-1 );
            
            System.out.println("\nDocIDt\tScore");
            for ( int x = 0 ;  freqs[x].fr != 0 ; x++)
                System.out.println(freqs[x].id + "\t\t" + freqs[x].fr);
        }

    public static void Sorted ( Termfreq [] t , int s , int e ) 
    {
        if ( s >= e )
            return;
        int midd = ( s + e ) / 2;
        Sorted (t , s , midd ) ;          
        Sorted (t , midd + 1 , e ) ;    
        merge (t , s , midd , e ) ;            
    }

    private static void merge ( Termfreq [] t , int s , int midd , int e ) 
    {
        Termfreq [] B = new Termfreq [ e - s + 1];
        int i = s , j = midd + 1 , k = 0;
    
        while ( i <= midd && j <= e )
        {
            if ( t [ i ].fr >= t [ j ].fr)
                B [ k ++] = t [ i ++];
            else
                B [ k ++] = t [ j ++];
        }
        
        if ( i > midd )
            while ( j <= e )
                B [ k ++] = t [ j ++];
        else
            while ( i <= midd )
                B [ k ++] = t [ i ++];
        
        for ( k = 0; k < B . length ; k ++)
            t [ k + s ] = B [ k ];
    }

    
}
