
public class Contact_BST<K extends Comparable<K>,T> {

	BSTNode<K,T> root, current;

	public Contact_BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve () {
		return current.data;
	}

	public boolean findkey(K tkey) {
		BSTNode<K,T>  p = root;
		BSTNode<K,T>  q = root;

		if(empty())
			return false;

		while(p != null) {
			q = p;
			if(p.key.compareTo(tkey) == 0) {
				current = p;
				return true;
			}
			else if(tkey.compareTo(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}    
	public boolean insert(K key, T data) {
		BSTNode<K,T>  p;
		BSTNode<K,T>  q; 
		q= current;

		if(findkey(key)) {
			current = q;   
			return false; 
		}

		p = new BSTNode<K,T> (key, data);
		if (empty()) {
			root = current = p;
			return true;
		}

		else {
			// current is pointing to parent of the new key
			if (key.compareTo(current.key) < 0) //return negative number meaning that key<(current.key)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public boolean removeKey(K k) {
		// Search 
		K k1 = k;      
		BSTNode<K,T>  p = root;      
		BSTNode<K,T>  q = null;    // Parent of p

		while (p != null) 
		{
			if (k1.compareTo(p.key) <0) 
			{
				q =p;
				p = p.left;
			}
			else if (k1.compareTo(p.key) >0) 
			{
				q = p;
				p = p.right;
			} 
			else { 
				// Found the key            
				// Check the three cases
				if ((p.left != null) && (p.right != null)) 
				{ 
					// Case 3: two children                
					// Search for the min in the right subtree
					BSTNode<K,T>  min = p.right;
					q = p;
					while (min.left != null) 
					{
						q = min;
						min = min.left;
					}
					p.key = min.key;               
					p.data = min.data;
					k1 = min.key;
					p = min;
					// Now fall back to either case 1 or 2
				}
				// The subtree rooted at p will change here             
				if (p.left != null) 
				{ 
					// One child
					p = p.left;
				} 
				else 
				{ 
					// One or no children
					p = p.right;
				}

				if (q == null) 
				{ 
					// No parent for p, root must change
					root = p;
				} 
				else 
				{
					if (k1.compareTo(q.key) <0) 
					{
						q.left = p;
					} 
					else 
					{
						q.right = p;
					}
				}
				current = root;
				return true;
			}
		}
		return false; // Not found
	}



	//////////METHOD SEARCH FIRST NAME ///////////////


	public boolean SearchFisrt_name(String name) {
		return SearchFisrt_name_PRV (root, name);  
	}

	private boolean SearchFisrt_name_PRV (BSTNode <K, T> node, String name){
		if (node == null)
			return false;

		boolean found = false;
		boolean leftFound = SearchFisrt_name_PRV(node.left , name);

		if (((Contact)node.data).compareFirstName(name) == 0) {
			System.out.println(node.data);
			found = true;
		}

		boolean rightFound = SearchFisrt_name_PRV(node.right, name);
		return found || leftFound || rightFound;

	}

	////////// METHOD SEARCH PHONE ///////////////

	public Contact searchPhone(String phone) {
		return searchPhone_PRV(root, phone);
	}


	private Contact searchPhone_PRV (BSTNode<K, T> node, String phone) {
		if (node == null)
			return null;

		Contact Left_result = searchPhone_PRV (node.left, phone);
		if (Left_result != null) {
			return Left_result;
		}

		if (((Contact) node.data).compareToPhone(phone) == 0)
			return (Contact) node.data;

		return searchPhone_PRV (node.right, phone);
	}





	//////////METHOD SEARCH EMAIL ///////////////

	public boolean SearchEmail(String email){
		return SearchEmail_PRV (root, email);
	}

	private boolean SearchEmail_PRV (BSTNode <K, T> node, String email) {
		if (node == null)
			return false;

		boolean found = false;
		boolean leftFound = SearchEmail_PRV(node.left , email);

		if (((Contact)node.data).compareToEmail(email) == 0) {
			System.out.println(node.data);
			found = true;
		}

		boolean rightFound = SearchEmail_PRV(node.right, email);
		return found || leftFound || rightFound;
	}


	////////// METHOD SEARCH BIRTHDAY ///////////////

	public boolean SearchDOB(String DOB){
		return SearchDOB_PRV (root, DOB);
	}

	private boolean SearchDOB_PRV (BSTNode <K, T> node, String DOB)
	{
		if (node == null)
			return false;

		boolean found = false;
		boolean leftFound = SearchDOB_PRV (node.left , DOB);

		if (((Contact)node.data).compareToBirthday(DOB) == 0) {
			System.out.println(node.data);
			found = true;
		}

		boolean rightFound = SearchDOB_PRV (node.right, DOB);
		return found || leftFound || rightFound;
	}
	//////////METHOD SEARCH ADDRESS ///////////////

	public boolean SearchADS(String ADS){
		return SearchADS_PRV (root, ADS);
	}

	private boolean SearchADS_PRV (BSTNode <K, T> node, String ADS) {
		if (node == null)
			return false;

		boolean found = false;
		boolean leftFound = SearchADS_PRV(node.left , ADS);

		if (((Contact)node.data).compareToAddress(ADS) == 0) {
			System.out.println(node.data);
			found = true;
		}

		boolean rightFound = SearchADS_PRV(node.right, ADS);
		return found || leftFound || rightFound;
	}

}
