package de.sekas.eicar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
    
	private TextView getTextView (int id)
	{
		return (TextView) findViewById(id);
	}
	
    private void showData (int id, String data)
    {
        getTextView(id).setText (data);
    }
    
    private void showData (int id, int resid)
    {
        getTextView(id).setText (resid);
    }
    
    private void showIcar (String line)
    {
        showData (R.id.label, R.string.found);
        showData (R.id.text, line);
        showData (R.id.verdict, R.string.bad);
        getTextView(R.id.verdict).setTextColor(getResources().getColor(R.color.error));
        showData (R.id.message, R.string.notremoved);
    }
    
    private void showError (String error)
    {
        showData (R.id.label, R.string.eaccess);
        showData (R.id.text, error);
        showData (R.id.verdict, R.string.good);
        getTextView(R.id.verdict).setTextColor(getResources().getColor(R.color.ok));
        showData (R.id.message, R.string.removed);
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            InputStream is = getResources().openRawResource(R.raw.eicar);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(ir);
            String line = br.readLine();
            showIcar (line);
        } catch (Exception ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            showError (ex.toString());
        }
    }
}
