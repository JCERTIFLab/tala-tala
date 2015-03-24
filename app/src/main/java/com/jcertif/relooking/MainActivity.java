package com.jcertif.relooking;

import android.annotation.TargetApi;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.jcertif.relooking.graphics.CanvasDimensions;
import com.jcertif.relooking.graphics.ICanvasOperation;
import com.jcertif.relooking.graphics.MainCanvas;
import com.jcertif.relooking.graphics.PaletteAdapter;
import com.jcertif.relooking.model.Avatar;
import com.jcertif.relooking.model.Cheveux;
import com.jcertif.relooking.model.ItemType;
import com.jcertif.relooking.model.RelookingItem;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ICanvasOperation{

    Toolbar toolbar;

    FrameLayout mainCanvas;
    GridView palette;
    PaletteAdapter paletteAdapter;
    List<RelookingItem> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainCanvas=(FrameLayout)findViewById(R.id.main_canvas);
        palette=(GridView)findViewById(R.id.palette);


        itemList=new ArrayList<>();
        paletteAdapter=new PaletteAdapter(this,itemList);


        getSupportActionBar().hide();


    }




    void loadItems(ItemType type){



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addItem(RelookingItem item) {

        ImageView imgView = new ImageView(this);
        imgView.setId(item.getResourceId());

        imgView.setImageDrawable(getResources().getDrawable(item.getResourceId()));

        mainCanvas.addView(imgView);


    }

    @Override
    public void removeItem(RelookingItem item) {

        mainCanvas.removeViewAt(item.getResourceId());
        mainCanvas.refreshDrawableState();

    }

    @Override
    public void changeAvatar(Avatar avatar) {

    }

    @Override
    public void upDateDimensions(CanvasDimensions newDimensions) {

        //zooming

    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {

        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
