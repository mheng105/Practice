package com.vmo.training.demo.models.assignment1;

public class Counter {
    public static String stockCode, stockName, change, volume;

    public Counter() {

    }

    public static String getStockCode() {
        return stockCode;
    }

    public static void setStockCode(String stockCode) {
        Counter.stockCode = stockCode;
    }

    public static String getStockName() {
        return stockName;
    }

    public static void setStockName(String stockName) {
        Counter.stockName = stockName;
    }

    public static String getChange() {
        return change;
    }

    public static void setChange(String change) {
        Counter.change = change;
    }

    public static String getVolume() {
        return volume;
    }

    public static void setVolume(String volume) {
        Counter.volume = volume;
    }
}
