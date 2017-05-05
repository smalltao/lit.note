#vue.js 笔记
原地址 https://cn.vuejs.org/v2/guide/

#组件 
## 什么是组件
组件可以扩展html实例，封装可重用的代码，组件是自定义元素，为vue添加特殊功能
1、注册
    
    全局注册组件
    语法：Vue.component(componentName, opthions) eg:
    Vue.component('my-componentName', {
        //选项
    })
    
    注册
    Vue.component('my-button', {
        template: '<p>这是一个组件</p>'
    })
    
    //实例中使用
    new Vue({
        el: '#example'
    })
    
    <div id="example">
        <my-button></my-button>
    </div>
    
    局部注册组件
    
    var child = {
        template : '<p>这是一个组件</p>'
    }
    new Vue({
        el: '#example',
        components: {
            'my-component': child
        }
    })
    
