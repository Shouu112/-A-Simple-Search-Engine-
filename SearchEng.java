/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
import java.io.File;
import java.util.Scanner;
public class SearchEng {
    int tokens = 0;
    int voc = 0;
    
    Index index;
    InvertedIndex invertedindex;
    InvertedIndexBST invertedindexBST;
    InvertedIndAVL invertedindexAVL;
    
    IndexRanked  indexranked;
    RankedInvertedIndex  invertedindexranked;
    RankedInvertedIndexBST  invertedindexBSTranked;
    RankedInvertedIndexAVL  invertedindexAVLranked;
    
    public SearchEng ()
    {
        index = new Index();
        invertedindex = new InvertedIndex();
        invertedindexBST = new InvertedIndexBST ();
        invertedindexAVL = new InvertedIndAVL();
        indexranked = new IndexRanked();
        invertedindexranked = new RankedInvertedIndex();
        invertedindexBSTranked = new RankedInvertedIndexBST();
        invertedindexAVLranked = new RankedInvertedIndexAVL();
    }
   
    public void LoadD (String stopF, String fName)
    {
            try{
                File stopfile = new File (stopF);
                Scanner r1 = new Scanner (stopfile).useDelimiter("\\Z");
                String stops = r1.next();
                
                stops = stops.replaceAll("\n", " ");
                
                File docsfile = new File(fName);
                Scanner r2 = new Scanner (docsfile);
                String line = r2.nextLine();
                
                for ( int lineID = 0 ; lineID <50 ; lineID ++ ) 
                {
                    line = r2.nextLine().toLowerCase();
                    
                    int pos = line.indexOf(',');
                    int id = Integer.parseInt( line .substring(0, pos));

                    String data = line.substring(pos+1, line.length() - pos).trim();
                    data = data.substring(0, data.length() - 1);

                    data = data.toLowerCase();
                    data =  data.replaceAll("[\']", " ");
                    data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;

                    String [] words = data.split(" "); // --1

                    for (int i = 0; i < words.length ; i++)
                    {
                        String word = words[i].trim(); //--2
                
                        if ( word.compareToIgnoreCase("") != 0)
                            tokens ++;

                        if ( ! stops.contains(word + " ")) //--3
                        { 
                            this.index.Add(id, word);
                            this.invertedindex.Add(id, word);
                            this.invertedindexBST.Add(id, word);
                            this.invertedindexAVL.Add(id, word);

                            this.indexranked.Add(id, word);
                            this.invertedindexranked.addKeyword(id, word);
                            this.invertedindexBSTranked.addKeyword(id, word);
                            this.invertedindexAVLranked.addKeyword(id, word);
                        }
                    }
                }
                
                voc = invertedindexAVL.invertedindexAVL.size();
      
                System.out.println("tokens " + tokens);
                System.out.println("vocabs " + voc);
                
                r1.close();
                r2.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
   
        
    public void RankedIndex(String s)
    {
        this.indexranked.TF(s);
    }

    public void RankedRetInvIndex(String s)
    {
        this.invertedindexranked.computeKeywordFrequency(s);
    }

    public void RankedRetBST(String s)
    {
        this.invertedindexBSTranked.computeKeywordFrequency(s);
    }

    public void RankedRetAVL(String s)
    {
        this.invertedindexAVLranked.computeKeywordFrequency(s);
    }
    
       public boolean []  BooleanRet(String str , int DSType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;
  
        switch (DSType)
        {
            case 1 :
                System.out.println(" Boolean Retrieval by index list");
                docs = this.index.AndOrF(str);
                break;
            case 2:    
                System.out.println(" Boolean Retrieval by inverted index list");
                docs = this.invertedindex.AndOrF(str);
                break;
            case 3:
                System.out.println(" Boolean Retrieval by BST");
                docs = this.invertedindexBST.AndOrF(str);
                break;
            case 4:
                System.out.println(" Boolean Retrieval by AVL");
                docs = this.invertedindexAVL.AndOrF(str);
                break;
            default :
                System.out.println("NO result, try again!");
                
        }
        return docs;
    }
    
    public boolean []  TermRet(String s, int DType)
    {
        boolean [] docs = new boolean [50] ;
        for ( int i = 0 ; i < docs.length ; i++)
            docs[i] = false;

        switch (DType)
        {
            case 1 :
                docs = index.getDocs(s);
                break;
            case 2 :
               if (invertedindex.containsWord(s))
                    docs = invertedindex.invertedindex.retrieve().fetchdocemunt();
                break;
             case 3:
                if (invertedindexBST.containsWord(s))
                    docs = invertedindexBST.invertedindexBST.retrieve().fetchdocemunt();
                break;
            case 4:
                if (invertedindexAVL.containsWord(s))
                    docs = invertedindexAVL.invertedindexAVL.retrieve().fetchdocemunt();
                break;
            default :
                System.out.println("NO result, try again!");
        }
        return docs;
    }
    
    public void IndexedTokens()
    {
        System.out.println("*********Each token and the documents it appears in********");
        invertedindexBST.invertedindexBST.Print();
        invertedindexAVL.invertedindexAVL.PrintData();
    }
    
    public void IndexedDoc()
    {
        System.out.println("********** All Documents with number of words ************** ");
        for ( int i = 0 ; i < 50 ; i++ ) //49
        {
            int size = index.indxs[i].index.size();
            System.out.println("Document NUM: " + i +" | size(" +  size + ")"  );
        }
        
    }
    
    
}