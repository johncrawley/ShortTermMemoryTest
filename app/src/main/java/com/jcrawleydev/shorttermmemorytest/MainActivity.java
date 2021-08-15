package com.jcrawleydev.shorttermmemorytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcrawleydev.shorttermmemorytest.animation.AnimationSetup;
import com.jcrawleydev.shorttermmemorytest.states.GameState;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManager;
import com.jcrawleydev.shorttermmemorytest.states.manager.StateManagerImpl;
import com.jcrawleydev.shorttermmemorytest.view.TextHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener, TextHolder {


    private TextView itemTextView;
    private StateManager stateManager;
    private EditText wordInputEditText;
    private Animation fadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button beginTestButton = findViewById(R.id.beginTestButton);
        Button doneButton = findViewById(R.id.doneButton);
        itemTextView = findViewById(R.id.itemView);
        wordInputEditText = findViewById(R.id.editTextTextMultiLine);


        beginTestButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        itemTextView.setOnKeyListener(this);

        stateManager = new StateManagerImpl(this);
        setupKeyAction(wordInputEditText, MainActivity.this);
        AnimationSetup animationSetup = new AnimationSetup(itemTextView);
        fadeInAnimation = animationSetup.getFadeInAnimation();
    }


    @Override
    public void onClick(View v){
        stateManager.onClick(v.getId());
    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }


    @Override
    public void setWordText(final String text){
        runOnUiThread(() -> {
            itemTextView.setText(text);
            itemTextView.startAnimation(fadeInAnimation);
        });
    }


    @Override
    public void clearRecallText(){
        runOnUiThread(() -> wordInputEditText.setText(""));
    }


    @Override
    public void setCountdownText(final String value){
        runOnUiThread(() -> {
                TextView textView = findViewById(R.id.countdownText);
                textView.setText(value);
            });
    }


    private void setupKeyAction(final EditText editText, final Context context){
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm == null){
                    return false;
                }
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                String contents = editText.getText().toString();
                stateManager.onKeyboardDone(contents);
                return true;
            }
            return false;
        });
    }


    public void stopTask(final GameState currentState){
        runOnUiThread(() -> {
            currentState.stop();
            setVisibilityOnCurrentLayout(currentState, View.GONE);
        });
    }


    public void startTask(final GameState currentState){
        runOnUiThread(() -> {
            currentState.start();
            setVisibilityOnCurrentLayout(currentState, View.VISIBLE);
        });
    }


    public void setVisibilityOnCurrentLayout(GameState currentState, int visibility){
        findViewById(currentState.getLayoutId()).setVisibility(visibility);
    }


    private void log(String msg){
        System.out.println("MainActivity: " + msg);
        System.out.flush();
    }

}