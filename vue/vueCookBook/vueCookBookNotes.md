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

