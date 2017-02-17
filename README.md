# AndroidArchitectureEvolution
##简介  
讲述Android开发（结构、框架）的演变史，包括MVP开发模式的引入、利用Retrofit进行网络请求、RxJava处理异步操作等  
## 各分支
* master ——初始化项目，是一个空的工程  

* architecture_old ——MVC的方式开发Android项目模块，所有的代码都集中在Activity中，导致View、Model以及Controller相互纠缠，异常混乱。这是因为Android项目View层的天生缺陷，因为如果你把xml看成View的话，其实它的能力非常有限，就需要控制层Activity来处理一些View相关的事情。

* architecture_mvp ——mvp开发模式，基于MVC的改进，Activity/Fragment只负责View的渲染，Model层负责数据的处理，Presenter层则负责逻辑处理，且View与Model无法直接沟通，需要Presenter层统一处理，使得模块的层次非常明显。在这里我们Model层数据请求方式用的是OKHttp，Presenter层异步处理的方式用的是enquene()+callback的方式。  

* l-architecture_mvp_retrofit ——基于MVP的开发模式，将Model层替换为时下最流行的网络请求框架：retrofit，retrofit其实是OKHttp的进一步封装，将接口定义层接口形式，其实请求用的就是OKHttp，从中我们更可以发现MVP的好处，当替换一层的业务处理方式时，对其它层几乎没有影响。

* architecture_mvp_retrofit_rx ——使用了Retrofit，怎么能不使用RxJava呢？Retrofit给予了RxJava良好的支持，使得我们能够将异步处理的框架很轻松地就换成RxJava，而且对于mock调试更是提供了极大地方便。换成RxJava之后，在Presenter层我们再也看不到那些神之回调了，每一步都像是流水一样，不禁让我想一起了一句诗：大江东去浪淘尽。