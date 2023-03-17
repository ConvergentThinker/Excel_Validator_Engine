package com.income.datavalidator;

import com.income.model.ErrorModel;
import com.income.utilities.ExcelUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule2ValidatorEngine {

    private ExcelUtils excelUtils;
    private List<ErrorModel> errors;

    public  Rule2ValidatorEngine() {
        System.out.println("Rule2ValidatorEngine Obj created...");
        excelUtils = new ExcelUtils();
        errors = new ArrayList<>();
    }

    public String getErrorsList(){
        System.out.println("errors size in Rule 2:: "+ errors.size());
        String data="";
        for (int i = 0; i < errors.size(); i++) {
            ErrorModel rule = errors.get(i);
            String x = "";
            x = x.concat(rule.getRule()).concat(",").concat(rule.getSheetName()).concat(",").concat(String.valueOf(rule.getRowNo())).concat(",")
                    .concat(rule.getColumnHeader()).concat(",").concat( rule.getInfo());
            data = data.concat(x).concat("&");
        }
        return data;
    }



    public void validateRule2(Map<String, Map<String, Map<Integer, String>>> inputExcelData) {
        System.out.println(">>>>>>>> R2 <<<<<<<<<<<<");


    }




}
