package com.income.model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;


@Data
@ExcelSheet("Rule2")
public class Rule2Model {

    @ExcelCellName("Run?[Yes/No]")
    private String isToRun = "";

    @ExcelCellName("Sheet")
    private String  sheet = "";

    @ExcelCellName("Row[All/Custom]")
    private String  ruleExecutionType = "";

    @ExcelCellName("Row From")
    private String  fromRow = "";

    @ExcelCellName("Row To")
    private String  toRow = "";

    @ExcelCellName("Target Header")
    private String  targetHeader = "";

    @ExcelCellName("Format")
    private String  format = "";


}
