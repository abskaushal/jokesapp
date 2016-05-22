package abhi.com.uilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.joke_of_the_day));

        if(getIntent().getStringExtra(JOKE)!=null){
            ((TextView)findViewById(R.id.joke)).setText(getIntent().getStringExtra(JOKE));
        }else{
            ((TextView)findViewById(R.id.joke)).setText(getString(R.string.forgot_my_joke));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}
