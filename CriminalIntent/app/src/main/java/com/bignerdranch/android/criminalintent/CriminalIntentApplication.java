// 继承 application 可以在 application 启动时就运行,
    // 使与此类绑定的东西声明周期跟随 application;
// 需要去 AndroidManifest.xml 中登记;

package com.bignerdranch.android.criminalintent;

import android.app.Application;

public class CriminalIntentApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrimeRepository.initialize(this);
    }
}
