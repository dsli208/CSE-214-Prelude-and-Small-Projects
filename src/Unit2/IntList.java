/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit2;

/**
 *
 * @author dsli
 */
public class IntList {
    private IntNode head;
    private IntNode tail;
    private IntNode cursor;
    
    public IntList() {
        head = null;
        tail = null;
        cursor = null;
    }
    
    public void addNewHead(int element) {
        IntNode newNode = new IntNode(element);
        newNode.setLink(head);
        head = newNode;
        if (tail == null) tail = head;//Meaning the list was EMPTY before this new IntNode was inserted
            cursor = head;
    }
    
    public void addIntAfter(int element) {
        IntNode newNode = new IntNode(element);
        if (cursor == null) {//meaning the list is EMPTY
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else {
            newNode.setLink(cursor.getLink());
            cursor.setLink(newNode);
            cursor = newNode; //advance cursor
            if (cursor.getLink() == null)
                tail = cursor;
        }
    }
    
    public void removeIntAfter() {
        if (cursor != tail) {
            cursor.setLink(cursor.getLink().getLink());
            if (cursor.getLink() == null)
                tail = cursor;//last node
        }
    }
    
    public void removeHead() {
        if (head != null)
            head = head.getLink();
        if (head == null)
            tail = null;
        cursor = head;
    }
    
    public boolean advanceCursor() {
        if (cursor != tail) {
            cursor = cursor.getLink();
            return true;
        }
        else
            return false;
    }
    
    public void resetCursor() {
        cursor = head;
    }
    
    public boolean isEmpty() {
        return (cursor == null);
    }
    
    public int listLength() {
        IntNode nodePtr = head;
        int answer = 0;
        while (nodePtr != null) {
            answer++;
            nodePtr = nodePtr.getLink();
        }
        return answer;
    }
    
    public boolean listSearch(int target) {
        IntNode nodePtr = head;
        while (nodePtr != null) {
            if (target == nodePtr.getData()) {
                cursor = nodePtr;
                return true;
            }
            nodePtr = nodePtr.getLink();
        }
        return false;
    }
    
    public boolean listPosition(int position) {
        IntNode nodePtr = head;
        int i = 1;
        if (position <= 0)
            throw new IllegalArgumentException();
        while (i < position && nodePtr != null) {
            nodePtr = nodePtr.getLink();
            i++;
        }
        if (nodePtr != null) {
            cursor = nodePtr;
        }
        return (nodePtr != null);
    }
    
    public static IntList listCopy(IntList source) {
        IntList newList = new IntList();
        IntNode nodePtr = source.head;
        while (nodePtr != null) {
            newList.addIntAfter(nodePtr.getData()); //adds AFTER the cursor
            nodePtr = nodePtr.getLink();
        }
        return newList;
    }
    
    public boolean remove(int target) {
        IntNode nodePtr = head;
        IntNode prevPtr = null;
        while (nodePtr != null && nodePtr.getData() != target) {
            prevPtr = nodePtr;
            nodePtr = nodePtr.getLink();
        }
        if (nodePtr != null)
            prevPtr.setLink(nodePtr.getLink());
        return (nodePtr != null);
    }
    
    public int getNodeData() throws EmptyListException {
        if (cursor == null)
            throw new EmptyListException();
        return cursor.getData();
    }
}
