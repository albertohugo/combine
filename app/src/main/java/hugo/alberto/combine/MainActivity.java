package hugo.alberto.combine;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTxt, result;
    private Context ctx = MainActivity.this;
    private ListView list;
    private Button btn;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    private char set1[] = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'M', 'N', 'K', 'P', 'Q', 'R', 'T', 'V', 'X', 'Y', 'Z', '2', '3', '4', '6', '7', '8', '9'};
    private int chars = 2;
    private String toReplace = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTxt = (EditText) findViewById(R.id.editText);
        result = (EditText) findViewById(R.id.result);
        list = (ListView) findViewById(R.id.listView);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    arrayList = new ArrayList<String>();
                    adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, arrayList);
                    list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    list.setAdapter(adapter);

                    chars = Integer.parseInt(String.valueOf(editTxt.getText()));
                    adapter.clear();

                    if (chars<5){
                        Snackbar.make(view, "Generating...", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        getLength(set1, chars);

                        if(chars==1){
                            toReplace="*";
                        }else if(chars==2){
                            toReplace="**";
                        }else if(chars==3){
                            toReplace="***";
                        }else if(chars==4){
                            toReplace="****";
                        }
                    }else{
                        Snackbar.make(view, "You need to input smaller than 5", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                } catch (Exception e) {
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView textView = (TextView) view.findViewById(android.R.id.text1);

                String willReplace = textView.getText().toString();
                if(chars==1){
                    result.setText(result.getText().toString().replace(toReplace, willReplace));
                }else if(chars==2){
                    result.setText(result.getText().toString().replace(toReplace, willReplace));
                }else if(chars==3){
                    result.setText(result.getText().toString().replace(toReplace, willReplace));
                }else if(chars==4){
                    result.setText(result.getText().toString().replace(toReplace, willReplace));
                }
                toReplace=willReplace;

                try{
                    for (int i = 0; i < list.getChildCount(); i++) {
                        if(position == i ){

                            list.getChildAt(i).setAlpha(.5f);
                        }else{
                            list.getChildAt(i).setAlpha(1.0f);

                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }});
    }

    void getLength(char set[], int chars) {
        int n = set.length;
        printRecord(set, "", n, chars);
    }

    void printRecord(char set[], String prefix, int n, int chars) {

        if (chars == 0) {
            System.out.println(prefix);
            arrayList.add(prefix);
            adapter.notifyDataSetChanged();


            return;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            printRecord(set, newPrefix, n, chars - 1);
        }
    }
}
