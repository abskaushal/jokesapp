package abhi.com.jokeapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import abhi.com.uilib.JokeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment implements IAsyncListener {


    private ProgressBar mProgressBar;

    public JokeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke, container, false);
        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar1);
        mProgressBar.setVisibility(View.INVISIBLE);
        ((Button) root.findViewById(R.id.getJoke)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    private void tellJoke() {
        new GetJokeAsync(this).execute();
    }

    @Override
    public void onAsyncStart() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAsyncFinish(String joke) {
        mProgressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE, joke);
        startActivity(intent);
    }


}
