package com.example.streetbellpos.managers.helper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultiPartHelper {

    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    public static MultipartBody.Part prepareFilePart(String partName, String fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = new File(fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/jpeg"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

   /* public static HashMap<String, RequestBody> getMapForGeneration(Generation generation) {
        HashMap<String, RequestBody> hashMap = new HashMap<>();
        hashMap.put("user[generation[id]]", createPartFromString(String.valueOf(generation.getId())));
        hashMap.put("user[generation[name]]", createPartFromString(generation.getName()));
        hashMap.put("user[generation[start_year]]", createPartFromString(String.valueOf(generation.getStartYear())));
        hashMap.put("user[generation[end_year]]", createPartFromString(String.valueOf(generation.getEndYear())));
        return hashMap;
    }*/

   /* public static HashMap<String, RequestBody> getMapForSparkItems(ArrayList<SparksItem> sparksItems) {
        HashMap<String, RequestBody> hashMap = new HashMap<String, RequestBody>();
        for (int i = 0; i < sparksItems.size(); i++) {
            hashMap.put("user[sparks[\" + i + \"][id]]", createPartFromString(String.valueOf(sparksItems.get(i).getId())));
            hashMap.put("user[sparks[\" + i + \"][name]]", createPartFromString(sparksItems.get(i).getName()));
            hashMap.put("user[sparks[\" + i + \"][active]]", createPartFromString(String.valueOf(sparksItems.get(i).isActive())));
            hashMap.put("user[sparks[\" + i + \"][deleted]]", createPartFromString(String.valueOf(sparksItems.get(i).isDeleted())));
            hashMap.put("user[sparks[\" + i + \"][created_at]]", createPartFromString(sparksItems.get(i).getCreatedAt()));
            hashMap.put("user[sparks[\" + i + \"][updated_at]]", createPartFromString(sparksItems.get(i).getUpdatedAt()));
        }
        return hashMap;
    }*/

   /* public static HashMap<String, RequestBody> getMapForSparkIds(ArrayList<Integer> sparkIds) {
        HashMap<String, RequestBody> skill = new HashMap<String, RequestBody>();
        for (int i = 0; i < sparkIds.size(); i++) {
            RequestBody requestBody= createPartFromString(String.valueOf(i));
            skill.put("user[spark_ids[" + i + "]]", requestBody);
        }
        return skill;
    }*/

//    public static HashMap<String, RequestBody> getMapForAddress(Address address) {
//        HashMap<String, RequestBody> hashMap = new HashMap<String, RequestBody>();
//        if (address.getFormattedAddress() != null) {
//            hashMap.put("user[address_attributes[formatted_address]]", createPartFromString(address.getFormattedAddress()));
//        }
//        if (address.getCity() != null) {
//            hashMap.put("user[address_attributes[city]]", createPartFromString(address.getCity()));
//        }
//        if (address.getState() != null) {
//            hashMap.put("user[address_attributes[state]]", createPartFromString(address.getState()));
//        }
//        if (address.getCountry() != null) {
//            hashMap.put("user[address_attributes[country]]", createPartFromString(address.getCountry()));
//        }
//        if (address.getZip() != null) {
//            hashMap.put("user[address_attributes[zip]]", createPartFromString(address.getZip()));
//        }
//        hashMap.put("user[address_attributes[latitude]]", createPartFromString(String.valueOf(address.getLatitude())));
//        hashMap.put("user[address_attributes[longitude]]", createPartFromString(String.valueOf(address.getLongitude())));
//
//        return hashMap;
//    }

}
