## 并发阶段任务的阶段切换 phaser ['feizə] phaser onAdvance方法会在阶段改变的时候自动执行

> onAdvance需要两个int型参数，当前的阶段数，以及注册的参数者数量，返回false表示phaser在继续执行，返回true表示phaser已经完成并进入最终态

> onAdvance 默认实现：当注册的参与者数量是0时返回true，否则返回false
> 当必须在从一个阶段过渡到另一个阶段时执行一些操作时，需要继承 phaser对象并覆盖 onAdvance方法

> 实现模拟考试类，考生需做三道题，只有当所有学生都做完一道题的时候，才能继续下一个






