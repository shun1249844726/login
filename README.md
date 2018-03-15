# login
login mvp demo

> 参考文章：[MVP 模式简单易懂的介绍方式](http://kaedea.com/2015/10/11/android-mvp-pattern/)

**MVP模式的核心思想：**

MVP把Activity中的UI逻辑抽象成View接口，把业务逻辑抽象成Presenter接口，Model类还是原来的Model。
![MVP](./doc/mvp.png)

该项目：
![mvp_login_demo](./doc/mvp_login_demo.png)

## 内存泄露问题
Presenter中持有View接口对象，这个接口对象实际为MainActivity.this，Modle中也同时拥有Presenter对象实例，当MainActivity要销毁时，Presenter中有Modle在获取数据，那么问题来了，这个Activity还能正常销毁吗？ 
答案是不能！ 
当Modle在获取数据时，不做处理，它就一直持有Presenter对象，而Presenter对象又持有Activity对象，这条GC链不剪断，Activity就无法被完整回收。 
换句话说：Presenter不销毁，Activity就无法正常被回收。

## 解决MVP的内存泄露
Presenter在Activity的onDestroy方法回调时执行资源释放操作，或者在Presenter引用View对象时使用更加容易回收的软引用，弱应用。 
比如示例代码： 
Activity
```java
@Override
public void onDestroy() {
   super.onDestroy();
   mPresenter.destroy();
   mPresenter = null;
}
```

Presenter
```java
public void destroy() {
    view = null;
    if(modle != null) {
        modle.cancleTasks();
        modle = null;
    }
}
```

Modle
```java
public void cancleTasks() {
    // TODO 终止线程池ThreadPool.shutDown()，AsyncTask.cancle()，或者调用框架的取消任务api
}
```