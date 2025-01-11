interface StringFunc {
    String func(String str);
}

public class LambdaArgumentDemo {
    public static void main(String args[]) {

        // String que será modificado
        String inStr = "Lambda Expressions Expand Java";
        // String modificado
        String outStr;

        System.out.println("Here is input string: " + inStr);
        
        StringFunc reverse = (str) -> {
            String result = "";
            for(int i = str.length()-1; i >= 0; i--)
                result += str.charAt(i);
            return result;
        };

        outStr = changeStr(reverse, inStr); // aqui a expressão lambda não está embutida diretamente
        System.out.println("The String reverse is: " + outStr);

        // Expressão lambda que substitui espaços por hifens;
        // Ela está embutida diretamente na chamada a changeStr()
        outStr = changeStr(((str) -> str.replace(' ', '-')), inStr);

        System.out.println("The string with spaces replaced: " + outStr);

        // Esta lambda de bloco inverte a caixa dos caracteres do string
        // Ela também está embutida diretamente na chamada a changeStr();
        outStr = changeStr((str) -> { // expressão lambda imbutida diretamente
                String result = "";
                char ch;

                for(int i = 0; i < str.length(); i++) {
                    ch = str.charAt(i);
                    if(Character.isUpperCase(ch))
                        result += Character.toLowerCase(ch); // character em upper, vira Lower
                    else
                        result += Character.toUpperCase(ch); // character em low, vira upper
                }
                return result;
        }, inStr);

        System.out.println(outStr);
    }    


    //O método aceita como primeiro parâmetro uma interface funcional (pode ser uma instância ou expressão lambda) e como segundo parâmetro, uma string a ser modificada.
    static String changeStr(StringFunc sf, String s) {
        return sf.func(s);
    }    
}