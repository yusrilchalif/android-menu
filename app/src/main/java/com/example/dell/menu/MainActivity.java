package com.example.dell.menu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    EditText etMessage1;
    EditText etMessage2;
    Integer[] arrayPointSize = {10, 20, 30, 40, 50};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register menu content
        etMessage1 = (EditText)findViewById(R.id.etMessage1);
        etMessage2 = (EditText)findViewById(R.id.etMessage2);

        //register individual content
        registerForContextMenu(etMessage1);
        registerForContextMenu(etMessage2);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        populateMyFirstMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        if(view.getId() == etMessage1.getId())
            populateMyFirstMenu(menu);
        if(view.getId() == etMessage2.getId())
            populateMyFirstMenu(menu);
    }

    private void populateMyFirstMenu(Menu menu){
        int groupId = 0;
        int order = 0;

        menu.add(groupId, 1, 1, "10 Point");
        menu.add(groupId, 2, 2, "20 Point");
        menu.add(groupId, 3, 3, "30 Point");
        menu.add(groupId, 4, 4, "40 Point");
        menu.add(groupId, 5, 5, "50 Point");

        menu.add(groupId, 6, 8, "Red Text");
        menu.add(groupId, 7, 7, "Green Text");
        menu.add(groupId, 8, 6, "Blue Text");
    }

    private void populateMySecondMenu(Menu menu){
        int groupId = 0;
        int order = 0;

        menu.add(groupId, 9, 1, "Bold");
        menu.add(groupId, 10, 2, "Italic");
        menu.add(groupId, 11, 3, "Normal");
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem){
        return(applyMenuOption(menuItem));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        return(applyMenuOption(menuItem));
    }

    private boolean applyMenuOption(MenuItem menuItem){
        int menuItemId = menuItem.getItemId();
        String strMsg2 = etMessage2.getText().toString();

        if(menuItemId <= 5){
            int newPointSize = arrayPointSize[menuItemId -1];
            etMessage1.setTextSize(newPointSize);
            etMessage2.setTextSize(newPointSize);
        }else{
            if(menuItemId == 6)
                etMessage1.setTextColor(0xffff0000);    //red
            else if(menuItemId == 7)
                etMessage1.setTextColor(0xff00ff00);    //green
            else if(menuItemId == 8)
                etMessage1.setTextColor(0xff0000ff);     //blue
            else if(menuItemId == 9)
                etMessage2.setText(beautify(strMsg2, "Bold"));
            else if(menuItemId == 10)
                etMessage2.setText(beautify(strMsg2, "Italic"));
            else if(menuItemId == 11)
                etMessage2.setText(beautify(strMsg2, "Normal"));
        }
        return false;
    }

    private Spanned beautify(String originalText, String selectedStyle){
        Spanned answer = null;
        if(selectedStyle.equals("Bold"))
            answer = Html.fromHtml("<b>" + originalText + "</b>");
        else if(selectedStyle.equals("Italic"))
            answer = Html.fromHtml("<i>" + originalText + "</i>");
        else if(selectedStyle.equals("Normal"))
            answer = Html.fromHtml("<normal>" + originalText +"</normal>");

        return answer;
    }
}
