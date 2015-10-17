import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Serves as a collection for a list of objects with a generic type.
 * 
 * @author Taylor, Andrey, Chelsea, Jean
 * @param <E>
 */
public class CollectionList<E extends Comparable<E>> implements Serializable {
    
    /**
	 * Declared local variables
	 */
	private static final long serialVersionUID = 1L;
	private List<E> list;
	
    
	/**
	 * Instantiates a empty a generic type linked list  
	 */
    public CollectionList() {
        list = new LinkedList<E>();
    }
    
    /**
     * Adds the element to the collection list
     * @param -- element to add into this collection
     */
    public void add(E element) {
        list.add(element);
        
    }
    
    /**
     * Removes the element from the collection list
     * @param -- element which to remove
     */
    public void remove(E element) {
        for(int iterate = 0; iterate<list.size();iterate++) {
            if(element.equals(list.get(iterate))) {
                list.remove(iterate);
                break;
            }
        }
    }
    
    /**
     * Searches the collection list for a specific element
     * @param -- searching element
     * @return -- found element or null if element is not found
     */
    public E search(E element) {
        for(int iterate=0; iterate<list.size();iterate++) {
            if(element.equals(list.get(iterate))) {
               return list.get(iterate);
            }
        }
        return null;
    }
    
    /**
     * Returns the iterator of the specific object in the collection list
     * @return -- Iterator of this collection 
     */
    public Iterator<E> iterator() {
        return list.iterator();  
    }
    
    /**
     * Returns the element at a specific index
     * @param -- index of element in this collection
     * @return -- returns element at the index
     */
    public E get(int index) {
    	return list.get(index);
    }
    
    /**
     * 
     * Returns the size of the collection list
     * @return -- size of this collection
     */
    public int size() {
    	return list.size();
    }
    
    
    /**
     * 
     */
    @Override
    public String toString() {
        return "Items In Collection: "+list.size();
    }
}
