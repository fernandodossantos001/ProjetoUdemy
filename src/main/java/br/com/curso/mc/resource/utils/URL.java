package br.com.curso.mc.resource.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
    public static List<Integer> decodeIntList(String ids){
//        String[] vet = ids.split(",");
//        List<Integer> listId = new ArrayList<Integer>();
//        for(int i = 0; i < vet.length;i++){
//            listId.add(Integer.parseInt(vet[i]));
//        }
//        return listId;

        return Arrays.asList(ids.split(",")).stream().map((x -> Integer.parseInt(x))).collect(Collectors.toList());
    }

    public static String decodeParam(String param){
        try{
            return URLDecoder.decode(param,"UTF-8");
        }catch (Exception e){
            return "";
        }
    }
}
