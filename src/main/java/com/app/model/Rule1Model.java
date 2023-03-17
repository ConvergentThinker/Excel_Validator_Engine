package com.app.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;


@Data
@ExcelSheet("Rule1")
public class Rule1Model {

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

    @ExcelCellName("operand1")
    private String  operand1 = "";

    @ExcelCellName("operand2")
    private String  operand2 = "";

    @ExcelCellName("operator")
    private String  operator = "";



}
