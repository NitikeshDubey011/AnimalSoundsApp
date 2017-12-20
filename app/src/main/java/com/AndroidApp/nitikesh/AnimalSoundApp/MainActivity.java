package com.AndroidApp.nitikesh.AnimalSoundApp;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private String[] animals_names={"cat","dog","lion","elephant","monkey","cow","horse","donkey","pig","guineapig"};


   ViewPager viewPager;
   ImageButton previousButton,nextButton,playButton;
   TextView nameChanger;
   MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Collections.shuffle(Arrays.asList(animals_names));

        nameChanger=(TextView)findViewById(R.id.txtName);
        nameChanger.setText(upperCaseFirstLetter(animals_names[0]));
        viewPager=(ViewPager)findViewById(R.id.pager_view);
        ViewPagerAdapter adapter=new ViewPagerAdapter(this,animals_names);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                nameChanger.setText(upperCaseFirstLetter(animals_names[position]));
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        previousButton=(ImageButton) findViewById(R.id.previous_button);
        nextButton=(ImageButton)findViewById(R.id.next_button);
        playButton=(ImageButton)findViewById(R.id.sound_button);

        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

    }

    private String upperCaseFirstLetter(String animals_name) {
        StringBuilder stringBuilder=new StringBuilder(animals_name);
        return stringBuilder.substring(0,1).toUpperCase()+stringBuilder.substring(1).toLowerCase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_button:
            {
                if (viewPager.getCurrentItem()==0){
                    viewPager.setCurrentItem(animals_names.length-1,true);
                }
                else{
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1,true);
                }

            }
            break;
            case R.id.next_button:
            {
                if (viewPager.getCurrentItem()==animals_names.length-1){
                    viewPager.setCurrentItem(0,true);
                }
                else{
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1,true);
                }
            }
            break;
            case R.id.sound_button:
            {
                int animal_sound=this.getResources().getIdentifier(nameChanger.getText().toString().toLowerCase(),"raw",this.getPackageName());
                mediaPlayer=MediaPlayer.create(this,animal_sound);
                mediaPlayer.setLooping(false);
                mediaPlayer.setVolume(1.0f,1.0f);
                mediaPlayer.start();
            }
        }
    }
}
