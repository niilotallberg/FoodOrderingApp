package com.example.foodorderingapp.Data;

import android.content.Context;

import com.example.foodorderingapp.Domain.FoodDomain;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Serializer {

    public static <T extends Serializable> void serializeObject(T obj, Context context, String fileName) {
        try
                (
                        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
                ) {
            objectOutputStream.writeObject(obj);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static HashMap<FoodDomain, Integer> deserializeCart(Context context, String fileName) {
        try
                (
                        FileInputStream fileInputStream = context.openFileInput(fileName);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
                ) {
            Object deserializedObject = objectInputStream.readObject();
            if (deserializedObject instanceof HashMap<?, ?>) {
                return (HashMap<FoodDomain, Integer>) deserializedObject;
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
