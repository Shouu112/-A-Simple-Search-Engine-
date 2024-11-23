/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;
import java.util.function.Function;

/**
 *
 * @author jojo0
 */
public class RankedInvertedIndexBST {
    
    
    class DocFrequency {
        int docID = 0;
        int frequency = 0;
    }

    
    BST<Integer, BST<String, ItemRank>> documentRankTree; 
    DocFrequency[] documentFrequencies = new DocFrequency[50];
    
    
    public RankedInvertedIndexBST() {
        documentRankTree = new BST<Integer, BST<String, ItemRank>>();
    }

  
    public boolean addKeyword(int docID, String keyword) {
        if (documentRankTree.empty()) {
            BST<String, ItemRank> rankInside = new BST<String, ItemRank>();
            rankInside.insert(keyword, new ItemRank(keyword, 1));

            documentRankTree.insert(docID, rankInside);
            return true;
        } else {
            if (documentRankTree.find(docID)) {
                BST<String, ItemRank> rankInside = documentRankTree.retrieve();
                if (rankInside.find(keyword)) {
                
                    ItemRank rank = rankInside.retrieve();
                    rank.incrementPosition();
                    rankInside.update(rank);
                    documentRankTree.update(rankInside);
                    return false;
                }
                
                rankInside.insert(keyword, new ItemRank(keyword, 1));
                documentRankTree.update(rankInside);
                return true;
            }
            
            BST<String, ItemRank> rankInside = new BST<String, ItemRank>();
            rankInside.insert(keyword, new ItemRank(keyword, 1));

            documentRankTree.insert(docID, rankInside);
            return true;
        }
    }

  
    public boolean containsKeyword(int docID, String keyword) {
        if (documentRankTree.find(docID)) 
          if(documentRankTree.retrieve().find(keyword))
            return true;
        return false;
    }

  
    public int getKeywordRank(int docID, String keyword) {
        int rankValue = 0;
        if (documentRankTree.find(docID)) 
            if (documentRankTree.retrieve().find(keyword)) 
                return documentRankTree.retrieve().retrieve().getposition();
 
        return rankValue;
    }

  
    public void printDocuments() {
        documentRankTree.TraverseT();
    }

    
    public void computeKeywordFrequency(String text) {
        text = text.toLowerCase().trim();
        String[] keywords = text.split(" ");

        int index = 0;
        for (int docID = 0; docID < 50; docID++) {
            int count = 0;
            for (int j = 0; j < keywords.length; j++) {
                count += this.getKeywordRank(docID, keywords[j]);
            }
            if (count > 0) {
                documentFrequencies[index] = new DocFrequency();
                documentFrequencies[index].docID = docID;
                documentFrequencies[index].frequency = count;
                index++;
            }
        }

        mergesort(documentFrequencies, 0, index - 1);

      
        for (int x = 0; x < index; x++) {
            System.out.println(documentFrequencies[x].docID + "\t\t" + documentFrequencies[x].frequency);
        }
    }

   
    public static void mergesort(DocFrequency[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergesort(array, left, mid);      
        mergesort(array, mid + 1, right);   
        merge(array, left, mid, right);       
    }

    private static void merge(DocFrequency[] array, int left, int mid, int right) {
        DocFrequency[] temp = new DocFrequency[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i].frequency >= array[j].frequency)
                temp[k++] = array[i++];
            else
                temp[k++] = array[j++];
        }

        while (i <= mid)
            temp[k++] = array[i++];
        while (j <= right)
            temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, left, temp.length);
    }
}

