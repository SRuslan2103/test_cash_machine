package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String [ ] operation = {"БАЛАНС", "ВНЕСТИ", "СНЯТЬ", "ОБМЕНЯТЬ","END" };            //массив на ОПЕРАЦИИ
        String [ ] val = {   "USD" ,  "CNY",  "KZT",  "TRY",   "RUB" };                     //массив на ВАЛЮТА
        double [ ] balance = { 0.00,      0.00,      0.00,     0.00,      0.00 };
        double [ ] curs = {  91.45,   11.91,   0.19,   2.69,   1.00};                       // массив НАЧАЛЬНЫЙ БАЛАНС

        //Фразы
        String
                Hi =  "-----------------------------------------" +
                "\n- - ВВЕДИТЕ ОПЕРАЦИЮ - - или - - END - - " +
                "\n- - БАЛАНС / ВНЕСТИ / СНЯТЬ / ОБМЕНЯТЬ - ",
                ErO = " - - ! ОШИБКА ! Невенрная операция ! - - ",

                ErF = "- Формат:  ОПЕРАЦИЯ 00.00 ВАЛЮТА ВАЛЮТА -",
                ErB = " - - - - -  Формат:    БАЛАНС   - - - - -",
                ErSum = " - - - ! ОШИБКА ! Формата  СУММЫ ! - - - ",
                ErVal = "- - - ! ОШИБКА ! Формата  ВАЛЮТЫ ! - - - ",
                ErV = " - - Формат:   ВНЕСТИ  00.00  ВАЛЮТА - - ",
                ErS = " - - Формат:   СНЯТЬ  00.00  ВАЛЮТА - - -",
                ErC = "- Формат:  ОБМЕНЯТЬ 00.00 ВАЛЮТА ВАЛЮТА -",
                ErA = " - - ! ОШИБКА ! Недостаточно средств - - ",
                Er0 = "- ! ВНИМАНИЕ ! Веденная  СУММА = 0,00 ! -",
                ErG = "----",
                ErZ = "- - Вы завершили работу.  До встречи - - ";
        //Er


        Scanner input = new Scanner(System.in);                                             // запуск сканера
        String stroka = "start";                                                                // счетчик - переменная для ВЫХОДА

        System.out.print("-----------------------------------------" + "\n" + "   - -- -  ДОБРО ПОЖАЛОВАТЬ !  - -- -   \n");


        do {                                                                                 // делать пока условие while верно
            System.out.println(Hi);
            String text = input.nextLine().toUpperCase().trim();       // перевод все в единый регистр и удаление пробелов
            stroka = text;
            if (stroka.isEmpty()) {
                System.out.println(ErO + "\n" + ErF);
                continue;}
            String[] word = stroka.split(" ");                                         // массив на строку ввода  - / - / - / -

            ///////////////// СООТВЕТВЕТСВИЕ ВВОДА /////////////////////

            int op = 0;
            for (int u = 0; u < operation.length; u++) {
                if (word[0].equals(operation[u])) { op = 1;
                }
            }
            if (op == 0 && word [0] != operation [4]) {
                System.out.println(ErO);
                continue;
            }

            //////////////////////////// КОНЕЦ ////////////////////////////
            if (word[0].equals(operation[4])) {                            // КОНЕЦ / - / - / -
                System.out.println(ErZ);
                stroka = word[0];                                           // присвоение счетчику - переменной значения ВЫХОД
            }

            /////////////////////////// БАЛАНС //////////////////////////////
            if (word[0].equals(operation[0])) {                                             // "БАЛАНС" / - / - / -

                if (word.length > 1) {
                    System.out.println(ErO + "\n" + ErB);
                    continue;
                }

                System.out.print("***  Баланс: ");
                for (int q = 0; q < val.length; q++) {                                  //массив - массив Валюта - Баланс
                    System.out.print(val[q] + ": " + String.format("%.2f", balance[q]));
                    if (q < (val.length - 1))  {System.out.print(" , ");}
                    if ( q == (val.length - 1)) {System.out.print("   ***\n");}
                }
            }

            /////////////////////////// ВНЕСТИ //////////////////////////////
            if (word[0].equals(operation[1])) {                                          // если "ВНЕСТИ"/ сумма/ валюта 1/ валюта 2

                if (word.length <= 1) {
                    System.out.println(ErSum + "\n" + ErV);
                    continue;
                }

                if (word.length <= 2) {                                                    // "ВНЕСТИ"
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue;
                    }

                    double num = Double.parseDouble(word[1]);
                    if (num==0) {System.out.println(Er0);}

                    System.out.println(ErVal + "\n" + ErV);
                    continue;        //отсутствие валюты
                }

                if (word.length <= 3) {
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue;
                    }

                    double num = Double.parseDouble(word[1]);                         // перевод - / суммы в 00,0 / валюта 1 / валюта 2
                    if (num==0) {System.out.println(Er0);}
                    int a = 0;                                                            // ошибка валюты
                    for (int i = 0; i < val.length; i++) {
                        if (word[2].contains(val[i])) { balance[i] = balance[i] + num; a = 0; }
                        else if (!(word [ 2 ].contains(val [i]))) {  a++; }
                    }
                    if (a == 5) {
                        System.out.println( ErVal + "\n"+ ErV );
                    }
                }

                if (word.length >= 4) {
                    System.out.println(ErO + "\n" + ErV);
                    continue;
                }
            }

            /////////////////////// СНЯТИЕ /////////////////////////////////////////////
            if (word[0].equals(operation[2])) {                                 // если "СНЯТЬ"/ сумма/ валюта 1/ валюта 2

                if (word.length <= 1) {
                    System.out.println(ErSum + "\n" +  ErS);
                    continue;
                }

                if (word.length <= 2) {

                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue; }

                    double num = Double.parseDouble(word[1]);                      // перевод - / суммы в 00,0 / валюта 1 / валюта 2
                    if (num==0) {System.out.println(Er0);}

                    System.out.println(ErVal + "\n" + ErS); continue;
                }

                if (word.length <= 3) {
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue;
                    }

                    double num = Double.parseDouble(word[1]);                      // перевод - / суммы в 00,0 / валюта 1 / валюта 2
                    if (num==0) {System.out.println(Er0);}
                    int a = 0;                                                                      // ошибка валюты
                    for (int i = 0; i < val.length; i++) {
                        if (word[2].contains(val[i])) {a = 0;
                            if (balance [i] < num) {System.out.println(ErA);}
                            if (balance [i] >= num) { balance [ i ] = balance [ i ] - num; }}
                        else if (!(word [ 2 ].contains(val [i]))) {  a++; }
                    }
                    if (a == 5) {System.out.println(ErVal + "\n" + ErS);}
                }

                if (word.length >= 4) {
                    System.out.println(ErO + "\n" + ErS);
                    continue;
                }
            }

            //////////////////////// ОБМЕНЯТЬ /////////////////////////////////////////////
            if (word[0].equals(operation[3])) {                                 // если "СНЯТЬ"/ сумма/ валюта 1/ валюта 2

                if (word.length <= 1) {
                    System.out.println(ErSum + "\n" + ErC);
                    continue;
                }

                if (word.length <= 2) {
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue;
                    }

                    double num = Double.parseDouble(word[1]);                      // перевод - / суммы в 00,0 / валюта 1 / валюта 2
                    if (num==0) {System.out.println(Er0);}

                    System.out.println(ErVal + "\n" + ErC);
                    continue;
                }

                if (word.length <= 3) {
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue;
                    }

                    double num = Double.parseDouble(word[1]);
                    if (num==0) {System.out.println(Er0);}
                    int a = 0;                                                      // ошибка валюты
                    for (int i = 0; i < val.length; i++) {
                        if (word[2].contains(val[i])) {
                            a = 0;
                            if (balance[i] < num) {
                                System.out.println(ErA);
                            }

                        } else if (!(word[2].contains(val[i]))) {
                            a = 1;
                        }

                    }
                    if (a != 0) {System.out.println(ErVal + "\n" + ErC);
                        continue;}
                    System.out.println(ErVal + "\n" + ErC);
                    continue;
                }

                if (word.length <= 4) {
                    word [1] = word [ 1 ].replace(",", ".");
                    word [1] = word [ 1 ].replace(".", ".");

                    try { double d = Double.parseDouble(word [1]); }
                    catch (NumberFormatException nfe) {
                        System.out.println(ErSum + "\n" + ErV);
                        continue; }

                    double num = Double.parseDouble(word[1]);
                    if (num==0) {System.out.println(Er0);}

                    int h = 0;                                                            // ошибка валюты
                    for (int i = 0; i < val.length; i++) {
                        if (word[3].contains(val[i])) {
                            h = 0;
                        } else if (!(word[3].contains(val[i]))) {
                            h++;
                        }
                    }
                    if (h == 5) {
                        System.out.println(ErVal + "\n" + ErC);
                        continue;
                    }


                    double charge1 = 0, charge2;
                    for (int s = 0; s < val.length; s++) {
                        if (word[2].contains(val[s])) {
                            if (balance[s] < num) {
                                System.out.println(ErA);
                            }
                            if (balance[s] >= num) {
                                balance[s] = balance[s] - num;
                                charge1 = curs[s] * num;
                            }
                        }
                    }
                    for (int k = 0; k < val.length; k++) {
                        if (word[3].contains(val[k])) {
                            balance[k] = balance[k] + (charge1 / curs[k]);
                        }
                    }




                }
                if (word.length >= 5 ) {
                    System.out.println(ErO + "\n" + ErC);
                    continue;
                }


            }

        }
        while (!(stroka.equals(operation[4]))) ;



    }
}