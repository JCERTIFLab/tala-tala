package com.jcertif.relooking;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.GridView;
import android.widget.ImageView;

import com.getbase.floatingactionbutton.FloatingActionButton;
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

    //FrameLayout mainCanvas;
    GridView palette;
    PaletteAdapter paletteAdapter;
    List<Drawable> drawablesList;

    private FloatingActionButton btnHabits;
    SlidingPaneLayout paletteContainer;

    ItemsUtils.ItemType selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }


        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        paletteContainer=(SlidingPaneLayout) findViewById(R.id.paletteContainer);
        btnHabits=(FloatingActionButton)findViewById(R.id.pick_robe);

    //  mainCanvas=(FrameLayout)findViewById(R.id.main_canvas);
      //  palette=(GridView)findViewById(R.id.palette);

        drawablesList=new ArrayList<>();
        paletteAdapter=new PaletteAdapter(this,drawablesList);

        palette=(GridView)findViewById(R.id.palette_grid);

        palette.setAdapter(paletteAdapter);

        btnHabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedType== ItemsUtils.ItemType.CLOTHES){
                    paletteContainer.openPane();
                    return;
                }
                new ItemLoaderTak().execute(ItemsUtils.ItemType.CLOTHES);
            }
        });


      // getSupportActionBar().hide();


    }


     List<Drawable>  loadItems(ItemsUtils.ItemType type) throws Resources.NotFoundException {


         String resBasename=ItemsUtils.getBaseName(type);

        for(int i=1;i<=ItemsUtils.getItemCount(type);i++){

            Drawable drawable = getResources().getDrawable(getResources()
                    .getIdentifier(resBasename+i, "drawable", getPackageName()));

            if(drawable==null){
                continue;

            }else{

                drawablesList.add(drawable);
            }

        }

        return  drawablesList;

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

            paletteContainer.openPane();
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

      //  mainCanvas.addView(imgView);


    }

    @Override
    public void removeItem(RelookingItem item) {

     //   mainCanvas.removeViewAt(item.getResourceId());
      //  mainCanvas.refreshDrawableState();

    }

    @Override
    public void changeAvatar(Avatar avatar) {

    }

    @Override
    public void upDateDimensions(CanvasDimensions newDimensions) {

        //zooming

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


    /**
     * Pour rendre la bare des status
     * @param on
     */
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
