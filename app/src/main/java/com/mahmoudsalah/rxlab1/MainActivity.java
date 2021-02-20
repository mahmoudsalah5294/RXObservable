package com.mahmoudsalah.rxlab1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
public static final String TAG = "log";
    Button arrayBtn, iteratorBtn,just;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        arrayBtn = findViewById(R.id.arrayBtn);
        just = findViewById(R.id.justBtn);
        iteratorBtn = findViewById(R.id.iteratorBtn);
        Integer[] numbers = {1,2,3,4,5,6};
        List<Integer> numberList = Arrays.asList(1,2,3,4,5);



        arrayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.fromArray(numbers).map(e->e*e*e).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.i(TAG,integer.toString());
                        Log.i(TAG,"onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"onComplete");
                    }
                });
            }
        });

        iteratorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.fromIterable(numberList).subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"Iterable:onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {

                        Log.i(TAG,integer.toString());
                        Log.i(TAG,"Iterable:onNext");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"Iterable:onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"Iterable:onComplete");
                    }
                });
            }
        });

        just.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.just(numbers).subscribe(new Observer<Integer[]>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i(TAG,"just:onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull Integer[] integers) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Arrays.stream(integers).forEach(e-> Log.i(TAG,e.toString()));

                            Log.i(TAG,"just:onNext");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i(TAG,"just:error");
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG,"just:complete");
                    }
                });
            }
        });
    }
}