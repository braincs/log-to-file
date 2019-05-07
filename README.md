# Android LogUtils 日志文件本地化
## 引入方法

1. 在Application中初始化

    ~~~java
    public class MegApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            initLog();
        }
    
        private void initLog() {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(dir + File.separator + "Meg-Logs");
            if (!file.exists()) file.mkdirs();
            LogUtils.setLogDir(file.getAbsolutePath());
            LogUtils.setLogLevel(LogUtils.LogLevel.DEBUG);
        }
    }
    ~~~

2. 使用

    类似Android中的Log类的使用方法。
    
    ~~~java
    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
           LogUtils.d(TAG, "--start--");
           LogUtils.e(TAG, e.toString());
           LogUtils.d(TAG, "--after dangerous method call--");
           LogUtils.w(TAG, "--test--");
    
        }
    
        @Override
        protected void onDestroy() {
            super.onDestroy();
            LogUtils.close();
        }
    }
    ~~~
    
    `注意`退出时需调用LogUtils.close(); 将队列中缓存的log，flush到本地文件中，不然会有log缺失的问题
    
    通过以下项配置最大日志文件数，和单一日志文件大小
    
    ~~~java
    private static final int LOG_FILES_MAX_NUM = 5; //文件最多有5个
    private static final int LOG_FILE_MAX_SIZE = 1024 * 1024; //文件最大1MB
    ~~~
    
    