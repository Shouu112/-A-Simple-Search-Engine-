/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;

/**
 *
 * @author jojo0
 */
public class RankedInvertedIndex {
    
    class DocumentFrequency {
        int docID = 0;
        int frequency = 0;
    }

    LinkedList<RankSearchKeyword> invertedIndex; 
    DocumentFrequency[] keywordFrequencies;
    
    
    public RankedInvertedIndex() {
        invertedIndex = new LinkedList<RankSearchKeyword>();
        keywordFrequencies = new DocumentFrequency[50];
    }

    
    
    public int size() {
        return invertedIndex.size();
    }

    public boolean addKeyword(int docID, String word) {
        if (invertedIndex.empty()) {
            RankSearchKeyword keyword = new RankSearchKeyword();
            keyword.setVocabulary(new Vocabulary(word));
            keyword.AddDocument(docID);
            invertedIndex.insert(keyword);
            return true;
        } else {
            invertedIndex.findFirst();
            while (!invertedIndex.last()) {
                if (invertedIndex.retrieve().word.word.compareTo(word) == 0) {
                    RankSearchKeyword keyword = invertedIndex.retrieve();
                    keyword.AddDocument(docID);
                    invertedIndex.update(keyword);
                    return false;
                }
                invertedIndex.findNext();
            }
            if (invertedIndex.retrieve().word.word.compareTo(word) == 0) {
                RankSearchKeyword keyword = invertedIndex.retrieve();
                keyword.AddDocument(docID);
                invertedIndex.update(keyword);
                return false;
            } else {
                RankSearchKeyword keyword = new RankSearchKeyword();
                keyword.setVocabulary(new Vocabulary(word));
                keyword.AddDocument(docID);
                invertedIndex.insert(keyword);
            }
            return true;
        }
    }

    public boolean containsKeyword(String word) {
        if (invertedIndex.empty()) return false;

        invertedIndex.findFirst();
        for (int i = 0; i < invertedIndex.size(); i++) {
            if (invertedIndex.retrieve().word.word.compareTo(word) == 0)
                return true;
            invertedIndex.findNext();
        }
        return false;
    }

    public void printDocuments() {
        if (this.invertedIndex.empty())
            System.out.println("Empty Inverted Index");
        else {
            this.invertedIndex.findFirst();
            while (!this.invertedIndex.last()) {
                System.out.println(invertedIndex.retrieve());
                this.invertedIndex.findNext();
            }
            System.out.println(invertedIndex.retrieve());
        }
    }

    
    public void computeKeywordFrequency(String str) {
        str = str.toLowerCase().trim();
        String[] words = str.split(" ");
        keywordFrequencies = new DocumentFrequency[50];
        for (int i = 0; i < 50; i++) {
            keywordFrequencies[i] = new DocumentFrequency();
            keywordFrequencies[i].docID = i;
            keywordFrequencies[i].frequency = 0;
        }

        for (String word : words) {
            if (containsKeyword(word)) {
                int[] documentIDs = invertedIndex.retrieve().GetDocument();
                for (int j = 0; j < documentIDs.length; j++) {
                    if (documentIDs[j] != 0) {
                        int docIndex = j;
                        keywordFrequencies[docIndex].docID = docIndex;
                        keywordFrequencies[docIndex].frequency += documentIDs[j];
                    }
                }
            }
        }

        mergesort(keywordFrequencies, 0, keywordFrequencies.length - 1);

        System.out.println("\nDocID\t\tScore");
        for (int x = 0; keywordFrequencies[x].frequency != 0; x++) {
            System.out.println(keywordFrequencies[x].docID + "\t\t" + keywordFrequencies[x].frequency);
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

