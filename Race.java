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

import java.util.Random;

public class Race {
    Graph racetrack;
    Car[] cars;
    Checkpoint[] checkpoints;
    Random random;
    int currentTime;
    int finishedCount;
    
    public Race(Graph racetrack, int N) {
        this.racetrack = racetrack;
        this.cars = new Car[N];
        this.checkpoints = new Checkpoint[racetrack.V];
        this.random = new Random();
        this.currentTime = 0;
        this.finishedCount = 0;
        
        for (int i = 0; i < N; i++) {
            cars[i] = new Car(i + 1);
        }
        
        for (int i = 0; i < racetrack.V; i++) {
            if (i == 0) {
                checkpoints[i] = new Checkpoint(i, "START", N);
            } else if (i == 10) {
                checkpoints[i] = new Checkpoint(i, "PIT", N);
            } else if (i == 15) {
                checkpoints[i] = new Checkpoint(i, "FINISH", N);
            } else {
                int rule = random.nextInt(3);
                String ruleType = "";
                switch (rule) {
                    case 0: ruleType = "FIFO"; break;
                    case 1: ruleType = "LIFO"; break;
                    case 2: ruleType = "MAX_HEAP"; break;
                }
                checkpoints[i] = new Checkpoint(i, ruleType, N);
            }
        }
        
        System.out.println("Checkpoint Rules:");
        for (int i = 0; i < checkpoints.length; i++) {
            System.out.println("  Checkpoint " + i + ": " + checkpoints[i].ruleType);
        }
        System.out.println();
    }
    
    public void startRace() {
    System.out.println("RACE START - Cars entering checkpoint 0 (START)");
    System.out.println("------------------------------------------------------------");
    
    for (int i = 0; i < cars.length; i++) {
        checkpoints[0].addCar(cars[i]);
    }
    
    System.out.println("\nCars leaving checkpoint 0:");
    while (!checkpoints[0].isEmpty()) {
        Car car = checkpoints[0].removeCar();
        System.out.println("Time " + currentTime + ": Car " + car.number + " starts from checkpoint 0");
        
        chooseAndMoveCar(car, 0);
        currentTime++;
    }
    
    System.out.println();
    
    int minArrival = Integer.MAX_VALUE;
    for (int i = 0; i < cars.length; i++) {
        if (cars[i].arrivalTime < minArrival) {
            minArrival = cars[i].arrivalTime;
        }
    }
    currentTime = minArrival;
    
    while (!isRaceOver()) {
        processCheckpoints();
        currentTime++;
    }
}
    
    private void chooseAndMoveCar(Car car, int currentCheckpoint) {
        int[] result = racetrack.getRandomNextWithDistance(currentCheckpoint, random);
        int nextCheckpoint = result[0];
        int distance = result[1];
        
        if (nextCheckpoint == -1) {
            return;
        }
        
        car.currentCheckpoint = nextCheckpoint;
        car.arrivalTime = currentTime + distance;
        car.path.insertLast(nextCheckpoint);
        
        System.out.println("Time " + currentTime + ": Car " + car.number + 
                         " travels to checkpoint " + nextCheckpoint + 
                         " (distance: " + distance + ", arrival time: " + car.arrivalTime + ")");
        
        if (nextCheckpoint == 10) {
            car.fellIntoPit = true;
            System.out.println("  >>> Car " + car.number + " fell into the PIT and is OUT of the race!");
        } else if (nextCheckpoint == 15) {
            car.isFinished = true;
            car.finishPosition = ++finishedCount;
            System.out.println("  >>> Car " + car.number + " FINISHED in position " + car.finishPosition + "!");
        }
    }
    
    private void processCheckpoints() {
        for (int i = 0; i < cars.length; i++) {
            Car car = cars[i];
            
            if (!car.isFinished && !car.fellIntoPit && car.arrivalTime == currentTime) {
                int checkpoint = car.currentCheckpoint;
                
                if (checkpoint != 0 && checkpoint != 10 && checkpoint != 15) {
                    checkpoints[checkpoint].addCar(car);
                    System.out.println("Time " + currentTime + ": Car " + car.number + 
                                     " arrived at checkpoint " + checkpoint + 
                                     " (" + checkpoints[checkpoint].ruleType + ")");
                }
            }
        }
        
        for (int i = 0; i < checkpoints.length; i++) {
            if (i == 0 || i == 10 || i == 15) continue;
            
            Checkpoint checkpoint = checkpoints[i];
            
            if (!checkpoint.isEmpty()) {
                Car car = checkpoint.removeCar();
                
                if (car != null) {
                    System.out.println("Time " + currentTime + ": Car " + car.number + 
                                     " passes checkpoint " + i + 
                                     " (" + checkpoint.ruleType + " rule)");
                    
                    chooseAndMoveCar(car, i);
                }
            }
        }
    }
    
    private boolean isRaceOver() {
        for (int i = 0; i < cars.length; i++) {
            if (!cars[i].isFinished && !cars[i].fellIntoPit) {
                return false;
            }
        }
        return true;
    }
    
    public void displayLeaderboard() {
        System.out.println("LEADERBOARD");
        System.out.println("============================================================");
        
        System.out.println("\nFinished Cars:");
        System.out.println("------------------------------------------------------------");
        
        boolean[] displayed = new boolean[cars.length];
        for (int pos = 1; pos <= finishedCount; pos++) {
            for (int i = 0; i < cars.length; i++) {
                if (cars[i].finishPosition == pos && !displayed[i]) {
                    System.out.println("  Position " + pos + ": Car " + cars[i].number);
                    displayed[i] = true;
                }
            }
        }
        
        System.out.println("\nDid Not Finish:");
        System.out.println("------------------------------------------------------------");
        
        for (int i = 0; i < cars.length; i++) {
            if (!cars[i].isFinished) {
                String reason = cars[i].fellIntoPit ? "Fell into pit" : "Did not complete";
                System.out.println("  Car " + cars[i].number + ": " + reason + 
                                 " (Last checkpoint: " + cars[i].currentCheckpoint + ")");
            }
        }
    }
    
    public void displayCarPath(int carNumber) {
        Car car = cars[carNumber - 1];
        
        System.out.println("\n============================================================");
        System.out.println("PATH FOR CAR " + carNumber);
        System.out.println("============================================================");
        
        System.out.print("Path: ");
        Node<Integer> node = car.path.first;
        while (node != null) {
            System.out.print(node.data);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
        
        String status = "";
        if (car.isFinished) {
            status = "FINISHED (Position " + car.finishPosition + ")";
        } else if (car.fellIntoPit) {
            status = "FELL INTO PIT";
        } else {
            status = "DID NOT FINISH";
        }
        System.out.println("Status: " + status);
        
        boolean isValidPath = validateCarPath(car);
        if (isValidPath) {
            System.out.println("Path Validation: VALID - No cheating detected");
        } else {
            System.out.println("Path Validation: INVALID - Cheating detected!");
        }
    }
    
    private boolean validateCarPath(Car car) {
        Node<Integer> current = car.path.first;
        
        if (current == null) return true;
        
        while (current.next != null) {
            int from = current.data;
            int to = current.next.data;
            
            if (!racetrack.hasDirectRoad(from, to)) {
                System.out.println("  Invalid move detected: " + from + " -> " + to + " (no direct road)");
                return false;
            }
            
            current = current.next;
        }
        
        return true;
    }
}
