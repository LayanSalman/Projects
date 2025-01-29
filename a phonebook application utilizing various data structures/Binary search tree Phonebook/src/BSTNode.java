

    public class BSTNode <K extends Comparable<K>,T> {
      public K key;  
      public T data;
      public BSTNode<K,T> left, right;
   
      public BSTNode() {
         left = right = null;
      }
   
      public BSTNode(K key, T data) {
         this.key = key  ;  
         this.data = data;
         left = right = null;
      }
   
      public BSTNode(K key, T data, BSTNode<K,T> l, BSTNode<K,T> r){
         this.key = key  ;  
         this.data = data;
         left = l;
         right = r;
      }
            
      @Override
        public String toString() {
         return " ["+ "key=" + key + ", data=" + data + "] ";
      }

   
   
}

