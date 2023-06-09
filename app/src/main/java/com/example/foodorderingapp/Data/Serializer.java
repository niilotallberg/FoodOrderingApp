// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Data;

import android.content.Context;
import com.example.foodorderingapp.General.FoodDomain;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Serializer {

    public static <T extends Serializable> void serializeObject(T obj, Context context, String fileName) { // Saves different types of information to serialized files
        try (
                FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(obj);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static <T extends Serializable> T deserializeObject(Context context, String fileName) { // Loads different types of information from serialized files
        try (
                FileInputStream fileInputStream = context.openFileInput(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            Object deserializedObject = objectInputStream.readObject();
            return (T) deserializedObject;
        } catch (FileNotFoundException ignored) {
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static HashMap<FoodDomain, Integer> deserializeCart(Context context, String fileName) {  // Loads different users carts from serialized files
        HashMap<FoodDomain, Integer> deserializedCart = deserializeObject(context, fileName);
        if (deserializedCart == null) {
            return new HashMap<>();
        }
        return deserializedCart;
    }
}