package csc311;


import java.io.*;

import java.util.*;

 
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

 

public class DynamicProgrammingPortfolioAllocation {

   

    public static int totalInvestment1, totalInvestment2;

    public static double riskTolerance1, riskTolerance2;

    public static boolean totalInvestment1Read = false, riskTolerance1Read = false,

                          totalInvestment2Read = false, riskTolerance2Read = false;

   

    public static void main(String[] args) {

        // Read input from the text file

        List<Asset> example1 = readInput("Assets3.txt");

        List<Asset> example2 = readInput("Assets.txt");

      
        	
       
        // Dynamic programming approach for Example 1
    	

        Object[] dpResult1 = dynamicProgrammingOptimalAllocation(example1, totalInvestment1, riskTolerance1);

        double dpReturn1 = (double) dpResult1[0];

        int[] dpAllocation1 = (int[]) dpResult1[1];
        
        printOutput("Example 1", dpAllocation1, totalInvestment1 > 0 ? dpReturn1 : 0,
        		totalInvestment1 > 0 ? calculatePortfolioRisk(dpAllocation1, example1, totalInvestment1) : 0 , example1);

        

      

        // Dynamic programming approach for Example 2
        

        Object[] dpResult2 = dynamicProgrammingOptimalAllocation(example2, totalInvestment2, riskTolerance2);

        double dpReturn2 = (double) dpResult2[0];

        int[] dpAllocation2 = (int[]) dpResult2[1];

        printOutput("Example 2", dpAllocation2, totalInvestment2 > 0 ? dpReturn2 : 0,
        		totalInvestment2 > 0 ? calculatePortfolioRisk(dpAllocation2, example2, totalInvestment2) : 0 , example2);

       

    }
    
    
    public static Object[] dynamicProgrammingOptimalAllocation(List<Asset> assets, int totalInvestment, double riskTolerance) {

        // Initialize arrays to store optimal return and allocation

        double[][] dp = new double[assets.size() + 1][totalInvestment + 1];

        int[][][] allocation = new int[assets.size() + 1][totalInvestment + 1][assets.size()];

        // base case with 0 budget; 
        
        for(int i = 0 ; i<=assets.size() ; i++) {
        	dp[i][0] = 0;
        }
        // base case with 0 assets; 
        for(int i = 0 ; i<=assets.size() ; i++) {
        	dp[0][i] = 0;
        }
        
         // Fill the dp array using dynamic programming
          for (int assetIdx = 1; assetIdx <= assets.size(); assetIdx++) {
            Asset currentAsset = assets.get(assetIdx - 1);
            for (int budget = 1; budget <= totalInvestment; budget++) {
                for (int quantity = 0; quantity <= Math.min(currentAsset.quantity, budget); quantity++) {
                	
                    // Calculate candidate return 
                    double candidateReturn = dp[assetIdx - 1][budget - quantity] + currentAsset.expectedReturn * quantity;
                    
                  
                    // Calculate the new allocation considering the current quantity
                    int[] newAllocation = Arrays.copyOf(allocation[assetIdx - 1][budget - quantity], assets.size());
                    newAllocation[assetIdx - 1] = quantity;
                    
                    // Calculate candidate risk
                    double candidateRisk = calculatePortfolioRisk(newAllocation, assets, totalInvestment);
                    
                    
                    if (candidateRisk <= riskTolerance) { // Check risk tolerance first
                        if (candidateReturn > dp[assetIdx][budget]) {
                            dp[assetIdx][budget] = candidateReturn;
                            // Copy allocation from the new state
                            allocation[assetIdx][budget] = newAllocation;
                        }
                        
                    }
                }
            }
        }

        // Extract the optimal allocation and return

        double maxReturn = dp[assets.size()][totalInvestment];

        maxReturn /= totalInvestment; // Convert to decimal form

        int[] bestAllocation = allocation[assets.size()][totalInvestment];

       

        return new Object[]{maxReturn, bestAllocation};

    }
    
    
    static double calculatePortfolioRisk(int[] dpAllocation, List<Asset> assets, int totalInvestment) {

                double totalRisk = 0;

        for (int i = 0; i < assets.size(); i++) {

           totalRisk += assets.get(i).riskLevel * dpAllocation[i];

        }

        return (totalRisk/totalInvestment);

    }

    static double calculateExpectedReturn(int[] newAllocation, List<Asset> assets, int totalInvestment) {
    	   
        double totalExpectedReturn = 0;
     
        for (int i = 0; i < assets.size(); i++) {
           totalExpectedReturn += assets.get(i).expectedReturn * newAllocation[i];
           
        }
     
        return totalExpectedReturn / totalInvestment;
     }

    public static List<Asset> readInput(String fileName) {

        List<Asset> assets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(":");

                if (parts.length == 4) {

                    String id = parts[0].trim();

                    double expectedReturn = Double.parseDouble(parts[1].trim());

                    double riskLevel = Double.parseDouble(parts[2].trim());

                    int quantity = Integer.parseInt(parts[3].trim());

                    assets.add(new Asset(id, expectedReturn, riskLevel, quantity));

                } else {

                    // Parse total investment and risk tolerance

                    if (!totalInvestment1Read) {

                        totalInvestment1 = Integer.parseInt(extractDigits(line));

                        totalInvestment1Read = true;

                    } else if (!riskTolerance1Read) {

                        riskTolerance1 = Double.parseDouble(extractDigits(line));

                        riskTolerance1Read = true;

                    } else if (!totalInvestment2Read) {

                        totalInvestment2 = Integer.parseInt(extractDigits(line));

                        totalInvestment2Read = true;

                    } else if (!riskTolerance2Read) {

                        riskTolerance2 = Double.parseDouble(extractDigits(line));

                        riskTolerance2Read = true;

                    }

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return assets;

    }

   

    public static String extractDigits(String str) {

        StringBuilder sb = new StringBuilder();

        boolean foundDecimal = false;

        for (int i = 0; i < str.length(); i++) {

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
    
    
    // Method to print output to console
    static void printOutput(String exampleName, int[] dpAllocation1, double expectedPortfolioReturn,
            double portfolioRiskLevel, List<Asset> assets) {
            
       System.out.println(exampleName + ": Optimal Allocation:");
       // Print each asset and its quantity in the optimal allocation
       for (int i = 0; i < dpAllocation1.length; i++) {
          System.out.println(assets.get(i).id + ": " + (int) dpAllocation1[i] + " units");
       }
       
       System.out.println("Expected Portfolio Return: " + String.format("%.3f" ,expectedPortfolioReturn ));
          
       System.out.println("Portfolio Risk Level: " + String.format("%.3f" ,portfolioRiskLevel) + "\n");
       
       
    }


}
