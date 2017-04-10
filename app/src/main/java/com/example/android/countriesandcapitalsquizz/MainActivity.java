package com.example.android.countriesandcapitalsquizz;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String COUNTRIES_LIST = "countriesNamesArrayList";
    static final String CAPITALS_LIST = "capitalsArrayList";
    static final String REGIONS_LIST = "regionsArrayList";
    static final String FLAGS_LIST = "flagsArrayList";
    static final String SELECTED_QUESTIONS = "selectedQuestions";
    static final String CORRECT_ANSWER_Q1 = "correctAnswerQ1";
    static final String CORRECT_ANSWER_Q2 = "correctAnswerQ2";
    static final String CORRECT_ANSWER_Q3 = "correctAnswerQ3";
    static final String CORRECT_ANSWERS_Q4 = "correctAnswersQ4";
    // static final String CORRECT_ANSWER_Q5="correctAnswerQ5";
    static final String TEXT_VIEWS_TEXT_Q2 = "textViewsTextsQ2";
    static final String RES_DRAWABLE_ID = "resID";
    static final String COUNTRY = "countryName";
    static final String CAPITAL = "capital";
    static final String REGION = "region";
    static final String FLAG = "flag";
    static final String CHECKED_Q1 = "checked1";
    static final String CHECKED_Q2 = "checked2";
    static final String CHECKED_Q3 = "checked3";
    static final String CHECKED_Q4 = "checked4";
    static final String RESULT_Q1 = "resQ1";
    static final String RESULT_Q2 = "resQ2";
    static final String RESULT_Q3 = "resQ3";
    static final String RESULT_Q4 = "resQ4";
    static final String FINAL_SCORE = "finalScore";

    //Stores Web service response info
    ArrayList<CountriesInfo> countriesInformation = new ArrayList<>();
    //I will use this in case of Localizing the app
    //Translations translationsCountryName;

    //Separate ArrayLists for each info got from web service
    ArrayList<String> countriesNamesArrayList = new ArrayList<>();
    ArrayList<String> capitalsArrayList = new ArrayList<>();
    ArrayList<String> regionsArrayList = new ArrayList<>();
    ArrayList<String> flagsArrayList = new ArrayList<>();

    //ArrayList with all capitals of one random-selected region created from countriesInformation
    ArrayList<String> capitalsOfOneRegion = new ArrayList<>();

    //ArrayLists for possible answers
    ArrayList<String> textViewsTextsQ2 = new ArrayList<>();
    ArrayList<String> checkboxesTextQ4 = new ArrayList<>();
    ArrayList<String> radioButtonsTexts = new ArrayList<>();

    //Info for questions 1, 3, 4 and 5
    ArrayList<String> selectedQuestions = new ArrayList<>();

    //Layout elements
    TextView question1;
    TextView question3;
    TextView question4;
    TextView question5;
    TextView q2_answer0;
    TextView q2_answer1;
    TextView q2_answer2;
    TextView q2_answer3;
    EditText q3_text_box;
    RadioButton rdb0;
    RadioButton rdb1;
    RadioButton rdb2;
    CheckBox ckb0;
    CheckBox ckb1;
    CheckBox ckb2;
    ImageView imageQ2;
    Button checkBtn;
    Button newQuizBtn;
    Button viewAnswersBtn;

    //Resource ID of drawable used in question 2
    int resID;

    // correct Answers
    String correctAnswerQ1;
    String correctAnswerQ2;
    String correctAnswerQ3;
    ArrayList<String> correctAnswersQ4 = new ArrayList<>();

    //Quiz Results
    String answerQ1;
    String answerQ2 = "";
    String answerQ3;
    ArrayList<String> answersQ4 = new ArrayList<>();
    int finalScore;

    //Questions answered or not, 0 no, 1 yes.
    int checked1 = 0;
    int checked2 = 0;
    int checked3 = 0;
    int checked4 = 0;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //ArrayLists with info obtained from web service
        savedInstanceState.putStringArrayList(COUNTRIES_LIST, countriesNamesArrayList);
        savedInstanceState.putStringArrayList(CAPITALS_LIST, capitalsArrayList);
        savedInstanceState.putStringArrayList(REGIONS_LIST, regionsArrayList);
        savedInstanceState.putStringArrayList(FLAGS_LIST, flagsArrayList);
        //Questions and answers info
        savedInstanceState.putStringArrayList(SELECTED_QUESTIONS, selectedQuestions);
        savedInstanceState.putString(CORRECT_ANSWER_Q1, correctAnswerQ1);
        savedInstanceState.putString(CORRECT_ANSWER_Q2, correctAnswerQ2);
        savedInstanceState.putStringArrayList(TEXT_VIEWS_TEXT_Q2, textViewsTextsQ2);
        savedInstanceState.putString(CORRECT_ANSWER_Q3, correctAnswerQ3);
        savedInstanceState.putStringArrayList(CORRECT_ANSWERS_Q4, correctAnswersQ4);
        savedInstanceState.putInt(RES_DRAWABLE_ID, resID);
        //Questions answered or not
        savedInstanceState.putInt(CHECKED_Q1, checked1);
        savedInstanceState.putInt(CHECKED_Q2, checked2);
        savedInstanceState.putInt(CHECKED_Q3, checked3);
        savedInstanceState.putInt(CHECKED_Q4, checked4);
        //Results info
        savedInstanceState.putString(RESULT_Q1, answerQ1);
        savedInstanceState.putString(RESULT_Q2, answerQ2);
        savedInstanceState.putString(RESULT_Q3, answerQ3);
        savedInstanceState.putStringArrayList(RESULT_Q4, answersQ4);
        savedInstanceState.putInt(FINAL_SCORE, finalScore);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //ArrayLists with info obtained from web service
        countriesNamesArrayList = savedInstanceState.getStringArrayList(COUNTRIES_LIST);
        capitalsArrayList = savedInstanceState.getStringArrayList(CAPITALS_LIST);
        regionsArrayList = savedInstanceState.getStringArrayList(REGIONS_LIST);
        flagsArrayList = savedInstanceState.getStringArrayList(FLAGS_LIST);
        selectedQuestions = savedInstanceState.getStringArrayList(SELECTED_QUESTIONS);
        //Questions and answers info
        selectedQuestions = savedInstanceState.getStringArrayList(SELECTED_QUESTIONS);
        correctAnswerQ1 = savedInstanceState.getString(CORRECT_ANSWER_Q1);
        correctAnswerQ2 = savedInstanceState.getString(CORRECT_ANSWER_Q2);
        textViewsTextsQ2 = savedInstanceState.getStringArrayList(TEXT_VIEWS_TEXT_Q2);
        correctAnswerQ3 = savedInstanceState.getString(CORRECT_ANSWER_Q3);
        correctAnswersQ4 = savedInstanceState.getStringArrayList(CORRECT_ANSWERS_Q4);
        resID = savedInstanceState.getInt(RES_DRAWABLE_ID);
        //Questions answered or not
        checked1 = savedInstanceState.getInt(CHECKED_Q1);
        checked2 = savedInstanceState.getInt(CHECKED_Q2);
        checked3 = savedInstanceState.getInt(CHECKED_Q3);
        checked4 = savedInstanceState.getInt(CHECKED_Q4);
        //Results info
        answerQ1 = savedInstanceState.getString(RESULT_Q1);
        answerQ2 = savedInstanceState.getString(RESULT_Q2);
        answerQ3 = savedInstanceState.getString(RESULT_Q3);
        answersQ4 = savedInstanceState.getStringArrayList(RESULT_Q4);
        finalScore = savedInstanceState.getInt(FINAL_SCORE);
        //Display info into views
        displayQuestions();
        displayQ2Answers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question1 = (TextView) findViewById(R.id.question1);
        question3 = (TextView) findViewById(R.id.question3);
        question4 = (TextView) findViewById(R.id.question4);
        //question5=(TextView) findViewById(R.id.question5);
        q2_answer0 = (TextView) findViewById(R.id.q2_answer0);
        q2_answer1 = (TextView) findViewById(R.id.q2_answer1);
        q2_answer2 = (TextView) findViewById(R.id.q2_answer2);
        q2_answer3 = (TextView) findViewById(R.id.q2_answer3);
        q3_text_box = (EditText) findViewById(R.id.q3_text_box);
        rdb0 = (RadioButton) findViewById(R.id.q1_rdb0);
        rdb1 = (RadioButton) findViewById(R.id.q1_rdb1);
        rdb2 = (RadioButton) findViewById(R.id.q1_rdb2);
        ckb0 = (CheckBox) findViewById(R.id.ckb0);
        ckb1 = (CheckBox) findViewById(R.id.ckb1);
        ckb2 = (CheckBox) findViewById(R.id.ckb2);
        imageQ2 = (ImageView) findViewById(R.id.q2_image);
        checkBtn = (Button) findViewById(R.id.check_btn);
        newQuizBtn = (Button) findViewById(R.id.new_quiz_btn);
        viewAnswersBtn = (Button) findViewById(R.id.view_answers_btn);
        if (savedInstanceState == null) {
            loadCountryInfo();
        }

        /*
        * onclicklisterners for radiobuttons in question 1
        */

        // Set a click listener on that View
        rdb0.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                checked1 = 1;
                if (rdb0.isChecked()) {
                    answerQ1 = rdb0.getText().toString();
                }
            }
        });

        // Set a click listener on that View
        rdb1.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                checked1 = 1;
                if (rdb1.isChecked()) {
                    answerQ1 = rdb1.getText().toString();
                }
            }
        });

        // Set a click listener on that View
        rdb2.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                checked1 = 1;
                if (rdb2.isChecked()) {
                    answerQ1 = rdb2.getText().toString();
                }
            }
        });

        /*
        * onclicklisterners for textviews in question 2
        */

        // Set a click listener on that View
        q2_answer0.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                answerQ2 = q2_answer0.getText().toString();
                q2_answer0.setBackgroundResource(R.color.colorAccent2);
                q2_answer1.setBackgroundResource(R.color.colorPrimary);
                q2_answer2.setBackgroundResource(R.color.colorPrimary);
                q2_answer3.setBackgroundResource(R.color.colorPrimary);
                checked2 = 1;
            }
        });

        // Set a click listener on that View
        q2_answer1.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                answerQ2 = q2_answer1.getText().toString();
                q2_answer1.setBackgroundResource(R.color.colorAccent2);
                q2_answer0.setBackgroundResource(R.color.colorPrimary);
                q2_answer2.setBackgroundResource(R.color.colorPrimary);
                q2_answer3.setBackgroundResource(R.color.colorPrimary);
                checked2 = 1;
            }
        });

        // Set a click listener on that View
        q2_answer2.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                answerQ2 = q2_answer2.getText().toString();
                q2_answer2.setBackgroundResource(R.color.colorAccent2);
                q2_answer0.setBackgroundResource(R.color.colorPrimary);
                q2_answer1.setBackgroundResource(R.color.colorPrimary);
                q2_answer3.setBackgroundResource(R.color.colorPrimary);
                checked2 = 1;
            }
        });

        // Set a click listener on that View
        q2_answer3.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                answerQ2 = q2_answer3.getText().toString();
                q2_answer3.setBackgroundResource(R.color.colorAccent2);
                q2_answer0.setBackgroundResource(R.color.colorPrimary);
                q2_answer1.setBackgroundResource(R.color.colorPrimary);
                q2_answer2.setBackgroundResource(R.color.colorPrimary);
                checked2 = 1;
            }
        });

        /*
        * onclicklisterner for Buttons: check, view answers and new quiz
        */

        // Set a click listener for view answers button
        viewAnswersBtn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                String message;
                message = getString(R.string.correctAnswer1, correctAnswerQ1);
                message = message + getString(R.string.correctAnswer2, correctAnswerQ2);
                message = message + getString(R.string.correctAnswer3, correctAnswerQ3);
                message = message + getString(R.string.correctAnswer4, correctAnswersQ4.get(0), correctAnswersQ4.get(1));
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

        //listener for new quiz button
        newQuizBtn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                rdb0.setChecked(false);
                rdb1.setChecked(false);
                rdb2.setChecked(false);
                ckb0.setChecked(false);
                ckb1.setChecked(false);
                ckb2.setChecked(false);
                q3_text_box.setText("");
                checked1 = 0;
                checked2 = 0;
                checked3 = 0;
                checked4 = 0;
                selectedQuestions.clear();
                capitalsOfOneRegion.clear();
                textViewsTextsQ2.clear();
                radioButtonsTexts.clear();
                checkboxesTextQ4.clear();
                answersQ4.clear();
                correctAnswersQ4.clear();
                q2_answer0.setBackgroundResource(R.color.colorPrimary);
                q2_answer1.setBackgroundResource(R.color.colorPrimary);
                q2_answer2.setBackgroundResource(R.color.colorPrimary);
                q2_answer3.setBackgroundResource(R.color.colorPrimary);
                createQuestion1();
                createQuestion2();
                createQuestion3();
                createQuestion4();
            }
        });

        //listener for button check
        checkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String message;
                finalScore = 0;

                //checks if question 3 is answered and if it is correct
                if (!q3_text_box.getText().toString().equals("")) {
                    answerQ3 = q3_text_box.getText().toString();
                    checked3 = 1;
                    if (answerQ3.equals(correctAnswerQ3)) {
                        finalScore = finalScore + 1;
                    }
                } else {
                    checked3 = 0;
                }

                //checks if question 4 is answered and if is correct
                if (ckb0.isChecked() || ckb1.isChecked() || ckb2.isChecked()) {
                    for (int i = 0; i < answersQ4.size(); i++) {
                        answersQ4.remove(i);
                    }
                    checked4 = 1;
                    int i = 0;
                    if (ckb0.isChecked()) {
                        answersQ4.add(i, ckb0.getText().toString());
                        i++;
                    }
                    if (ckb1.isChecked()) {
                        answersQ4.add(i, ckb1.getText().toString());
                        i++;
                    }
                    if (ckb2.isChecked()) {
                        answersQ4.add(i, ckb2.getText().toString());
                    }

                    if (correctAnswersQ4.containsAll(answersQ4) && answersQ4.containsAll(correctAnswersQ4)) {
                        finalScore = finalScore + 1;
                    }

                } else {
                    checked4 = 0;
                }

                //checks if all questions are answered or shows a message asking to complete the Quiz

                if (checked1 == 1 && checked2 == 1 && checked3 == 1 && checked4 == 1) {
                    //checks if question 1 is correct
                    if (answerQ1.equals(correctAnswerQ1)) {
                        finalScore = finalScore + 1;
                    }

                    //checks if question 2 is correct
                    if (answerQ2.equals(correctAnswerQ2)) {
                        finalScore = finalScore + 1;
                    }
                    //Shows a final message depending on score
                    if (finalScore == 4) {
                        message = getString(R.string.all_correct);
                    } else {
                        message = getString(R.string.final_score, finalScore);
                    }
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                } else {
                    message = getString(R.string.answer_all);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    //Gets Info from web service REST countries and calls to createQuestions methods
    public void loadCountryInfo() {
        String strURL = "https://restcountries.eu/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(strURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi service = retrofit.create(RestApi.class);

        Call<ArrayList<CountriesInfo>> call = service.getCountryInfo();

        call.enqueue(new Callback<ArrayList<CountriesInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<CountriesInfo>> call, Response<ArrayList<CountriesInfo>> response) {
                if (response.isSuccessful()) {
                    //saves response in countriesInformation
                    countriesInformation = response.body();
                    //creates diferent arraylists for different kinds of info with paralel indexes: country names, capitals, region, etc.
                    for (int i = 0; i < countriesInformation.size(); i++) {
                        countriesNamesArrayList.add(i, countriesInformation.get(i).getName());
                        capitalsArrayList.add(i, countriesInformation.get(i).getCapital());
                        regionsArrayList.add(i, countriesInformation.get(i).getRegion());
                        flagsArrayList.add(i, countriesInformation.get(i).getFlag());
                    }
                    //select info from those arraylists to create questions
                    createQuestion1();
                    createQuestion2();
                    createQuestion3();
                    createQuestion4();
                    //createQuestion5();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.error_response)+response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, getString(R.string.error_on_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Selects info for question 1
     * Saves correct question and answer
     * Display answers in layout RadioGroup
     */
    public void createQuestion1() {
        int num = calcRandomNum();
        selectedQuestions.add(0, getInfoOfOneCountry(num, COUNTRY));
        correctAnswerQ1 = getInfoOfOneCountry(num, CAPITAL);
        radioButtonsTexts.add(0, correctAnswerQ1);
        for (int i = 1; i <= 2; i++) {
            int num2 = calcDiffRandomNum(num);
            radioButtonsTexts.add(i, getInfoOfOneCountry(num2, CAPITAL));
        }
        Collections.shuffle(radioButtonsTexts);
        rdb0.setText(getString(R.string.q1_rdb0, radioButtonsTexts.get(0)));
        rdb1.setText(getString(R.string.q1_rdb1, radioButtonsTexts.get(1)));
        rdb2.setText(getString(R.string.q1_rdb2, radioButtonsTexts.get(2)));
    }

    /**
     * Selects a drawable resource in a random way
     * to show in Question 2 and saves its name as correct answer
     */
    public void createQuestion2() {
        //Selects drawable randomly
        final TypedArray imgs = getResources().obtainTypedArray(R.array.capital_images);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        resID = imgs.getResourceId(rndInt, 0);
        imgs.recycle();

        //Gets drawable name and stores it as correct answer
        String b = imageQ2.getResources().getResourceName(resID);
        int i = b.indexOf("/");
        b = b.substring(i + 1);
        correctAnswerQ2 = b.substring(0, 1).toUpperCase() + b.substring(1);

        //Creates an array of capitals to show as possible answers in TextViews of question 2
        textViewsTextsQ2.add(0, correctAnswerQ2);
        i = 0;
        while (i <= 2) {
            int num = calcRandomNum();
            String capital = getInfoOfOneCountry(num, CAPITAL);
            if (!capital.equals(correctAnswerQ2)) {
                textViewsTextsQ2.add(i, capital);
                i++;
            }
        }
        Collections.shuffle(textViewsTextsQ2);
    }

    /**
     * Selects info for question 3
     * Saves question and correct answer
     */

    public void createQuestion3() {
        int num = calcRandomNum();
        selectedQuestions.add(1, getInfoOfOneCountry(num, CAPITAL));
        correctAnswerQ3 = getInfoOfOneCountry(num, COUNTRY);
    }

    /**
     * Selects info for question 4
     * Saves question and correct answers
     */
    public void createQuestion4() {
        int num = calcRandomNum();
        int i;
        selectedQuestions.add(2, getInfoOfOneCountry(num, REGION));

        int j = 0;
        //Creates an ArrayList of countries of the selected region
        for (i = 0; i < countriesInformation.size(); i++) {
            if (countriesInformation.get(i).getRegion().equals(selectedQuestions.get(2))) {
                capitalsOfOneRegion.add(j, countriesInformation.get(i).getCapital());
                j++;
            }
        }

        for (i = 0; i <= 1; i++) {
            int num2 = calcRandomNumInterval(0, capitalsOfOneRegion.size());
            correctAnswersQ4.add(i, capitalsOfOneRegion.get(num2));
        }
        i = 0;
        while (i < 1) {
            int num2 = calcRandomNumInterval(0, countriesInformation.size());
            String region = countriesInformation.get(num2).getRegion();
            if (!region.equals(selectedQuestions.get(2))) {
                checkboxesTextQ4.add(i, countriesInformation.get(num2).getCapital());
                i++;
            }
        }
        checkboxesTextQ4.addAll(correctAnswersQ4);
        ckb0.setText(getString(R.string.q4_ckb0, checkboxesTextQ4.get(0)));
        ckb1.setText(getString(R.string.q4_ckb1, checkboxesTextQ4.get(1)));
        ckb2.setText(getString(R.string.q4_ckb2, checkboxesTextQ4.get(2)));
        displayQuestions();
        displayQ2Answers();
    }

    /**
     * Selects info for question 5
     * Saves question and correct answers
     *
     * public void createQuestion5(){
     * int num = calcRandomNum();
     * selectedQuestions.add(3,getInfoOfOneCountry(num, COUNTRY));
     * correctAnswerQ5=getInfoOfOneCountry(num, FLAG);
     *
     * }
     */

    //Displays questions
    public void displayQuestions() {
        question1.setText(getString(R.string.question1, selectedQuestions.get(0)));
        imageQ2.setImageResource(resID);
        question3.setText(getString(R.string.question3, selectedQuestions.get(1)));
        question4.setText(getString(R.string.question4, selectedQuestions.get(2)));
        //question5.setText(getString(R.string.question5,selectedQuestions.get(3)));
    }



    //Displays question 2 answers
    public void displayQ2Answers() {
        q2_answer0.setText(textViewsTextsQ2.get(0));
        q2_answer1.setText(textViewsTextsQ2.get(1));
        q2_answer2.setText(textViewsTextsQ2.get(2));
        q2_answer3.setText(textViewsTextsQ2.get(3));

        if (!answerQ2.equals("")) {
            if (answerQ2.equals(q2_answer0.getText().toString())) {
                q2_answer0.setBackgroundResource(R.color.colorAccent2);
            } else if (answerQ2.equals(q2_answer1.getText().toString())) {
                q2_answer1.setBackgroundResource(R.color.colorAccent2);
            } else if (answerQ2.equals(q2_answer2.getText().toString())) {
                q2_answer2.setBackgroundResource(R.color.colorAccent2);
            } else if (answerQ2.equals(q2_answer3.getText().toString())) {
                q2_answer3.setBackgroundResource(R.color.colorAccent2);
            }
        }

    }

    /**
     * gets the info of one of the countries stored in countries ArrayList
     *
     * @param index country index in the ArrayList
     * @param info  specific info to get from country (name, capital, region or flag)
     * @return string with the info requested.
     */
    public String getInfoOfOneCountry(int index, String info) {
        String str = "";
        if (info.equals(COUNTRY)) {
            str = countriesInformation.get(index).getName();
        } else if (info.equals(CAPITAL)) {
            str = countriesInformation.get(index).getCapital();
        } else if (info.equals(REGION)) {
            str = countriesInformation.get(index).getRegion();
        } else if (info.equals(FLAG)) {
            str = countriesInformation.get(index).getFlag();
        }
        return str;
    }

    /**
     * Calculates a random number bewteen 0 and countries ArrayList size
     *
     * @return int The number randomly generated.
     */
    public int calcRandomNum() {
        Random rnNum = new Random();
        return rnNum.nextInt(countriesInformation.size() + 1);
    }

    /**
     * Calculates a random number in interval between min and max values
     *
     * @param min Interval minimum value
     * @param max Interval maximum value
     * @return int The number randomly generated.
     */
    public int calcRandomNumInterval(int min, int max) {
        Random rnNum = new Random();
        return rnNum.nextInt(max - min + 1) + min;
    }

    /**
     * Calculates a random number bewteen 0 and countries ArrayList size and different from num
     *
     * @param num random number calculated for correct answer.
     * @return int The number randomly generated different from num.
     */
    public int calcDiffRandomNum(int num) {
        int num2 = calcRandomNum();
        while (num2 == num) {
            num2 = calcRandomNum();
        }
        return num2;
    }

}