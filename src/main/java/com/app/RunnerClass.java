package com.app;

import com.app.datavalidator.*;
import com.app.engine.ReaderEngine;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class RunnerClass {


    public static void main(String[] args) throws IOException, InterruptedException {

        String ruleXlsxPath = "";
        String testXlsxPath = "";
        String rulesList = "";

        // creates an object of Scanner
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Rule Key (Available now only 'R4': ");
        // takes input from the keyboard
        rulesList = input.nextLine();

        System.out.println("Enter Rule Excel path :");
        ruleXlsxPath = input.nextLine();

        System.out.println("Enter Input excel file path : ");
        testXlsxPath = input.nextLine();

        // closes the scanner
        input.close();


       // System.out.println("rulesList :: " + rulesList);
       // System.out.println("ruleXlsxPath :: "+ ruleXlsxPath);
       // System.out.println( "testXlsxPath :: "+ testXlsxPath);


        final RunnerClass runner = new RunnerClass();
        final ReaderEngine readerEngine = new ReaderEngine();
        Map<String, Map<String, Map<Integer, String>>> inputExcelData = readerEngine.readCompleteExcel(testXlsxPath);
        String[] rulesArr = rulesList.split(",");
        int noOfThreads = rulesArr.length;

        System.out.println("noOfThreads : "+ noOfThreads);

        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);
        Set<Callable<String>> callables = new HashSet<>();

        for (int i = 0; i < noOfThreads; i++) {
            int finalI = i;

            String finalRuleXlsxPath = ruleXlsxPath;
            callables.add(new Callable<String>() {
                public String call() {
                    //System.out.println(" Thread name: " + Thread.currentThread().getName());
                    return runner.executeRule(rulesArr[finalI], inputExcelData, finalRuleXlsxPath);
                }
            });
        }

        // Run all rules
        List<Future<String>> futures = executorService.invokeAll(callables);


        // print results
        for (Future<String> future : futures) {
            //System.out.println("future.get = " + future.isDone());
            try {
                // this is to return anything after that particular thread completed.
                // In our case, we are returning errors list,  just print them on the console.

                if (future.get().contains("&")) {

                    String[] infoArr = future.get().split("&");
                    System.out.println("===============INFO===============");

                    for (String arr : infoArr) {
                        String[] item = arr.split(",");
                        System.out.println("For " + item[0] + ",in sheet " + item[1] + " and Row No:" + item[2] + " in column " + item[3] + " >>> INFO: " + item[4]);
                    }
                    System.out.println("===================================");
                }

            } catch (ExecutionException e) {
                // this is best place to see program failure reason, why?
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }


    public String executeRule(String value, Map<String, Map<String, Map<Integer, String>>> inputExcelData,String ruleXlsxPath) {

        String getErrorListSTR = "";

        switch (value) {

            case "R1":
                Rule1ValidatorEngine rule1ValidatorEngine = new Rule1ValidatorEngine();
                rule1ValidatorEngine.validateRule1(inputExcelData);
                getErrorListSTR = rule1ValidatorEngine.getErrorsList();
                break;
            case "R2":
                Rule2ValidatorEngine rule2ValidatorEngine = new Rule2ValidatorEngine();
                rule2ValidatorEngine.validateRule2(inputExcelData);
                getErrorListSTR = rule2ValidatorEngine.getErrorsList();
                break;
            case "R3":
                Rule3ValidatorEngine rule3ValidatorEngine = new Rule3ValidatorEngine();
                rule3ValidatorEngine.validateRule3(inputExcelData);
                getErrorListSTR = rule3ValidatorEngine.getErrorsList();
                break;
            case "R4":
                Rule4ValidatorEngine rule4ValidatorEngine = new Rule4ValidatorEngine();
                rule4ValidatorEngine.validateRule4(inputExcelData,ruleXlsxPath);
                getErrorListSTR = rule4ValidatorEngine.getErrorsList();
                break;

            default:
                System.out.println("input Rule not present to proceed validation. please input available Rules ");

        }

        return getErrorListSTR;

    }


}
