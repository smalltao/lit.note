# vue.js 笔记 [vue 官方教程](https://cn.vuejs.org/v2/guide/) 

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
 
# 进阶
## vue 混合
    
    //定义一个混合对象
    var myMixin = {
        create: function() {
            this.hello()
        },
        methods: {
            hello: function() {
                console.log('hello from mixin!')
            }
        }
    }
    //定义一个使用混合对象的组件
    var Component = Vue.extend({
        mixins: [myMixin]
    })
    var component = new Component() // -> "hello from mixin!"
    
    var mixxin = {
        created: function() {
            console.log('混合对象的钩子被调用')
        }
    }
    new Vue({
        mixins: [mixxin],
        created: function() {
            console.log('组件钩子被调用')
        }
    })
    
    结果 
    // -> 混合对象的钩子被调用 
    // -> 组件的钩子被调用
    
    //值为对象的选项，例如 methods, components 和 directives, 将被混合成为同一个对象，两个对象键名冲突时，取组件对象的键值对
    
    var mixin = {
        methods: {
            foo: function() {
                console.log('foo')
            },
            ocnflicting: function() {
                console.log('from mixin')
            }
        }
    }
    var vm = new Vue({
        mixins: [mixin],
        methods: {
           bar: function() {
                console.log('bar')
           },
           conlicting: function() {
            console.log('from self')
           }
        }
    })
    vm.foo() // -> "foo"
    vm.bar() // -> "bar"
    vm.conflicting() // -> "from self"
    Vue.extend() 也是用同样的策略进行合并
    
## 全局混合

    也可以使用全局注册混合对象。 注意：一旦使用了全局混合对象，将会影响到所有之后创建的Vue实例。恰当使用时，可以为自定义注入处理逻辑
    //为自定义的选项 'myOption' 注入一个处理器。
    Vue.mixin({
        create: function() {
            var myOption = this.$options.myOption
            if (myOption) {
                console.log(myOption)
            }
        } 
    })
    new Vue({
        myOption:　'hello!'
    })
    // -> "hello!"
    请谨慎使用全局混合对象，因为会影响到每一个单独创建的Vue实例 （包括第三方模板）。大多数情况下，只应当应用于自定义选项，就想上面示例一样，
    也可以将其作为plugins 以免重复应用

## 自定义选项混合策略

    自定义选项将使用默认策略，即简单的覆盖已有值。如果想让自定义选项以自定义逻辑混合，可以向Vue.config.optionMergeStrategies 添加一个函数
    Vue.config.optionMergeStrategies.myOption = function(toVal, fromVal) {
        //return mergedVal
    }
    对于大多数对象选项，可以使用methods 的合并策略:
    var strategies = Vue.config.optionMergeStrategids
    strategies.myOption = strategies.methods
    
    更多高级的例子可以在 Vuex 1.x的混合策略里找到:
    const merge = Vue.config.optionMergeStrategies.computed
    Vue.config.optionMergeStrategies.vuex = function(toVal, fromVal) {
        if (!toVal) return fromVal
        if (!fromVal) return toVal
         return {
            getters: merge(toVal.getters, fromVal.getters),
            state:merge(toVal.state, fromVal.state),
            actions: merge(toVal.actions, fromVal.actions)
         }
    }

## 插件
插件通常会为Vue添加全局功能。插件的范围没有限制--- 一般有下面几种：
1. 添加全局方法或者属性，如：vue-element2
2. 添加全局资源：指令/过滤器/过度等，如vue-touch
3. 通过全局mixmi方法添加一些组件选项，如vuex
4. 添加Vue实例方法，通过把它们添加到Vue.prototype 上实现
5. 一个库，提供自己的api， 同时提供上面提到的一个或多个功能，如vue-router

Vue.js 的插件应当有一个公开方法 install 。这个方法的第一个参数是Vue构造器，第二个参数是一个可选的选项对象：
    
        MyPlugin.install = function (Vue, options) {
            //1. 添加全局方法或属性
            Vue.myGlobalMethod = function() {
                //逻辑...
            }
            //2. 添加全局资源
            Vue.directive('my-directive', {
                bind (el, binding, vnode, oldVnode) {
                    //逻辑
                }
            })
            //3. 注入组件
            Vue.mixin({
               created: function() {
                //逻辑 ...
               }
            })
            //4.添加实例方法
            Vue.prototype.$myMethod = function (options) {
                // 逻辑
            } 
        }
        
[使用插件](https://github.com/vuejs/awesome-vue#libraries--plugins)
    
    通过全局方法 Vue.use() 使用插件
    //Vue.use(MyPlugin)
    也可以传入一选项对象：
    Vue.ues(MyPlugin, { someOption: true })
    Vue.use 会自动阻止注册相同的插件多次 ，届时只会注册一次插件。
    一些插件， 如 vue-router 如果是全局变量则自动调用　Vue.sue(). 不过在模块环境中应始终显示调用 Vue.use():
    //通过 Browserify 或 Webpack 使用 CommonJS 兼容模块
    var Vue = require('vue')
    var VueRouter = require('vue-router')
    //不要忘了调用此方法
    Vue.use(VueRouter)
    awesome-vue 集合了来自社区贡献的数以千计的插件和库。 
    
    
## 单文件组件

在很多Vue项目中，我们使用 Vue.component 来定义全局组件，紧接着用 new Vue({ el: '#container '}) 在每个页面内指定一个容器元素。
这种方式在很多中小规模的项目中运作的很好，在这些项目里 JavaScript 只被用来加强特定的视图。但当在更复杂的项目中，或者你的前端完全由 JavaScript 驱动的时候，
下面这些缺点将变得非常明显：
全局定义(Global definitions) 强制要求每个 component 中的命名不得重复
字符串模板(String templates) 缺乏语法高亮，在 HTML 有多行的时候，需要用到丑陋的 \
不支持CSS(No CSS support) 意味着当 HTML 和 JavaScript 组件化时，CSS 明显被遗漏
没有构建步骤(No build step) 限制只能使用 HTML 和 ES5 JavaScript, 而不能使用预处理器，如 Pug (formerly Jade) 和 Babel
文件扩展名为 .vue 的 single-file components(单文件组件) 为以上所有问题提供了解决方法，并且还可以使用 Webpack 或 Browserify 等构建工具。
这是一个文件名为 Hello.vue 的简单实例：

    <template>
        <p>{{ greeting }} World! </p>
    </template>
    
    <script>
        import OtherComponent from  './OtherComponent.vue'
        
        export default {
            data() {
                return {
                    greeting: 'Hello'
                }
            },
            component: {
                OtherComponent
            }
        }
    </script>

这些特定的语言只是例子，你可以只是简单地使用 Babel，TypeScript，SCSS，PostCSS - 或者其他任何能够帮助你提高生产力的预处理器。

# 生产环境部署

## 删除警告
为了减小文件大小，Vue 精简独立版已经删除了所有警告，但是当你使用 Webpack 或 Broswerify 等工具时，你需要一些额外的配置实现这点。

## Webpack
使用Webpack 的DefinePlugin 来指定生产环境，以便在压缩时可以让UglifyJS 自动删除代码块内的警告语句。例如配置：

    var webpack = require('webpack')
    module.exports = {
        // ...
        plugins: [
            // ...
            new webpack.DefinePlugin({
                'process.env': {
                    NODE_ENV: '"production"'
                }
            }),
            new webpack.optimize.UglifyJsPlugin({
                compress: {
                    warnings: false
                }
            })
        ]
    }

## Browerify

    运行打包命令， 设置 NODE_ENV 为"production"。 等于告诉vueify 避免引入热重载和开发相关代码。
    使用一个全局的 envify 转换你的 bundle 文件。 这可以精简掉包含在Vue 源码中所有环境变量条件相关代码内的警告语句。例如：
    NODE_ENV=production browserify -g envify -e main.js | uglifyjs -c -m > build.js
    使用 vueify 中包含的 extract-css 插件，提取样式到单独的css文件。
    NODE_ENV=production browserify -g envify -p [ vueify/plugins/extract-css -o build.css ] -e main.js | uglifyjs -c -m > build.js

## 跟踪运行时错误

    如果在组件渲染时出现运行错误，错误将会被传递至全局 Vue.config.errorHandler 配置函数（如果已设置）。
    利用这个钩子函数和错误跟踪服务（如 Sentry, 它为 Vue 提供官方集成）， 可能是个不错的注意。
    
## 提取css

    使用单文件组件时，<style> 标签在开发运行过程中会被动态实时注入。 在生产环境中，你可能需要从所有组件中提取样式到单独的css文件中。
    有关如何实现的详细信息，请查阅 vue-loader 和 vueify 响应文档
    vue-cli 已经配置好了官方的 webpack 模板 

# 路由 
## 官方路由
对于大多数单页面应用，都推荐使用官方支持的vue-router库。 更多细节可以看vue-router 文档。

## 从零开始学习简单的路由

    如果只需要非常简单的路由而不需要引入整个路由库，可以动态的渲染一个页面级的组件 ，像这样：
    const NotFound = { template: '<p>Page not found</p>' }
    const Home = {template: '<p>home page</p>'}
    const About = {template: '<p>about page</p>'}
    const routes = {
        '/': Home,
        '/about': About
    }
    new Vue({
        el: '#app',
        data: {
            currentRoute: window.location.pathname
        },
        computed: {
            ViewComponent() {
                return routes[this.currentRoute] || NoFound
            }
        },
        render (h) { return h(this.ViewComponent) }
    })
    
结合 HTML5 History API , 你可以建立一个非常基本但是功能齐全的客户端路由器。可以直接查看 [实例应用](https://github.com/chrisvfritz/vue-2.0-simple-routing-example)

## 整合第三方路由 
如果有非常喜欢的第三方路由，如[Page.js](https://github.com/visionmedia/page.js)或者 [Director](https://github.com/flatiron/director), 整合很[简单](https://github.com/chrisvfritz/vue-2.0-simple-routing-example/compare/master...pagejs)。 这有个用了Page.js的[复杂示例](https://github.com/chrisvfritz/vue-2.0-simple-routing-example/tree/pagejs) 。

# 状态管理
## 类 Flux 状态管理的官方实现
由于多个状态分散的跨越在许多组件和交互各个角落，大型应用复杂度也经常逐渐增加。为了解决这个问题 ， Vue提供 vuex： 我们有受到 Elm启发的状态管理库 .
[vuex](https://github.com/vuejs/vuex) 甚至集成到 [vue-devtools](https://github.com/vuejs/vue-devtools),无需配置即可访问时光旅行。(没理解啥叫时光旅行，也是醉了)

## 简单状态管理 起步使用
经常忽略的是，Vue 应用中原始 数据 对象的实际来源 - 当访问数据对象时， 一个Vue实例只是简单的代理访问。
所以，如果你有一处需要被多个实例共享的状态，可以简单的通过维护一份数据来实现共享。

    const sourceOfTruth = {}
    const vmA = new Vue({
        data: sourceOfTruth
    })
    const vmB = new Vue({
        data: sourceOfTruth
    })
现在当 sourceOfTruth 发生变化， vmA 和vmB 都将自动更新引用它们的视图。子组件的每个实例也会通过 this.$root.$data 去访问。
现在我们有了唯一的实例来源，但是，调试将会变成噩梦。任何时间，我们应用中的任何部分，在任何数据改变后，都不会留下变更过的记录。
为了解决这个问题，我们采用一个简单的 store 模式：

    var store = {
        debug: true,
        state: {
            message: 'Hello!'
        },
        setMessageAction (newValue) {
            this.debug && console.log('setMessgaeAction triggered with ', newValue)
            this.state.message = newValue
        },
        clearMessageAction () {
            this.debug && console.log('clearMessageAction triggered')
            this.state.message = 'clearMessageAction triggered'
        } 
    }

需要注意，所有 store 中 state 的改变，都放置在 store 自身的 action 中去管理。
这种集中式状态管理能够被更容易地理解哪种类型的 mutation 将会发生，以及它们是如何被触发。
当错误出现时，我们现在也会有一个 log 记录 bug 之前发生了什么。

此外，每个实例/组件仍然可以拥有和管理自己的私有状态：

    var vmA = new Vue({
      data: {
        privateState: {},
        sharedState: store.state
      }
    })
    var vmB = new Vue({
      data: {
        privateState: {},
        sharedState: store.state
      }
    })


# 单元测试


任何兼容基于模块的构建系统都可以正常使用，但如果你需要一个具体的建议，可以使用 [Karma](http://karma-runner.github.io/1.0/index.html) 进行自动化测试。
它有很多社区版的插件，包括对 [Webpack](https://github.com/webpack/karma-webpack) 和 [Browserify](https://github.com/Nikku/karma-browserify) 的支持。
更多详细的安装步骤，请参考各项目的安装文档，通过这些 Karma 配置的例子可以快速帮助你上手（[Webpack](https://github.com/vuejs-templates/webpack/blob/master/template/test/unit/karma.conf.js) 配置，[Browserify](https://github.com/vuejs-templates/browserify/blob/master/template/karma.conf.js) 配置）。
    
##简单的断言
   在测试代码的结构方面，你不别为了可测试在你爹组件中做任何特殊的操作，只要导出原始配置就可以了：
   
       <template>
            <span>{{ message }}</sapn>
       </template>
       <script>
            export default {
                data () {
                    return {
                        message: 'hello!'
                    }
                },
                create(){
                    this.message = 'bye!'
                }
            }
       </script>
       
       当测试的是组件时，所要做的就是导入对象和 Vue 然后使用许多常见的断言：
       // 导入 Vue.js 和组件，进行测试
       import Vue from 'vue'
       import MyComponent from 'path/to/MyComponent.vue'
       // 这里是一些 Jasmine 2.0 的测试 
       describe('MyComponent', () => {
            //检查原始组件选项
            it('has a create hook', () => {
                expect(typeof MyComponent.created).toBe('function')
            })
            //评估原始组件中选项的函数的结果
            it('sets the correct default data', () => {
               expect(typeof MyComponent.data).toBe('function')
               const defaultData = MyComponent.data()
               expect(defaultData.message).toBe('hello!')
            })
             // 检查mount中的组件实例
             it('correctly sets the message when created', () => {
               const vm = new Vue(MyComponent).$mount()
               expect(vm.message).toBe('bye!')
             })
             // 创建一个实例并检查渲染输出
             it('renders the correct message', () => {
               const Ctor = Vue.extend(MyComponent)
               const vm = new Ctor().$mount()
               expect(vm.$el.textContent).toBe('bye!')
             })
       })
       
## 编写可被测试的组件

很多组件的渲染输出由它的 props 决定。事实上，如果一个组件的渲染输出完全取决于它的 props，那么它会让测试变得简单，就好像断言不同参数的纯函数的返回值。

    看下面这个例子:
    <template>
      <p>{{ msg }}</p>
    </template>
    <script>
      export default {
        props: ['msg']
      }
    </script>
    
你可以在不同的 props 中，通过 propsData 选项断言它的渲染输出:

    import Vue from 'vue'
    import MyComponent from './MyComponent.vue'
    // 挂载元素并返回已渲染的文本的工具函数 
    function getRenderedText (Component, propsData) {
      const Ctor = Vue.extend(Component)
      const vm = new Ctor({ propsData }).$mount()
      return vm.$el.textContent
    }
    describe('MyComponent', () => {
      it('render correctly with different props', () => {
        expect(getRenderedText(MyComponent, {
          msg: 'Hello'
        })).toBe('Hello')
        expect(getRenderedText(MyComponent, {
          msg: 'Bye'
        })).toBe('Bye')
      })
    })

## 断言异步更新

由于 Vue 进行 异步更新DOM 的情况，一些依赖DOM更新结果的断言必须在 Vue.nextTick 回调中进行：
// 在状态更新后检查生成的 HTML

    it('updates the rendered message when vm.message updates', done => {
      const vm = new Vue(MyComponent).$mount()
      vm.message = 'foo'
      // 在状态改变后和断言 DOM 更新前等待一刻
      Vue.nextTick(() => {
        expect(vm.$el.textContent).toBe('foo')
        done()
      })
    })
    
我们计划做一个通用的测试工具集，让不同策略的渲染输出（例如忽略子组件的基本渲染）和断言变得更简单。












































