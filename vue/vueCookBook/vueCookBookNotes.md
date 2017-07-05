#笔记
### npm支持
1. 安装npm
2. 安装 vue 脚手架
```
# npm install -g vue-cli //-g 表示全局安装，任何地方都可以使用vue的脚手架工具
```
3. 创建一个workspack 作为工作空间
4. 从官方仓库或者其它仓库获取vue模板
5. 初始化 简单项目　
```
# vue init simple 创建简单页
# vue init webpack-simple 创建带有webpack的简单项目
# vue init browserify-simple 创建带有browserify的简单项目
```
6. 初始化依赖：npm install && 运行 #npm run dev
7. 项目结构说明
```
src/main.js  程序的入口点，类似于java主函数
import Vue from 'vue'
import App from './App.vue'
new Vue({
el: '#app',
render: h => h(App)
})
这段代码将在index.html中被加载，作用是作为id="app"属性元素的组件
App.vue是自包含式的组件
```

> 注意: 运行`npm run dev`时 windows 不支持 package.json 下 NODE_ENV=development设置，需要特殊处理：

```
1. set NODE_ENV=development && webpack --config webpack.config.dev.js
2. 安装across-env:npm install cross-env --save-dev
   在NODE_ENV=xxxxxxx前面添加cross-env就可以了。

```

8. 模板大致结构
> template 标签存放html script 标签存放javascript style标签存放css

```
<template>
	<div id="app">
		<img src="./assets/logo.png" />
        <h1>\{{ msg }}</h1>
    </div>
</template>
<script>
export default {
    data() {
            return {
            msg: 'Hello Vue 2.0!'
        }
    }
}
</script>
<style>
    body {
    font-family: Helvetica, sans-serif;
    }
</style>
```

### 使用 npm 增加依赖
> 增加其他依赖库需要 npm 依赖的库 ，然后在文件中使用下面语句引用：
```
# import MyLibrary from 'mylibrary'
导入moment.js 库
# npm install moment
使用
#import moment from 'moment'
```

### 使用过滤器
> vue1.0 自带过滤器，2.0 已经不自带过滤器了
#### 创建自定义过滤器
1. 写入以下脚本并注册vue实例
```
Vue.filter('capitalize', function(string) {
    var capitalFirst = string.charAt(0).toUpperCase()
    var noCaseTail = string.slice(1, string.length)
    return capitalFirst + noCaseTail
})
new Vue({el:'#app'})
```
2. 在html中写入
```
{{'hello world' | capitalize}}
```
使用ES6 编写如下：
```
Vue.filter('capitalize', function(string) {
    var [first, ...tail] = string
    return first.toUpperCase() + tail.join('')
})
```

### 使用`mustaches` 调试程序 [mə'stɑːʃ]
1. 写入下面的javascript
```
new Vue({
    el: '#app',
    data: {
        cat:{
            sound:'meow'
        }
    }
})
```
2. 在html中写入
```
<p>Cat object: {{ cat }}</p>
```
3. 输出结果 类似使用 JSON.stringify 函数的效果 ，旧版本vue中实现同等效果需要使用json过滤器 {{ cat | json }}
4. 注意：避免引入循环引用，程序将不工作

### 使用开发者工具暴露你的程序 Vuex chrome的vue插件 在google开发者商店中安装 搜索vue.js 不支持iframe 中调试
1. 唤起工具：cmd+opt+I 或者 Ctrl+Shift+I 在组件树中可以实时看见内部的变量
2. 在组件树中显示的$vm0 可以在控制台中使用（vue的组件对象），使用Inspect DOM 可以滚动当前组件到元素所在位置
3. 控制台输出 $vm0.msg 打印组件属性

### 升级代码到vue 2.0 使用vue的引导迁移工具 migration [maɪ'greɪʃ(ə)n]
1. 安装引导迁移工具 https://github.com/vuejs/vue-migration-helper
```
npm install -g git//github.com/vuejs/vue-migration-helper.git
```
2. 导航到app目录
3. 执行以下命令
```
vue-migration-helper
```

### 已经弃用的api $broadcast $dispatch  事件广播 事件分发
1. 使用专用空实例充当事件中心 vue 1.0 写法
```
var eventBus = new Vue()
```
2. 创建组件，并在回调中写入 vue 1.0 写法
```
new Vue({
    el: '#app',
    components: {
        comp1: {
            template:'<div/>',
            created() {
                eventBus.$on('brew', () => {
                    console.log('HTTP Error 418: I'm a teapot'); //收到分发的事件并执行
                })
            }
        },
        comp2: {
            template:'<div/>',
            created() {
                eventBus.$emit('brew') //分发事件
            }
        }
    }
})
```
3. 写入html
```
<div>
    <comp1></comp1>
    <comp2></comp2>
</div>
```
4. 建议使用vue2.0 的$off 移除自定义事件监听器。 $once 监听一个自定义事件，但是只触发一次，在第一次触发之后移除监听器。

#### 使用v-for 的过滤器列表，将被移除，不建议使用，建议使用 计算属性

#### 反对使用自定义分隔符：比如：Vue.config.delimiters
```
<div id="app">
    {!msg!}
</div>

new Vue({
    el:'#app',
    data: {
        msg:'hello world'
    },
    delimiters:['{!','}']
})
```
### 声明周期命名规则

旧回调 | 新回调
--- | ---
init | beforeCreate
created	| created
beforeCompile | created
no equivalent | beforeMount
compiled | mounted
ready | mounted
attached | no equivalent
detached | no equivalent
no equivalent | beforeUpdate
no equivalent | updated

> 声明周期图：同目录下 vue-lift-cycle.png

### 使用计算属性
> 结合 v-model 和 @event注解
1. v-model 实现表单输入和应用状态之间的双向绑定。

```
<div id="app">
    <input type="text" v-model="name"/>
    <input type=text id="surname" value='Snow' />
    <button @click="saveSurname">Save Surname</button>
    <output>{{computedFullName}}</output>
</div>
let surname = 'Snow'
new Vue({
    el: '#app',
    data: {
        name: 'John'
    },
    computed: {
        computedFullName() {
            return this.name + ' '+surname;
        }
    },
    methods: {
       saveSurname() {
            surname = this.$el.querySelector('#surname').value
       }
    }
})
```








