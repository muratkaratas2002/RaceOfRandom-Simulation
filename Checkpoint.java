/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raceofrandom;

/*
 * COMP2112 Fall 2025 - Project 3: Race of Random
 * Group 2
 * Murat Karatas - 22COMP1015
 * Bugra Alp - 23SOFT1048
 */

public class Checkpoint {
    int id;
    String ruleType;
    
    Queue<Car> queue;
    Stack<Car> stack;
    MaxHeap maxHeap;
    MinHeap minHeap;
    
    public Checkpoint(int id, String ruleType, int capacity) {
        this.id = id;
        this.ruleType = ruleType;
        
        if (ruleType.equals("FIFO")) {
            queue = new Queue<Car>();
        } else if (ruleType.equals("LIFO")) {
            stack = new Stack<Car>();
        } else if (ruleType.equals("MAX_HEAP")) {
            maxHeap = new MaxHeap(capacity);
        } else if (ruleType.equals("START")) {
            minHeap = new MinHeap(capacity);
        }
    }
    
    public void addCar(Car car) {
        if (ruleType.equals("FIFO")) {
            queue.enqueue(car);
        } else if (ruleType.equals("LIFO")) {
            stack.push(car);
        } else if (ruleType.equals("MAX_HEAP")) {
            maxHeap.insert(car);
        } else if (ruleType.equals("START")) {
            minHeap.insert(car);
        }
    }
    
    public Car removeCar() {
        if (ruleType.equals("FIFO")) {
            return queue.dequeue();
        } else if (ruleType.equals("LIFO")) {
            return stack.pop();
        } else if (ruleType.equals("MAX_HEAP")) {
            return maxHeap.delMax();
        } else if (ruleType.equals("START")) {
            return minHeap.delMin();
        }
        return null;
    }
    
    public boolean isEmpty() {
        if (ruleType.equals("FIFO")) {
            return queue.isEmpty();
        } else if (ruleType.equals("LIFO")) {
            return stack.isEmpty();
        } else if (ruleType.equals("MAX_HEAP")) {
            return maxHeap.isEmpty();
        } else if (ruleType.equals("START")) {
            return minHeap.isEmpty();
        }
        return true;
    }
}