/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class RankedInvertedIndexAVL {
    
    class DocumentFrequency {
        int docID = 0;
        int frequency = 0;
    }

    AVLTree<Integer, AVLTree<String, ItemRank>> docRankIndex;
    DocumentFrequency[] docFrequencies = new DocumentFrequency[50];
    
    
    public RankedInvertedIndexAVL() {
        docRankIndex = new AVLTree<Integer, AVLTree<String, ItemRank>>();
    }

   
    public boolean addKeyword(int docID, String keyword) {
        if (docRankIndex.empty()) {
            AVLTree<String, ItemRank> rankInside = new AVLTree<String, ItemRank>();
            rankInside.insert(keyword, new ItemRank(keyword, 1));

            docRankIndex.insert(docID, rankInside);
            return true;
        } else {
            if (docRankIndex.find(docID)) {
                AVLTree<String, ItemRank> rankInside = docRankIndex.retrieve();
                if (rankInside.find(keyword)) {
                  
                    ItemRank rank = rankInside.retrieve();
                    rank.incrementPosition();
                    rankInside.update(rank);
                    docRankIndex.update(rankInside);
                    return false;
                }
            
                rankInside.insert(keyword, new ItemRank(keyword, 1));
                docRankIndex.update(rankInside);
                return true;
            }
        
            AVLTree<String, ItemRank> rankInside = new AVLTree<String, ItemRank>();
            rankInside.insert(keyword, new ItemRank(keyword, 1));

            docRankIndex.insert(docID, rankInside);
            return true;
        }
    }

    
    public boolean containsKeyword(int docID, String keyword) {
        if (docRankIndex.find(docID)) 
             if(docRankIndex.retrieve().find(keyword))
             return true;
        return false;
    }

    
    public int getKeywordRank(int docID, String keyword) {
        int rankValue = 0;
        if (docRankIndex.find(docID))
            if (docRankIndex.retrieve().find(keyword)) 
                return docRankIndex.retrieve().retrieve().getposition();
        
        return rankValue;
    }

   
    public void printDocuments() {
        docRankIndex.TraverseT();
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
                docFrequencies[index] = new DocumentFrequency();
                docFrequencies[index].docID = docID;
                docFrequencies[index].frequency = count;
                index++;
            }
        }

        mergesort(docFrequencies, 0, index - 1);

       
        for (int x = 0; x < index; x++) {
            System.out.println(docFrequencies[x].docID + "\t\t" + docFrequencies[x].frequency);
        }
    }

   
    public static void mergesort(DocumentFrequency[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergesort(array, left, mid);          
        mergesort(array, mid + 1, right);     
        merge(array, left, mid, right);       
    }

    private static void merge(DocumentFrequency[] array, int left, int mid, int right) {
        DocumentFrequency[] temp = new DocumentFrequency[right - left + 1];
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
