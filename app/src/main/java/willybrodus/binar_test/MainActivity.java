package willybrodus.binar_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import willybrodus.binar_test.order_menu.IsiOrder_menuActivity;
import willybrodus.binar_test.order_menu.ListOrder_menuActivity;
import willybrodus.binar_test.resto.IsiRestoActivity;
import willybrodus.binar_test.resto.ListRestoActivity;
import willybrodus.binar_test.user_app.IsiUser_appActivity;
import willybrodus.binar_test.user_app.ListUser_appActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIsiResto = (Button) findViewById(R.id.btnIsiResto);
        btnIsiResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IsiRestoActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnListResto = (Button) findViewById(R.id.btnListResto);
        btnListResto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, ListRestoActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        Button btnIsiOrder = (Button) findViewById(R.id.btnIsiOrder);
        btnIsiOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, IsiOrder_menuActivity.class);
                startActivity(intent2);
                finish();
            }
        });


        Button btnListOrder = (Button) findViewById(R.id.btnListOrder);
        btnListOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, ListOrder_menuActivity.class);
                startActivity(intent3);
                finish();
            }
        });


        Button btnIsiUser = (Button) findViewById(R.id.btnIsiUser);
        btnIsiUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MainActivity.this, IsiUser_appActivity.class);
                startActivity(intent4);
                finish();
            }
        });


        Button btnListUser = (Button) findViewById(R.id.btnListUser);
        btnListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(MainActivity.this, ListUser_appActivity.class);
                startActivity(intent5);
                finish();
            }
        });


        Button btnIsiMenu = (Button) findViewById(R.id.btnIsiMenu);
        btnIsiMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent(MainActivity.this, IsiOrder_menuActivity.class);
                startActivity(intent6);
                finish();
            }
        });


        Button btnListMenu = (Button) findViewById(R.id.btnListMenu);
        btnListMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(MainActivity.this, ListOrder_menuActivity.class);
                startActivity(intent7);
                finish();
            }
        });



    }
}
