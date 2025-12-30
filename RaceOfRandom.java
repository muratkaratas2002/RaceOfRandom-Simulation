/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package raceofrandom;

/*
 * COMP2112 Fall 2025 - Project 3: Race of Random
 * Group 2
 * Murat Karatas - 22COMP1015
 * Bugra Alp - 23SOFT1048
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RaceOfRandom {
    
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("        COMP2112 - RACE OF RANDOM");
        System.out.println("        Group 2");
        System.out.println("        Murat Karatas - 22COMP1015");
        System.out.println("        Bugra Alp - 23SOFT1048");
        System.out.println("============================================================");
        
        Graph racetrack = loadRacetrack("racetrack.txt");
        
        if (racetrack == null) {
            System.out.println("Error: Could not load racetrack!");
            return;
        }
        
        Scanner input = new Scanner(System.in);
        int N = 0;
        
        while (N <= 0) {
            System.out.print("\nEnter the number of cars in the race: ");
            try {
                N = input.nextInt();
                if (N <= 0) {
                    System.out.println("Please enter a positive number!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                input.nextLine();
            }
        }
        
        System.out.println("\n============================================================");
        System.out.println("RACE STARTING WITH " + N + " CARS!");
        System.out.println("============================================================\n");
        
        Race race = new Race(racetrack, N);
        race.startRace();
        
        System.out.println("\n============================================================");
        System.out.println("RACE FINISHED!");
        System.out.println("============================================================\n");
        
        race.displayLeaderboard();
        
        while (true) {
            System.out.print("\nEnter car number to see its path (0 to exit): ");
            try {
                int carNum = input.nextInt();
                if (carNum == 0) break;
                if (carNum < 1 || carNum > N) {
                    System.out.println("Invalid car number!");
                    continue;
                }
                race.displayCarPath(carNum);
            } catch (Exception e) {
                System.out.println("Invalid input!");
                input.nextLine();
            }
        }
        
        System.out.println("\n============================================================");
        System.out.println("PATH VERIFICATION");
        System.out.println("============================================================");
        
        while (true) {
            System.out.print("\nCheck if path exists between checkpoints (format: from to) or 0 to exit: ");
            try {
                int from = input.nextInt();
                if (from == 0) break;
                int to = input.nextInt();
                
                if (from < 0 || from >= racetrack.V || to < 0 || to >= racetrack.V) {
                    System.out.println("Invalid checkpoint numbers!");
                    continue;
                }
                
                boolean pathExists = racetrack.hasPath(from, to);
                if (pathExists) {
                    System.out.println("YES - Path exists from checkpoint " + from + " to checkpoint " + to);
                } else {
                    System.out.println("NO - No path exists from checkpoint " + from + " to checkpoint " + to);
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                input.nextLine();
            }
        }
        
        System.out.println("\n============================================================");
        System.out.println("Thank you for using Race of Random!");
        System.out.println("============================================================");
        
        input.close();
    }
    
    private static Graph loadRacetrack(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            
            int V = scanner.nextInt();
            int E = scanner.nextInt();
            
            System.out.println("Loading racetrack...");
            System.out.println("Checkpoints: " + V);
            System.out.println("Roads: " + E);
            
            Graph graph = new Graph(V);
            
            for (int i = 0; i < E; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int distance = scanner.nextInt();
                
                graph.addEdge(from, to, distance);
            }
            
            scanner.close();
            
            graph.buildArrays();
            
            System.out.println("Racetrack loaded successfully!\n");
            return graph;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found!");
            return null;
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
