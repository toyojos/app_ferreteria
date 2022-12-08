package com.emergentes.utiles;

public class Serie {

    public static String getSerie(String lastId) {
        String serie = "0000000001";
        try {
            int id = Integer.valueOf(lastId)+1;
            switch (String.valueOf(id).length()) {
                case 1:
                    serie="000000000"+id;
                    break;
                case 2:
                    serie="00000000"+id;
                    break;
                case 3:
                    serie="0000000"+id;
                    break;
                case 4:
                    serie="000000"+id;
                    break;
                case 5:
                    serie="00000"+id;
                    break;
                case 6:
                    serie="0000"+id;
                    break;
                case 7:
                    serie="000"+id;
                    break;
                case 8:
                    serie="00"+id;
                    break;
                case 9:
                    serie="0"+id;
                    break;
                case 10:
                    serie=""+id;
                    break;
                default:
                    serie = "0000000001";
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error:"+e);
        }
        return serie;
    }
}
