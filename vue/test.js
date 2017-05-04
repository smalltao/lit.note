
var app = new Vue({
    el: '#app',
    data: {
        message: 'hello vue',
    }
});

var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于' + new Date()
    }
});

var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: true
    }
});

var app4 = new Vue({
    el: '#app-4',
    data: {
        todos:[
            {text: '学习是一件快乐的事'},
            {text: '是的'},
            {text: '不能学习是很无聊的'}
        ]
    }
});

var app5 = new Vue({
    el: '#app-5',
    data: {
        message:' 这是 一个 测试'
    },
    methods: {
        reverseMessage: function() {
            this.message = this.message.split(" ").reverse().join(" ")
        }
    }
});

var app6 = new Vue({
    el: '#app-6',
    data: {
        message: '你好！'
    }
});

//定义为todo-item的新组件
Vue.component('todo-item', {
    //todo-item 组件接收一个属性
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
});

var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            { text: '白日依山尽' },
            { text: '黄河入海流' },
            { text: '欲穷千里目' },
            { text: '更上一层楼' }
        ]
    }
});

var vm = new Vue({
    el: '#example',
    data: {
        message: 'hello 你好'
    },
    computed: {
        reversedMessage: function() {
            return this.message.split("").reverse().join(" ")
        }
    }
});

var demo1 = new Vue({
    el: '#demo1',
    data: {
        firstName: 'tao',
        lastName: 'li',
        fullName: 'tao li'
    },
    watch: {
        firstName: function(val) {
            this.fullName = val + ' ' + this.lastName;
        },
        lastName: function(val) {
            this.fullName = this.firstName + '' + val;
        }
    }
});

var demo2 = new Vue({
    el: '#demo2',
    data: {
        firstName: 'tao',
        lastName: 'li'
    },
    computed: {
        fullName: function() {
            return this.firstName + ' ' + this.lastName;
        }
    }
});
//computed 提供get 和set
var demo3 = new Vue({
    el: '#demo3',
    data: {
        firstName: 'tao',
        lastName: 'li'
    },
    computed: {
        fullName:{
            get: function() {
                return this.firstName + ' ' + this.lastName;
            },
            set: function(newVal) {
                var names = newVal.split(" ");
                this.firstName = names[0];
                this.lastName = names[names.length-1];
            }
        }
    }
});


var watchExampleVM = new Vue({
    el: '#watch-example',
    data: {
        question: '',
        answer: '我不能给你答案 除非你给我问题'
    },
    watch: {
        // 如果 question 发生改变，这个函数就会运行
        question: function (newQuestion) {
            this.answer = '等待你打字结束。。。.'
            this.getAnswer()
        }
    },
    methods: {
        // _.debounce 是一个通过 lodash 限制操作频率的函数。
        // 在这个例子中，我们希望限制访问yesno.wtf/api的频率
        // ajax请求直到用户输入完毕才会发出
        // 学习更多关于 _.debounce function (and its cousin
        // _.throttle), 参考: https://lodash.com/docs#debounce
        getAnswer: _.debounce(
            function () {
                var vm = this
                if (this.question.indexOf('?') === -1) {
                    vm.answer = '问题通常包含问号'
                    return
                }
                vm.answer = '思考中...'
                axios.get('https://yesno.wtf/api')
                    .then(function (response) {
                        vm.answer = _.capitalize(response.data.answer)
                    })
                    .catch(function (error) {
                        vm.answer = 'Error! Could not reach the API. ' + error
                    })
            },
            // 这是我们为用户停止输入等待的毫秒数
            500
        )
    }
})

var counter = new Vue({
    el: '#counter',
    data: {
        counter: 0
    }
});

var greetVm = new Vue({
    el: '#greetVm',
    data: {
        name: '你好'
    },
    methods: {
        greet: function(event) {
            alert(this.name);
            alert(event.target.tagName);
        }
    }
});

var sayVm = new Vue({
    el: '#sayVm',
    methods: {
        say: function(message) {
            alert(message);
        }
    }
});

var eventVm = new Vue({
    el: '#eventVm',
    methods: {
        warn: function(message, event) {
            if (event) {
                event.preventDefault();
            }
            alert(message);


        }
    }
});


