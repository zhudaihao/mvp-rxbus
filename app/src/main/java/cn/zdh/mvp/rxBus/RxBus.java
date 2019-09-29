package cn.zdh.mvp.rxBus;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 多线程总线
 * 处理注解逻辑
 */
public class RxBus {
    /**
     * 注解类集合
     */

    private Set<Object> set;


    /**
     * 注册
     */
    public synchronized void register(Object object) {
        set.add(object);
    }


    /**
     * 注销
     */

    public synchronized void unRegister(Object object) {
        set.remove(object);
    }


    /**
     * 双重锁单利
     */
    private RxBus() {
        //使用读写分类集合
        set = new CopyOnWriteArraySet<>();
    }

    private static volatile RxBus rxBus;

    public static synchronized RxBus getInstance() {
        if (rxBus == null) {
            synchronized (RxBus.class) {
                if (rxBus == null) {
                    rxBus = new RxBus();
                }
            }
        }

        return rxBus;
    }


    /**
     * 核心代码 找到需要获取数据的方法，并且把数据插入到方法参数里面
     * 需要使用rxjava异步操作
     *
     * @param function 就是用户的操作
     */
    public void chainProcess(Function function) {
        Observable.just("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(function)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object data) throws Exception {
                        //异步回调的结果（model层 获取的数据）
                        if (data != null) {
                            send(data);
                        }

                    }
                });

    }

    /**
     * 核心代码 找到需要获取数据的方法，并且把数据插入到方法参数里面
     * 需要使用rxjava异步操作
     *
     * @param tag      标记，用于区分不同表示层
     * @param function 就是用户的操作
     */
    public void chainProcess(final String tag, Function function) {

        Observable.just("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(function)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object data) throws Exception {
                        //异步回调的结果（model层 获取的数据）
                        if (data != null) {
                            send(tag, data);
                        }

                    }
                });

    }

    /**
     * 处理 model数据
     * 遍历set集合
     *
     * @param data
     */
    private void send(Object data) {

        for (Object object : set) {
            if (object != null) {
                callMethodByAnnotation(object, data);
            }
        }

    }

    private void send(String tag, Object data) {

        for (Object object : set) {
            if (object != null) {
                callMethodByAnnotation(object, data, tag);
            }
        }

    }


    /**
     * 插入注解参数数据
     *
     * @param object 表示层
     * @param data   插入的数据
     */
    private void callMethodByAnnotation(Object object, Object data) {

        //获取表示层类 所有方法集合
        Method[] methods = object.getClass().getDeclaredMethods();

        //遍历方法集合
        for (int i = 0; i < methods.length; i++) {
            //判断方法是否使用RegisterRxBus注解
            try {
                if (methods[i].getAnnotation(RegisterRxBus.class) != null) {
                    //方法参数是否是一致（可以有数据类型不同的情况）
                    Class paramType = methods[i].getParameterTypes()[0];
                    if (data.getClass().getName().equals(paramType.getName())
                            ||data.getClass().isAssignableFrom(paramType)
                            ||paramType.isAssignableFrom(data.getClass())) {
                        //代码执行到这说明是注解对，参数类型对 的方法
                        methods[i].setAccessible(true);
                        //反射插入数据
                        methods[i].invoke(object, data);

                    } else {
                        throw new RuntimeException(paramType + "/" + data.getClass().getName());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    private void callMethodByAnnotation(Object object, Object data, String tag) {

        //获取表示层类 所有方法集合
        Method[] methods = object.getClass().getDeclaredMethods();

        //遍历方法集合
        for (int i = 0; i < methods.length; i++) {
            //判断方法是否使用RegisterRxBus注解
            try {
                if (methods[i].getAnnotation(RegisterRxBus.class) != null) {
                    //判断注解参数标记 和model 标记一致
                    String value = methods[i].getAnnotation(RegisterRxBus.class).value();
                    if (value.equals(tag)) {
                        //方法参数是否是一致（可以有数据类型不同的情况）
                        Class paramType = methods[i].getParameterTypes()[0];
                        if (data.getClass().getName().equals(paramType.getName())
                                ||data.getClass().isAssignableFrom(paramType)
                                ||paramType.isAssignableFrom(data.getClass())) {
                            //执行
                            //代码执行到这说明是注解对，参数类型对 的方法
                            methods[i].setAccessible(true);
                            //反射插入数据
                            methods[i].invoke(object, data);

                        } else {
                            throw new RuntimeException(paramType + "/" + data.getClass().getName());
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
