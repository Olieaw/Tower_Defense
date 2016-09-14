package ru.spbstu.icc.kspt.kuryakin.app;


public class FirstClass {
    public static void main(String[] args) {

        FirstClass first = new FirstClass();

        System.out.println(first.TranslationValues(2));
    }

    int TranslationValues(int meter){
        return meter * 100;
    }
}
