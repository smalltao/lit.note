# vue.js �ʼ� [vue �ٷ��̳�](https://cn.vuejs.org/v2/guide/) 

#��� 
## ʲô�����
���������չhtmlʵ������װ�����õĴ��룬������Զ���Ԫ�أ�Ϊvue������⹦��
1��ע��
    
    ȫ��ע�����
    �﷨��Vue.component(componentName, opthions) eg:
    Vue.component('my-componentName', {
        //ѡ��
    })
    
    ע��
    Vue.component('my-button', {
        template: '<p>����һ�����</p>'
    })
    
    //ʵ����ʹ��
    new Vue({
        el: '#example'
    })
    
    <div id="example">
        <my-button></my-button>
    </div>
    
    �ֲ�ע�����
    
    var child = {
        template : '<p>����һ�����</p>'
    }
    new Vue({
        el: '#example',
        components: {
            'my-component': child
        }
    })
 
# ����
## vue ���
    
    //����һ����϶���
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
    //����һ��ʹ�û�϶�������
    var Component = Vue.extend({
        mixins: [myMixin]
    })
    var component = new Component() // -> "hello from mixin!"
    
    var mixxin = {
        created: function() {
            console.log('��϶���Ĺ��ӱ�����')
        }
    }
    new Vue({
        mixins: [mixxin],
        created: function() {
            console.log('������ӱ�����')
        }
    })
    
    ��� 
    // -> ��϶���Ĺ��ӱ����� 
    // -> ����Ĺ��ӱ�����
    
    //ֵΪ�����ѡ����� methods, components �� directives, ������ϳ�Ϊͬһ�������������������ͻʱ��ȡ�������ļ�ֵ��
    
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
    Vue.extend() Ҳ����ͬ���Ĳ��Խ��кϲ�
    
## ȫ�ֻ��

    Ҳ����ʹ��ȫ��ע���϶��� ע�⣺һ��ʹ����ȫ�ֻ�϶��󣬽���Ӱ�쵽����֮�󴴽���Vueʵ����ǡ��ʹ��ʱ������Ϊ�Զ���ע�봦���߼�
    //Ϊ�Զ����ѡ�� 'myOption' ע��һ����������
    Vue.mixin({
        create: function() {
            var myOption = this.$options.myOption
            if (myOption) {
                console.log(myOption)
            }
        } 
    })
    new Vue({
        myOption:��'hello!'
    })
    // -> "hello!"
    �����ʹ��ȫ�ֻ�϶�����Ϊ��Ӱ�쵽ÿһ������������Vueʵ�� ������������ģ�壩�����������£�ֻӦ��Ӧ�����Զ���ѡ���������ʾ��һ����
    Ҳ���Խ�����Ϊplugins �����ظ�Ӧ��

## �Զ���ѡ���ϲ���

    �Զ���ѡ�ʹ��Ĭ�ϲ��ԣ����򵥵ĸ�������ֵ����������Զ���ѡ�����Զ����߼���ϣ�������Vue.config.optionMergeStrategies ���һ������
    Vue.config.optionMergeStrategies.myOption = function(toVal, fromVal) {
        //return mergedVal
    }
    ���ڴ��������ѡ�����ʹ��methods �ĺϲ�����:
    var strategies = Vue.config.optionMergeStrategids
    strategies.myOption = strategies.methods
    
    ����߼������ӿ����� Vuex 1.x�Ļ�ϲ������ҵ�:
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

## ���
���ͨ����ΪVue���ȫ�ֹ��ܡ�����ķ�Χû������--- һ�������漸�֣�
1. ���ȫ�ַ����������ԣ��磺vue-element2
2. ���ȫ����Դ��ָ��/������/���ȵȣ���vue-touch
3. ͨ��ȫ��mixmi�������һЩ���ѡ���vuex
4. ���Vueʵ��������ͨ����������ӵ�Vue.prototype ��ʵ��
5. һ���⣬�ṩ�Լ���api�� ͬʱ�ṩ�����ᵽ��һ���������ܣ���vue-router

Vue.js �Ĳ��Ӧ����һ���������� install ����������ĵ�һ��������Vue���������ڶ���������һ����ѡ��ѡ�����
    
        MyPlugin.install = function (Vue, options) {
            //1. ���ȫ�ַ���������
            Vue.myGlobalMethod = function() {
                //�߼�...
            }
            //2. ���ȫ����Դ
            Vue.directive('my-directive', {
                bind (el, binding, vnode, oldVnode) {
                    //�߼�
                }
            })
            //3. ע�����
            Vue.mixin({
               created: function() {
                //�߼� ...
               }
            })
            //4.���ʵ������
            Vue.prototype.$myMethod = function (options) {
                // �߼�
            } 
        }
        
[ʹ�ò��](https://github.com/vuejs/awesome-vue#libraries--plugins)
    
    ͨ��ȫ�ַ��� Vue.use() ʹ�ò��
    //Vue.use(MyPlugin)
    Ҳ���Դ���һѡ�����
    Vue.ues(MyPlugin, { someOption: true })
    Vue.use ���Զ���ֹע����ͬ�Ĳ����� ����ʱֻ��ע��һ�β����
    һЩ����� �� vue-router �����ȫ�ֱ������Զ����á�Vue.sue(). ������ģ�黷����Ӧʼ����ʾ���� Vue.use():
    //ͨ�� Browserify �� Webpack ʹ�� CommonJS ����ģ��
    var Vue = require('vue')
    var VueRouter = require('vue-router')
    //��Ҫ���˵��ô˷���
    Vue.use(VueRouter)
    awesome-vue �����������������׵�����ǧ�ƵĲ���Ϳ⡣ 
    
    
## ���ļ����

�ںܶ�Vue��Ŀ�У�����ʹ�� Vue.component ������ȫ��������������� new Vue({ el: '#container '}) ��ÿ��ҳ����ָ��һ������Ԫ�ء�
���ַ�ʽ�ںܶ���С��ģ����Ŀ�������ĺܺã�����Щ��Ŀ�� JavaScript ֻ��������ǿ�ض�����ͼ�������ڸ����ӵ���Ŀ�У��������ǰ����ȫ�� JavaScript ������ʱ��
������Щȱ�㽫��÷ǳ����ԣ�
ȫ�ֶ���(Global definitions) ǿ��Ҫ��ÿ�� component �е����������ظ�
�ַ���ģ��(String templates) ȱ���﷨�������� HTML �ж��е�ʱ����Ҫ�õ���ª�� \
��֧��CSS(No CSS support) ��ζ�ŵ� HTML �� JavaScript �����ʱ��CSS ���Ա���©
û�й�������(No build step) ����ֻ��ʹ�� HTML �� ES5 JavaScript, ������ʹ��Ԥ���������� Pug (formerly Jade) �� Babel
�ļ���չ��Ϊ .vue �� single-file components(���ļ����) Ϊ�������������ṩ�˽�����������һ�����ʹ�� Webpack �� Browserify �ȹ������ߡ�
����һ���ļ���Ϊ Hello.vue �ļ�ʵ����

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

��Щ�ض�������ֻ�����ӣ������ֻ�Ǽ򵥵�ʹ�� Babel��TypeScript��SCSS��PostCSS - ���������κ��ܹ������������������Ԥ��������

# ������������

## ɾ������
Ϊ�˼�С�ļ���С��Vue ����������Ѿ�ɾ�������о��棬���ǵ���ʹ�� Webpack �� Broswerify �ȹ���ʱ������ҪһЩ���������ʵ����㡣

## Webpack
ʹ��Webpack ��DefinePlugin ��ָ�������������Ա���ѹ��ʱ������UglifyJS �Զ�ɾ��������ڵľ�����䡣�������ã�

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

    ���д����� ���� NODE_ENV Ϊ"production"�� ���ڸ���vueify �������������غͿ�����ش��롣
    ʹ��һ��ȫ�ֵ� envify ת����� bundle �ļ��� ����Ծ����������Vue Դ�������л�������������ش����ڵľ�����䡣���磺
    NODE_ENV=production browserify -g envify -e main.js | uglifyjs -c -m > build.js
    ʹ�� vueify �а����� extract-css �������ȡ��ʽ��������css�ļ���
    NODE_ENV=production browserify -g envify -p [ vueify/plugins/extract-css -o build.css ] -e main.js | uglifyjs -c -m > build.js

## ��������ʱ����

    ����������Ⱦʱ�������д��󣬴��󽫻ᱻ������ȫ�� Vue.config.errorHandler ���ú�������������ã���
    ����������Ӻ����ʹ�����ٷ����� Sentry, ��Ϊ Vue �ṩ�ٷ����ɣ��� �����Ǹ������ע�⡣
    
## ��ȡcss

    ʹ�õ��ļ����ʱ��<style> ��ǩ�ڿ������й����лᱻ��̬ʵʱע�롣 �����������У��������Ҫ�������������ȡ��ʽ��������css�ļ��С�
    �й����ʵ�ֵ���ϸ��Ϣ������� vue-loader �� vueify ��Ӧ�ĵ�
    vue-cli �Ѿ����ú��˹ٷ��� webpack ģ�� 

# ·�� 
## �ٷ�·��
���ڴ������ҳ��Ӧ�ã����Ƽ�ʹ�ùٷ�֧�ֵ�vue-router�⡣ ����ϸ�ڿ��Կ�vue-router �ĵ���

## ���㿪ʼѧϰ�򵥵�·��

    ���ֻ��Ҫ�ǳ��򵥵�·�ɶ�����Ҫ��������·�ɿ⣬���Զ�̬����Ⱦһ��ҳ�漶����� ����������
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
    
��� HTML5 History API , ����Խ���һ���ǳ��������ǹ�����ȫ�Ŀͻ���·����������ֱ�Ӳ鿴 [ʵ��Ӧ��](https://github.com/chrisvfritz/vue-2.0-simple-routing-example)

## ���ϵ�����·�� 
����зǳ�ϲ���ĵ�����·�ɣ���[Page.js](https://github.com/visionmedia/page.js)���� [Director](https://github.com/flatiron/director), ���Ϻ�[��](https://github.com/chrisvfritz/vue-2.0-simple-routing-example/compare/master...pagejs)�� ���и�����Page.js��[����ʾ��](https://github.com/chrisvfritz/vue-2.0-simple-routing-example/tree/pagejs) ��

# ״̬����
## �� Flux ״̬����Ĺٷ�ʵ��
���ڶ��״̬��ɢ�Ŀ�Խ���������ͽ����������䣬����Ӧ�ø��Ӷ�Ҳ���������ӡ�Ϊ�˽��������� �� Vue�ṩ vuex�� �������ܵ� Elm������״̬����� .
[vuex](https://github.com/vuejs/vuex) �������ɵ� [vue-devtools](https://github.com/vuejs/vue-devtools),�������ü��ɷ���ʱ�����С�(û���ɶ��ʱ�����У�Ҳ������)

## ��״̬���� ��ʹ��
�������Ե��ǣ�Vue Ӧ����ԭʼ ���� �����ʵ����Դ - ���������ݶ���ʱ�� һ��Vueʵ��ֻ�Ǽ򵥵Ĵ�����ʡ�
���ԣ��������һ����Ҫ�����ʵ�������״̬�����Լ򵥵�ͨ��ά��һ��������ʵ�ֹ���

    const sourceOfTruth = {}
    const vmA = new Vue({
        data: sourceOfTruth
    })
    const vmB = new Vue({
        data: sourceOfTruth
    })
���ڵ� sourceOfTruth �����仯�� vmA ��vmB �����Զ������������ǵ���ͼ���������ÿ��ʵ��Ҳ��ͨ�� this.$root.$data ȥ���ʡ�
������������Ψһ��ʵ����Դ�����ǣ����Խ�����ج�Ρ��κ�ʱ�䣬����Ӧ���е��κβ��֣����κ����ݸı�󣬶��������±�����ļ�¼��
Ϊ�˽��������⣬���ǲ���һ���򵥵� store ģʽ��

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

��Ҫע�⣬���� store �� state �ĸı䣬�������� store ����� action ��ȥ����
���ּ���ʽ״̬�����ܹ��������׵�����������͵� mutation ���ᷢ�����Լ���������α�������
���������ʱ����������Ҳ����һ�� log ��¼ bug ֮ǰ������ʲô��

���⣬ÿ��ʵ��/�����Ȼ����ӵ�к͹����Լ���˽��״̬��

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


# ��Ԫ����


�κμ��ݻ���ģ��Ĺ���ϵͳ����������ʹ�ã����������Ҫһ������Ľ��飬����ʹ�� [Karma](http://karma-runner.github.io/1.0/index.html) �����Զ������ԡ�
���кܶ�������Ĳ���������� [Webpack](https://github.com/webpack/karma-webpack) �� [Browserify](https://github.com/Nikku/karma-browserify) ��֧�֡�
������ϸ�İ�װ���裬��ο�����Ŀ�İ�װ�ĵ���ͨ����Щ Karma ���õ����ӿ��Կ��ٰ��������֣�[Webpack](https://github.com/vuejs-templates/webpack/blob/master/template/test/unit/karma.conf.js) ���ã�[Browserify](https://github.com/vuejs-templates/browserify/blob/master/template/karma.conf.js) ���ã���
    
##�򵥵Ķ���
   �ڲ��Դ���Ľṹ���棬�㲻��Ϊ�˿ɲ����������������κ�����Ĳ�����ֻҪ����ԭʼ���þͿ����ˣ�
   
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
       
       �����Ե������ʱ����Ҫ���ľ��ǵ������� Vue Ȼ��ʹ����ೣ���Ķ��ԣ�
       // ���� Vue.js ����������в���
       import Vue from 'vue'
       import MyComponent from 'path/to/MyComponent.vue'
       // ������һЩ Jasmine 2.0 �Ĳ��� 
       describe('MyComponent', () => {
            //���ԭʼ���ѡ��
            it('has a create hook', () => {
                expect(typeof MyComponent.created).toBe('function')
            })
            //����ԭʼ�����ѡ��ĺ����Ľ��
            it('sets the correct default data', () => {
               expect(typeof MyComponent.data).toBe('function')
               const defaultData = MyComponent.data()
               expect(defaultData.message).toBe('hello!')
            })
             // ���mount�е����ʵ��
             it('correctly sets the message when created', () => {
               const vm = new Vue(MyComponent).$mount()
               expect(vm.message).toBe('bye!')
             })
             // ����һ��ʵ���������Ⱦ���
             it('renders the correct message', () => {
               const Ctor = Vue.extend(MyComponent)
               const vm = new Ctor().$mount()
               expect(vm.$el.textContent).toBe('bye!')
             })
       })
       
## ��д�ɱ����Ե����

�ܶ��������Ⱦ��������� props ��������ʵ�ϣ����һ���������Ⱦ�����ȫȡ�������� props����ô�����ò��Ա�ü򵥣��ͺ�����Բ�ͬ�����Ĵ������ķ���ֵ��

    �������������:
    <template>
      <p>{{ msg }}</p>
    </template>
    <script>
      export default {
        props: ['msg']
      }
    </script>
    
������ڲ�ͬ�� props �У�ͨ�� propsData ѡ�����������Ⱦ���:

    import Vue from 'vue'
    import MyComponent from './MyComponent.vue'
    // ����Ԫ�ز���������Ⱦ���ı��Ĺ��ߺ��� 
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

## �����첽����

���� Vue ���� �첽����DOM �������һЩ����DOM���½���Ķ��Ա����� Vue.nextTick �ص��н��У�
// ��״̬���º������ɵ� HTML

    it('updates the rendered message when vm.message updates', done => {
      const vm = new Vue(MyComponent).$mount()
      vm.message = 'foo'
      // ��״̬�ı��Ͷ��� DOM ����ǰ�ȴ�һ��
      Vue.nextTick(() => {
        expect(vm.$el.textContent).toBe('foo')
        done()
      })
    })
    
���Ǽƻ���һ��ͨ�õĲ��Թ��߼����ò�ͬ���Ե���Ⱦ������������������Ļ�����Ⱦ���Ͷ��Ա�ø��򵥡�












































