import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите выражение типа 1 + 2, " +
                    "где вместо + также могут быть - , *, /, " +
                    "а вместо 1 и 2 целые или римские числа, " +
                    "но от 1 до 10");
            String inputtext = scanner.nextLine();
            System.out.println(calc(inputtext));
            System.out.println("THE text");
            System.out.println("The next text");
            System.out.println("Some new text is added");
            System.out.println(" ");
        }
    }
    public static String calc(String input) throws IOException {
        String[] inputexpr = input.split(" ");

        Operations operation = Operations.ADD;
        for (int i = 0; i < inputexpr.length; i++) {
            String text1 = inputexpr[i].trim();
            inputexpr[i] = text1;
        }
        if (inputexpr.length > 3 || inputexpr.length < 3) {
            Exceptions exception = Exceptions.INCORRECT_INPUT;
            throw new IOException("IO: " + exception + " Этот ввод содержит " +
                    "больше либо меньше трех слов, считая оператор");
        } else {

            // Тогда выявляем, что за оператор стоит посередине
            String leftString = inputexpr[0];
            String centerString = inputexpr[1];
            String rightString = inputexpr[2];
            if (inputexpr[1].compareTo("+") == 0) {
                operation = Operations.ADD;
            } else if (inputexpr[1].compareTo("-") == 0) {
                operation = Operations.SUBSTRACT;
            } else if (inputexpr[1].compareTo("*") == 0) {
                operation = Operations.MULTIPLY;
            } else if (inputexpr[1].compareTo("/") == 0) {
                operation = Operations.DEVIDE;
            } else {
                Exceptions exception = Exceptions.INCORRECT_OPERATION;
                throw new IOException(exception + ": Посередине не стоит ни одного из операторов " +
                        "+, -, *, /");
                // Смотрим что стоит слева и справа
            }
        }
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        dictionary.put(1, "I");
        dictionary.put(2, "II");
        dictionary.put(3, "III");
        dictionary.put(4, "IV");
        dictionary.put(5, "V");
        dictionary.put(6, "VI");
        dictionary.put(7, "VII");
        dictionary.put(8, "VIII");
        dictionary.put(9, "IX");
        dictionary.put(10, "X");
        dictionary.put(20, "XX");
        dictionary.put(30, "XXX");
        dictionary.put(40, "XL");
        dictionary.put(50, "L");
        dictionary.put(60, "LX");
        dictionary.put(70, "LXX");
        dictionary.put(80, "LXXX");
        dictionary.put(90, "XC");

        boolean leftIsInt = false;
        int leftint = 0;
        for (int i = 0; i < 11; i++) {
            String comparetoint = Integer.toString(i);
            if (comparetoint.equals(inputexpr[0])) {
                leftIsInt = true;
                leftint = i;
            }
        }
        boolean leftIsRome = false;
        for (int j = 1; j < 11; j++) {
            String comparetoint = Integer.toString(j);
            String firstvalueOfInput = inputexpr[0].substring(0, 1);
            if (((dictionary.get(j)).substring(0, 1)).equalsIgnoreCase(firstvalueOfInput)) {
                leftIsRome = true;
            }
        }

        boolean rightIsInt = false;
        int rightint = 0;
        for (int i = 0; i < 11; i++) {
            String comparetoint = Integer.toString(i);
            if (comparetoint.equals(inputexpr[2])) {
                rightIsInt = true;
                rightint = i;
            }
        }
        boolean rightIsRome = false;
        for (int j = 1; j < 11; j++) {
            String comparetoint = Integer.toString(j);
            String firstvalueOfInput = inputexpr[2].substring(0, 1);
            if (((dictionary.get(j)).substring(0, 1)).equalsIgnoreCase(firstvalueOfInput)) {
                rightIsRome = true;
            }
        }
        if ((leftIsRome & rightIsInt) || (rightIsRome & leftIsInt)) {
            throw new IOException(Exceptions.ARE_OF_DIFFERENT_TYPES+": Введены одновременно " +
                    "разные типы чисел");
        }

        boolean IsFloat = false;
        if(IsFloat(inputexpr[0])==true || IsFloat(inputexpr[2])==true){
            System.out.println(IsFloat(inputexpr[0]));
            System.out.println("String");
            throw new IOException(Exceptions.FLOAT+": Введено вещественное число");
        }

        int intResult = 0;
        String Result = new String();
        if (leftIsInt & rightIsInt) {
            if (leftint > 10 || rightint > 10) {
                throw new IOException(Exceptions.GRATER_THEN_10 + ": Как минимум одно из чисел больше 10");
            }
            if (leftint < 1 || rightint < 1) {
                throw new IOException(Exceptions.LESS_THEN_1 + ": Как минимум одно из чисел меньше 1");
            }
            System.out.println("leftisint + someoutput");
            switch (operation) {
                case ADD:
                    intResult = leftint + rightint;
                    break;
                case SUBSTRACT:
                    intResult = leftint - rightint;
                    break;
                case MULTIPLY:
                    intResult = leftint * rightint;
                    break;
                case DEVIDE:
                    intResult = leftint / rightint;
                    break;
            }
            Result = Integer.toString(intResult);
        }

        if (leftIsRome & rightIsRome) {
            leftint = RomeToArabic(inputexpr[0]);
            rightint = RomeToArabic(inputexpr[2]);
            if (leftint > 10 || rightint > 10) {
                throw new IOException(Exceptions.GRATER_THEN_10 + ": Как минимум одно из чисел больше 10");
            }
            if (leftint < 1 || rightint < 1) {
                throw new IOException(Exceptions.LESS_THEN_1 + ": Как минимум одно из чисел меньше 1");
            }
            switch (operation) {
                case ADD:
                    intResult = RomeToArabic(inputexpr[0]) + RomeToArabic(inputexpr[2]);
                    break;
                case SUBSTRACT:
                    intResult = RomeToArabic(inputexpr[0]) - RomeToArabic(inputexpr[2]);
                    break;
                case MULTIPLY:
                    intResult = RomeToArabic(inputexpr[0]) * RomeToArabic(inputexpr[2]);
                    break;
                case DEVIDE:
                    intResult = RomeToArabic(inputexpr[0]) / RomeToArabic(inputexpr[2]);
                    break;
            }

//            if (intResult<1){
//                throw new IOException(Exceptions.RESULT_ROME_NUMBER_LESS_THEN_1+": " +
//                        "В римской системе счисления нет чисел меньше 1");
//            }
//            if(intResult<=10 || ((intResult%10)==0 & (intResult != 100))) {
//                Result = dictionary.get(intResult);
//            } else {
//                String strresult = String.valueOf(intResult);
//
//                if(strresult.length()==3) {
//                    Result = "C";
//                } else {
//                    String Str1 = "";
//                    char[] strresultChar = strresult.toCharArray();
//                    char a = strresultChar[0];
//                    String S = "";
//                    for(int i = 1; i<10; i++) {
//                        S = String.valueOf(i);
//                        if(S.equals(a)){
//                        switch(S) {
//                            case ("1"):
//                                Str1 = "X";
//                                break;
//                            case ("2"):
//                                Str1 = "XX";
//                                break;
//                            case ("3"):
//                                Str1 = "XXX";
//                                break;
//                            case ("4"):
//                                Str1 = "XL";
//                                break;
//                            case ("5"):
//                                Str1 = "L";
//                                break;
//                            case ("6"):
//                                Str1 = "LX";
//                                break;
//                            case ("7"):
//                                Str1 = "LXX";
//                                break;
//                            case ("8"):
//                                Str1 = "LXXX";
//                                break;
//                            case ("9"):
//                                Str1 = "LC";
//                                break;
//                        }
//                    }
//                String Str2 = "";
//                char b = strresultChar[1];
//                    for (int i = 1; i<10; i++){
//                        String s = String.valueOf(i);
//                        if(s.equals(b)){
//                            Str2 = dictionary.get(i);
//                        }
//                    }
//                Result = Str1+Str2;
//                }
//
//
//            }
        }

        return Result;
    }

    static boolean IsFloat(String theinput){
        boolean isfloat = false;
        System.out.println("the input:"+theinput);
        char[] chararray = theinput.toCharArray();
        int comp = 0;
        for (char element : chararray) {
            System.out.println(element);
            comp = Character.compare(element,'.');
            if (comp==0) {
                isfloat = true;
                System.out.println("IsFloat");
                break;
            } else{
                isfloat = true;
            }
        }
        return isfloat;
    }

    static int RomeToArabian(String romeValue) {
        int arabicValue = 0;
        switch (romeValue.length()) {
            case 4:
                arabicValue = 8;
                break;
            case 1:
                if (romeValue.compareToIgnoreCase("I") == 0) {
                    arabicValue = 1;
                } else if (romeValue.compareToIgnoreCase("V") == 0) {
                    arabicValue = 5;
                } else if (romeValue.compareToIgnoreCase("X") == 0) {
                    arabicValue = 10;
                };
                break;
            case 3:
                if (romeValue.compareToIgnoreCase("VII") == 0) {
                    arabicValue = 7;
                } else {
                    arabicValue = 3;
                };
                break;
            case 2:
                if (romeValue.compareToIgnoreCase("II") == 0) {
                    arabicValue = 2;
                } else if (romeValue.compareToIgnoreCase("VI") == 0) {
                    arabicValue = 6;
                } else {
                    char[] Arr = romeValue.toCharArray();
                    String Sarr1 = String.valueOf(Arr[0]);
                    String Sarr2 = String.valueOf(Arr[1]);

                    if (Sarr1.compareToIgnoreCase("I") == 0) {
                        if (Sarr2.compareToIgnoreCase("V") == 0) {
                            arabicValue = 4;
                        } else if (Sarr2.compareToIgnoreCase("X") == 0) {
                            arabicValue = 9;
                        }
                    } // Закончили перебор римских
                }
        }// Закончили Switch
        return arabicValue;
    }

}