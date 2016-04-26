package com.shuyinqi.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;

/**
 * Created by jiayusun on 2016/4/25.
 * 在guava库中还提供了一种二维表结构：Table。使用Table可以实现二维矩阵的数据结构，可以是稀溜矩阵。
 */
public class GuavaTable {

    public static void main(String[] args) {
        Table<Integer,Integer,String> table = HashBasedTable.create();
        for(int row=0;row<10;row++){
            for(int column=0;column<5;column++){
                table.put(row,column,"value of cell("+row+","+column+"0");
            }
        }

        for (int row=0;row<table.rowMap().size();row++) {
            Map<Integer,String> rowData = table.row(row);
            for (int column =0;column < rowData.size(); column ++) {
                System.out.println("cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }

    }
}
