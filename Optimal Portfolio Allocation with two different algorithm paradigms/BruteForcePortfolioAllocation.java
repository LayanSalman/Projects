package csc311;

import java.io.*;
import java.util.*;


// Class to represent each asset in the portfolio
class Asset {
   String id;
   double expectedReturn;
   double riskLevel;
   int quantity;

   public Asset(String id, double expectedReturn, double riskLevel, int quantity) {
      this.id = id;
      this.expectedReturn = expectedReturn;
      this.riskLevel = riskLevel;
      this.quantity = quantity;
   }
}


public class BruteForcePortfolioAllocation {

   public static int totalInvestment1, totalInvestment2;
   public static double riskTolerance1, riskTolerance2;
   public static boolean totalInvestment1Read, riskTolerance1Read, 
   totalInvestment2Read, riskTolerance2Read;
   
   
   public static void main(String[] args) {
   
      // Read input from the text file
      List<Asset> example1 = readInput("Assets.txt");
      List<Asset> example2 = readInput("Assets2.txt");
   
      // Call the brute force method to find optimal allocation
      double[] anwr1 = bruteForce(totalInvestment1, riskTolerance1, example1);
      double[] anwr2 = bruteForce(totalInvestment2, riskTolerance2, example2);
   
      // Print optimal allocation and results to console
      printOutput("Example 1", anwr1, totalInvestment1 > 0 ? calculateExpectedReturn(anwr1, example1, totalInvestment1) : 0,
             totalInvestment1 > 0 ? calculatePortfolioRisk(anwr1, example1, totalInvestment1) : 0, example1);
      printOutput("Example 2", anwr2, totalInvestment2 > 0 ? calculateExpectedReturn(anwr2, example2, totalInvestment2) : 0,
             totalInvestment2 > 0 ? calculatePortfolioRisk(anwr2, example2, totalInvestment2) : 0, example2);
   }

   // Brute force algorithm to find all possible allocations 
   static double[] bruteForce(int totalInvestment, double riskTolerance, List<Asset> assets) {
      double maxReturn = 0;
      double[] optimalAllocation = new double[assets.size()];
      double[] investment = new double[assets.size()];
   
      for (int i = 0; i <= totalInvestment; i++) {
         for (int j = 0; j <= totalInvestment - i; j++) {
            int k = totalInvestment - i - j;
            investment[0] = i;
            investment[1] = j;
            investment[2] = k;
         
            boolean isValid = true;
         
            for (int l = 0; l < assets.size(); l++) {
               if (investment[l] > assets.get(l).quantity) {
                  isValid = false;
                  break;
               }
            }
            if (isValid) {
               double totalExpectedReturn = 0;
               double totalRisk = 0;
               for (int l = 0; l < assets.size(); l++) {
                  totalExpectedReturn += assets.get(l).expectedReturn * (investment[l] / totalInvestment);
                  totalRisk += assets.get(l).riskLevel * (investment[l] / totalInvestment);
               }
            
               if (totalRisk <= riskTolerance && totalExpectedReturn > maxReturn) {
                  maxReturn = totalExpectedReturn;
                  System.arraycopy(investment, 0, optimalAllocation, 0, assets.size());
               }
            }
         }
      }
      return optimalAllocation;
   }

   // Method to calculate expected return based on the optimal allocation
   static double calculateExpectedReturn(double[] allocation, List<Asset> assets, int totalInvestment) {
   
      double totalExpectedReturn = 0;
   
      for (int i = 0; i < assets.size(); i++) {
         totalExpectedReturn += assets.get(i).expectedReturn * allocation[i];
         
      }
   
      return totalExpectedReturn / totalInvestment;
   }

   // Method to calculate portfolio risk based on the optimal allocation
   static double calculatePortfolioRisk(double[] allocation, List<Asset> assets, int totalInvestment) {
   
      double totalRisk = 0;
      
      for (int i = 0; i < assets.size(); i++) {
         totalRisk += assets.get(i).riskLevel * allocation[i];
         
      }
   
      return totalRisk / totalInvestment;
   }
   

   // Method to print output to console
   static void printOutput(String exampleName, double[] optimalAllocation, double expectedPortfolioReturn,
           double portfolioRiskLevel, List<Asset> assets) {
           
      System.out.println(exampleName + ": Optimal Allocation:");
      // Print each asset and its quantity in the optimal allocation
      for (int i = 0; i < optimalAllocation.length; i++) {
         System.out.println(assets.get(i).id + ": " + (int) optimalAllocation[i] + " units");
      }
      
      System.out.println("Expected Portfolio Return: " + String.format("%.3f" ,expectedPortfolioReturn ));
         
      System.out.println("Portfolio Risk Level: " + String.format("%.3f" ,portfolioRiskLevel) + "\n");
      
      
   }


   // Method to read input from the text file
   public static List<Asset> readInput(String fileName) {
      List<Asset> assets = new ArrayList<>();
   
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
         String line;
      
         while ((line = br.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length != 4) {
               if (totalInvestment1Read == false) {
                  String TempString1 = extractDigits(line);
                  totalInvestment1 = Integer.parseInt(TempString1);
                  totalInvestment1Read = true;
               } else {
                  if (riskTolerance1Read == false) {
                     String TempString1 = extractDigits(line);
                     riskTolerance1 = Double.parseDouble(TempString1);
                     riskTolerance1Read = true;
                  } else {
                     if (totalInvestment2Read == false) {
                        String TempString1 = extractDigits(line);
                        totalInvestment2 = Integer.parseInt(TempString1);
                        totalInvestment2Read = true;
                     } else {
                        String TempString1 = extractDigits(line);
                        riskTolerance2 = Double.parseDouble(TempString1);
                        riskTolerance2Read = true;
                     }
                  }
               }
            } else {
               String id = parts[0].trim();
               double expectedReturn = Double.parseDouble(parts[1].trim());
               double riskLevel = Double.parseDouble(parts[2].trim());
               int quantity = Integer.parseInt(parts[3].trim());
               assets.add(new Asset(id, expectedReturn, riskLevel, quantity));
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   
      return assets;
   }


   // Method to extract digits from a string
   public static String extractDigits(String str) {
      StringBuilder sb = new StringBuilder();
      boolean foundDecimal = false;
   
      for (int i=0 ; i< str.length() ; i++) {
         char c = str.charAt(i);
         if (Character.isDigit(c) || (c == '.' && !foundDecimal)) {
            sb.append(c);
            if (c == '.') {
               foundDecimal = true;
            }
         }
      }
      return sb.toString();
   }
}
