package com.jcertif.relooking;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.jcertif.relooking.com.jcertif.relooking.utils.ItemsUtils;
import com.jcertif.relooking.graphics.CanvasDimensions;
import com.jcertif.relooking.graphics.ICanvasOperation;
import com.jcertif.relooking.graphics.IResizable;
import com.jcertif.relooking.graphics.PaletteAdapter;
import com.jcertif.relooking.model.Avatar;
import com.jcertif.relooking.model.RelookingItem;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ICanvasOperation, View.OnDragListener, IResizable {

    Toolbar toolbar;

    FrameLayout mainCanvas;
    GridView palette;
    PaletteAdapter paletteAdapter;


    List<Drawable> drawablesList;

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

        drawablesList=new ArrayList<>();
        paletteAdapter=new PaletteAdapter(this,drawablesList);


        getSupportActionBar().hide();


    }




    public List<Drawable>  loadItems(ItemsUtils.ItemType type){

        for(int i=0;i<ItemsUtils.getItemCount(type);i++){


            Drawable drawable = getResources().getDrawable(getResources()
                    .getIdentifier(ItemsUtils.getBaseName(type)+i, "drawable", getPackageName()));



            if(drawable==null){

                continue;

            }else{

                drawablesList.add(drawable);
            }



        }

        return  drawablesList;


    }

    @Override
    public void resize(float ratio) {

    }

    @Override
    public void rotate(float angle) {

    }

    @Override
    public void translate(float x0, float y0, float x1, float y1) {

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        return false;
    }



    class ItemLoaderTak extends AsyncTask<ItemsUtils.ItemType ,Integer, List<Drawable>> {


        @Override
        protected List<Drawable> doInBackground(ItemsUtils.ItemType... type) {

            List<Drawable> list;

            list=loadItems(type[0]);

            if(list==null){
                list=new ArrayList<>();
            }

            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute( List<Drawable> list) {
            super.onPostExecute(list);

            drawablesList=list;
            paletteAdapter.notifyDataSetChanged();
        }
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
