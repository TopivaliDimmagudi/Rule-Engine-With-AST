package com.example;



    public class Node {
        public String type; 
        public Node left;   
        public Node right; 
        public String value; 
    
        public Node(String type, String value) {
            this.type = type;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    

