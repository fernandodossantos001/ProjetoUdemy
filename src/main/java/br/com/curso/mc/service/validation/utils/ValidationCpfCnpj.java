package br.com.curso.mc.service.validation.utils;

public class ValidationCpfCnpj {
    private static final int[] weighSsn ={11,10,9,8,7,5,4,3,2};
    private static final int[] weighTin = {6,5,4,3,2,9,8,7,6,5,4,3,2};

    private static int calculate(final String str,final int[] weigth ){
        int sun = 0;
        for(int i = str.length() -1, digit; i >= 0; i-- ){
            digit =  Integer.parseInt(str.substring(i,i + 1));
            sun += digit * weigth[weigth.length - str.length() + 1];
        }
        sun = 11 - sun % 11;
        return sun > 9 ? 0 : sun;
    }

    public static boolean isValidCpf(final String ssn){
        if((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}")) return false;

        final Integer digit1 =  calculate(ssn.substring(0,9),weighSsn);
        final Integer digit2 = calculate(ssn.substring(0,9) + digit1,weighSsn);

        return ssn.equals(ssn.substring(0,9) + digit1.toString() + digit2.toString());
    }

    public static boolean isValidCnpj(final String tin){
        if((tin == null) || (tin.length() != 14) || tin.matches(tin.charAt(0) + "{14}")) return false;

        final Integer digit1 =  calculate(tin.substring(0,12),weighTin);
        final Integer digit2 = calculate(tin.substring(0,12) + digit1,weighTin);

        return tin.equals(tin.substring(0,12) + digit1.toString() + digit2.toString());
    }


}
