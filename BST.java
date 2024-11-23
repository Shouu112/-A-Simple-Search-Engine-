/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ds;


public class BST<K extends Comparable<K>, T>
{
   class BSTNode<K extends Comparable<K>, T> {
        public K key;
        public T data;
        public BSTNode<K,T> left, right;

        /** Creates a new instance of BSTNode */
        public BSTNode(K k, T val) {
                key = k;
                data = val;
                left = right = null;
        }

        public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
                key = k;
                data = val;
                left = l;
                right = r;
        }
    }
    
        BSTNode<K, T> root; 
        BSTNode<K, T > current;
        int count ;
                
        public BST()
        {
            root = current = null;
            count = 0; //
        }
        
        public int size() 
        {
            return count;
        }

        public boolean empty()
        {
            return root == null;
        }

        public T retrieve()
        {
            T data =null;
            if (current != null)
                data = current.data;
            return data;
        }

        public void update(T a)
        {
            if (current != null)
                current.data = a;
        }
        public boolean find(K key) 
        {
            BSTNode<K,T> p = root;

            if(empty())
                    return false;

            while(p != null) {
                    if(p.key.compareTo(key) == 0)
                    {
                            current = p;
                            return true;
                    }
                    else if(key.compareTo(p.key) < 0)
                            p = p.left;
                    else
                            p = p.right;
            }
            return false;
        }

        public boolean insert(K key, T data)
        {

            if(empty())
            {
                current = root = new BSTNode <K, T> ( key, data);
                count ++;
                return true;
            }
            BSTNode<K,T> q = null;
            BSTNode<K,T> p  = root;
            
            while(p != null) {
                    if(p.key.compareTo(key) == 0)  
                    {
                            return false;
                    }
                    else if(key.compareTo(p.key) < 0)
                    {
                        q = p;
                        p = p.left;
                    }
                    else
                    {
                        q = p;
                        p = p.right;
                    }
            }
            if(key.compareTo(q.key) < 0)
            {
                q.left = new BSTNode <K, T> ( key, data);
                current = q.left;
            }
            
            else
            {
                q.right = new BSTNode <K, T> ( key, data);
                current = q.right;
            }
            count ++;
            return true;
        }

        public boolean remove(K key)
        {
            Boolean removed = new Boolean(false);
            BSTNode<K,T> p;
            
            p = remove_aux(key, root, removed);
            root = p;
            
            if (current.key.compareTo(key) == 0)
                current = root;
            if (removed)
                count -- ;
            
            return removed;
        }
    
        private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag) 
        {
            BSTNode<K,T> q, child = null;
            if(p == null)
                    return null;
            if(key.compareTo(p.key ) < 0)
                    p.left = remove_aux(key, p.left, flag); //go left
            else if(key.compareTo(p.key) > 0)
                    p.right = remove_aux(key, p.right, flag); //go right
            else {
                    
                    flag = true;
                    if (p.left != null && p.right != null)
                    { //two children
                            q = find_min(p.right);
                            p.key = q.key;
                            p.data = q.data;
                            p.right = remove_aux(q.key, p.right, flag);
                    }
                    else 
                    {
                            if (p.right == null) //one child
                                    child = p.left;
                            else if (p.left == null) //one child
                                    child = p.right;
                            return child;
                    }
                }
            return p;
        }
        private BSTNode<K,T> find_min(BSTNode<K,T> p)
        {
            if(p == null)
                    return null;

            while(p.left != null){
                    p = p.left;
            }
            return p;
        }
        
        public void Traverse()   
        {
            if (root != null)
                traverseTree1(root);
        }
        
        private void traverseTree1 (BSTNode<K,T> node  )    
        {
            if (node == null)
                return;
            traverseTree1( node.left);
            System.out.println(node.data);
            traverseTree1( node.right);
            
        }

       public void TraverseT()
        {
            if (root != null)
                traverseTree2(root);
        }
        
        private void traverseTree2(BSTNode<K,T> node)
        {
            if (node == null)
                return;
            traverseTree2( node.left );
            if (node.data instanceof BST )
            {
                System.out.println( "Node key ==== "+ node.key);
                ((BST <String,ItemRank>) node.data).Traverse();
            }
            else
                System.out.println(node.data);
            
            traverseTree2( node.right);
            
        }
          public void Print()
        {
            if (root != null)
                PrintData(root);
        }
        
        private void PrintData (BSTNode<K,T> node)
        {
            if (node == null)
                return;
            PrintData( node.left );
           
           System.out.print(node.key);
            if (node.data instanceof SearchKeyword )
            {
                System.out.print("   Documents_IDs: ");
                boolean [] docs = ((SearchKeyword) node.data).fetchdocemunt();
                for ( int i  = 0 ; i < 50 ; i++)
                    if ( docs[i])
                        System.out.print( " " + i + " " );
                System.out.println("");
                System.out.println("*******************************************************************");

            }
            PrintData( node.right);
        }
}


