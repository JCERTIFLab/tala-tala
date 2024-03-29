package com.jcertif.relooking;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.colorpicker.ColorPickerDialog;
import com.android.colorpicker.ColorPickerSwatch;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jcertif.relooking.com.jcertif.relooking.utils.ItemsUtils;
import com.jcertif.relooking.graphics.CanvasDimensions;
import com.jcertif.relooking.graphics.ICanvasOperation;
import com.jcertif.relooking.graphics.IResizable;
import com.jcertif.relooking.graphics.PaletteAdapter;
import com.jcertif.relooking.model.RelookingItem;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ICanvasOperation, IResizable, View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int MAX_ITEMS_ON_ON_CANVAS = 6;
    Toolbar mToolbar;

    ImageView avatar;
    GridView palette;
    PaletteAdapter paletteAdapter;
    List<Drawable> drawablesList;

    FrameLayout mainCanvas;


    private FloatingActionsMenu mFabButton;
    private FloatingActionButton fabHabits, fabYeux, fabSouliers, fabBouche, fabCheveux;

    SlidingPaneLayout paletteContainer;
    static ItemsUtils.ItemType selectedType = ItemsUtils.ItemType.DEFAULT;

    private MyDragListener dragListener;

    int itemTypeAlreadyAdded = 0;

    View selectedView;

    private int mSelectedColor;

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

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        paletteContainer = (SlidingPaneLayout) findViewById(R.id.paletteContainer);

        mFabButton = (FloatingActionsMenu) findViewById(R.id.floating_menu);

        fabHabits = (FloatingActionButton) findViewById(R.id.pick_robe);
        fabYeux = (FloatingActionButton) findViewById(R.id.pick_yeux);
        fabBouche = (FloatingActionButton) findViewById(R.id.pick_bouche);
        fabSouliers = (FloatingActionButton) findViewById(R.id.pick_souliers);

        mainCanvas = (FrameLayout) findViewById(R.id.main_canvas);

        fabCheveux = (FloatingActionButton) findViewById(R.id.pick_cheveux);

        drawablesList = loadItems(ItemsUtils.ItemType.HAIR);

        paletteAdapter = new PaletteAdapter(this, drawablesList);

        palette = (GridView) findViewById(R.id.palette_grid);

        palette.setAdapter(paletteAdapter);

        palette.setOnItemClickListener(this);

        setupClickListener(fabBouche, fabHabits, fabSouliers, fabYeux, fabCheveux);

        mFabButton.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                showToolBar();
            }

            @Override
            public void onMenuCollapsed() {

                hideToolBar();
            }
        });
        hideToolBar();


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (!paletteContainer.isOpen()) {
            return; //capital puisque le slidingDrawer meme invisbile est accessible
        }

        Drawable item = (Drawable) parent.getItemAtPosition(position);


        if (itemTypeAlreadyAdded == MAX_ITEMS_ON_ON_CANVAS) {

            Toast.makeText(this, "Retirez un item d'abord", Toast.LENGTH_SHORT).show();

            paletteContainer.closePane();
            return;
        }

        final ImageView imgView = new ImageView(this);
        imgView.setId(itemTypeAlreadyAdded);
        imgView.setImageDrawable(item);
        imgView.setAdjustViewBounds(true);

        imgView.setLongClickable(true);

        imgView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

              //  Toast.makeText(MainActivity.this, "lONG CLICK ON id: " + imgView.getId(), Toast.LENGTH_SHORT).show();

                selectedView = v;

              //  paintColor(imgView);

                showToolBar();


                return false;
            }
        });

        mainCanvas.addView(imgView);

        selectedView = imgView;

        //  mainCanvas.refreshDrawableState();

        paletteContainer.closePane();

        itemTypeAlreadyAdded++;
    }

    void setupClickListener(FloatingActionButton... fabs) {

        for (FloatingActionButton b : fabs) {
            b.setOnClickListener(this);
        }

    }

    List<Drawable> loadItems(ItemsUtils.ItemType type) throws Resources.NotFoundException {

        List<Drawable> loadItems = new ArrayList<>();

        String resBasename = ItemsUtils.getBaseName(type);

        for (int i = 0; i < ItemsUtils.getItemCount(type); i++) {

            Drawable drawable = getResources().getDrawable(getResources()
                    .getIdentifier(resBasename + i, "drawable", getPackageName()));

            if (drawable == null) {
                continue;

            } else {

                loadItems.add(drawable);
            }

        }

        return loadItems;

    }

    @Override
    public void onClick(View v) {

        if (v instanceof FloatingActionButton) {

            mFabButton.collapse();
            if (getSupportActionBar().isShowing()) {
                hideToolBar();
            }


        }
        if (v.getId() == fabHabits.getId()) {

            startSelectingItem(ItemsUtils.ItemType.CLOTHES);

        }

        if (v.getId() == fabYeux.getId()) {

            startSelectingItem(ItemsUtils.ItemType.EYES);

        }

        if (v.getId() == fabBouche.getId()) {

            startSelectingItem(ItemsUtils.ItemType.LIPS);

        }

        if (v.getId() == fabSouliers.getId()) {

            startSelectingItem(ItemsUtils.ItemType.SHOES);

        }

        if (v.getId() == fabCheveux.getId()) {

            startSelectingItem(ItemsUtils.ItemType.HAIR);

        }
    }


    void startSelectingItem(ItemsUtils.ItemType type) {

        if (selectedType.equals(type)) {
            paletteContainer.openPane();
            return;
        } else {
            selectedType = type;

            new ItemLoaderTak().execute(type);
        }

    }


    class ItemLoaderTak extends AsyncTask<ItemsUtils.ItemType, Integer, List<Drawable>> {


        @Override
        protected List<Drawable> doInBackground(ItemsUtils.ItemType... type) {


            drawablesList = loadItems(type[0]);

            if (drawablesList == null) {
                drawablesList = new ArrayList<>();
            }

            return drawablesList;
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
        protected void onPostExecute(final List<Drawable> list) {
            super.onPostExecute(list);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    paletteAdapter.updateModel(list);

                    paletteContainer.openPane();
                }
            });


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
        if (id == R.id.action_done) {
            return true;
        }


        if (id == R.id.action_undo) {

            if (itemTypeAlreadyAdded >= 1) {
                mainCanvas.removeViewAt(itemTypeAlreadyAdded);
                itemTypeAlreadyAdded--;
            }

            return true;
        }

        if (id == R.id.action_remove_all) {

            if (itemTypeAlreadyAdded > 0) {

                while (itemTypeAlreadyAdded > 0) {
                    mainCanvas.removeViewAt(itemTypeAlreadyAdded);
                    itemTypeAlreadyAdded--;
                }


            }

            return true;
        }

        if (id == R.id.action_palette) {

            startPickingAcolor();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addItem(RelookingItem item) {


    }

    @Override
    public void removeItem(RelookingItem item) {


    }

    @Override
    public void changeAvatar(RelookingItem avatar) {

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


    /**
     * Pour rendre la bare des status
     *
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


    void hideToolBar() {

        new Handler()

                .postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getSupportActionBar().hide();
                    }
                }, 600);
    }

    void showToolBar() {

        new Handler()

                .postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getSupportActionBar().show();
                    }
                }, 600);
    }


    void paintColor(ImageView iv) {
        Drawable drawable = iv.getDrawable();

        drawable.setColorFilter(new PorterDuffColorFilter(mSelectedColor, PorterDuff.Mode.MULTIPLY));
    }


    class MyDragListener implements View.OnDragListener {

        Drawable toDragShape;
        Drawable targetShape;

        MyDragListener(Drawable targetShape, Drawable toDragShape) {
            this.targetShape = targetShape;
            this.toDragShape = toDragShape;
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(toDragShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(targetShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(targetShape);
                default:
                    break;
            }
            return true;
        }

        public Drawable getToDragShape() {
            return toDragShape;
        }

        public void setToDragShape(Drawable toDragShape) {
            this.toDragShape = toDragShape;
        }

        public Drawable getTargetShape() {
            return targetShape;
        }

        public void setTargetShape(Drawable targetShape) {
            this.targetShape = targetShape;
        }
    }


    void startPickingAcolor() {

        ColorPickerDialog colorPickerDialog = ColorPickerDialog.newInstance(
                R.string.color_picker_default_title,
                colorChoice(), 0, 5,
                isTablet() ? ColorPickerDialog.SIZE_LARGE : ColorPickerDialog.SIZE_SMALL);
        colorPickerDialog.show(getFragmentManager(), "cal");

     colorPickerDialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
         @Override
         public void onColorSelected(int color) {

             mSelectedColor=color;

             paintColor((ImageView) selectedView);
         }
     });


    }

    private boolean isTablet() {
        return ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

    public int[] colorChoice() {

        int[] mColorChoices = null;
        String[] color_array = this.getResources().
                getStringArray(R.array.default_color_choice_values);

        if (color_array != null && color_array.length > 0) {
            mColorChoices = new int[color_array.length];
            for (int i = 0; i < color_array.length; i++) {
                mColorChoices[i] = Color.parseColor(color_array[i]);
            }
        }
        return mColorChoices;
    }

}
