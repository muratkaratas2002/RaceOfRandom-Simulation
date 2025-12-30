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

public class MaxHeap {
    private Car[] heap;
    private int n;
    
    public MaxHeap(int capacity) {
        heap = new Car[capacity + 1];
        n = 0;
    }
    
    public void insert(Car car) {
        heap[++n] = car;
        swim(n);
    }
    
    public Car delMax() {
        if (isEmpty()) return null;
        
        Car max = heap[1];
        swap(1, n);
        n--;
        sink(1);
        return max;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    private void swim(int k) {
        while (k > 1 && heap[k].number > heap[k/2].number) {
            swap(k, k/2);
            k = k/2;
        }
    }
    
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && heap[j].number < heap[j + 1].number) {
                j++;
            }
            if (heap[k].number >= heap[j].number) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }
    
    private void swap(int i, int j) {
        Car temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}