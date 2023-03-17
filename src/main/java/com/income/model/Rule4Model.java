package com.income.model;


import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

@Data
@ExcelSheet("Rule4")
public class Rule4Model {

    @ExcelCellName("Run?[Yes/No]")
    private String isToRun = "";

    @ExcelCellName("Row[All/Custom]")
    private String  ruleExecutionType = "";

    @ExcelCellName("Row From")
    private String  fromRow = "";

    @ExcelCellName("Row To")
    private String  toRow = "";

    @ExcelCellName("Sheet")
    private String  sheet = "";

    @ExcelCellName("Target Header")
    private String  targetHeader = "";


}
