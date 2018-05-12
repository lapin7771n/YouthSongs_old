package com.example.nikit.youthsongs;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FoldingCell foldingCell = findViewById(R.id.folding_cell);
        foldingCell.initialize(30, 1000, Color.DKGRAY, 2);

        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldingCell.toggle(false);
            }
        });
    }
}
