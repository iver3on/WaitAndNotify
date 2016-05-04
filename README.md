# WaitAndNotify
使用两种方式：1是基于忙等待的方式通知其他线程2 基于wait 和notify的机制通知其他线程

Synchronized和Lock是两种不同的多线程同步机制 所以在同步中不要同时使用Synchronized和Lock
同时使用的时候，例子中的输出结果是不按照顺序的，所以没有达到同步的效果。
同时使用Synchronized进行同步的输出结果：
Thread-0:1
Thread-1:2
Thread-0:3
Thread-1:4
Thread-0:5
Thread-1:6
Thread-0:7
Thread-1:8
Thread-0:9
Thread-1:10
同时使用Synchronized和Lock：
Thread-0:2
Thread-1:2
Thread-0:4
Thread-1:4
Thread-0:6
Thread-1:6
Thread-1:8
Thread-0:8
Thread-0:10
Thread-1:10
同时使用LOCK：
Thread-0:1
Thread-1:2
Thread-1:3
Thread-0:4
Thread-1:5
Thread-0:6
Thread-1:7
Thread-0:8
Thread-1:9
Thread-0:10



