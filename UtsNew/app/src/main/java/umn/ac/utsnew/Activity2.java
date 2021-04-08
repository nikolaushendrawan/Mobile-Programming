package umn.ac.utsnew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;


import java.util.ArrayList;


public class Activity2 extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome").setMessage("Nikolaus Hendrawan" + "\n" + "00000031190").setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }
    public static final int REQUEST_CODE = 1;
    static ArrayList<MusicFile> musicFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        this.setTitle("List Lagu");
        permission();
        ImageView popupmenuicon = findViewById(R.id.popupicon);

        popupmenuicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Activity2.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
        ,REQUEST_CODE);
        }
        else
        {
            musicFiles = getAllAudio(this);
            intViewPager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                musicFiles = getAllAudio(this);
                intViewPager();
            }
            else{
                ActivityCompat.requestPermissions(Activity2.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                        ,REQUEST_CODE);
            }
        }
    }

    private void intViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SongsFragment(),"Song");
        viewPagerAdapter.addFragment(new AlbumFragment(), "Album");
        viewPager.setAdapter(viewPagerAdapter);
    }
    public static class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }
        void addFragment(Fragment fragment, String title)
        {
            fragments.add(fragment);
            titles.add(title);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
    private void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(Activity2.this,v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.profilebtn2)
                    Profile();
                if (item.getItemId() == R.id.logoutbtn)
                    MainActivity();
                return false;
            }
        });
        popupMenu.show();
    }
    public static ArrayList<MusicFile> getAllAudio(Context context)
    {
        ArrayList<MusicFile> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST
        };
        Cursor cursor = context.getContentResolver().query(uri, projection,
                null, null, null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                MusicFile musicFile = new MusicFile(path, title, artist, album, duration);
                Log.e("Path : " + path,"Album : "+ album);
                tempAudioList.add(musicFile);
            }
            cursor.close();
        }
        return tempAudioList;
    }
    public void Profile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
