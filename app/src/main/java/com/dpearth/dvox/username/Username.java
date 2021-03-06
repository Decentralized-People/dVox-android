package com.dpearth.dvox.username;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dpearth.dvox.R;
import com.dpearth.dvox.livedata.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Username {

    private String animal;
    private String adjective;
    private String number;  //Made it into a string

    private String oldAnimal = "";
    private String oldAdjective = "";
    private String oldNumber = "0";

    private static final String[] adjectivesList = new String[]{
            "Sturdy", "Loud", "Delicious", "Decorous", "Pricey",
            "Knowing", "Scientific", "Lazy", "Fair", "Loutish",
            "Wonderful", "Strict", "Gaudy", "Innocent", "Horrible",
            "Puzzled", "Happy", "Grandiose", "Observant", "Pumped",
            "Pale", "Royal", "Flawless", "Actual", "Realistic", "Cynical",
            "Clean", "Strict", "Super", "Powerful", "Mixed", "Slim",
            "Ubiquitous", "Faithful", "Amusing", "Emotional", "Staking",
            "Former", "Scarce", "Tense", "Black-and-White", "Tangy", "Wrong",
            "Sloppy", "Regular", "Deafening", "Savory", "Classy", "First",
            "Second", "Third", "Valuable", "Outgoing", "Free", "Terrific",
            "Sleepy", "Adorable", "Cozy"
    };

    private static final String[] animalsList = new String[]{
            "Boar", "Koala", "Snake", "Frog", "Parrot", "Lion", "Pig",
            "Rhino", "Sloth", "Horse", "Sheep", "Chameleon", "Giraffe",
            "Yak", "Cat", "Dog", "Penguin", "Elephant", "Fox", "Otter",
            "Gorilla", "Rabbit", "Raccoon", "Wolf", "Panda", "Goat", "Chicken",
            "Duck", "Cow", "Ray", "Catfish", "Ladybug", "Dragonfly", "Owl", "Beaver",
            "Alpaca", "Mouse", "Walrus", "Kangaroo", "Butterfly", "Jellyfish",
            "Deer", "Beetle", "Tiger", "Pigeon", "Bearded_Dragon", "Bat",
            "Hippo", "Crocodile", "Monkey",
    };

    public static final String USERNAME_PREFS = "usernamePrefs";
    private static Context mContext;

    /*  Need to pass contexts as Username is non-activity but a helper class    */
    public Username(Context mContext) {
        this.animal = "Retrieving";
        this.adjective = "Username";
        this.number = "0";
        this.mContext = mContext;

    }

    /*Don't think its necessary*/
    public int getAvatar() {

        Map<String, Integer> map = new HashMap<>();
        map.put("boar", R.drawable.boar);
        map.put("koala", R.drawable.koala);
        map.put("snake", R.drawable.snake);
        map.put("frog", R.drawable.frog);
        map.put("parrot", R.drawable.parrot);
        map.put("lion", R.drawable.lion);
        map.put("pig", R.drawable.pig);
        map.put("rhino", R.drawable.rhino);
        map.put("sloth", R.drawable.sloth);
        map.put("horse", R.drawable.horse);
        map.put("sheep", R.drawable.sheep);
        map.put("chameleon", R.drawable.chameleon);
        map.put("giraffe", R.drawable.giraffe);
        map.put("yak", R.drawable.yak);
        map.put("cat", R.drawable.cat);
        map.put("dog", R.drawable.dog);
        map.put("penguin", R.drawable.penguin);
        map.put("elephant", R.drawable.elephant);
        map.put("fox", R.drawable.fox);
        map.put("otter", R.drawable.otter);
        map.put("gorilla", R.drawable.gorilla);
        map.put("rabbit", R.drawable.rabbit);
        map.put("raccoon", R.drawable.raccoon);
        map.put("wolf", R.drawable.wolf);
        map.put("panda", R.drawable.panda);
        map.put("goat", R.drawable.goat);
        map.put("chicken", R.drawable.chicken);
        map.put("duck", R.drawable.duck);
        map.put("cow", R.drawable.cow);
        map.put("ray", R.drawable.ray);
        map.put("catfish", R.drawable.catfish);
        map.put("ladybug", R.drawable.ladybug);
        map.put("dragonfly", R.drawable.dragonfly);
        map.put("owl", R.drawable.owl);
        map.put("beaver", R.drawable.beaver);
        map.put("alpaca", R.drawable.alpaca);
        map.put("mouse", R.drawable.mouse);
        map.put("walrus", R.drawable.walrus);
        map.put("kangaroo", R.drawable.kangaroo);
        map.put("butterfly", R.drawable.butterfly);
        map.put("jellyfish", R.drawable.jellyfish);
        map.put("deer", R.drawable.deer);
        map.put("beetle", R.drawable.beetle);
        map.put("tiger", R.drawable.tiger);
        map.put("pigeon", R.drawable.pigeon);
        map.put("bearded_dragon", R.drawable.bearded_dragon);
        map.put("bat", R.drawable.bat);
        map.put("hippo", R.drawable.hippo);
        map.put("crocodile", R.drawable.crocodile);
        map.put("monkey", R.drawable.monkey);

        try {
            return map.get(this.animal.toLowerCase());
        } catch (Exception error) {
           Log.d("Username", "The animal doesn't exist");
        }
        return map.get("hacker");
    }

    public String getUsernameString() {
        if (this.animal.equals("Hacker")){
            return this.animal;
        } else {
            //@ is additional
            return this.adjective + "_" + this.animal + "_" + this.number;
        }
    }

    public void stringToUsername(String usernameString) {
        String[] splitName = usernameString.split("_");

        if (splitName.length == 3){
            this.adjective = splitName[0];
            this.animal = splitName[1];
            this.number = splitName[2];
        }
        else if (splitName.length == 4){
            this.adjective = splitName[0];
            animal = splitName[1] + "_" + splitName[2];
            number = splitName[3];
        }
        else {
            this.adjective = "Hacker";
            this.adjective = "";
            this.number = "";
        }
    }

    public void retrieveUsername(boolean firstRun) {

        Log.d("Username", "(retrieving) Current username: " +
                mContext.getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE).getString(USERNAME_PREFS, ""));

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(USERNAME_PREFS, "").equals(null) ||
                sharedPreferences.getString(USERNAME_PREFS, "").equals("") ||
                sharedPreferences.getString(USERNAME_PREFS, "").equals("Username_Retrieving_0") ||
                sharedPreferences.getString(USERNAME_PREFS, "").equals("Again_Try_404")){

            //Add threads here
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //PERFORM BACKGROUND ACTION
                    Activity activity = (Activity) mContext;

                    generateUsername(firstRun);

                    String usernameString = getUsernameString();

                    SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, usernameString);
                    editor.apply();

                    if (!sharedPreferences.getString(USERNAME_PREFS, "").equals(null) ||
                            !sharedPreferences.getString(USERNAME_PREFS, "").equals("") ||
                            !sharedPreferences.getString(USERNAME_PREFS, "").equals("Username_Retrieving_0") ||
                            !sharedPreferences.getString(USERNAME_PREFS, "").equals("Again_Try_404")) {
                        Log.d("Username", "The username was generated successfully: " + getUsernameString());
                    } else {
                        Log.d("Username", "Error getting username");
                    }
                }
            });

            thread.run();
            //Stop thread

        } else {

            this.stringToUsername(
                    sharedPreferences.getString(USERNAME_PREFS, "")
            );

            Log.d("Username", "retrieveUsername: success");
        }
    }

    public void getNewUsername() {

        String[] numbers = new String[100];

        for (int i = 0; i < 100; i++){
            numbers[i] = String.valueOf(i);
        }

        Random random = new Random();
        int randomAdjective = random.nextInt(this.adjectivesList.length);
        int randAnimal = random.nextInt(this.animalsList.length);
        int randNumber = random.nextInt(99) + 1;

        this.adjective = adjectivesList[randomAdjective];
        this.animal = animalsList[randAnimal];
        this.number = numbers[randNumber];
    }

    public Username generateUsername(boolean firstRun) {

        saveOldUsernameToAccessLater();

        Log.d("Username", "(regenerating) Current username: " +
                mContext.getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE).getString(USERNAME_PREFS, ""));

        getNewUsername();

        //PERFORM BACKGROUND ACTION
        Activity activity = (Activity) mContext;

        DocumentReference firebaseAnimal = FirebaseFirestore.getInstance().collection("Nicknames")
                .document(animal);

        firebaseAnimal.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){

                    DocumentSnapshot document = task.getResult();

                    //Checking if animal exists
                    if (document.exists()){
                        String field = adjective + "_" + number;

                        //Checking if Adjective & number combination exists
                        if (document.getBoolean(field) == null){

                            Log.d("Username", "We can create it");

                            Log.d("Username", "New username: " + animal);


                            Map<String, Boolean> animalToAdd = new HashMap<>();
                            animalToAdd.put(field, firstRun);


                            //I hope this one is right !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            firebaseAnimal.set(animalToAdd, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("firebase RNG", "animal " + field + " successfully written!");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("firebase RNG", "Error writing " + field, e);
                                }
                            });
                        }
                        else {
                            Log.d("Username", field + " already exists");
                            generateUsername(firstRun);
                        }
                    }
                    else {
                        Log.d("Username", "The document does not exists");

                        animal = "Try";
                        adjective = "Again";
                        number = "404";
                    }
                }
            }
        });

        return this;
    }

    public void saveOldUsernameToAccessLater() {
        this.oldAnimal = this.animal;
        this.oldAdjective = this.adjective;
        this.oldNumber = this.number;
        Log.d("Username", "Saving old username..." + oldAnimal);

    }

    public void userNameAbort() {


        //PERFORM BACKGROUND ACTION
        Activity activity = (Activity) mContext;

        DocumentReference firebaseAnimal = FirebaseFirestore.getInstance().collection("Nicknames")
                .document(animal);
        firebaseAnimal.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Username", "DocumentSnapshot data: " + document.getData());
                        String field = adjective + "_" + number;

                        Map<String, Object> animalToDelete = new HashMap<>();
                        animalToDelete.put(adjective + "_" + number, FieldValue.delete());

                        firebaseAnimal.update(animalToDelete).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.d("Username", "Successfully deleted generatedName: " + field);
                            }
                        });

                        Log.d("Username", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("Username", "No such document");
                    }
                } else {
                    Log.d("Username", "get failed with ", task.getException());
                }
            }
        });


        Log.d("Username", "userNameAbort: Creating aborted");

        this.animal = this.oldAnimal;
        this.adjective = this.oldAdjective;
        this.number = this.oldNumber;
    }

    public void userNameConfirm() {

        //PERFORM BACKGROUND ACTION
        Activity activity = (Activity) mContext;

        DocumentReference firebaseAnimal = FirebaseFirestore.getInstance().collection("Nicknames")
                .document(animal);

        firebaseAnimal.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    //Checking if animal exists
                    if (document.exists()) {
                        String field = adjective + "_" + number;

                        Map<String, Boolean> animalToAdd = new HashMap<>();
                        animalToAdd.put(field, true);

                        //I hope this one is right !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        firebaseAnimal.update(field, true) .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("firebase RNG", "animal " + field + " successfully written!");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("firebase RNG", "Error writing " + field, e);
                            }
                        });

                        // End thread here
                    } else {
                        Log.d("Username", "The document does not exists");
                    }
                }
            }
        });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //UPDATE UI ACTION
            }
        });


        Log.d("Username", "userName create Confirm");

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, getUsernameString());
        editor.apply();

    }
}
