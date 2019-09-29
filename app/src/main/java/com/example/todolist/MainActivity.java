package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.todolist.active_task.ActiveTaskFragment;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.login_screen.LoginActivity;
import com.example.todolist.old_task.OldTaskFragment;
import com.example.todolist.to_do.ToDoFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public static final String GOOGLE_ACCOUNT = "GOOGLE_ACCOUNT";

    ImageView profileImage;
    TextView profileName;
    TextView profileEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        profileImage = header.findViewById(R.id.profile_image);
        profileName = header.findViewById(R.id.profile_title);
        profileEmail = header.findViewById(R.id.profile_mail);
        navigationView.setNavigationItemSelectedListener(this);
        initLocalDataBase();
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        setDataOnView();
    }

    void initLocalDataBase() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        MySingleton.getInstance().saveDatabaseHelper(databaseHelper);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (id == R.id.nav_to_do) {
            fragmentTransaction.replace(R.id.frame_layout_id, new ToDoFragment());
        } else if (id == R.id.nav_old_task) {
            fragmentTransaction.replace(R.id.frame_layout_id, new OldTaskFragment());
        } else if (id == R.id.nav_active_task) {
            fragmentTransaction.replace(R.id.frame_layout_id, new ActiveTaskFragment());
        } else if (id == R.id.sign_out) {
            signOut();
        }
        fragmentTransaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        GoogleSignInClient googleSignInClient = MySingleton.getInstance().getGoogleSignInClient();
        googleSignInClient.signOut().addOnCompleteListener(task -> {
            //On Succesfull signout we navigate the user back to LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void setDataOnView() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        assert googleSignInAccount != null;
        Picasso.get().load(googleSignInAccount.getPhotoUrl()).error(R.drawable.ic_gmail)
                .centerInside().fit().into(profileImage);
        profileName.setText(googleSignInAccount.getDisplayName());
        profileEmail.setText(googleSignInAccount.getEmail());
    }

}
